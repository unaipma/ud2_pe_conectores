/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexiones;

import java.sql.*;

public class DatabaseConnection {
    private static String URL;
    private static String USER ;
    private static  String PASSWORD ;

    public static Connection getConnection() throws SQLException {
        
        URL="jdbc:mysql://localhost:3306/ud2conectores";
        USER= "root";
        PASSWORD="";
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error de conexión a la base de datos.");
        }
    }
    
    public static Connection getConnectionpostgre() throws SQLException {
        URL="jdbc:postgresql://localhost:5432/ud2conectores";
        USER= "postgre";
        PASSWORD="lenovo";
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error de conexión a la base de datos.");
        }
    }
    
    
}
