package MenuBack;

import auxiliar.Libreriaaux;
import daos.JugadorDAO;
import modelos.Jugador;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author eugeniolorentecristobal
 */
public class JugadorMenu {
    private JugadorDAO jugadorDAO;

    /**
     * Constructor que inicializa el menú con una implementación de {@link JugadorDAO}.
     *
     * @param jugadorDAO el objeto {@link JugadorDAO} utilizado para interactuar con los datos de los jugadores.
     */
    public JugadorMenu(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }

    /**
     * Muestra el menú de opciones al usuario y maneja las interacciones de acuerdo a la elección.
     *
     * @throws SQLException si ocurre un error durante las operaciones de base de datos.
     */
    public void mostrarMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n--- Menú de Gestión de Jugadores ---");
            System.out.println("1. Agregar un Jugador");
            System.out.println("2. Actualizar Jugador");
            System.out.println("3. Eliminar Jugador");
            System.out.println("4. Mostrar Jugador por ID");
            System.out.println("5. Listar Top 10 Jugadores");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarJugador(scanner);
                    break;
                case 2:
                    actualizarJugador(scanner);
                    break;
                case 3:
                    eliminarJugador(scanner);
                    break;
                case 4:
                    mostrarJugador(scanner);
                    break;
                case 5:
                    listarTop10Jugadores();
                    break;
                case 6:
                    System.out.println("Saliendo del menú.");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    /**
     * Solicita al usuario la información necesaria para agregar un nuevo jugador
     * y lo registra en la base de datos.
     *
     * @param scanner el objeto {@link Scanner} utilizado para capturar la entrada del usuario.
     */
    private void agregarJugador(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer
        System.out.println("\n--- Agregar un Jugador ---");
        System.out.print("Nickname del jugador: ");
        String nick = scanner.nextLine();
        System.out.print("Nivel de experiencia: ");
        int experience = Libreriaaux.compruebaNumero();
        System.out.print("Nivel de vida: ");
        int lifeLevel = Libreriaaux.compruebaNumero();
        System.out.print("Cantidad de monedas: ");
        int coins = Libreriaaux.compruebaNumero();

        Jugador nuevoJugador = new Jugador(0, nick, experience, lifeLevel, coins);

        try {
            jugadorDAO.addJugador(nuevoJugador);
            System.out.println("Jugador agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el jugador: " + e.getMessage());
        }
    }

    /**
     * Solicita al usuario los datos necesarios para actualizar un jugador existente.
     *
     * @param scanner el objeto {@link Scanner} utilizado para capturar la entrada del usuario.
     */
    private void actualizarJugador(Scanner scanner) {
        System.out.println("\n--- Actualizar Jugador ---");
        System.out.print("NickName del jugador a actualizar: ");
        scanner.nextLine();
        String NickName = scanner.nextLine();

        try {
            Jugador jugadorExistente = jugadorDAO.getJugador(NickName);
            if (jugadorExistente != null) {
                System.out.print("Nuevo nickname (actual: " + jugadorExistente.getNick() + "): ");
                String nuevoNick = scanner.nextLine();
                System.out.print("Nuevo nivel de experiencia (actual: " + jugadorExistente.getExperience() + "): ");
                int nuevaExperience = Libreriaaux.compruebaNumero();
                System.out.print("Nuevo nivel de vida (actual: " + jugadorExistente.getLifeLevel() + "): ");
                int nuevoLifeLevel = Libreriaaux.compruebaNumero();
                System.out.print("Nueva cantidad de monedas (actual: " + jugadorExistente.getCoins() + "): ");
                int nuevasCoins = Libreriaaux.compruebaNumero();

                jugadorExistente.setNick(nuevoNick);
                jugadorExistente.setExperience(nuevaExperience);
                jugadorExistente.setLifeLevel(nuevoLifeLevel);
                jugadorExistente.setCoins(nuevasCoins);

                jugadorDAO.updateJugador(jugadorExistente);
                System.out.println("[INFO] Jugador actualizado exitosamente.");
            } else {
                System.out.println("[ERROR] No se encontró un jugador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Jugador no encontrado");
        }
    }

    /**
     * Solicita al usuario el ID de un jugador y lo elimina de la base de datos.
     *
     * @param scanner el objeto {@link Scanner} utilizado para capturar la entrada del usuario.
     * @throws SQLException si ocurre un error durante la operación de eliminación.
     */
    private void eliminarJugador(Scanner scanner) throws SQLException {
        System.out.println("\n--- Eliminar Jugador ---");
        System.out.print("Nombre del jugador a eliminar: ");
        scanner.nextLine();
        String NickName = scanner.nextLine();

        if (jugadorDAO.getJugador(NickName) == null) {
            System.out.println("Jugador no encontrado");
        } else {
            try {
                if (jugadorDAO.deleteJugador(NickName)) {
                    System.out.println("Jugador eliminado exitosamente.");
                } else {
                    System.out.println("El jugador no existe, no se ha podido eliminar");
                }
            } catch (SQLException e) {
                System.out.println("No se ha podido eliminar el jugador");
            }
        }
    }

    /**
     * Solicita al usuario el ID de un jugador y muestra su información en la consola.
     *
     * @param scanner el objeto {@link Scanner} utilizado para capturar la entrada del usuario.
     */
    private void mostrarJugador(Scanner scanner) {
        System.out.println("\n--- Mostrar Jugador ---");
        System.out.print("Nombre del jugador a mostrar: ");
        scanner.nextLine();
        String NickName = scanner.nextLine();

        try {
            Jugador jugador = jugadorDAO.getJugador(NickName);
            if (jugador != null) {
                System.out.println(jugador);
            } else {
                System.out.println("No se encontró un jugador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("No se ha encontrado al jugador");
        }
    }

    /**
     * Recupera y muestra en la consola el top 10 de jugadores ordenados por su rendimiento.
     */
    private void listarTop10Jugadores() {
        System.out.println("\n--- Listar Top 10 Jugadores ---");

        try {
            List<Jugador> top10Jugadores = jugadorDAO.getTop10Jugadores();
            if (!top10Jugadores.isEmpty()) {
                for (Jugador jugador : top10Jugadores) {
                    System.out.println(jugador);
                }
            } else {
                System.out.println("No hay jugadores en el top 10.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los jugadores: " + e.getMessage());
        }
    }
}












