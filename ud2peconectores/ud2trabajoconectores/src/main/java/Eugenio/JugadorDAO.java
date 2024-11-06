
package Eugenio;

import java.sql.*;
import java.util.*;

public class JugadorDAO {
    private Connection connection;

    public JugadorDAO(Connection connection) {
        this.connection = connection;
    }

    // Agregar un jugador
    public void addPlayer(int playerId, String nickName, int experience, int lifeLevel, int coins) {
        String query = "INSERT INTO jugadores (player_id, nick_name, experience, life_level, coins) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerId);
            stmt.setString(2, nickName);
            stmt.setInt(3, experience);
            stmt.setInt(4, lifeLevel);
            stmt.setInt(5, coins);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener jugadores por experiencia
    public List<String> getTopPlayers() {
        List<String> topPlayers = new ArrayList<>();
        String query = "SELECT * FROM jugadores ORDER BY experience DESC LIMIT 10";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                topPlayers.add(rs.getString("nick_name") + " - " + rs.getInt("experience"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topPlayers;
    }

    // Actualizar el progreso de un jugador
    public void updatePlayerProgress(int playerId, int experience, int lifeLevel, int coins) {
        String query = "UPDATE jugadores SET experience = ?, life_level = ?, coins = ? WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, experience);
            stmt.setInt(2, lifeLevel);
            stmt.setInt(3, coins);
            stmt.setInt(4, playerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
