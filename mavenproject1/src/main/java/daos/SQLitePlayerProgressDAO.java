
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */

import Conexiones.Sqlliteconexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Jugador;
import modelos.PlayerProgress;

public class SQLitePlayerProgressDAO implements PlayerProgressDAO {

    private Sqlliteconexion sqlliteconexion;

    public SQLitePlayerProgressDAO() throws SQLException {
        connect();
    }

    private Connection connect() throws SQLException {
        return sqlliteconexion.getConnection();
    }

    @Override
    public void addPlayerProgress(PlayerProgress progress) throws SQLException {
        String sql = "INSERT INTO player_progress (player_id, nick_name, experience, life_level, coins, session_count, last_login) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, progress.getPlayerId());
            stmt.setString(2, progress.getNickName());
            stmt.setInt(3, progress.getExperience());
            stmt.setInt(4, progress.getLifeLevel());
            stmt.setInt(5, progress.getCoins());
            stmt.setInt(6, progress.getSessionCount());
            stmt.setString(7, progress.getLastLogin()); // Guardar como texto en formato "YYYY-MM-DD HH:MM:SS"

            stmt.executeUpdate();
        }
    }
    
    public void addPlayerProgress(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO player_progress (player_id, nick_name, experience, life_level, coins, session_count, last_login) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNick());
            stmt.setInt(3, jugador.getExperience());
            stmt.setInt(4, jugador.getLifeLevel());
            stmt.setInt(5, jugador.getCoins());
            stmt.setInt(6, jugador.getCoins());
            stmt.setDate(7, jugador.getLast_login()); // Guardar como texto en formato "YYYY-MM-DD HH:MM:SS"

            stmt.executeUpdate();
        }
    }

    @Override
    public List<PlayerProgress> getPlayerProgressById(int playerId) throws SQLException {
        List<PlayerProgress> progressList = new ArrayList<>();
        String sql = "SELECT * FROM player_progress WHERE player_id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayerProgress progress = new PlayerProgress(
                        rs.getInt("player_id"),
                        rs.getString("nick_name"),
                        rs.getInt("experience"),
                        rs.getInt("life_level"),
                        rs.getInt("coins"),
                        rs.getInt("session_count"),
                        rs.getString("last_login")
                );
                progressList.add(progress);
            }
        }
        return progressList;
    }

    @Override
    public void updatePlayerProgress(PlayerProgress progress) throws SQLException {
        String sql = "UPDATE player_progress SET nick_name = ?, experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ? WHERE player_id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, progress.getNickName());
            stmt.setInt(2, progress.getExperience());
            stmt.setInt(3, progress.getLifeLevel());
            stmt.setInt(4, progress.getCoins());
            stmt.setInt(5, progress.getSessionCount());
            stmt.setString(6, progress.getLastLogin());
            stmt.setInt(7, progress.getPlayerId());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<PlayerProgress> getAllPlayerProgress(Jugador jugador) throws SQLException {
        List<PlayerProgress> progressList = new ArrayList<>();
        String sql = "SELECT * FROM player_progress WHERE nick_name =\"" + jugador.getNick()+ "\"";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PlayerProgress progress = new PlayerProgress(
                        rs.getInt("player_id"),
                        rs.getString("nick_name"),
                        rs.getInt("experience"),
                        rs.getInt("life_level"),
                        rs.getInt("coins"),
                        rs.getInt("session_count"),
                        rs.getString("last_login")
                );
                progressList.add(progress);
            }
        }
        return progressList;
    }

    @Override
    public void deletePlayerProgress(int playerId) throws SQLException {
        String sql = "DELETE FROM PlayerProgress WHERE playerId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            stmt.executeUpdate();
        }
    }
}

