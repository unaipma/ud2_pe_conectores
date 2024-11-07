
package Eugenio;

import java.sql.*;

public class JugadorClienteDAO {
    private Connection connection;

    public JugadorClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Guardar progreso local
    public void saveProgress(int playerId, int experience, int lifeLevel, int coins) {
        String query = "INSERT INTO progreso (player_id, experience, life_level, coins) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, experience);
            stmt.setInt(3, lifeLevel);
            stmt.setInt(4, coins);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener progreso del jugador local
    public void getProgress(int playerId) {
        String query = "SELECT * FROM progreso WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Experience: " + rs.getInt("experience"));
                System.out.println("Life Level: " + rs.getInt("life_level"));
                System.out.println("Coins: " + rs.getInt("coins"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
