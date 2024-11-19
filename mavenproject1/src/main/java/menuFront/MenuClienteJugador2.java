/**
 *
 * @author eugeniolorentecristobal
 */
package menuFront;

import Conexiones.Sqlliteconexion;
import auxiliar.Json;
import auxiliar.JuegoConf;
import daos.DAOFactory;
import daos.JugadorDAO;
import daos.PartidaDAO;
import daos.SQLiteConfiguracion;
import daos.SQLitePlayerProgressDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import modelos.ConfjugadorLite;
import modelos.Jugador;
import modelos.Partida;
import modelos.PlayerProgress;

public class MenuClienteJugador2 {
    
    private ConfjugadorLite  conf;
    private JuegoConf juegoconf;
    private Json json = new Json();
    private Jugador jugador;
    private JuegoConf jugadorConf;
    private SQLitePlayerProgressDAO sqliteManager;
    private SQLiteConfiguracion sqliteconf = new SQLiteConfiguracion();
    private Connection serverSyncManager;  // Administrador para sincronización con el servidor
    private Partida partida;
    private PartidaDAO partidaDAO;
    private JugadorDAO jugDAO;
    private String tipoBD;
    private PlayerProgress playerProgress;

    private final ConfiguracionServicio configuracionServicio;
    private final GestionPartidasServicio gestionPartidasServicio;
    private final JuegoServicio juegoServicio;

    public MenuClienteJugador2() throws IOException, SQLException {
        this.configuracionServicio = new ConfiguracionServicio();
        this.gestionPartidasServicio = new GestionPartidasServicio();
        this.juegoServicio = new JuegoServicio(configuracionServicio.getJugador());
        
        // Se inicializa la conexión a la base de datos local
        this.serverSyncManager = Sqlliteconexion.getConnection();
        
        System.out.println("Bienvenido a la Aplicación de CLiente");
        
        jugador= jugDAO.getJugador(1);
        sqliteManager = new SQLitePlayerProgressDAO();
        conf = sqliteconf.getConfig(jugador);
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
