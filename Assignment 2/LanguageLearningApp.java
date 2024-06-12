/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LanguageLearningApp {

    // main frame of the application
    JFrame frame;

    // text field for the user to enter their name
    JTextField nameField;

    // combo box for language selection
    JComboBox<String> languageComboBox;

    // label to display the current question
    private JLabel questionLabel;

    // array of buttons for answer options
    private JButton[] optionButtons;

    // label to display the current score
    private JLabel scoreLabel;

    // button for credits
    private JButton creditsButton;

    // button for leaderboard
    private JButton leaderboardButton;

    // manages the questions for the quiz
    private QuestionManager questionManager;

    // manages the score for the quiz
    ScoreManager scoreManager;

    // manages the leaderboard
    LeaderboardManager leaderboardManager;

    // string to store the user's name
    private String userName;

    // string to store the subject chosen by user
    private String subject;
    
    // string to store the user's name
    public LanguageLearningApp() 
    {
        initialize();
    }

    // Method to set up the initial GUI components
    private void initialize() 
    {
        frame = new JFrame("Language Learning App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 360);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());

        // Initialize components for the start panel
        nameField = new JTextField();
        languageComboBox = new JComboBox<>(new String[]{"Korean", "Japanese"});
        JButton startButton = new JButton("Start Quiz");
        startButton.addActionListener(e -> startQuiz());
        creditsButton = new JButton("Credits");
        creditsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This Language Quiz was made by Ivan Vay & Taran Singh"));
        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(e -> leaderboardManager.showLeaderboard());

        // Set up the start panel using GUIManager
        GUIManager.setupStartPanel(frame, nameField, languageComboBox, startButton, creditsButton, leaderboardButton);

        // Initialize components for the quiz panel
        questionLabel = new JLabel("Question");
        optionButtons = new JButton[3];
        scoreLabel = new JLabel("Score: 0");
        JButton backButton = new JButton("Return Home");
        backButton.addActionListener(e -> confirmBackToHomeScreen());

        // Set up the quiz panel using GUIManager
        GUIManager.setupQuizPanel(frame, questionLabel, optionButtons, scoreLabel, backButton);
        
        // Adding action listeners to the option buttons
        for (int i = 0; i < optionButtons.length; i++) 
        {
            int finalI = i;
            optionButtons[i].addActionListener(e -> checkAnswer(optionButtons[finalI].getText()));
        }

        // Initialize the leaderboard and score managers
        leaderboardManager = new LeaderboardManager(frame);
        scoreManager = new ScoreManager();

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to start the quiz
    public void startQuiz() {
        
        // stores what the user input in the name field as their username
        userName = nameField.getText();
        
        // stores the langugae the user selected from the combo box
        subject = (String) languageComboBox.getSelectedItem();

        // if the users username is invalid it shows a pop screen with an error
        if (!isValidName(userName)) 
        {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a proper name without numbers.");
            return;
        }

        
        List<String[]> questions;
        
        // if the subject equals 'Korean' it will get questions from the KoreanQuestion class
        if (subject.equals("Korean")) 
        {
            questions = KoreanQuestions.getQuestions();
        } 
        
        // else it will get the questions from the JapaneseQuestions class 
        else 
        {
            questions = JapaneseQuestions.getQuestions();
        }

        // Initialize the question manager and reset the score
        questionManager = new QuestionManager(questions);
        scoreManager.resetScore();
        showNextQuestion();
    }

    // Method to display the next question
    private void showNextQuestion() 
    {
        if (!questionManager.hasNextQuestion()) 
        {
            // if there is no next question then the quiz is finished
            completeQuiz();
            return;
        }

        // Get the current question and options
        String[] question = questionManager.getCurrentQuestion();
        questionLabel.setText("What is the translation of '" + question[0] + "'?");
        List<String> options = QuestionManager.generateOptions(question[1]);

        // Updates the option buttons with new options each question
        for (int i = 0; i < optionButtons.length; i++) 
        {
            optionButtons[i].setText(options.get(i));
        }

        // Update the score label
        scoreLabel.setText("Score: " + scoreManager.getScore());
        
        // Show the quiz panel
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "QuizPanel");
    }

    // Method to check answer is right
    public void checkAnswer(String answer) {
        String correctAnswer = questionManager.getCurrentQuestion()[1];
        
        // if the answer is correct it will add to the score and show a pop up window
        // saying the answer is correct
        if (answer.equals(correctAnswer)) {
            scoreManager.incrementScore();
            JOptionPane.showMessageDialog(frame, "Correct!");
        } 
        // if the answer is wrong it will show a pop up window saying the answer is wrong
        // along with giving them the right answer
        else {
            JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is: " + correctAnswer);
        }
        questionManager.nextQuestion();
        showNextQuestion();
    }

    // Method to handle the end of the quiz
    private void completeQuiz() {
        // once the user is finished it shows a pop up window telling them their final scores
        JOptionPane.showMessageDialog(frame, "Quiz Completed! Your score: " + scoreManager.getScore());
        // saves the score into a database
        scoreManager.saveScoreToDatabase(userName, subject);
        backToHomeScreen();
    }

    // Method to validate the user's name
    private boolean isValidName(String userName) {
        if (userName.isEmpty()) {
            return false;
        }
        // the user name must be letters and no longer then 10
        return userName.matches("[a-zA-Z\\s]{1,10}");
    }

    // method to return back to home screen
    private void backToHomeScreen() {
        CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
        cl.show(frame.getContentPane(), "StartPanel");
    }

    // conformation method to return to home screen
    private void confirmBackToHomeScreen() {
        // asks user if they are sure if they want to return back to the home screen
        int userResponse = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to return back to the main home screen?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        // if user selects yes , then it will take them back
        if (userResponse == JOptionPane.YES_OPTION) {
            backToHomeScreen();
        }
    }
}