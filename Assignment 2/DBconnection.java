/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603_assignment;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBconnection {
    private static final String URL = "jdbc:derby://localhost:1527/UserAndScoreDB";
    private static final String USERNAME = "tarvan";
    private static final String PASSWORD = "momo";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
