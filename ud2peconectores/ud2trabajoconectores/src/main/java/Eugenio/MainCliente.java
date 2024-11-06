
package Eugenio;

import java.sql.*;

public class MainCliente {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:player.db")) {
            JugadorClienteDAO jugadorClienteDAO = new JugadorClienteDAO(connection);

            // Guardar progreso del jugador
            jugadorClienteDAO.saveProgress(1, 150, 90, 600);

            // Obtener progreso del jugador
            jugadorClienteDAO.getProgress(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
