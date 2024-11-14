/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.List;
import modelos.Partida;

/**
 *
 * @author unaip
 */
public interface PartidaDAO {
    void addPartida(Partida partida) throws SQLException;
    List<Partida> getPartidasByPlayer(int playerId) throws SQLException;
    void updatePartida(Partida partida) throws SQLException;
}
