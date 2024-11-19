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
import modelos.Jugador;

/**
 *
 * @author unaip
 */
public class MysqljugadorDAO implements JugadorDAO {

    private Connection connection;

    public MysqljugadorDAO() throws SQLException {
        this.connection = Mysqlconexion.getConnection();
    }

    @Override
    public void addJugador(Jugador jugador) throws SQLException {
        
        String sql = "INSERT INTO jugadores (player_id,nickname, experience, life_level, coins, session_count, last_login) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1,jugador.getId());
            stmt.setString(2, jugador.getNick());
            stmt.setInt(3, jugador.getExperience());
            stmt.setInt(4, jugador.getLifeLevel());
            stmt.setInt(5, jugador.getCoins());
            stmt.setInt(6, jugador.getSession_count());
            stmt.setDate(7, jugador.getLast_login());
            stmt.executeUpdate();

            
        }
    }

    @Override
    public List<Jugador> getTop10Jugadores() throws SQLException {
        String sql = "SELECT * FROM jugadores ORDER BY experience DESC LIMIT 10";
        List<Jugador> jugadores = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                jugadores.add(new Jugador(
                        rs.getInt("player_id"),
                        rs.getString("nickname"),
                        rs.getInt("experience"),
                        rs.getInt("life_level"),
                        rs.getInt("coins")
                ));
            }
        }
        return jugadores;
    }

    @Override
    public void updateJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE jugadores SET nickname = ?, experience = ?, life_level = ?, coins = ? WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNick());
            stmt.setInt(2, jugador.getExperience());
            stmt.setInt(3, jugador.getLifeLevel());
            stmt.setInt(4, jugador.getCoins());
            stmt.setInt(5, jugador.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean deleteJugador(String NickName) throws SQLException {
        
         Jugador eliminado= getJugador(NickName);
        if (eliminado==null) {
            return true;
        }else{
        String sql = "DELETE FROM jugadores WHERE nickname = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, NickName);
            stmt.executeUpdate();
            return false;
        }
        }
    }

    @Override
    public Jugador getJugador(String NickName) throws SQLException {
        String sql = "SELECT * FROM jugadores WHERE nickname = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, NickName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Jugador(
                            rs.getInt("player_id"),
                            rs.getString("nickname"),
                            rs.getInt("experience"),
                            rs.getInt("life_level"),
                            rs.getInt("coins")
                    );
                }
            }
        }
        return null;
    }
}
