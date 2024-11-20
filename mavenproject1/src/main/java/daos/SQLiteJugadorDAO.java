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

    private static final String URL = "jdbc:sqlite:C:\\Users\\unaip\\OneDrive\\Documentos\\github\\ud2_pe_conectores\\ud2conectores.db"; // Cambia este path a tu base de datos SQLite

    /**
     * Establece la conexión con la base de datos SQLite.
     *
     * @return La conexión a la base de datos SQLite.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Agrega un nuevo jugador a la base de datos.
     *
     * @param jugador El jugador que se va a agregar.
     * @throws SQLException Si ocurre un error al agregar el jugador.
     */
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

    /**
     * Actualiza los datos de un jugador existente en la base de datos.
     *
     * @param jugador El jugador con los nuevos datos.
     * @throws SQLException Si ocurre un error al actualizar los datos del jugador.
     */
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

    /**
     * Elimina un jugador de la base de datos por su nombre de usuario (nick_name).
     *
     * @param NickName El nombre de usuario del jugador que se va a eliminar.
     * @return `true` si el jugador fue eliminado correctamente, `false` si no se encuentra el jugador.
     * @throws SQLException Si ocurre un error al eliminar el jugador.
     */
    @Override
    public boolean deleteJugador(String NickName) throws SQLException {
        String sql = "DELETE FROM Jugador WHERE nick_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, NickName);
            stmt.executeUpdate();
        }
        return true; // Devuelve true asumiendo que el jugador fue eliminado correctamente
    }

    /**
     * Obtiene un jugador de la base de datos por su nombre de usuario (nick_name).
     *
     * @param NickName El nombre de usuario del jugador que se va a obtener.
     * @return El jugador con el nombre de usuario especificado, o `null` si no se encuentra.
     * @throws SQLException Si ocurre un error al obtener el jugador.
     */
    @Override
    public Jugador getJugador(String NickName) throws SQLException {
        String sql = "SELECT * FROM Jugador WHERE nick_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, NickName);
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

    /**
     * Obtiene los 10 jugadores con más experiencia en la base de datos.
     *
     * @return Una lista de los 10 jugadores con más experiencia.
     * @throws SQLException Si ocurre un error al obtener los jugadores.
     */
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