
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

    /**
     * Constructor de la clase que establece la conexión con la base de datos SQLite.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public SQLitePlayerProgressDAO() throws SQLException {
        connect();
    }

    /**
     * Establece la conexión con la base de datos SQLite utilizando la clase Sqlliteconexion.
     *
     * @return La conexión a la base de datos SQLite.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    private Connection connect() throws SQLException {
        return sqlliteconexion.getConnection();
    }

    /**
     * Agrega un nuevo progreso de jugador a la base de datos.
     *
     * @param progress El progreso del jugador que se va a agregar.
     * @throws SQLException Si ocurre un error al agregar el progreso.
     */
    @Override
    public void addPlayerProgress(PlayerProgress progress) throws SQLException {
        String sql = "INSERT INTO player_progress (player_id, nick_name, experience, life_level, coins, session_count, last_login, partidaId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, progress.getPlayerId());
            stmt.setString(2, progress.getNickName());
            stmt.setInt(3, progress.getExperience());
            stmt.setInt(4, progress.getLifeLevel());
            stmt.setInt(5, progress.getCoins());
            stmt.setInt(6, progress.getSessionCount());
            stmt.setDate(7, progress.getLastLogin()); // Guardar como texto en formato "YYYY-MM-DD HH:MM:SS"
            stmt.setInt(8, progress.getPartidaId());

            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene el progreso de un jugador por su ID.
     *
     * @return Una lista de objetos PlayerProgress que representan el progreso del jugador.
     * @throws SQLException Si ocurre un error al obtener el progreso.
     */
    @Override
    public List<PlayerProgress> getPlayerProgressById(String playerNick) throws SQLException {
        List<PlayerProgress> progressList = new ArrayList<>();
        String sql = "SELECT * FROM player_progress WHERE nick_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, playerNick);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayerProgress progress = new PlayerProgress(
                        rs.getInt("player_id"),
                        rs.getString("nick_name"),
                        rs.getInt("experience"),
                        rs.getInt("life_level"),
                        rs.getInt("coins"),
                        rs.getDate("last_login"),
                        rs.getInt("partidaId")
                );
                progressList.add(progress);
            }
        }
        return progressList;
    }

    // Obtener progreso por playerId
    public PlayerProgress getPlayerProgressById2(int partidaId, String nick) throws SQLException {
        String sql = "SELECT * FROM player_progress WHERE partidaId = ? AND nick_name = ?";
        try (Connection conn = connect();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, partidaId);
            stmt.setString(2, nick);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PlayerProgress(
                            rs.getInt("player_id"),
                            rs.getString("nick_name"),
                            rs.getInt("experience"),
                            rs.getInt("life_level"),
                            rs.getInt("coins"),
                            rs.getDate("last_login"),
                            rs.getInt("partidaId")
                    );
                }
            }
        }
        return null;
    }


    /**
     * Actualiza el progreso de un jugador existente en la base de datos.
     *
     * @param progress El progreso actualizado del jugador.
     * @throws SQLException Si ocurre un error al actualizar el progreso.
     */
    @Override
    public void updatePlayerProgress(PlayerProgress progress) throws SQLException {
        String sql = "UPDATE player_progress SET nick_name = ?, experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ?, partidaId = ? WHERE player_id = ? AND partidaId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, progress.getNickName());
            stmt.setInt(2, progress.getExperience());
            stmt.setInt(3, progress.getLifeLevel());
            stmt.setInt(4, progress.getCoins());
            stmt.setInt(5, progress.getSessionCount());
            stmt.setDate(6, progress.getLastLogin());
            stmt.setInt(7, progress.getPartidaId());
            stmt.setInt(8, progress.getPlayerId());
            stmt.setInt(9, progress.getPartidaId());

            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene todo el progreso de un jugador por su nombre de usuario (nick_name).
     *
     * @param jugador El jugador cuyo progreso se va a obtener.
     * @return Una lista de objetos PlayerProgress que representan el progreso del jugador.
     * @throws SQLException Si ocurre un error al obtener el progreso.
     */
    @Override
    public List<PlayerProgress> getAllPlayerProgress(Jugador jugador) throws SQLException {
        List<PlayerProgress> progressList = new ArrayList<>();
        String sql = "SELECT * FROM player_progress WHERE nick_name = \"" + jugador.getNick() + "\"";

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
                        rs.getDate("last_login")
                );
                progressList.add(progress);
            }
        }
        return progressList;
    }

    /**
     * Elimina el progreso de un jugador de la base de datos por su ID.
     *
     * @param playerId El ID del jugador cuyo progreso se va a eliminar.
     * @throws SQLException Si ocurre un error al eliminar el progreso.
     */
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