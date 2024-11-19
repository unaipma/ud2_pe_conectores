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
    
    public static Connection getConnectionNube() throws SQLException {
        String url = "jdbc:postgresql://ep-spring-rain-a24pgyn5.eu-central-1.aws.neon.tech/ud2conectores?user=ud2conectores_owner&password=7gmIlvAUcB4M&sslmode=require";
        
        return DriverManager.getConnection(url);
    }
    
    public static Connection getConnectionUnaiCasa() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ud2conectores";
        String user = "postgre";
        String password = "lenovo";
        return DriverManager.getConnection(url, user, password);
    }
    public static Connection conexionUserUnaiCasa(String url,String user, String password) throws SQLException {
        
        return DriverManager.getConnection(url,user,password);
    }
    
    public static Connection getConnectionEugenioCasa() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ud2conexiones";
        return DriverManager.getConnection(url);
    }
    public static Connection conexionUserEugenioCasa(String url) throws SQLException {
        
        return DriverManager.getConnection(url);
    }
}
