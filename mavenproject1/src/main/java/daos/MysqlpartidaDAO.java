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
import java.util.ArrayList;
import java.util.List;
import modelos.Partida;

/**
 *
 * @author unaip
 */
public class MysqlpartidaDAO implements PartidaDAO{
   private Connection connection;

    public MysqlpartidaDAO() throws SQLException {
        this.connection = Mysqlconexion.getConnection();
    }

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
    }

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
       
        stmt.executeUpdate();
    }
}

    
} 

