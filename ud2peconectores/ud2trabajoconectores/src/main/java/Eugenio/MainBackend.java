
package Eugenio;

import java.sql.SQLException;
import java.util.*;

public class MainBackend {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        System.out.println("Gestión de Videojuegos y Jugadores:");
        System.out.println("1. Gestionar Videojuegos");
        System.out.println("2. Gestionar Jugadores");
        System.out.println("3. Salir");

        int option = scanner.nextInt();

        switch(option) {
            case 1:
                // Gestión de Videojuegos
                System.out.println("Introduzca el ID del juego:");
                int gameId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Introduzca el título del juego:");
                String title = scanner.nextLine();
                // Agregar juego
                VideojuegoDAO videojuegoDAO = new VideojuegoDAO(DatabaseConnection.getConnection());
                videojuegoDAO.addGame(gameId, title);
                break;
            case 2:
                // Gestión de Jugadores
                System.out.println("Introduzca el ID del jugador:");
                int playerId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduzca el nombre del jugador:");
                String nickName = scanner.nextLine();
                // Agregar jugador
                JugadorDAO jugadorDAO = new JugadorDAO(DatabaseConnection.getConnection());
                jugadorDAO.addPlayer(playerId, nickName, 0, 100, 50); // Ejemplo de valores predeterminados
                break;
            case 3:
                System.out.println("Saliendo...");
                break;
        }
    }
}

