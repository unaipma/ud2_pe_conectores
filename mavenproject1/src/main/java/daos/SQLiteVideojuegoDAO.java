/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */

import modelos.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteVideojuegoDAO implements VideojuegoDAO {

    private static final String URL = "jdbc:sqlite:path_to_your_sqlite_db_file.db"; // Cambia este path a tu base de datos SQLite

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void addVideojuego(Videojuego videojuego) throws SQLException {
        String sql = "INSERT INTO Videojuego (game_id, isbn, title, player_count, total_sessions, last_session) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, videojuego.getGame_id());
            stmt.setInt(2, videojuego.getIsbn());
            stmt.setString(3, videojuego.getTitle());
            stmt.setInt(4, videojuego.getPlayer_count());
            stmt.setInt(5, videojuego.getTotal_sessions());
            stmt.setDate(6, new java.sql.Date(videojuego.getLast_session().getTime())); // Convertir Date a java.sql.Date
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateVideojuego(Videojuego videojuego) throws SQLException {
        String sql = "UPDATE Videojuego SET isbn = ?, title = ?, player_count = ?, total_sessions = ?, last_session = ? WHERE game_id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, videojuego.getIsbn());
            stmt.setString(2, videojuego.getTitle());
            stmt.setInt(3, videojuego.getPlayer_count());
            stmt.setInt(4, videojuego.getTotal_sessions());
            stmt.setDate(5, new java.sql.Date(videojuego.getLast_session().getTime())); // Convertir Date a java.sql.Date
            stmt.setInt(6, videojuego.getGame_id());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean deleteVideojuego(int gameId) throws SQLException {
        String sql = "DELETE FROM Videojuego WHERE game_id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, gameId);
            stmt.executeUpdate();
        }
        return true;
    }

    @Override
    public Videojuego getVideojuego(int gameId) throws SQLException {
        String sql = "SELECT * FROM Videojuego WHERE game_id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Videojuego(
                    rs.getInt("game_id"),
                    rs.getInt("isbn"),
                    rs.getString("title"),
                    rs.getInt("player_count"),
                    rs.getInt("total_sessions"),
                    rs.getDate("last_session") // Se convierte automáticamente a java.util.Date
                );
            }
        }
        return null; // Retorna null si no se encuentra el videojuego
    }

    @Override
    public List<Videojuego> getAllVideojuegos() throws SQLException {
        List<Videojuego> videojuegos = new ArrayList<>();
        String sql = "SELECT * FROM Videojuego";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Videojuego videojuego = new Videojuego(
                    rs.getInt("game_id"),
                    rs.getInt("isbn"),
                    rs.getString("title"),
                    rs.getInt("player_count"),
                    rs.getInt("total_sessions"),
                    rs.getDate("last_session") // Se convierte automáticamente a java.util.Date
                );
                videojuegos.add(videojuego);
            }
        }
        return videojuegos;
    }
}

