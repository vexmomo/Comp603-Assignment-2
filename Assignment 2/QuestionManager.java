/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionManager {

    // List of questions
    private List<String[]> questions;
    
    // Number of the current question
    private int currentQuestionNum;

    // Constructor to initialize questions and set the current question to 0
    public QuestionManager(List<String[]> questions) 
    {
        this.questions = questions;
        this.currentQuestionNum = 0;
    }
    
    // method to get the current question
    public String[] getCurrentQuestion() 
    {
        return questions.get(currentQuestionNum);
    }

    // method to check if there is a next question
    public boolean hasNextQuestion() 
    {
        return currentQuestionNum < questions.size() - 1;
    }

    // method to move on to the next question
    public void nextQuestion() 
    {
        if (currentQuestionNum < questions.size() - 1) 
        {
            currentQuestionNum++;
        }
    }

    
    public static List<String> generateOptions(String correctTranslation) 
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
}