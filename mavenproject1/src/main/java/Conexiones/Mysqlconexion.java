/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author unaip
 */
public class Mysqlconexion {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ud2conectores";
        String user = "mysql";
        String password = "lenovo";
        return DriverManager.getConnection(url, user, password);
    }
    
    public static Connection conexionUser(String url,String user, String password) throws SQLException {
        
        return DriverManager.getConnection(url,user,password);
    }
}
