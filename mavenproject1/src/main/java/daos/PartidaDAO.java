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
    /**
     * Agrega una nueva partida a la base de datos.
     *
     * @param partida La partida que se va a agregar.
     * @throws SQLException Si ocurre un error al agregar la partida.
     */
    void addPartida(Partida partida) throws SQLException;

    /**
     * Obtiene todas las partidas de un jugador específico.
     *
     * @param playerId El ID del jugador para obtener sus partidas.
     * @return Una lista de partidas del jugador.
     * @throws SQLException Si ocurre un error al obtener las partidas.
     */
    List<Partida> getPartidasByPlayer(int playerId) throws SQLException;

    /**
     * Actualiza la información de una partida en la base de datos.
     *
     * @param partida La partida con la información actualizada.
     * @throws SQLException Si ocurre un error al actualizar la partida.
     */
    void updatePartida(Partida partida) throws SQLException;
}












