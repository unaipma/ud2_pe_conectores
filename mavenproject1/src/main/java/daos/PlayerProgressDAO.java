/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author eugeniolorentecristobal
 */
import java.sql.SQLException;
import java.util.List;
import modelos.PlayerProgress;

public interface PlayerProgressDAO {
    void addPlayerProgress(PlayerProgress progress) throws SQLException;
    List<PlayerProgress> getPlayerProgressById(int playerId) throws SQLException;
    void updatePlayerProgress(PlayerProgress progress) throws SQLException;
    List<PlayerProgress> getAllPlayerProgress() throws SQLException;
    void deletePlayerProgress(int playerId) throws SQLException;
}

