/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// List of String arrays to store Japanese words along with their translation
class JapaneseQuestions implements LanguageQuestions {
    private static final List<String[]> questions = Arrays.asList(
            new String[]{"Kon'nichiwa", "Hello"},
            new String[]{"Ringo", "Apple"},
            new String[]{"Arigatō", "Thank you"},
            new String[]{"Gakkō", "School"},
            new String[]{"Umi", "Sea"},
            new String[]{"Yūjin", "Friend"},
            new String[]{"Kōen", "Park"},
            new String[]{"Ai", "Love"},
            new String[]{"Ie", "House"},
            new String[]{"Amerika", "Rice"},
            new String[]{"Kuruma", "Car"},
            new String[]{"Okāsan", "Mom"},
            new String[]{"Otōsan", "Dad"},
            new String[]{"Kyōdai", "Brother"},
            new String[]{"Imōto", "Sister"},
            new String[]{"Sofu", "Grand-Dad"},
            new String[]{"Sobo", "Grand-Mother"}
    );

    // Method to get a list of Japanese questions with their translation
    public static List<String[]> getQuestions() {
        
        // Shuffles the list to randomize them
        Collections.shuffle(questions);
        
        // Returns a sublist with 10 random questions
        return questions.subList(0, 10); 
    }
}

