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

    /**
     * Constructor que inicializa la conexión a la base de datos MySQL.
     *
     * @throws SQLException Si ocurre un error al conectar con la base de datos.
     */
    public MysqljugadorDAO() throws SQLException {
        this.connection = Mysqlconexion.getConnection();
    }

    /**
     * Agrega un nuevo jugador a la base de datos.
     *
     * @param jugador El jugador a agregar.
     * @throws SQLException Si ocurre un error al agregar el jugador.
     */
    @Override
    public void addJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO jugadores (player_id,nickname, experience, life_level, coins, session_count, last_login) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNick());
            stmt.setInt(3, jugador.getExperience());
            stmt.setInt(4, jugador.getLifeLevel());
            stmt.setInt(5, jugador.getCoins());
            stmt.setInt(6, jugador.getSession_count());
            stmt.setDate(7, jugador.getLast_login());
            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene el top 10 de jugadores basados en la experiencia.
     *
     * @return Una lista de los 10 jugadores con mayor experiencia.
     * @throws SQLException Si ocurre un error al obtener los jugadores.
     */
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

    /**
     * Actualiza la información de un jugador en la base de datos.
     *
     * @param jugador El jugador con la información actualizada.
     * @throws SQLException Si ocurre un error al actualizar el jugador.
     */
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

    /**
     * Elimina un jugador de la base de datos utilizando su nombre de usuario.
     *
     * @param NickName El nombre de usuario del jugador a eliminar.
     * @return true si el jugador fue eliminado con éxito, false si no se encontró.
     * @throws SQLException Si ocurre un error al eliminar el jugador.
     */
    @Override
    public boolean deleteJugador(String NickName) throws SQLException {
        Jugador eliminado = getJugador(NickName);
        if (eliminado == null) {
            return true;
        } else {
            String sql = "DELETE FROM jugadores WHERE nickname = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, NickName);
                stmt.executeUpdate();
                return false;
            }
        }
    }

    /**
     * Obtiene un jugador de la base de datos utilizando su nombre de usuario.
     *
     * @param NickName El nombre de usuario del jugador a obtener.
     * @return El jugador con el nombre de usuario especificado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al obtener el jugador.
     */
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











