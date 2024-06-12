/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */

public class Main {
    public static void main(String[] args) {
        // setup the database
        DBconnection.setupDB();
        // Start the Language Learning App with GUI
        javax.swing.SwingUtilities.invokeLater(() -> new LanguageLearningApp());
    }
}
