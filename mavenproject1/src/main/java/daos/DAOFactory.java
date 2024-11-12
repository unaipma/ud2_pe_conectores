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

    public static PartidaDAO getPartidaDAO(String dbType) throws SQLException {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MysqlpartidaDAO();
            case "postgresql":
                return new PostgrePartidaDAO();
            case "sqlite":
                return new SQLitePartidaDAO();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }

    public static VideojuegoDAO getVideojuegoDAO(String dbType) throws SQLException {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MysqlvideojuegoDAO();
            case "postgresql":
                return new PostgrevideojuegoDAO();
            case "sqlite":
                return new SQLiteVideojuegoDAO();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }
}

