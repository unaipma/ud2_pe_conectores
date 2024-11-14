/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.List;
import modelos.Videojuego;

/**
 *
 * @author unaip
 */
public interface VideojuegoDAO {
    void addVideojuego(Videojuego videojuego) throws SQLException;
    void updateVideojuego(Videojuego videojuego) throws SQLException;
    void deleteVideojuego(int gameId) throws SQLException;
    Videojuego getVideojuego(int gameId) throws SQLException;
    List<Videojuego> getAllVideojuegos() throws SQLException;
}
