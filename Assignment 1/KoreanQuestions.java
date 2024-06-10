/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// List of String arrays to store Korean words along with their translation
class KoreanQuestions implements LanguageQuestions {
    private static List<String[]> questions = Arrays.asList(
            new String[]{"Annyeonghaseyo", "Hello"},
            new String[]{"Sagwa", "Apple"},
            new String[]{"Gamsahabnida", "Thank you"},
            new String[]{"Haggyo", "School"},
            new String[]{"Bada", "Sea"},
            new String[]{"Chingu", "Friend"},
            new String[]{"Gong-won", "Park"},
            new String[]{"Salang", "Love"},
            new String[]{"Jib", "House"},
            new String[]{"Ssal", "Rice"},
            new String[]{"Jadongcha", "Car"},
            new String[]{"Eomma", "Mom"},
            new String[]{"Appa", "Dad"},
            new String[]{"Hyeongje", "Brother"},
            new String[]{"Jamae", "Sister"},
            new String[]{"Hal-Abeoji", "Grand-Dad"},
            new String[]{"Hal-Meoni", "Grand-Mother"}
    );
    
    // Method to get a list of Korean questions with their translation
    public static List<String[]> getQuestions() {
        
        // Shuffles the list to randomize them
        Collections.shuffle(questions);
        
        // Returns a sublist with 10 random questions
        return questions.subList(0, 10);
    }
}