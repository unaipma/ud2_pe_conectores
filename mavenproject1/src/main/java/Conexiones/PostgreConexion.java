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

    /**
     * Establece una conexión con la base de datos PostgreSQL en la nube.
     *
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnectionNube() throws SQLException {
        String url = "jdbc:postgresql://ep-spring-rain-a24pgyn5.eu-central-1.aws.neon.tech/ud2conectores?user=postgresql&password=ab4GIg8KBWny&sslmode=require";
        return DriverManager.getConnection(url);
    }

    /**
     * Establece una conexión con la base de datos PostgreSQL desde un servidor local en casa de Unai.
     *
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnectionUnaiCasa() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ud2conectores";
        String user = "postgres";
        String password = "unpama2005";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Establece una conexión con la base de datos PostgreSQL desde un servidor local en casa de Eugenio.
     *
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnectionEugenioCasa() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ud2conexiones";
        return DriverManager.getConnection(url);
    }

    /**
     * Establece una conexión personalizada con la base de datos PostgreSQL en casa de Eugenio.
     *
     * @param url La URL de la base de datos.
     * @return Un objeto {@link Connection} si la conexión es exitosa.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection conexionUserEugenioCasa(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
}