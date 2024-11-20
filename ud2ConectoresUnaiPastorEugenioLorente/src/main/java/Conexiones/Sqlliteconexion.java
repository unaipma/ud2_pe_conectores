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

    /**
     * Establece una conexión personalizada con la base de datos SQLite con varias urls dependiendo del lugar de trabajo.
     *
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
         String urlUnai = "jdbc:sqlite:C:\\Users\\unaip\\OneDrive\\Documentos\\github\\trabajo\\ud2_pe_conectores-Eugenio\\ud2_pe_conectores-Eugenio\\ud2conectores.db"; // Cambia este path a tu base de datos SQLite
         String urlEugenio = "jdbc:sqlite:/Volumes/Eugenio/MULWEB2/Acceso a datos/Tema02/ud2_pe_conectores/ud2conectores.db";
         String urlEugenioClase = "jdbc:sqlite:G:\\MULWEB2\\Acceso a datos\\Tema02\\ud2_pe_conectores\\ud2conectores.db"; //EugenioClase
        return DriverManager.getConnection(urlEugenioClase);
    }
}
