/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */
import java.sql.SQLException;
import java.util.List;
import modelos.Jugador;
import modelos.PlayerProgress;

public interface PlayerProgressDAO {
    /**
     * Agrega el progreso de un jugador a la base de datos.
     *
     * @param progress El progreso del jugador que se va a agregar.
     * @throws SQLException Si ocurre un error al agregar el progreso.
     */
    void addPlayerProgress(PlayerProgress progress) throws SQLException;

    /**
     * Obtiene el progreso de un jugador específico utilizando su ID.
     *
     * @param playerNick El ID del jugador cuyo progreso se desea obtener.
     * @return Una lista de los progresos del jugador.
     * @throws SQLException Si ocurre un error al obtener el progreso.
     */
    List<PlayerProgress> getPlayerProgressById(String playerNick) throws SQLException;

    /**
     * Actualiza el progreso de un jugador en la base de datos.
     *
     * @param progress El progreso actualizado del jugador.
     * @throws SQLException Si ocurre un error al actualizar el progreso.
     */
    void updatePlayerProgress(PlayerProgress progress) throws SQLException;

    /**
     * Obtiene todos los progresos de un jugador.
     *
     * @param jugador El jugador cuyos progresos se desean obtener.
     * @return Una lista de los progresos del jugador.
     * @throws SQLException Si ocurre un error al obtener los progresos.
     */
    List<PlayerProgress> getAllPlayerProgress(Jugador jugador) throws SQLException;

    /**
     * Elimina el progreso de un jugador de la base de datos.
     *
     * @param playerId El ID del jugador cuyo progreso se eliminará.
     * @throws SQLException Si ocurre un error al eliminar el progreso.
     */
    void deletePlayerProgress(int playerId) throws SQLException;
}

