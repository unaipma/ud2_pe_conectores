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
    /**
     * Agrega un nuevo jugador a la base de datos.
     *
     * @param jugador El jugador a agregar.
     * @throws SQLException Si ocurre un error al agregar el jugador.
     */
    void addJugador(Jugador jugador) throws SQLException;

    /**
     * Actualiza la información de un jugador en la base de datos.
     *
     * @param jugador El jugador con la información actualizada.
     * @throws SQLException Si ocurre un error al actualizar el jugador.
     */
    void updateJugador(Jugador jugador) throws SQLException;

    /**
     * Elimina un jugador de la base de datos utilizando su nombre de usuario.
     *
     * @param NickName El nombre de usuario del jugador a eliminar.
     * @return true si el jugador fue eliminado con éxito, false si no se encontró.
     * @throws SQLException Si ocurre un error al eliminar el jugador.
     */
    boolean deleteJugador(String NickName) throws SQLException;

    /**
     * Obtiene un jugador de la base de datos utilizando su nombre de usuario.
     *
     * @param NickName El nombre de usuario del jugador a obtener.
     * @return El jugador con el nombre de usuario especificado.
     * @throws SQLException Si ocurre un error al obtener el jugador.
     */
    Jugador getJugador(String NickName) throws SQLException;

    /**
     * Obtiene el top 10 de jugadores de la base de datos, basado en un criterio específico.
     *
     * @return Una lista de los 10 mejores jugadores.
     * @throws SQLException Si ocurre un error al obtener el top 10 de jugadores.
     */
    List<Jugador> getTop10Jugadores() throws SQLException;
}













