/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */
import java.util.List;

// Defining a interface
interface LanguageQuestions {
    
    // Static method getQuestions() which returns a List of String arrays
   static List<String[]> getQuestions()
   {
       // default implmentation returns null
       // It can be overridden by classes implementing this interface with providing functions
       return null;
   }
    
}

