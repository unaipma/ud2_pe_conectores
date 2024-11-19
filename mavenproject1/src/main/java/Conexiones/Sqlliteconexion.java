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
         String urlUnai = "jdbc:sqlite:C:\\Users\\unaip\\OneDrive\\Documentos\\github\\ud2_pe_conectores\\ud2conectores.db"; // Cambia este path a tu base de datos SQLite
         String urlEugenio = "jdbc:sqlite:jdbc:sqlite:/Volumes/Eugenio/MULWEB2/Acceso a datos/Tema02/ud2_pe_conectores/ud2conectores.db";
        return DriverManager.getConnection(urlEugenio);
    }
}
