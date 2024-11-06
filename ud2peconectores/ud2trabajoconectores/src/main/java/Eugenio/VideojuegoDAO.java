
package Eugenio;

import java.sql.*;

public class VideojuegoDAO {
    private Connection connection;

    public VideojuegoDAO(Connection connection) {
        this.connection = connection;
    }

    // Agregar un videojuego
    public void addGame(int gameId, String title) {
        String query = "INSERT INTO videojuegos (game_id, title, total_sessions, last_session) VALUES (?, ?, 0, NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameId);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener detalles del videojuego
    public void getGameDetails(int gameId) {
        String query = "SELECT * FROM videojuegos WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Game Title: " + rs.getString("title"));
                System.out.println("Total Sessions: " + rs.getInt("total_sessions"));
                System.out.println("Last Session: " + rs.getString("last_session"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar estadísticas de sesión
    public void updateGameSessions(int gameId, int newSessions, String lastSessionDate) {
        String query = "UPDATE videojuegos SET total_sessions = ?, last_session = ? WHERE game_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newSessions);
            stmt.setString(2, lastSessionDate);
            stmt.setInt(3, gameId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
