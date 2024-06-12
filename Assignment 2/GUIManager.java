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
public class GUIManager 
{
    // Gets all the necessary componets needed to set up the start panel
    public static void setupStartPanel(JFrame frame, JTextField nameField, JComboBox<String> languageComboBox, JButton startButton, JButton creditsButton, JButton leaderboardButton) 
    {
        
        // new panel for the start screen
        JPanel startPanel = new JPanel();
        
        // setting the layout to null 
        startPanel.setLayout(null);
        
        // creating and adding a label for the name field
        JLabel nameLabel = new JLabel("Please enter your name:");
        nameLabel.setBounds(150, 20, 300, 30);
        startPanel.add(nameLabel);

        // setting the bounds and adding it to the panel
        nameField.setBounds(150, 50, 250, 30);
        startPanel.add(nameField);

        // creating and adding a label for language selection
        JLabel languageLabel = new JLabel("Select language for quiz:");
        languageLabel.setBounds(150, 90, 300, 30);
        startPanel.add(languageLabel);

        // setting the bounds for combo box and adding it to the panel
        languageComboBox.setBounds(150, 120, 250, 30);
        startPanel.add(languageComboBox);

        // setting the bounds and adding it to the panel
        startButton.setBounds(170, 170, 200, 40);
        startPanel.add(startButton);

        // setting the bounds and adding it to the panel
        creditsButton.setBounds(170, 220, 200, 40);
        startPanel.add(creditsButton);

        // setting the bounds and adding it to the panel
        leaderboardButton.setBounds(170, 270, 200, 40);
        startPanel.add(leaderboardButton);

        frame.add(startPanel, "StartPanel");
    }

    // Gets all the necessary componets needed to set up the quiz panel
    public static void setupQuizPanel(JFrame frame, JLabel questionLabel, JButton[] optionButtons, JLabel scoreLabel, JButton backButton) 
    {
        // new panel for quiz screen
        JPanel quizPanel = new JPanel();
        
        // setting the layout to null
        quizPanel.setLayout(null);

        // setting the bounds and adding it to the pane
        questionLabel.setBounds(150, 20, 300, 30);
        quizPanel.add(questionLabel);

        // Looping through the option buttons to set their bounds and adding them to the panel
        for (int i = 0; i < optionButtons.length; i++) 
        {
            optionButtons[i] = new JButton();
            optionButtons[i].setBounds(150, 60 + i * 50, 200, 40);
            quizPanel.add(optionButtons[i]);
        }

        // setting the bounds and adding it to the pane
        scoreLabel.setBounds(150, 210, 300, 30);
        quizPanel.add(scoreLabel);

        // setting the bounds and adding it to the pane
        backButton.setBounds(185, 250, 120, 40);
        quizPanel.add(backButton);

        frame.add(quizPanel, "QuizPanel");
    }
}
