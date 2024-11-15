/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Jugador;

public class SQLiteJugadorDAO implements JugadorDAO {
    
    private static final String URL = "jdbc:sqlite:path_to_your_sqlite_db_file.db"; // Cambia este path a tu base de datos SQLite

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void addJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO Jugador (id, nick_name, experience, life_level, coins) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNick());
            stmt.setInt(3, jugador.getExperience());
            stmt.setInt(4, jugador.getLifeLevel());
            stmt.setInt(5, jugador.getCoins());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE Jugador SET nick_name = ?, experience = ?, life_level = ?, coins = ? WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, jugador.getNick());
            stmt.setInt(2, jugador.getExperience());
            stmt.setInt(3, jugador.getLifeLevel());
            stmt.setInt(4, jugador.getCoins());
            stmt.setInt(5, jugador.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean deleteJugador(int id) throws SQLException {
        String sql = "DELETE FROM Jugador WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return true;
    }

    @Override
    public Jugador getJugador(int id) throws SQLException {
        String sql = "SELECT * FROM Jugador WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Jugador(
                    rs.getInt("id"),
                    rs.getString("nick_name"),
                    rs.getInt("experience"),
                    rs.getInt("life_level"),
                    rs.getInt("coins")
                );
            }
        }
        return null; // Si no se encuentra el jugador con el ID dado
    }

    @Override
    public List<Jugador> getTop10Jugadores() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM Jugador ORDER BY experience DESC LIMIT 10";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                jugadores.add(new Jugador(
                    rs.getInt("id"),
                    rs.getString("nick_name"),
                    rs.getInt("experience"),
                    rs.getInt("life_level"),
                    rs.getInt("coins")
                ));
            }
        }
        return jugadores;
    }
}
