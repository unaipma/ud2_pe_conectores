package MenuBack;

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

    public JugadorMenu(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }

    public void mostrarMenu() {
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

    private void agregarJugador(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer
        System.out.println("\n--- Agregar un Jugador ---");
        System.out.print("ID del jugador: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer
        System.out.print("Nickname del jugador: ");
        String nick = scanner.nextLine();
        System.out.print("Nivel de experiencia: ");
        int experience = scanner.nextInt();
        System.out.print("Nivel de vida: ");
        int lifeLevel = scanner.nextInt();
        System.out.print("Cantidad de monedas: ");
        int coins = scanner.nextInt();

        Jugador nuevoJugador = new Jugador(id, nick, experience, lifeLevel, coins);

        try {
            jugadorDAO.addJugador(nuevoJugador);
            System.out.println("Jugador agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el jugador: " + e.getMessage());
        }
    }

    private void actualizarJugador(Scanner scanner) {
        System.out.println("\n--- Actualizar Jugador ---");
        System.out.print("ID del jugador a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        try {
            Jugador jugadorExistente = jugadorDAO.getJugador(id);
            if (jugadorExistente != null) {
                System.out.print("Nuevo nickname (actual: " + jugadorExistente.getNick() + "): ");
                String nuevoNick = scanner.nextLine();
                System.out.print("Nuevo nivel de experiencia (actual: " + jugadorExistente.getExperience() + "): ");
                int nuevaExperience = scanner.nextInt();
                System.out.print("Nuevo nivel de vida (actual: " + jugadorExistente.getLifeLevel() + "): ");
                int nuevoLifeLevel = scanner.nextInt();
                System.out.print("Nueva cantidad de monedas (actual: " + jugadorExistente.getCoins() + "): ");
                int nuevasCoins = scanner.nextInt();

                jugadorExistente.setNick(nuevoNick);
                jugadorExistente.setExperience(nuevaExperience);
                jugadorExistente.setLifeLevel(nuevoLifeLevel);
                jugadorExistente.setCoins(nuevasCoins);

                jugadorDAO.updateJugador(jugadorExistente);
                System.out.println("Jugador actualizado exitosamente.");
            } else {
                System.out.println("No se encontró un jugador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el jugador: " + e.getMessage());
        }
    }

    private void eliminarJugador(Scanner scanner) {
        System.out.println("\n--- Eliminar Jugador ---");
        System.out.print("ID del jugador a eliminar: ");
        int id = scanner.nextInt();

        try {
            jugadorDAO.deleteJugador(id);
            System.out.println("Jugador eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el jugador: " + e.getMessage());
        }
    }

    private void mostrarJugador(Scanner scanner) {
        System.out.println("\n--- Mostrar Jugador ---");
        System.out.print("ID del jugador a mostrar: ");
        int id = scanner.nextInt();

        try {
            Jugador jugador = jugadorDAO.getJugador(id);
            if (jugador != null) {
                System.out.println(jugador);
            } else {
                System.out.println("No se encontró un jugador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el jugador: " + e.getMessage());
        }
    }

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

