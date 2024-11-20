package MenuBack;

import Conexiones.ConexionSelector;
import auxiliar.Libreriaaux;
import daos.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author eugeniolorentecristobal
 */
public class MenuBackend {

    /**
     * Método principal que ejecuta el menú backend.
     * Solicita al usuario que seleccione la base de datos y proporciona opciones
     * para gestionar videojuegos, jugadores y partidas.
     *
     * @param args argumentos de la línea de comandos (no se utilizan en este programa).
     * @throws SQLException si ocurre un error al interactuar con la base de datos.
     */
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String tipoBD;

        System.out.println("Bienvenido a la Aplicación de Backend");

        do {
            System.out.println("Indica qué base de datos quieres utilizar: 1. MySQL 2. PostgreSQL");
            int n = Libreriaaux.compruebaNumero();

            switch (n) {
                case 1:
                    tipoBD = "MySQL";
                    break;
                case 2:
                    tipoBD = "PostgreSQL";
                    break;
                default:
                    System.out.println("Opción no válida. Por defecto se seleccionará MySQL.");
                    tipoBD = "MySQL";
                    break;
            }

            Connection conexionSelector = ConexionSelector.obtenerConexion(tipoBD);

            if (conexionSelector != null) {
                System.out.println("Conexión establecida exitosamente.");
                break;
            } else {
                System.out.println("No se pudo establecer la conexión. Intenta nuevamente.");
            }
        } while (true);

        DAOFactory factory = new DAOFactory();

        while (true) {
            System.out.println("1. Gestionar Videojuegos");
            System.out.println("2. Gestionar Jugadores");
            System.out.println("3. Gestionar Partidas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        VideojuegoMenu videojuegoMenu = new VideojuegoMenu(factory.getVideojuegoDAO(tipoBD));
                        videojuegoMenu.mostrarMenuGestionVideoJuegos();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    try {
                        JugadorMenu jugadorMenu = new JugadorMenu(factory.getJugadorDAO(tipoBD));
                        jugadorMenu.mostrarMenu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        PartidaMenu partidaMenu = new PartidaMenu(factory.getPartidaDAO(tipoBD));
                        partidaMenu.mostrarMenuGestionarPartidas();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}