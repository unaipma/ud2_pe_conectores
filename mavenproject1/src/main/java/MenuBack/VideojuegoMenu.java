
package MenuBack;

/**
 *
 * @author eugeniolorentecristobal
 */
import auxiliar.Libreriaaux;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import daos.VideojuegoDAO;
import java.sql.Date;
import java.time.LocalDate;
import modelos.Videojuego;

public class VideojuegoMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static VideojuegoDAO videojuegoDAO;

    /**
     * Constructor que inicializa el menú con un objeto de acceso a datos de videojuegos.
     *
     * @param videojuegoDAO el DAO para interactuar con la base de datos de videojuegos.
     */
    public VideojuegoMenu(VideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    /**
     * Muestra el menú de gestión de videojuegos y procesa la entrada del usuario.
     */
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
            scanner.nextLine();

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

    /**
     * Solicita datos al usuario para dar de alta un nuevo videojuego en el sistema.
     */
    private static void altaVideojuego() {
        System.out.println("\n--- Alta de Videojuego ---");

        System.out.print("Ingrese el ISBN: ");
        int isbn = Libreriaaux.compruebaNumero();

        System.out.print("Ingrese el título del videojuego: ");
        String title = scanner.nextLine();
        int playerCount = 0;
        int totalSessions = 0;
        Date lastSession = Date.valueOf(LocalDate.now());
        Videojuego videojuego = new Videojuego(0, isbn, title, playerCount, totalSessions, lastSession);

        try {
            videojuegoDAO.addVideojuego(videojuego);
            System.out.println("Videojuego agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el videojuego: " + e.getMessage());
        }
    }

    /**
     * Solicita al usuario el ID de un videojuego para eliminarlo del sistema.
     */
    private static void bajaVideojuego() {
        System.out.println("\n--- Baja de Videojuego ---");
        System.out.print("Ingrese el ID del videojuego a eliminar: ");
        int gameId = Libreriaaux.compruebaNumero();

        try {
            if (videojuegoDAO.deleteVideojuego(gameId)) {
                System.out.println("Se ha eliminado correctamente");
            } else {
                System.out.println("El videojuego no existe, no se ha podido eliminar");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el videojuego: " + e.getMessage());
        }
    }

    /**
     * Solicita datos al usuario para modificar un videojuego existente en el sistema.
     */
    private static void modificarVideojuego() {
        System.out.println("\n--- Modificación de Videojuego ---");
        System.out.print("Ingrese el ID del videojuego a modificar: ");
        int gameId = Libreriaaux.compruebaNumero();

        try {
            Videojuego videojuego = videojuegoDAO.getVideojuego(gameId);
            if (videojuego == null) {
                System.out.println("No se encontró el videojuego con ID: " + gameId);
                return;
            }

            scanner.nextLine();
            System.out.print("Ingrese el nuevo ISBN (actual: " + videojuego.getIsbn() + "): ");
            videojuego.setIsbn(Libreriaaux.compruebaNumero());
            scanner.nextLine();
            System.out.print("Ingrese el nuevo título (actual: " + videojuego.getTitle() + "): ");
            videojuego.setTitle(scanner.nextLine());
            System.out.print("Ingrese la nueva cantidad de jugadores (actual: " + videojuego.getPlayer_count() + "): ");
            videojuego.setPlayer_count(Libreriaaux.compruebaNumero());
            System.out.print("Ingrese el nuevo total de sesiones (actual: " + videojuego.getTotal_sessions() + "): ");
            videojuego.setTotal_sessions(Libreriaaux.compruebaNumero());
            videojuego.setLast_session(Date.valueOf(LocalDate.now()));
            videojuegoDAO.updateVideojuego(videojuego);
            System.out.println("Videojuego modificado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el videojuego: " + e.getMessage());
        }
    }

    /**
     * Lista todos los videojuegos registrados en el sistema.
     */
    private static void listarVideojuegos() {
        System.out.println("\n--- Listar Todos los Videojuegos ---");

        try {
            List<Videojuego> videojuegos = videojuegoDAO.getAllVideojuegos();
            if (!videojuegos.isEmpty()) {
                for (Videojuego videojuego : videojuegos) {
                    System.out.println(videojuego.toString());
                }
            } else {
                System.out.println("No hay videojuegos registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los videojuegos: " + e.getMessage());
        }
    }
}








