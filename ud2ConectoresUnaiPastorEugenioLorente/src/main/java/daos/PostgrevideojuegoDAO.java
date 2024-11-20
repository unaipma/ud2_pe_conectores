/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Conexiones.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Videojuego;

/**
 *
 * @author unaip
 */
public class PostgrevideojuegoDAO implements VideojuegoDAO{
    private Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos PostgreSQL.
     *
     * @throws SQLException Si ocurre un error al obtener la conexión.
     */
    public PostgrevideojuegoDAO() throws SQLException {
        this.connection = PostgreConexion.getConnectionUnaiCasa();
    }

    /**
     * Agrega un nuevo videojuego a la base de datos.
     *
     * @param videojuego El videojuego que se va a agregar a la base de datos.
     * @throws SQLException Si ocurre un error al agregar el videojuego.
     */
    @Override
    public void addVideojuego(Videojuego videojuego) throws SQLException {
        String sql = "INSERT INTO videojuegos (isbn, title, player_count, total_sessions, last_session) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, videojuego.getIsbn());
            stmt.setString(2, videojuego.getTitle());
            stmt.setInt(3, videojuego.getPlayer_count());
            stmt.setInt(4, videojuego.getTotal_sessions());
            stmt.setDate(5, videojuego.getLast_session());
            stmt.executeUpdate();


        }
    }

    /**
     * Actualiza los datos de un videojuego existente en la base de datos.
     *
     * @param videojuego El videojuego con los datos actualizados.
     * @throws SQLException Si ocurre un error al actualizar el videojuego.
     */
    @Override
    public void updateVideojuego(Videojuego videojuego) throws SQLException {
        String sql = "UPDATE videojuegos SET isbn = ?, title = ?, player_count = ?, total_sessions = ?, last_session = ? WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, videojuego.getIsbn());
            stmt.setString(2, videojuego.getTitle());
            stmt.setInt(3, videojuego.getPlayer_count());
            stmt.setInt(4, videojuego.getTotal_sessions());
            stmt.setDate(5, videojuego.getLast_session());
            stmt.setInt(6, videojuego.getGame_id());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un videojuego de la base de datos.
     *
     * @param gameId El ID del videojuego a eliminar.
     * @return true si el videojuego fue eliminado con éxito, false si no existía.
     * @throws SQLException Si ocurre un error al eliminar el videojuego.
     */
    @Override
    public boolean deleteVideojuego(int gameId) throws SQLException {
        Videojuego eliminado = getVideojuego(gameId);
        if (eliminado == null) {
            return false;
        } else {
            String sql = "DELETE FROM videojuegos WHERE game_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, gameId);
                stmt.executeUpdate();
            }
            return true;
        }
    }

    /**
     * Obtiene un videojuego por su ID.
     *
     * @param gameId El ID del videojuego a obtener.
     * @return El videojuego con el ID especificado, o null si no existe.
     * @throws SQLException Si ocurre un error al obtener el videojuego.
     */
    @Override
    public Videojuego getVideojuego(int gameId) throws SQLException {
        String sql = "SELECT * FROM videojuegos WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Videojuego(
                        rs.getInt("game_id"),
                        rs.getInt("isbn"),
                        rs.getString("title"),
                        rs.getInt("player_count"),
                        rs.getInt("total_sessions"),
                        rs.getDate("last_session")
                );
            }
        }
        return null;
    }

    /**
     * Obtiene todos los videojuegos de la base de datos.
     *
     * @return Una lista de todos los videojuegos en la base de datos.
     * @throws SQLException Si ocurre un error al obtener los videojuegos.
     */
    @Override
    public List<Videojuego> getAllVideojuegos() throws SQLException {
        List<Videojuego> videojuegos = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                videojuegos.add(new Videojuego(
                        rs.getInt("game_id"),
                        rs.getInt("isbn"),
                        rs.getString("title"),
                        rs.getInt("player_count"),
                        rs.getInt("total_sessions"),
                        rs.getDate("last_session")
                ));
            }
        }
        return videojuegos;
    }
}
