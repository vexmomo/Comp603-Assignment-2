/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LanguageLearningApp {

    // main frame of the application
    private JFrame frame;
    
    // text field for the user to enter their name
    private JTextField nameField;
    
    // combo box for language selection
    private JComboBox<String> languageComboBox;
    
    // label to display the current question
    private JLabel questionLabel;
    
    // array of buttons for answer options
    private JButton[] optionButtons;
    
    // label to display the current score
    private JLabel scoreLabel;
    
    // list to store questions and answers
    private List<String[]> questions;
    
    // integer to store the index of the current question
    private int currentQuestionNum;
    
    // integer to store the user's score
    private int score;
    
    // string to store the user's name
    private String userName;

    // Constructor for the main class
    public LanguageLearningApp() 
    {
        // Call the initialize method to set up the GUI
        initialize();
    }

    private void initialize() 
    {
        //  new JFrame with the title "Language Learning App"
        frame = new JFrame("Language Learning App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new CardLayout());

        // Start Panel
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(5, 1));

        // JLabel for the name prompt
        JLabel nameLabel = new JLabel("Please enter your name:");
        startPanel.add(nameLabel);

        // JTextField for user to enter their name
        nameField = new JTextField();
        startPanel.add(nameField);

        // JLabel for the language selection prompt
        JLabel languageLabel = new JLabel("Select language for quiz:");
        startPanel.add(languageLabel);

        // JComboBox with language options
        languageComboBox = new JComboBox<>(new String[]{"Korean", "Japanese"});
        startPanel.add(languageComboBox);

        // JButton to start the quiz
        JButton startButton = new JButton("Start Quiz");
        startButton.addActionListener(e -> startQuiz());
        startPanel.add(startButton);

        frame.add(startPanel, "StartPanel");

        // Quiz Panel
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(6, 1));

        // JLabel for displaying the question
        questionLabel = new JLabel("Question");
        quizPanel.add(questionLabel);

        // Initialize the array of option buttons with a size of 3
        optionButtons = new JButton[3];
        
        // Loop to create each option button
        for (int i = 0; i < 3; i++) 
        {
            optionButtons[i] = new JButton();
            int finalI = i;
            optionButtons[i].addActionListener(e -> checkAnswer(optionButtons[finalI].getText()));
            quizPanel.add(optionButtons[i]);
        }

        // JLabel for displaying the score
        scoreLabel = new JLabel("Score: 0");
        quizPanel.add(scoreLabel);

        frame.add(quizPanel, "QuizPanel");

        frame.setVisible(true);
    }

    // Method to start the quiz
    private void startQuiz() 
    {
        userName = nameField.getText();
        String language = (String) languageComboBox.getSelectedItem();

        // Check if the user's name is valid
        if (!isValidName(userName)) 
        {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a proper name without numbers.");
            return;
        }

        // If the selected language is Korean start Koren quiz
        if (language.equals("Korean")) 
        {
            questions = KoreanQuestions.getQuestions();
        } 
        else 
        {
            // else start Japanese quiz
            questions = JapaneseQuestions.getQuestions();
        }

        // Initialize the current question number to 0
        currentQuestionNum = 0;
        
        // Initialize the score to 0
        score = 0;
        
        // Show the first question
        showNextQuestion();
    }

    // Method to display the next question
    private void showNextQuestion() {
        
        // Check if there are no more questions
        if (currentQuestionNum >= questions.size()) 
        {
            // Shows a completion message with the user's score , once the quiz is finished
            JOptionPane.showMessageDialog(frame, "Quiz Completed! Your score: " + score);
            
            // Save the user's score to a file
            saveScoreToFile(userName, score);
            
            // Remove all components from the frame
            frame.getContentPane().removeAll();
            
            // Re-initialize the application
            initialize();
            return;
        }

        // Gets the current question
        String[] question = questions.get(currentQuestionNum);
        
        // Add a new word as the question
        questionLabel.setText("What is the translation of '" + question[0] + "'?");
        
        // Generates options for the current question
        List<String> options = generateOptions(question[1]);

        // Loop through the option buttons and adds text to each button
        for (int i = 0; i < optionButtons.length; i++) 
        {
            optionButtons[i].setText(options.get(i));
        }

        // Update the score label
        scoreLabel.setText("Score: " + score);
        
        // Remove all components from the frame
        frame.getContentPane().removeAll();
        
        // Add the parent container of the question label to the frame
        frame.add(questionLabel.getParent());
        
        // Revalidate the frame
        frame.revalidate();
        
        // Repaint the frame
        frame.repaint();
    }
    
    // Method to check the user's answer
    private void checkAnswer(String answer) {  
        // Get the correct answer for the current question
        String correctAnswer = questions.get(currentQuestionNum)[1];  
        // Check if the user's answer is correct
        if (answer.equals(correctAnswer)) {
            // Increment the score
            score++;
            // Show a message that the answer is correct
            JOptionPane.showMessageDialog(frame, "Correct!"); 
             
        // If the user's answer is incorrect
        } else {
            // Show a message that the answer is incorrect
            JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is: " + correctAnswer);  
        }
        // Increment the current question index
        currentQuestionNum++;
        // Show the next question
        showNextQuestion(); 
    }

    // Method to validate the user's name
    private boolean isValidName(String userName) {
        // Check if the name is empty
        if (userName.isEmpty()) {
            return false;
        }
        // Checks if the username only has letters and is 10 or less characters
        return userName.matches("[a-zA-Z\\s]{1,10}");
    }

    private static List<String> generateOptions(String correctTranslation) 
    {
        List<String> options = new ArrayList<>();
        options.add(correctTranslation);

        // List of options that can be used 
        List<String> incorrectOptions = new ArrayList<>(Arrays.asList
        (
                "Hello", "Friend", "Apple", "Thank You",
                "School", "Sea", "Rice", "House", "Love", "Park", "Car",
                "Mom", "Dad", "Brother", "Sister", "Grand-Dad", "Grand-Mother"
        ));

        // Makes sure that the correct translation is not shown twice
        incorrectOptions.remove(correctTranslation);

        // Adding two incorrect opitions 
        options.add(incorrectOptions.get(0));
        options.add(incorrectOptions.get(1));

        // Shuffle the options list
        Collections.shuffle(options);

        return options;
    }

    // Method to save the user's score to a file
    private static void saveScoreToFile(String userName, int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("user_scores.txt", true))) {
            writer.println(userName + "," + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


