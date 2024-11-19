package menuFront;

import Conexiones.ConexionSelector;
import Conexiones.Mysqlconexion;
import Conexiones.PostgreConexion;
import Conexiones.Sqlliteconexion;
import auxiliar.Json;
import auxiliar.JuegoConf;
import daos.DAOFactory;
import daos.PartidaDAO;
import daos.SQLitePlayerProgressDAO;
import java.io.File;
import java.io.IOException;
import modelos.Jugador;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Partida;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import daos.JugadorDAO;
import daos.SQLiteConfiguracion;
import java.time.LocalDate;
import modelos.ConfjugadorLite;
import modelos.PlayerProgress;

/**
 *
 * @author eugeniolorentecristobal
 */
public class MenuClienteJugador {
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
    

    public MenuClienteJugador() throws IOException, SQLException {
        // Se inicializa la conexión a la base de datos local
        this.serverSyncManager = Sqlliteconexion.getConnection();
        // Inicializar sqliteManager para manejar las partidas
        Scanner scanner = new Scanner(System.in);
        String tipoBD;

        System.out.println("Bienvenido a la Aplicación de CLiente");
        System.out.println("Indica que base de datos quieres utilizar : 1.Mysql 2. Postgres");
        int n = scanner.nextInt();

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
        jugDAO= DAOFactory.getJugadorDAO(tipoBD);
        jugador= jugDAO.getJugador("");
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
                    mostrarMenuConfiguracion(scanner);
                    break;
                case 2:
                    mostrarMenuGestionPartidas(scanner);
                    break;
                case 3:
                    menuJugar(scanner);
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

    private void mostrarMenuConfiguracion(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nConfiguración Inicial y Sincronización:");
            System.out.println("1. Configurar conexión con el servidor");
            System.out.println("2. Verificar credenciales del jugador");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    configurarConexionServidor(scanner);
                    break;
                case 2:
                    verificarCredencialesJugador(scanner);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    private void configurarConexionServidor(Scanner scanner) {
        System.out.println("\n=== Configurar Conexión ===");
        System.out.println("1.Mysql 2.Postgre");
        switch (scanner.nextInt()) {
            case 1:
                tipoBD="Mysql";
                break;
            case 2:
                tipoBD="Postgre";
                break;
            default:
                tipoBD="Mysql";
        }
        System.out.print("Ingrese el host del servidor: ");
       
        String host = scanner.nextLine();

        System.out.print("Ingrese el puerto: ");
        int puerto = scanner.nextInt();

        scanner.nextLine(); 
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.print("Ingrese su nickName: ");
        String nickName = scanner.nextLine();
        
        try{
            if (tipoBD.equals("Mysql")) {
                Mysqlconexion.conexionUser("jdbc:mysql://"+host+":"+puerto+"/ud2conectores", usuario, contraseña);
            }else{
                //PostgreConexion.conexionUser("jdbc:postgresql://"+host+":"+puerto+"/ud2conectores", usuario, contraseña);
                PostgreConexion.conexionUserEugenioCasa("jdbc:postgresql://localhost:5432/ud2conexiones");
            }
            
            
        }catch (SQLException e){
            System.out.println("Error");
            
        }
        juegoconf = new JuegoConf(host, puerto, usuario, host, nickName);
        json.saveConfig(juegoconf);
        System.out.println("\nConexión configurada correctamente:");
        System.out.println("Host: " + host);
        System.out.println("Puerto: " + puerto);
        System.out.println("Usuario: " + usuario);
        System.out.println("NickName: " + nickName);
        
    }

    

    private void verificarCredencialesJugador(Scanner scanner) {
        boolean credencialesValidas;
        do {
            
            System.out.print("Introduce el ID del jugador: ");
            int playerId = scanner.nextInt();
            System.out.print("Introduce la contraseña: ");
            String password = scanner.next();
            credencialesValidas = jugadorConf.verificarCredenciales(playerId, password);
            System.out.println(credencialesValidas ? "Credenciales válidas." : "Credenciales incorrectas.");
        } while (!credencialesValidas);
    }

    private void mostrarMenuGestionPartidas(Scanner scanner) throws SQLException {
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
                    editarConfiguracion(scanner, conf);
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
        ConfjugadorLite conf= new ConfjugadorLite();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dime la resolucion");
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
            sqliteconf.saveConfig(conf, jugador); 
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
                System.out.println("No se encontraron partidas para el jugador : " + jugador.getNick());
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

    private void editarConfiguracion(Scanner scanner,ConfjugadorLite confi) throws SQLException {
        

        System.out.println("\nActualización de configuración");

       

        System.out.print("Nueva resolucion (actual: " + confi.getResolucion() + "): ");
        scanner.nextLine();
        confi.setResolucion(scanner.nextLine());

        System.out.print("Nuevo idioma (actual: " + confi.getIdioma() + "): ");
        confi.setIdioma(scanner.nextLine());

        System.out.print("1.Sonido activo- 2.Desactivar sonido (actual: " + confi.isSound_enabled()+ "): ");
        switch (scanner.nextInt()) {
            case 1:
                confi.setSound_enabled(true);
                break;
            case 2:
                confi.setSound_enabled(false);
                break;
            default:
                confi.setSound_enabled(false);
        }

        sqliteconf.updateConfig(conf, jugador);
        System.out.println("\n Configuración actualizada");
        System.out.println(confi.toString());
        
    }

    private void menuJugar(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nJugar - ¿Qué deseas hacer?");
            System.out.println("1. Iniciar nueva partida");
            System.out.println("2. Continuar partida existente");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    iniciarNuevaPartida(scanner);
                    break;
                case 2:
                    continuarPartidaExistente(scanner);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    private void iniciarNuevaPartida(Scanner scanner) {
        System.out.println("\nIniciar Nueva Partida:");

        try {
            // Solicitar los datos de la nueva partida
            
            
         

            

            System.out.print("Introduce las monedas iniciales: ");
            int coins = scanner.nextInt();

            System.out.print("Introduce la experiencia inicial: ");
            int experience = scanner.nextInt();

            System.out.print("Introduce el nivel de vida inicial: ");
            int lifeLevel = scanner.nextInt();

            
         

             String lastLogin = (LocalDate.now().toString());

            // Crear objeto PlayerProgress
            PlayerProgress nuevaPartida = new PlayerProgress(
                    jugador.getId(),
                    jugador.getNick(),
                    experience,
                    lifeLevel,
                    coins,
                    0,
                    lastLogin
            );

            // Guardar en la base de datos
            sqliteManager.addPlayerProgress(nuevaPartida);

            System.out.println("Nueva partida iniciada con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al iniciar nueva partida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de datos inválido. Por favor, verifica la entrada.");
        }
    }

    private void continuarPartidaExistente(Scanner scanner) {
        System.out.println("\nSeleccionar Partida Existente:");
        System.out.print("Introduce el ID del jugador: ");
        int playerId = scanner.nextInt();

        try {
            List<PlayerProgress> playerProgresses = sqliteManager.getAllPlayerProgress(jugador);
            if (playerProgresses.isEmpty()) {
                System.out.println("No se encontraron partidas guardadas para el jugador con ID: " + playerId);
            } else {
                System.out.println("Selecciona la partida para continuar:");
                for (int i = 0; i < playerProgresses.size(); i++) {
                    System.out.println((i + 1) + ". " + playerProgresses.get(i));
                }
                System.out.print("Elige el número de la partida que deseas continuar: ");
                int seleccion = scanner.nextInt();

                if (seleccion >= 1 && seleccion <= playerProgresses.size()) {
                    PlayerProgress partidaSeleccionada = playerProgresses.get(seleccion - 1);
                    modificarPartidaExistente(scanner, partidaSeleccionada);
                } else {
                    System.out.println("Selección inválida.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar partidas: " + e.getMessage());
        }
    }

    private void modificarPartidaExistente(Scanner scanner, PlayerProgress playerProgress) throws SQLException {
        System.out.println("\nModificando partida: " + playerProgress);
        int opcion;
        do {
            System.out.println("\n¿Qué deseas modificar en la partida?");
            System.out.println("1. Aumentar nivel");
            System.out.println("2. Incrementar experiencia");
            System.out.println("3. Agregar monedas");
            System.out.println("4. Volver al Menú Jugar");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    
                    System.out.print("Tiene:"+ playerProgress.getLifeLevel()+"\nIntroduce el nuevo nivel: ");
                    int nuevoNivel = scanner.nextInt();
                    playerProgress.setLifeLevel(nuevoNivel);
                    sqliteManager.updatePlayerProgress(playerProgress);  // Actualizar en base de datos
                    System.out.println("Nivel actualizado.");
                    break;
                case 2:
                    System.out.print("Tiene:"+ playerProgress.getExperience()+"\nIntroduce la nueva experiencia: ");
                    int nuevaExp = scanner.nextInt();
                    playerProgress.setExperience(nuevaExp);
                    sqliteManager.updatePlayerProgress(playerProgress);  // Actualizar en base de datos
                    System.out.println("Experiencia actualizada.");
                    break;
                case 3:
                    System.out.print("Tiene:"+ playerProgress.getCoins() +"\n Introduce las monedas a agregar: ");
                    int monedasSumar = scanner.nextInt();
                    playerProgress.setCoins( monedasSumar);
                    sqliteManager.updatePlayerProgress(playerProgress);  // Actualizar en base de datos
                    System.out.println("Monedas actualizadas.");
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Jugar...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) throws IOException, SQLException {
        MenuClienteJugador menu = new MenuClienteJugador();
        menu.mostrarMenuPrincipal();
    }
}
