/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.SQLException;

/**
 *
 * @author unaip
 */
public class DAOFactory {
    /**
     * Obtiene una implementación de {@link JugadorDAO} según el tipo de base de datos.
     *
     * @param dbType El tipo de base de datos (mysql, postgresql, sqlite).
     * @return Una implementación de {@link JugadorDAO}.
     * @throws SQLException Si ocurre un error al obtener la conexión.
     * @throws IllegalArgumentException Si el tipo de base de datos no es soportado.
     */
    public static JugadorDAO getJugadorDAO(String dbType) throws SQLException {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MysqljugadorDAO();
            case "postgresql":
                return new PostgreJugadorDAO();
            case "sqlite":
                return new SQLiteJugadorDAO();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }

    /**
     * Obtiene una implementación de {@link PartidaDAO} según el tipo de base de datos.
     *
     * @param dbType El tipo de base de datos (mysql, postgresql).
     * @return Una implementación de {@link PartidaDAO}.
     * @throws SQLException Si ocurre un error al obtener la conexión.
     * @throws IllegalArgumentException Si el tipo de base de datos no es soportado.
     */
    public static PartidaDAO getPartidaDAO(String dbType) throws SQLException {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MysqlpartidaDAO();
            case "postgresql":
                return new PostgrePartidaDAO();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }

    /**
     * Obtiene una implementación de {@link VideojuegoDAO} según el tipo de base de datos.
     *
     * @param dbType El tipo de base de datos (mysql, postgresql, sqlite).
     * @return Una implementación de {@link VideojuegoDAO}.
     * @throws SQLException Si ocurre un error al obtener la conexión.
     * @throws IllegalArgumentException Si el tipo de base de datos no es soportado.
     */
    public static VideojuegoDAO getVideojuegoDAO(String dbType) throws SQLException {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MysqlvideojuegoDAO();
            case "postgresql":
                return new PostgrevideojuegoDAO();

            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }
}