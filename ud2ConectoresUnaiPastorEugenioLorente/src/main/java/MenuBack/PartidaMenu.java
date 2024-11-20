package MenuBack;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import auxiliar.Libreriaaux;
import daos.PartidaDAO;
import java.time.LocalDate;
import modelos.Partida;
/**
 *
 * @author eugeniolorentecristobal
 */
public class PartidaMenu {
    private PartidaDAO partidaDAO;

    /**
     * Constructor que inicializa el menú con un objeto de acceso a datos de partidas.
     *
     * @param partidaDAO el DAO para interactuar con la base de datos de partidas.
     */
    public PartidaMenu(PartidaDAO partidaDAO) {
        this.partidaDAO = partidaDAO;
    }

    /**
     * Muestra el menú de opciones para gestionar partidas y procesa la entrada del usuario.
     */
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
            scanner.nextLine();

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

    /**
     * Solicita datos al usuario para agregar una nueva partida a la base de datos.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
    private void agregarPartida(Scanner scanner) {
        try {
            System.out.println("Ingrese los detalles de la nueva partida:");
            System.out.print("ID del juego: ");
            int idJuego = Libreriaaux.compruebaNumero();
            System.out.print("ID del jugador: ");
            int idJugador = Libreriaaux.compruebaNumero();
            System.out.print("Monedas: ");
            int monedas = Libreriaaux.compruebaNumero();
            System.out.print("Experiencia: ");
            int experiencia = Libreriaaux.compruebaNumero();
            System.out.print("Nivel: ");
            int nivel = Libreriaaux.compruebaNumero();
            scanner.nextLine();

            Date ultimaConexion = Date.valueOf(LocalDate.now());

            Partida nuevaPartida = new Partida(idJuego, idJugador, monedas, experiencia, nivel, ultimaConexion);
            partidaDAO.addPartida(nuevaPartida);
            System.out.println("Partida agregada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar la partida" + e.getMessage());
        }
    }

    /**
     * Solicita el ID de un jugador y muestra una lista de sus partidas.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
    private void listarPartidasDeJugador(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del jugador para listar sus partidas: ");
            int idJugador = Libreriaaux.compruebaNumero();
            scanner.nextLine();
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

    /**
     * Solicita datos al usuario para actualizar una partida existente en la base de datos.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario.
     */
    private void actualizarPartida(Scanner scanner) {
        try {
            System.out.println("Ingrese los detalles de la partida a actualizar:");
            System.out.print("ID del juego: ");
            int idJuego = Libreriaaux.compruebaNumero();
            System.out.print("ID del jugador: ");
            int idJugador = Libreriaaux.compruebaNumero();
            System.out.print("Monedas: ");
            int monedas = Libreriaaux.compruebaNumero();
            System.out.print("Experiencia: ");
            int experiencia = Libreriaaux.compruebaNumero();
            System.out.print("Nivel: ");
            int nivel = Libreriaaux.compruebaNumero();
            scanner.nextLine();
            System.out.print("Última conexión (YYYY-MM-DD): ");

            Date ultimaConexion = Date.valueOf(scanner.nextLine());

            Partida partidaActualizada = new Partida(idJuego, idJugador, monedas, experiencia, nivel, ultimaConexion);
            partidaDAO.updatePartida(partidaActualizada);
            System.out.println("Partida actualizada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la partida: " + e.getMessage());
        }
    }
}








