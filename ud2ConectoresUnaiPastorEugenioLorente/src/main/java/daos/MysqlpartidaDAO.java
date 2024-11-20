/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Conexiones.Mysqlconexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelos.Partida;
import modelos.Videojuego;

/**
 *
 * @author unaip
 */
public class MysqlpartidaDAO implements PartidaDAO {

    private Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos MySQL.
     *
     * @throws SQLException Si ocurre un error al conectar con la base de datos.
     */
    public MysqlpartidaDAO() throws SQLException {
        this.connection = Mysqlconexion.getConnection();
    }

    /**
     * Agrega una nueva partida a la base de datos.
     *
     * @param partida La partida a agregar.
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

        MysqlvideojuegoDAO pg = new MysqlvideojuegoDAO();
        Videojuego videojuego = pg.getVideojuego(partida.getIdjuego());
        videojuego.setPlayer_count(videojuego.getPlayer_count() + 1);
        pg.updateVideojuego(videojuego);
    }

    /**
     * Obtiene las partidas de un jugador específico.
     *
     * @param playerId El ID del jugador para obtener sus partidas.
     * @return Una lista de las partidas del jugador.
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
     * Actualiza una partida en la base de datos.
     *
     * @param partida La partida con la información actualizada.
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
