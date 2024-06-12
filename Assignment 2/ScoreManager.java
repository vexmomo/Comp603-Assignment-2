/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreManager {
    // Variable to store the current score
    private int score;

    // Constructor to initialize the score to 0
    public ScoreManager() 
    {
        this.score = 0;
    }

    // Method to increment the score by 1
    public void incrementScore() 
    {
        score++;
    }

    // Method to get the current score
    public int getScore() 
    {
        return score;
    }

    // Method to reset the score to 0
    public void resetScore() 
    {
        score = 0;
    }

    // Method to save the current score to the database
    public void saveScoreToDatabase(String userName, String subject) 
    {    
        // sql statement to input the information 
        String insertSQL = "INSERT INTO QUIZRESULTS (USERNAME, SCORE, SUBJECT) VALUES (?, ?, ?)";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertSQL)) {
            
            // sets the username
            ps.setString(1, userName);
            
            // sets the score they got
            ps.setInt(2, score);
            
            // sets the language they selected
            ps.setString(3, subject);
            
            // executes the insert statement
            ps.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
