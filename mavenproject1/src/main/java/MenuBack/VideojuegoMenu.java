
package MenuBack;

/**
 *
 * @author eugeniolorentecristobal
 */
import daos.DAOFactory;
import daos.JugadorDAO;
import daos.SQLiteVideojuegoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import daos.VideojuegoDAO;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import modelos.Videojuego;

public class VideojuegoMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static VideojuegoDAO videojuegoDAO;

    public VideojuegoMenu(VideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    public static void mostrarMenuGestionVideoJuegos() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Gestión de Videojuegos ---");
            System.out.println("1. Alta de Videojuego");
            System.out.println("2. Baja de Videojuego");
            System.out.println("3. Modificación de Videojuego");
            System.out.println("4. Consulta de Videojuegos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> altaVideojuego();
                case 2 -> bajaVideojuego();
                case 3 -> modificarVideojuego();
                case 4 -> listarVideojuegos();
                case 5 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void altaVideojuego() {
        System.out.println("\n--- Alta de Videojuego ---");
        System.out.print("Ingrese el ID del juego: ");
        int gameId = scanner.nextInt();
        System.out.print("Ingrese el ISBN: ");
        int isbn = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el título del videojuego: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese la cantidad de jugadores: ");
        int playerCount = scanner.nextInt();
        System.out.print("Ingrese el total de sesiones: ");
        int totalSessions = scanner.nextInt();
        Date lastSession = Date.valueOf(LocalDate.now()) /*Date.from(Instant.now())*/;// Inicializa con la fecha actual
        
        Videojuego videojuego = new Videojuego(gameId, isbn, title, playerCount, totalSessions, lastSession);

        try {
            videojuegoDAO.addVideojuego(videojuego);
            System.out.println("Videojuego agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el videojuego: " + e.getMessage());
        }
    }

    private static void bajaVideojuego() {
        System.out.println("\n--- Baja de Videojuego ---");
        System.out.print("Ingrese el ID del videojuego a eliminar: ");
        int gameId = scanner.nextInt();

        try {
            videojuegoDAO.deleteVideojuego(gameId);
            System.out.println("Videojuego eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el videojuego: " + e.getMessage());
        }
    }

    private static void modificarVideojuego() {
        System.out.println("\n--- Modificación de Videojuego ---");
        System.out.print("Ingrese el ID del videojuego a modificar: ");
        int gameId = scanner.nextInt();

        try {
            Videojuego videojuego = videojuegoDAO.getVideojuego(gameId);
            if (videojuego == null) {
                System.out.println("No se encontró el videojuego con ID: " + gameId);
                return;
            }

            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese el nuevo ISBN (actual: " + videojuego.getIsbn() + "): ");
            videojuego.setIsbn(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Ingrese el nuevo título (actual: " + videojuego.getTitle() + "): ");
            videojuego.setTitle(scanner.nextLine());
            System.out.print("Ingrese la nueva cantidad de jugadores (actual: " + videojuego.getPlayer_count() + "): ");
            videojuego.setPlayer_count(scanner.nextInt());
            System.out.print("Ingrese el nuevo total de sesiones (actual: " + videojuego.getTotal_sessions() + "): ");
            videojuego.setTotal_sessions(scanner.nextInt());
           // videojuego.setLast_session((java.sql.Date) new Date());

            videojuegoDAO.updateVideojuego(videojuego);
            System.out.println("Videojuego modificado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el videojuego: " + e.getMessage());
        }
    }
    
    private static void listarVideojuegos() {
        System.out.println("\n--- Listar Todos los Videojuegos ---");

        try {
            List<Videojuego> videojuegos = videojuegoDAO.getAllVideojuegos();
            if (!videojuegos.isEmpty()) {
                for (Videojuego videojuego : videojuegos) {
                    System.out.println(videojuego);
                }
            } else {
                System.out.println("No hay videojuegos registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los videojuegos: " + e.getMessage());
        }
    }
}


