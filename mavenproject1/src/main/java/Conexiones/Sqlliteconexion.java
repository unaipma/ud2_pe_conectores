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
public class Sqlliteconexion {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:ud2conectores.db";
        return DriverManager.getConnection(url);
    }
}
