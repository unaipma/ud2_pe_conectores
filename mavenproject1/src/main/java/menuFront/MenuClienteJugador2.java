/**
 *
 * @author eugeniolorentecristobal
 */
package menuFront;

import auxiliar.Json;
import auxiliar.JuegoConf;
import daos.DAOFactory;
import daos.JugadorDAO;
import daos.PartidaDAO;
import daos.SQLitePlayerProgressDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelos.Jugador;
import modelos.Partida;

public class MenuClienteJugador2 {

    private JugadorDAO jugDAO;
    private Jugador jugador;
    private PartidaDAO partidaDAO;
    private Partida partida = null;
    private JuegoConf juegoconf = new JuegoConf() ;
    private final SQLitePlayerProgressDAO playerProgressDAO = new SQLitePlayerProgressDAO();
    
    private final ConfiguracionServicio configuracionServicio;
    private final GestionPartidasServicio gestionPartidasServicio;
    private final JuegoServicio juegoServicio;

    public MenuClienteJugador2() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la Aplicación de CLiente");
        if (Json.loadConfig() != null) {
            System.out.println("¿Quiere continuar con un jugador local o cargar uno nuevo?");
            System.out.println("1. Si \t 2. No");
            int num = scanner.nextInt();
            boolean res;
            switch (num){
                case 1:
                    res = true;
                case 2:
                    res = false;
                default:
                    res = true;
            }
            if (!res) {
                menuConexion(scanner);
            } else {
                juegoconf = Json.loadConfig();
                jugador.setNick(juegoconf.getNick_name());
            }
        } else {
            menuConexion(scanner);
        }
        this.configuracionServicio = new ConfiguracionServicio();
        this.gestionPartidasServicio = new GestionPartidasServicio(jugador);
        this.juegoServicio = new JuegoServicio( jugador);
    }

    private void menuConexion (Scanner scanner) throws SQLException {
        System.out.println("Indica que base de datos quieres utilizar : 1.Mysql 2. Postgres");
        int n = scanner.nextInt();
        String tipoBD;

        switch (n) {
            case 1:
                tipoBD = "mysql";
                break;
            case 2:
                tipoBD = "postgresql";
                break;
            default:
                tipoBD = "mysql";
                break;
        }
        jugDAO = DAOFactory.getJugadorDAO(tipoBD);
        listarTop10Jugadores();

        System.out.println("Escriba el NickName deseado");
        scanner.nextLine();
        String NickName = scanner.nextLine();
        jugador = jugDAO.getJugador(NickName);

        partidaDAO = DAOFactory.getPartidaDAO(tipoBD);
        listarTop10Partidas();

        System.out.println("Escriba la partida deseada");
        int partidaElegida = scanner.nextInt();
        this.partida = partidaDAO.getPartidasByPlayer(jugador.getId()).get(partidaElegida - 1);

        if (playerProgressDAO.getPlayerProgressById(jugador.getId()) == null) {
            playerProgressDAO.addPlayerProgress(jugador);
        }
        juegoconf.setNick_name(NickName);
        juegoconf.setPartidaId(partida.getIdjuego());

        Json.saveConfig(juegoconf);
    }

    private void listarTop10Jugadores() {
        System.out.println("\n--- Listar Jugadores ---");

        try {
            List<Jugador> top10Jugadores = jugDAO.getTop10Jugadores();
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
    
    private void listarTop10Partidas() {
        System.out.println("\n--- Listar Partidas ---");

        try {
            List<Partida> top10JPartidas = partidaDAO.getPartidasByPlayer(jugador.getId());
            if (!top10JPartidas.isEmpty()) {
                for (Partida paridas : top10JPartidas) {
                    System.out.println(paridas);
                }
            } else {
                System.out.println("No hay jugadores en el top 10.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los jugadores: " + e.getMessage());
        }
    }

    public void mostrarMenuPrincipal() throws SQLException {
        int opcion;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nAplicación Cliente del Jugador - Menú Principal:");
            System.out.println("1. Configuración Inicial y Sincronización");
            System.out.println("2. Gestión de Partidas");
            System.out.println("3. Jugar");
            System.out.println("4. Salir");
            System.out.print("\nSelecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    configuracionServicio.mostrarMenuConfiguracion(scanner);
                    break;
                case 2:
                    gestionPartidasServicio.mostrarMenuGestionPartidas(scanner);
                    break;
                case 3:
                    juegoServicio.menuJugar(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void main(String[] args) throws IOException, SQLException {
        MenuClienteJugador2 menu = new MenuClienteJugador2();
        menu.mostrarMenuPrincipal();
    }
}
