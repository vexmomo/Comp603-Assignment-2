/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Comp603;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main 
{
    public static void main(String[] args) 
    {

        // Assigning the file name
        String fileName= "user_scores.txt";
       
        // Header
        System.out.println("Past Score's");
        System.out.println("====================");
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            // Read each line in the file and print it
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        // Footer for the past scores
        System.out.println("====================");
        
        // Starts the Language Learning Awpp
        LanguageLearningApp.startApp();
    }
}
