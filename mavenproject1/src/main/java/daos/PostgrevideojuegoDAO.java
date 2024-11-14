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

    public PostgrevideojuegoDAO() throws SQLException {
        this.connection = Mysqlconexion.getConnection();
    }

    @Override
    public void addVideojuego(Videojuego videojuego) throws SQLException {
        String sql = "INSERT INTO videojuegos (isbn, title, player_count, total_sessions, last_session) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, videojuego.getIsbn());
            stmt.setString(2, videojuego.getTitle());
            stmt.setInt(3, videojuego.getPlayer_count());
            stmt.setInt(4, videojuego.getTotal_sessions());
            stmt.setDate(5, videojuego.getLast_session() );
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                videojuego.setGame_id(keys.getInt(1));
            }
        }
    }

    // Implementación de updateVideojuego, deleteVideojuego y getAllVideojuegos

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

    @Override
public boolean deleteVideojuego(int gameId) throws SQLException 
{
    Videojuego eliminado= getVideojuego(gameId);
        if (eliminado==null) {
           return false;
        }else{
    String sql = "DELETE FROM videojuegos WHERE game_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, gameId);
        stmt.executeUpdate();
    }
            return true;
        }
}

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
