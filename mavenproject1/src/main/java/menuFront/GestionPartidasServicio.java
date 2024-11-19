/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */
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
    private Jugador jugador;
    private Partida partida;

    public GestionPartidasServicio(Jugador jugador) throws SQLException {
        this.sqliteconf = new SQLiteConfiguracion();
        this.sqliteManager = new SQLitePlayerProgressDAO();
        this.conf =  sqliteconf.getConfig(jugador );
        this.jugador = jugador;
    }

    public void mostrarMenuGestionPartidas(Scanner scanner) throws SQLException {
        int opcion;
        do {
            System.out.println("\nGestión de Partidas:");
            System.out.println("1. Guardar configuraciones en SQLite");
            System.out.println("2. Sincronizar progreso del jugador");
            System.out.println("3. Mostrar estadísticas y progreso actual");
            System.out.println("4. Editar configuración");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    guardarConfiguracionesSQLite();
                    break;
                case 2:
                    sincronizarProgresoJugador();
                    break;
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

    private void guardarConfiguracionesSQLite() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dime la resolucion");
        scanner.nextLine();
        conf.setResolucion(scanner.nextLine());

        System.out.println("Dime el lenguage");
        conf.setIdioma(scanner.nextLine());

        System.out.println("1.Sonido activado\n2.Sonido desactivado");

        switch (scanner.nextInt()) {
            case 1:
                conf.setSound_enabled(true);
                break;
            case 2:
                conf.setSound_enabled(false);
            default:
                conf.setSound_enabled(true);
        }
        try {
            if (sqliteconf.getConfig(jugador)==null) {
                 sqliteconf.saveConfig(conf, jugador);

            }else{
                sqliteconf.updateConfig(conf, jugador);
            }
                       System.out.println("Configuraciones guardadas en SQLite.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    private void sincronizarProgresoJugador() {
        try {
            // Implementar lógica para sincronizar con el servidor
            partidaDAO.updatePartida(partida);
            System.out.println("Progreso sincronizado con el servidor.");
        } catch (Exception e) {
            System.out.println("Error al sincronizar el progreso: " + e.getMessage());
        }
    }

    private void mostrarEstadisticasJugador(Scanner scanner) {
        
        try {
            List<PlayerProgress> playerProgresses = sqliteManager.getPlayerProgressById(jugador.getId());
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

    private void editarConfiguracion(Scanner scanner) throws SQLException {

        System.out.println("\nActualización de configuración");

        System.out.print("Nueva resolucion (actual: " + conf.getResolucion() + "): ");
        conf.setResolucion(scanner.nextLine());

        System.out.print("Nuevo idioma (actual: " + conf.getIdioma() + "): ");
        conf.setIdioma(scanner.nextLine());

        System.out.print("1.Sonido activo- 2.Desactivar sonido (actual: " + conf.isSound_enabled() + "): ");
        switch (scanner.nextInt()) {
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
}
