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
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBconnection {
    private static final String URL = "jdbc:derby:UserAndScoreDB;create=true";
    private static final String SCHEMA = "TARVAN";
    private static final String CREATE_SCHEMA = "CREATE SCHEMA " + SCHEMA;
    private static final String CREATE_TABLE_QUIZRESULTS = "CREATE TABLE QUIZRESULTS (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
            + "USERNAME VARCHAR(50),"
            + "SCORE INT,"
            + "SUBJECT VARCHAR(50))";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    
    public static void setupDB() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create schema if it does not exist
            try {
                stmt.executeUpdate(CREATE_SCHEMA);
                System.out.println("Schema created successfully.");
            } catch (SQLException e) {
                if (e.getSQLState().equals("X0Y68")) { // Ignore if schema already exists
                    System.out.println("Schema already exists.");
                } else {
                    throw e;
                }
            }
            // Create table within the schema
            try {
                stmt.executeUpdate(CREATE_TABLE_QUIZRESULTS);
                System.out.println("Table created successfully.");
            } catch (SQLException e) {
                if (e.getSQLState().equals("X0Y32")) { // Ignore if table already exists
                    System.out.println("Table already exists.");
                } else {
                    throw e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


