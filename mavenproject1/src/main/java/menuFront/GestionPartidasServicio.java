/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */
import Conexiones.ConexionSelector;
import auxiliar.Json;
import auxiliar.JuegoConf;
import auxiliar.Libreriaaux;
import daos.DAOFactory;
import daos.JugadorDAO;
import daos.PartidaDAO;
import daos.SQLiteConfiguracion;
import daos.SQLitePlayerProgressDAO;
import modelos.PlayerProgress;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelos.ConfjugadorLite;
import modelos.Jugador;
import modelos.Partida;

public class GestionPartidasServicio {

    private final SQLiteConfiguracion sqliteconf;
    private final SQLitePlayerProgressDAO sqliteManager;
    private ConfjugadorLite conf;
    private PartidaDAO partidaDAO;
    private JugadorDAO jugadorDAO;
    private Jugador jugador;
    private PlayerProgress playerProgress;
    private Partida partida;

    /**
     * Constructor de la clase GestionPartidasServicio.
     *
     * @param jugador El jugador actual.
     * @param partida La partida actual.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public GestionPartidasServicio(Jugador jugador, Partida partida, PlayerProgress playerProgress) throws SQLException {
        this.sqliteconf = new SQLiteConfiguracion();
        this.sqliteManager = new SQLitePlayerProgressDAO();
        this.conf = sqliteconf.getConfig(jugador);
        this.jugador = jugador;
        this.partida = partida;
        this.playerProgress = playerProgress;
    }

    /**
     * Muestra el menú de opciones para gestionar las partidas. Permite guardar
     * configuraciones, sincronizar el progreso, ver estadísticas y editar
     * configuraciones.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void mostrarMenuGestionPartidas(Scanner scanner) throws SQLException {
        int opcion;
        do {
            System.out.println("\nGestión de Partidas/Configuración:");
            System.out.println("1. Guardar configuraciones en SQLite");
            System.out.println("2. Subir progreso a la nube");
            System.out.println("3. Mostrar estadísticas y progreso actual");
            System.out.println("4. Editar configuración");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = Libreriaaux.compruebaNumero();

            switch (opcion) {
                case 1:
                    guardarConfiguracionesSQLite();
                    break;
                case 2:
                    if (ConfiguracionServicio.isCredenciales()) {
                        sincronizarProgresoJugador(scanner);
                        break;
                    } else {
                        System.out.println("Tienes que verificar credenciales");
                        System.out.println("Ve a 1. ConfiguracionServicio --> 2. Verificar credenciales del jugador");
                        break;
                    }
                case 3:
                    mostrarEstadisticasJugador(scanner);
                    break;
                case 4:
                    editarConfiguracion(scanner);
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    }

    /**
     * Guarda las configuraciones de la partida en SQLite. Permite modificar la
     * resolución, idioma y sonido de la configuración.
     */
    private void guardarConfiguracionesSQLite() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dime la resolucion");
        scanner.nextLine();
        conf.setResolucion(scanner.nextLine());

        System.out.println("Dime el lenguage");
        conf.setIdioma(scanner.nextLine());

        System.out.println("1.Sonido activado\n2.Sonido desactivado");

        switch (Libreriaaux.compruebaNumero()) {
            case 1:
                conf.setSound_enabled(true);
                break;
            case 2:
                conf.setSound_enabled(false);
                break;
            default:
                conf.setSound_enabled(true);
                break;
        }
        try {
            if (sqliteconf.getConfig(jugador) == null) {
                sqliteconf.saveConfig(conf, jugador);
            } else {
                sqliteconf.updateConfig(conf, jugador);
            }
            System.out.println("Configuraciones guardadas en SQLite.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    /**
     * Sincroniza el progreso del jugador con el servidor. Actualiza los datos
     * de la partida del jugador en la base de datos.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private void sincronizarProgresoJugador(Scanner scanner) {
        try {
            if (menuConexion(scanner)) {

                partida.setIdjugador(playerProgress.getPlayerId());
                partida.setExp(playerProgress.getExperience());
                partida.setMonedas(playerProgress.getCoins());
                partida.setNivel(playerProgress.getLifeLevel());

                partidaDAO.updatePartida(partida);
                System.out.println("Progreso sincronizado con el servidor.");
            }
        } catch (Exception e) {
            System.out.println("Error al sincronizar el progreso: " + e.getMessage());
        }
        implementarCambiosJugador();
    }

    private void implementarCambiosJugador(){
        try {
            List<Partida> partidas = partidaDAO.getPartidasByPlayer(playerProgress.getPlayerId()); // Obtener las partidas del jugador

            // Inicializamos las variables que almacenarán los totales
            int totalExperiencia = 0;
            int totalVida = 0;
            int totalMonedas = 0;

            // Iteramos sobre la lista de partidas
            for (Partida partida : partidas) {
                // Sumamos los valores de cada partida al total
                totalExperiencia += partida.getExp();
                totalVida += partida.getNivel();
                totalMonedas += partida.getMonedas();
            }

            // Asumimos que el objeto 'jugador' tiene métodos para actualizar su progreso
            jugador.setExperience(totalExperiencia);
            jugador.setLifeLevel(totalVida);
            jugador.setCoins(totalMonedas);

            jugadorDAO.updateJugador(jugador);
        } catch (Exception e) {
            System.out.println("Error al sincronizar el progreso: " + e.getMessage());
        }
    }

    /**
     * Muestra las estadísticas y el progreso del jugador.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private void mostrarEstadisticasJugador(Scanner scanner) {
        try {
            List<PlayerProgress> playerProgresses = sqliteManager.getPlayerProgressById(jugador.getNick());
            if (playerProgresses.isEmpty()) {
                System.out.println("No se encontraron partidas para el jugador: " + jugador.getNick());
            } else {
                System.out.println("Estadísticas y progreso del jugador:");
                for (PlayerProgress playerProgress : playerProgresses) {
                    System.out.println(playerProgress.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar estadísticas: " + e.getMessage());
        }
    }

    /**
     * Permite editar la configuración actual de la partida. Modifica la
     * resolución, idioma y configuración de sonido.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    private void editarConfiguracion(Scanner scanner) throws SQLException {
        System.out.println("\nActualización de configuración");

        System.out.print("Nueva resolucion (actual: " + conf.getResolucion() + "): ");
        conf.setResolucion(scanner.nextLine());

        System.out.print("Nuevo idioma (actual: " + conf.getIdioma() + "): ");
        conf.setIdioma(scanner.nextLine());

        System.out.print("1.Sonido activo- 2.Desactivar sonido (actual: " + conf.isSound_enabled() + "): ");
        switch (Libreriaaux.compruebaNumero()) {
            case 1:
                conf.setSound_enabled(true);
                break;
            case 2:
                conf.setSound_enabled(false);
                break;
            default:
                conf.setSound_enabled(false);
        }

        sqliteconf.updateConfig(conf, jugador);
        System.out.println("\n Configuración actualizada");
        System.out.println(conf.toString());
    }

    /**
     * Muestra un menú para seleccionar el tipo de base de datos a utilizar
     * (MySQL o PostgreSQL).
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @return true si la conexión con la base de datos se establece
     * correctamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al intentar establecer la
     * conexión.
     */
    private boolean menuConexion(Scanner scanner) throws SQLException {
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
        ConexionSelector conexionSelector = new ConexionSelector();
        this.partidaDAO = DAOFactory.getPartidaDAO(tipoBD);
        this.jugadorDAO = DAOFactory.getJugadorDAO(tipoBD);
        if (conexionSelector.obtenerConexion(tipoBD) != null) {
            return true;
        } else {
            return false;
        }
    }
}
