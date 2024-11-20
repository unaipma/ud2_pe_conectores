
package daos;

import Conexiones.Sqlliteconexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.ConfjugadorLite;
import modelos.Jugador;

/**
 *
 * @author eugeniolorentecristobal
 */
public class SQLiteConfiguracion {

    private Sqlliteconexion sqlliteconexion;

    private Connection connect() throws SQLException {
        return sqlliteconexion.getConnection();
    }

    public void saveConfig(ConfjugadorLite config, Jugador jugador) throws SQLException {
        String sql = "INSERT INTO player_settings (player_id,sound_enabled, resolution,language) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jugador.getId());
            stmt.setBoolean(2, config.isSound_enabled());
            stmt.setString(3, config.getResolucion());
            stmt.setString(4, config.getIdioma());

            stmt.executeUpdate();
        }
    }

    public void updateConfig(ConfjugadorLite config, Jugador jugador) throws SQLException {
        String sql = "UPDATE player_settings SET  player_id = ?, sound_enabled = ? , resolution = ? , language = ? WHERE player_id = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, jugador.getId());
            stmt.setBoolean(2, config.isSound_enabled());
            stmt.setString(3, config.getResolucion());
            stmt.setString(4, config.getIdioma());
            stmt.setInt(5, jugador.getId());

            stmt.executeUpdate();
        }
    }

    public ConfjugadorLite getConfig(Jugador jugador) throws SQLException {

        String sql = "SELECT * FROM player_settings WHERE player_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jugador.getId());
            ResultSet rs = stmt.executeQuery();

            ConfjugadorLite conf = new ConfjugadorLite(
                    rs.getInt("id"),
                    rs.getInt("player_id"),
                    rs.getString("resolution"),
                    rs.getString("language"),
                    rs.getBoolean("sound_enabled")); // Este campo se convierte automáticamente a java.util.Date

            return conf;
        }

    }


}
