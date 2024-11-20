/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Conexiones.Mysqlconexion;
import Conexiones.PostgreConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Partida;
import modelos.Videojuego;

/**
 *
 * @author unaip
 */
public class PostgrePartidaDAO implements PartidaDAO{
    private Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos PostgreSQL.
     *
     * @throws SQLException Si ocurre un error al obtener la conexión.
     */
    public PostgrePartidaDAO() throws SQLException {
        this.connection = PostgreConexion.getConnectionUnaiCasa();
    }

    /**
     * Agrega una nueva partida a la base de datos.
     *
     * @param partida La partida que se va a agregar a la base de datos.
     * @throws SQLException Si ocurre un error al agregar la partida.
     */
    @Override
    public void addPartida(Partida partida) throws SQLException {
        String sql = "INSERT INTO partidas (game_id, player_id, experience, life_level, coins, session_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, partida.getIdjuego());
            stmt.setInt(2, partida.getIdjugador());
            stmt.setInt(3, partida.getExp());
            stmt.setInt(4, partida.getNivel());
            stmt.setInt(5, partida.getMonedas());
            stmt.setDate(6, partida.getUltimaconexion());
            stmt.executeUpdate();
        }

        // Actualiza el contador de jugadores en el videojuego
        PostgrevideojuegoDAO pg = new PostgrevideojuegoDAO();
        Videojuego videojuego = pg.getVideojuego(partida.getIdjuego());
        videojuego.setPlayer_count(videojuego.getPlayer_count() + 1);
        pg.updateVideojuego(videojuego);
    }

    /**
     * Obtiene todas las partidas de un jugador específico.
     *
     * @param playerId El ID del jugador para obtener sus partidas.
     * @return Una lista de partidas correspondientes al jugador.
     * @throws SQLException Si ocurre un error al obtener las partidas.
     */
    @Override
    public List<Partida> getPartidasByPlayer(int playerId) throws SQLException {
        String sql = "SELECT * FROM partidas WHERE player_id = ?";
        List<Partida> partidas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    partidas.add(new Partida(
                            rs.getInt("game_id"),
                            rs.getInt("player_id"),
                            rs.getInt("experience"),
                            rs.getInt("life_level"),
                            rs.getInt("coins"),
                            rs.getDate("session_date")
                    ));
                }
            }
        }
        return partidas;
    }

    /**
     * Actualiza los datos de una partida existente en la base de datos.
     *
     * @param partida La partida con los datos actualizados.
     * @throws SQLException Si ocurre un error al actualizar la partida.
     */
    @Override
    public void updatePartida(Partida partida) throws SQLException {
        String sql = "UPDATE partidas SET game_id = ?, player_id = ?, experience = ?, life_level = ?, coins = ?, session_date = ? WHERE player_id = ? AND game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, partida.getIdjuego());
            stmt.setInt(2, partida.getIdjugador());
            stmt.setInt(3, partida.getExp());
            stmt.setInt(4, partida.getNivel());
            stmt.setInt(5, partida.getMonedas());
            stmt.setDate(6, partida.getUltimaconexion());
            stmt.setInt(7, partida.getIdjugador());
            stmt.setInt(8, partida.getIdjuego());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró ninguna partida para actualizar con los criterios dados.");
            } else {
                System.out.println("Partida actualizada correctamente.");
            }

            

            
        } catch (SQLException e) {
            System.err.println("Error al actualizar la partida: " + e.getMessage());
            throw e;
        }
    }
}