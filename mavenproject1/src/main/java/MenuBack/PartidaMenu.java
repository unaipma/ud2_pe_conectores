package MenuBack;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import daos.PartidaDAO;
import java.time.LocalDate;
import modelos.Partida;
/**
 *
 * @author eugeniolorentecristobal
 */
public class PartidaMenu {
    private PartidaDAO partidaDAO;

    public PartidaMenu(PartidaDAO partidaDAO) {
        this.partidaDAO = partidaDAO;
    }

    public void mostrarMenuGestionarPartidas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("----- Menú Gestión de Partidas -----");
            System.out.println("1. Agregar Partida");
            System.out.println("2. Listar Partidas de un Jugador");
            System.out.println("3. Actualizar Partida");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarPartida(scanner);
                    break;
                case 2:
                    listarPartidasDeJugador(scanner);
                    break;
                case 3:
                    actualizarPartida(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del menú de gestión de partidas...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void agregarPartida(Scanner scanner) {
        try {
            System.out.println("Ingrese los detalles de la nueva partida:");
            System.out.print("ID de la partida: ");
            int idPartida = scanner.nextInt();
            System.out.print("ID del juego: ");
            int idJuego = scanner.nextInt();
            System.out.print("ID del jugador: ");
            int idJugador = scanner.nextInt();
            System.out.print("Monedas: ");
            int monedas = scanner.nextInt();
            System.out.print("Experiencia: ");
            int experiencia = scanner.nextInt();
            System.out.print("Nivel: ");
            int nivel = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Última conexión (YYYY-MM-DD): ");
            Date ultimaConexion = Date.valueOf(LocalDate.now()); // Inicializa con la fecha actual

            Partida nuevaPartida = new Partida(idPartida, idJuego, idJugador, monedas, experiencia, nivel,ultimaConexion);
            partidaDAO.addPartida(nuevaPartida);
            System.out.println("Partida agregada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar la partida: " + e.getMessage());
        }
    }

    private void listarPartidasDeJugador(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del jugador para listar sus partidas: ");
            int idJugador = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            List<Partida> partidas = partidaDAO.getPartidasByPlayer(idJugador);

            if (partidas.isEmpty()) {
                System.out.println("No se encontraron partidas para el jugador con ID " + idJugador);
            } else {
                System.out.println("Partidas del jugador con ID " + idJugador + ":");
                for (Partida partida : partidas) {
                    System.out.println(partida);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar partidas: " + e.getMessage());
        }
    }

    private void actualizarPartida(Scanner scanner) {
        try {
            System.out.println("Ingrese los detalles de la partida a actualizar:");
            System.out.print("ID de la partida: ");
            int idPartida = scanner.nextInt();
            System.out.print("ID del juego: ");
            int idJuego = scanner.nextInt();
            System.out.print("ID del jugador: ");
            int idJugador = scanner.nextInt();
            System.out.print("Monedas: ");
            int monedas = scanner.nextInt();
            System.out.print("Experiencia: ");
            int experiencia = scanner.nextInt();
            System.out.print("Nivel: ");
            int nivel = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Última conexión (YYYY-MM-DD): ");
            Date ultimaConexion = Date.valueOf(LocalDate.now()); // Inicializa con la fecha actual

            Partida partidaActualizada = new Partida(idPartida, idJuego, idJugador, monedas, experiencia, nivel,ultimaConexion);
            partidaDAO.updatePartida(partidaActualizada);
            System.out.println("Partida actualizada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la partida: " + e.getMessage());
        }
    }
}

