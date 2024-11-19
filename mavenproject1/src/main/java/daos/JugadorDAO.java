/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.List;
import modelos.Jugador;

/**
 *
 * @author unaip
 */
public interface JugadorDAO {
    void addJugador(Jugador jugador) throws SQLException;
    void updateJugador(Jugador jugador) throws SQLException;
    boolean deleteJugador(String NickName) throws SQLException;
    Jugador getJugador(String NickName) throws SQLException;
    List<Jugador> getTop10Jugadores() throws SQLException;
}
