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
    /**
     * Agrega un nuevo videojuego a la base de datos.
     *
     * @param videojuego el objeto {@link Videojuego} que contiene los datos a insertar.
     * @throws SQLException si ocurre un error durante la operación de inserción.
     */
    void addVideojuego(Videojuego videojuego) throws SQLException;

    /**
     * Actualiza la información de un videojuego existente en la base de datos.
     *
     * @param videojuego el objeto {@link Videojuego} que contiene los datos actualizados.
     * @throws SQLException si ocurre un error durante la operación de actualización.
     */
    void updateVideojuego(Videojuego videojuego) throws SQLException;

    /**
     * Elimina un videojuego de la base de datos basado en su identificador único.
     *
     * @param gameId el identificador único del videojuego a eliminar.
     * @return {@code true} si la operación de eliminación fue exitosa.
     * @throws SQLException si ocurre un error durante la operación de eliminación.
     */
    boolean deleteVideojuego(int gameId) throws SQLException;

    /**
     * Recupera un videojuego específico de la base de datos utilizando su identificador único.
     *
     * @param gameId el identificador único del videojuego a recuperar.
     * @return un objeto {@link Videojuego} que representa el videojuego encontrado,
     *         o {@code null} si no se encuentra ningún videojuego con ese ID.
     * @throws SQLException si ocurre un error durante la operación de consulta.
     */
    Videojuego getVideojuego(int gameId) throws SQLException;

    /**
     * Recupera una lista con todos los videojuegos almacenados en la base de datos.
     *
     * @return una lista de objetos {@link Videojuego} que representa todos los videojuegos almacenados.
     * @throws SQLException si ocurre un error durante la operación de consulta.
     */
    List<Videojuego> getAllVideojuegos() throws SQLException;
}