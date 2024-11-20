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
    /**
     * Establece una conexión predeterminada con la base de datos MySQL.
     *
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ud2conectores";
        String user = "mysql";
        String password = "lenovo";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Establece una conexión con la base de datos MySQL utilizando parámetros personalizados.
     *
     * @param url      La URL de la base de datos.
     * @param user     El nombre de usuario para la conexión.
     * @param password La contraseña para la conexión.
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection conexionUser(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
