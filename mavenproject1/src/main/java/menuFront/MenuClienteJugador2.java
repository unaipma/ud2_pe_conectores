/**
 *
 * @author eugeniolorentecristobal
 */
package menuFront;

import Conexiones.ConexionSelector;
import auxiliar.Json;
import auxiliar.JuegoConf;
import auxiliar.Libreriaaux;
import daos.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelos.Jugador;
import modelos.Partida;
import modelos.PlayerProgress;

public class MenuClienteJugador2 {

    private JugadorDAO jugDAO;
    private Jugador jugador;
    private PartidaDAO partidaDAO;
    private VideojuegoDAO videojuegoDAO;
    private Partida partida;
    private JuegoConf juegoconf;
    private final SQLitePlayerProgressDAO playerProgressDAO = new SQLitePlayerProgressDAO();
    
    private final ConfiguracionServicio configuracionServicio;
    private final GestionPartidasServicio gestionPartidasServicio;
    private final JuegoServicio juegoServicio;
    private String tipoBD;

    private PlayerProgress  playerProgress;

    public MenuClienteJugador2() throws SQLException {
        juegoconf = new JuegoConf();
        jugador = new Jugador();
        partida = new Partida();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la Aplicación de CLiente");
        if (Json.loadConfig() != null) {
            System.out.println("¿Quiere continuar con un jugador online o local?");
            System.out.println("1. Local \t 2. Online");
            int num = Libreriaaux.compruebaNumero();
           
            boolean res;
            switch (num){
                case 1:
                    res = true;
                    break;
                case 2:
                    res = false;
                    break;
                default:
                    res = true;
                    break;
            }
            if (!res) {
                menuConexion(scanner);
                ConfiguracionServicio.setCredenciales(true);
            } else {
                juegoconf = Json.loadConfig();
                jugador.setNick(juegoconf.getNick_name());
                partida.setIdjuego(juegoconf.getVideojuegoId());
                partida.setIdjugador(juegoconf.getPartidaId());

                playerProgress = playerProgressDAO.getPlayerProgressById2(partida.getIdjuego(), jugador.getNick());
            }
        } else {
            menuConexion(scanner);
            ConfiguracionServicio.setCredenciales(true);
        }
        this.configuracionServicio = new ConfiguracionServicio(jugador, juegoconf);
        this.gestionPartidasServicio = new GestionPartidasServicio(jugador, partida, playerProgress);
        this.juegoServicio = new JuegoServicio(jugador, partida, playerProgress);
    }

    /**
     * Menú que permite seleccionar el tipo de base de datos (MySQL o PostgreSQL) y establece la conexión.
     * Además, carga al jugador y sus partidas.
     *
     * @param scanner El escáner para leer la entrada del usuario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    private void menuConexion (Scanner scanner) throws SQLException {
        System.out.println("Indica que base de datos quieres utilizar : 1.Mysql 2. Postgres");
        int n = Libreriaaux.compruebaNumero();

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
        Connection conexionData = ConexionSelector.obtenerConexion(tipoBD);

        jugDAO = DAOFactory.getJugadorDAO(tipoBD);
        listarTop10Jugadores();
        String NickName;
        do {
            System.out.println("Escriba el NickName deseado");
            try{
                NickName = scanner.nextLine();
                jugador = jugDAO.getJugador(NickName);
            }catch ( Exception e){
                System.out.println("jugador no válido");
            }
        }while(jugador==null);

        partidaDAO = DAOFactory.getPartidaDAO(tipoBD);
        videojuegoDAO= DAOFactory.getVideojuegoDAO(tipoBD);
        listarTop10Partidas();


        int partidaElegida=0 ;

        do {
            System.out.println("Escriba la partida del juego deseada");
            try{
                partidaElegida = Libreriaaux.compruebaNumero();
                if (partidaDAO.getPartidasByPlayer(jugador.getId()).get(partidaElegida - 1)!=null){
                    this.partida = partidaDAO.getPartidasByPlayer(jugador.getId()).get(partidaElegida - 1);
                }

            }catch ( Exception e){
                System.out.println("patida  no válida");
            }
        }while(this.partida==null);
        playerProgress = new PlayerProgress(partida.getIdjugador(), jugador.getNick(), partida.getExp(), partida.getNivel(), partida.getMonedas(), partida.getUltimaconexion(), partida.getIdjuego());

        // Comprobar si ya existe progreso del jugador
        PlayerProgress progresoExistente = playerProgressDAO.getPlayerProgressById2(partidaElegida, jugador.getNick());
        if (progresoExistente != null) {
            // Si existe, actualizar el progreso
            playerProgress.setPartidaId(progresoExistente.getPartidaId()); // Asegurarse de mantener el mismo ID
            playerProgressDAO.updatePlayerProgress(playerProgress);
            System.out.println("Progreso existente actualizado con éxito.");
        } else {
            // Si no existe, agregar nuevo progreso
            playerProgressDAO.addPlayerProgress(playerProgress);
            System.out.println("Nueva partida iniciada con éxito.");
        }

        System.out.println("Introduzca su contraseña");
        String pass = scanner.nextLine();
        // Configurar JuegoConf
        juegoconf = new JuegoConf();
        try {
            juegoconf.setHost(conexionData.getMetaData().getURL().split("//")[1].split(":")[0]);
            juegoconf.setPort(obtenerPuertoDesdeURL(conexionData.getMetaData().getURL()));
            juegoconf.setUser(conexionData.getMetaData().getUserName()); // Obtener usuario
            juegoconf.setPass(pass); // Asignar manualmente si no está disponible en metadata
            juegoconf.setNick_name(jugador.getNick());
            juegoconf.setPartidaId(partida.getIdjugador());
            juegoconf.setVideojuegoId(partida.getIdjuego());
        } catch (SQLException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error al configurar JuegoConf: " + e.getMessage());
        }

        Json.saveConfig(juegoconf);
    }

    /**
     * Muestra los 10 mejores jugadores en la base de datos.
     */
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

    /**
     * Muestra las partidas disponibles para el jugador en la base de datos.
     */
    private void listarTop10Partidas() {
        System.out.println("\n--- Listar Partidas ---");
        int cont=1;
        try {
            List<Partida> top10JPartidas = partidaDAO.getPartidasByPlayer(jugador.getId());
            if (!top10JPartidas.isEmpty()) {
                for (Partida paridas : top10JPartidas) {
                    System.out.println(cont+"."+"Nombre del juego:"+videojuegoDAO.getVideojuego(paridas.getIdjuego()).getTitle()+" Info partida:"+paridas);
                    cont++;
                }
            } else {
                System.out.println("No hay partidas disponibles.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las partidas: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú principal para gestionar la aplicación cliente del jugador.
     * Permite seleccionar opciones como configuración, gestión de partidas, jugar, o salir.
     *
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
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

    /**
     * Método principal que inicia la aplicación cliente del jugador.
     *
     * @param args Argumentos de la línea de comandos.
     * @throws IOException Si ocurre un error al leer archivos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public static void main(String[] args) throws IOException, SQLException {
        MenuClienteJugador2 menu = new MenuClienteJugador2();
        menu.mostrarMenuPrincipal();
    }

    /**
     * Obtiene el puerto de la URL de conexión a la base de datos.
     *
     * @param url La URL de conexión a la base de datos.
     * @return El puerto extraído de la URL.
     */
    private int obtenerPuertoDesdeURL(String url) {
        try {
            URI uri = new URI(url);
            return uri.getPort();
        } catch (URISyntaxException e) {
            return 0;
        }
    }
}