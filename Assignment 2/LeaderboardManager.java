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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardManager {
    private JFrame frame;
    // text area to show the leaderboard
    private JTextArea leaderboardArea;

    // Constructor to initialize the LeaderboardManager with the main application frame.
    public LeaderboardManager(JFrame frame) {
        this.frame = frame;
        initializeLeaderboardPanel();
    }

    // Initializes the leaderboard panel and its components.
    private void initializeLeaderboardPanel() {
        // Create the leaderboard panel with BorderLayout
        JPanel leaderboardPanel = new JPanel(new BorderLayout(10, 10));

        // Add a title label
        JLabel titleLabel = new JLabel("Leaderboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leaderboardPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a JTextArea for the leaderboard
        leaderboardArea = new JTextArea();
        leaderboardArea.setEditable(false);
        leaderboardArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add button to return back to the main screen
        JButton backToStartButton = new JButton("Back To Start");
        backToStartButton.addActionListener(e -> backToHomeScreen());
        leaderboardPanel.add(leaderboardArea, BorderLayout.CENTER);
        leaderboardPanel.add(backToStartButton, BorderLayout.SOUTH);

        frame.add(leaderboardPanel, "LeaderboardPanel");
    }

    // Shows the leaderboard by retrieving the data and displaying it in the JTextArea
    public void showLeaderboard() {
        List<String> leaderboard = getLeaderboard();
        // header showing what each section is
        StringBuilder leaderboardText = new StringBuilder(
                "====================\n" +
                "Username | Score | Subject\n"
                + "====================\n");
        for (String entry : leaderboard) {
            leaderboardText.append(entry).append("\n");
        }
        leaderboardArea.setText(leaderboardText.toString());
        
        // Show the leaderboard panel
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "LeaderboardPanel");
    }

    // Retrieves the leaderboard data from the database.
    private List<String> getLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        
        // sql statement to get the information 
        String selectSQL = "SELECT USERNAME, SCORE, SUBJECT FROM QUIZRESULTS ORDER BY SCORE DESC";
        try (Connection connection = DBconnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                // formating how it should show
                String entry = String.format("%s - %d - (%s)", rs.getString("USERNAME"), rs.getInt("SCORE"), rs.getString("SUBJECT"));
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    // method to go back to home screen (after looking at the leaderboard)
    private void backToHomeScreen() {
        CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
        cl.show(frame.getContentPane(), "StartPanel");
    }
}
