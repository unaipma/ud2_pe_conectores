/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */

import modelos.Partida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLitePartidaDAO implements PartidaDAO {

    private static final String URL = "jdbc:sqlite:path_to_your_sqlite_db_file.db"; // Cambia este path a tu base de datos SQLite

    public SQLitePartidaDAO() throws SQLException {
        connect();
    }

    
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void addPartida(Partida partida) throws SQLException {
        String sql = "INSERT INTO Partida (idpartida, idjuego, idjugador, monedas, exp, nivel, ultimaconexion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, partida.getIdpartida());
            stmt.setInt(2, partida.getIdjuego());
            stmt.setInt(3, partida.getIdjugador());
            stmt.setInt(4, partida.getMonedas());
            stmt.setInt(5, partida.getExp());
            stmt.setInt(6, partida.getNivel());
            stmt.setDate(7, new java.sql.Date(partida.getUltimaconexion().getTime())); // Convertir Date a java.sql.Date
            
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Partida> getPartidasByPlayer(int playerId) throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        String sql = "SELECT * FROM Partida WHERE idjugador = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Partida partida = new Partida(
                    rs.getInt("idpartida"),
                    rs.getInt("idjuego"),
                    rs.getInt("idjugador"),
                    rs.getInt("monedas"),
                    rs.getInt("exp"),
                    rs.getInt("nivel"),
                    rs.getDate("ultimaconexion") // Este campo se convierte automáticamente a java.util.Date
                );
                partidas.add(partida);
            }
        }
        return partidas;
    }

    @Override
    public void updatePartida(Partida partida) throws SQLException {
        String sql = "UPDATE Partida SET monedas = ?, exp = ?, nivel = ?, ultimaconexion = ? WHERE idpartida = ?";
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, partida.getMonedas());
            stmt.setInt(2, partida.getExp());
            stmt.setInt(3, partida.getNivel());
            stmt.setDate(4, new java.sql.Date(partida.getUltimaconexion().getTime())); // Convertir Date a java.sql.Date
            stmt.setInt(5, partida.getIdpartida());
            
            stmt.executeUpdate();
        }
    }
}
