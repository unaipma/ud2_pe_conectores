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
public class PostgreConexion {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ud2conectores";
        String user = "postgre";
        String password = "lenovo";
        return DriverManager.getConnection(url, user, password);
    }
}
