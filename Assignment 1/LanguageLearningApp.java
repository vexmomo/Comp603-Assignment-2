/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LanguageLearningApp {

    public static void startApp() 
    {
        Scanner scanner = new Scanner(System.in);

        // Welcome message and a prompt asking for a name
        System.out.println("Welcome to the Language Learning App! (Input 'x' to quit)");
        String userName = getValidName();
        System.out.print("Hello, " + userName + "! let's start learning!\n");
        
        System.out.println("1. Korean Quiz");
        
        System.out.println("2. Japanese Quiz");

        char languageChoice;
        
        // Loop to validate user input
        while (true) 
        {
            System.out.print("Enter your choice (1 or 2), or 'x' to quit: ");
            String input = scanner.nextLine();

            // Check if the input is 'x', if so, close the program
            if (input.equalsIgnoreCase("x")) {
                System.out.println("Closing program.");
                System.exit(0);
            }

            if (input.length() == 1) 
            {
                languageChoice = input.charAt(0);

                if (languageChoice == '1' || languageChoice == '2') 
                {
                    System.out.println("Quiz will begin shortly...");

                    // If the user inputs '1' it will begin the Korean Quiz
                    if (languageChoice == '1') 
                    {
                        // Starts the Korean quiz if users input is '1'
                        System.out.println("\nStarting the Korean Quiz:");
                        startQuiz(KoreanQuestions.getQuestions(), userName);
                    } else 
                    {
                        // Else starts Japanese quiz
                        System.out.println("\nStarting the Japanese Quiz:");
                        startQuiz(JapaneseQuestions.getQuestions(), userName);
                    }
                    break; // Exit the loop after starting the quiz
                } else 
                {
                    // If users input is invalid a error message is shown
                    System.out.println("Invalid choice. Please pick between 1 or 2");
                }
            } else 
            {
                // If user enters more than one character error message is shown
                System.out.println("Invalid input. Please enter only one character");
            }
        }

    }

    private static boolean startQuiz(List<String[]> questions, String userName) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Keeps track of users score
        int score = 0;

        // To keep track of the current question number
        int questionNumber = 0;

        // Loop through each question
        for (String[] q : questions) 
        {
            // Increment the question number
            questionNumber++;

            // Gets the word for the question from the Korean or Japanese classes
            String word = q[0];

            // Gets the correct translation for the word chosen above, from the Korean or Japanese classes
            String correctTranslation = q[1];

            // Generate options for the questions using the options in the generateOptions method
            List<String> options = generateOptions(correctTranslation);

            // Shuffle the options for the questions
            Collections.shuffle(options, random);

            // Displays the questions with the options for the user
            System.out.println("What is the translation of '" + word + "'?");
            for (int i = 0; i < options.size(); i++) 
            {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            String userInput;
            int userChoice;

            // Loops until a valid input is given
            while (true) 
            {
                System.out.print("Enter your choice (1, 2, or 3), or 'x' to quit: ");
                userInput = scanner.nextLine();

                // Checking if user inputs 'x', if they do it closes the program
                if (userInput.equalsIgnoreCase("x")) 
                {
                    System.out.println("Closing program.");

                    // Indicates that the user wants to quit
                    return true;
                }

                try 
                {
                    // Parse user input to integer
                    userChoice = Integer.parseInt(userInput);

                    // Checking if the userChoice is between the option size
                    if (userChoice >= 1 && userChoice <= options.size()) 
                    {
                        // Valid input, exit the loop
                        break;
                    } else 
                    {
                        // Message telling the user their input is invalid
                        System.out.println("Invalid choice. Please enter a number between 1 and " + options.size());
                    }
                } catch (NumberFormatException e) 
                {
                    // Error Message if the user does not enter a number
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            }

            // Get the users answer based on their choice
            String userAnswer = options.get(userChoice - 1);

            // Checks if users answers is correct
            if (userAnswer.equals(correctTranslation)) 
            {
                // If users answer equals to the correct translation print "Correct!"
                System.out.println("Correct!");
                // Adds to score if answer is correct
                score++;
            } else 
            {
                // Else if the users answer is wrong it tells the user its incorrect
                // and gives them the correct answer allowing them to learn
                System.out.println("Incorrect. The correct answer is: " + correctTranslation);
            }
        }

        // Completion message outside the loop, so it does not show every time a question is answered
        System.out.println("Congrats " + userName + " you completed the quiz. Your score: " + score + "/" + questions.size());
        saveScoreToFile(userName, score);
        return false;
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String getValidName() {
        while (true) {
            System.out.println("Please enter your name: ");
            String userName = scanner.nextLine();

            // Check if the input is 'x', if so, close the program
            if (userName.equalsIgnoreCase("x")) {
                System.out.println("Closing program.");
                System.exit(0);
            }

            // check if the input is empty
            if (userName.isEmpty()) {
                System.out.println("Invalid input. Input cannot be empty.");
                continue; // ask for input again
            }

            // Ensure input contains only letters
            if (userName.matches("[a-zA-Z\\s]{0,10}")) {
                return userName;
            } else {
                System.out.println("Invalid input. Please enter a proper name without numbers.");
            }

        }
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

    private static void saveScoreToFile(String userName, int score) 
    {
        // Opening the 'user_score,txt' file if it does not exist it will be created
        try ( PrintWriter writer = new PrintWriter(new FileWriter("user_scores.txt", true))) 
        {

            // Once file is opened update the file with the users name and the score they got
            writer.println(userName + "," + score);
        } catch (IOException e)
        {
            // If a IOException occurs print that stack trace
            e.printStackTrace();
        }
    }
}
