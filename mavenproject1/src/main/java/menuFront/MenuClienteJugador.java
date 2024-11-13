package menuFront;

import Conexiones.ConexionSelector;
import Conexiones.Sqlliteconexion;
import auxiliar.JuegoConf;
import daos.DAOFactory;
import daos.PartidaDAO;
import daos.SQLitePartidaDAO;
import java.io.File;
import java.io.IOException;
import modelos.Jugador;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Partida;

/**
 *
 * @author eugeniolorentecristobal
 */
public class MenuClienteJugador {

    private File json = new File("config.json");
    private Jugador jugador;
    private JuegoConf jugadorConf;
    private SQLitePartidaDAO sqliteManager;
    private Connection serverSyncManager;  // Administrador para sincronización con el servidor
    private Partida partida;
    private PartidaDAO partidaDAO;
    private String tipoBD;

    public MenuClienteJugador() throws IOException, SQLException {
        // Se inicializa la conexión a la base de datos local
        this.serverSyncManager = Sqlliteconexion.getConnection();
        // Si no existe el archivo de configuración, se crea
        if (!json.exists()) {
            json.createNewFile();
        }
        // Inicializar sqliteManager para manejar las partidas
        sqliteManager = new SQLitePartidaDAO();
    }

    public void mostrarMenuPrincipal() {
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
        /*System.out.print("Seleccione el tipo de base de datos (MySQL o PostgreSQL): ");
        String tipoBaseDatos = scanner.next();
        try {
            serverSyncManager = ConexionSelector.obtenerConexion(tipoBaseDatos);
            System.out.println("Conexión configurada correctamente con " + tipoBaseDatos);
        } catch (SQLException e) {
            System.err.println("Error al configurar la conexión: " + e.getMessage());
        }*/
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
        try {
            partidaDAO = DAOFactory.getPartidaDAO(tipoBD);
        } catch (SQLException ex) {
            Logger.getLogger(MenuClienteJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void mostrarMenuGestionPartidas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGestión de Partidas:");
            System.out.println("1. Guardar configuraciones en SQLite");
            System.out.println("2. Sincronizar progreso del jugador");
            System.out.println("3. Mostrar estadísticas y progreso actual");
            System.out.println("4. Iniciar nueva partida");
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
                    iniciarNuevaPartida(scanner);
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
        try {
            sqliteManager.addPartida(partida); // Suponiendo que "partida" es una instancia de la clase Partida
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
        System.out.print("Introduce el ID del jugador para ver estadísticas: ");
        int playerId = scanner.nextInt();
        try {
            List<Partida> partidas = sqliteManager.getPartidasByPlayer(playerId);
            if (partidas.isEmpty()) {
                System.out.println("No se encontraron partidas para el jugador con ID: " + playerId);
            } else {
                System.out.println("Estadísticas y progreso del jugador:");
                for (Partida partida : partidas) {
                    System.out.println(partida);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar estadísticas: " + e.getMessage());
        }
    }

    private void iniciarNuevaPartida(Scanner scanner) {
        System.out.print("Introduce el ID de la nueva partida: ");
        int idpartida = scanner.nextInt();
        System.out.print("Introduce el ID del juego: ");
        int idjuego = scanner.nextInt();
        System.out.print("Introduce el ID del jugador: ");
        int playerId = scanner.nextInt();
        System.out.print("Introduce las monedas iniciales: ");
        int monedas = scanner.nextInt();
        System.out.print("Introduce la experiencia inicial: ");
        int exp = scanner.nextInt();
        System.out.print("Introduce el nivel inicial: ");
        int nivel = scanner.nextInt();
        System.out.print("Introduce la última conexión (formato YYYY-MM-DD): ");
        String fechaConexion = scanner.next();

        try {
            Date ultimaconexion = Date.valueOf(fechaConexion);
            Partida nuevaPartida = new Partida(idpartida, idjuego, playerId, monedas, exp, nivel, ultimaconexion);
            sqliteManager.addPartida(nuevaPartida);
            System.out.println("Nueva partida iniciada.");
        } catch (SQLException e) {
            System.out.println("Error al iniciar nueva partida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Por favor, usa el formato YYYY-MM-DD.");
        }
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
        System.out.print("Introduce el ID de la nueva partida: ");
        int idpartida = scanner.nextInt();
        System.out.print("Introduce el ID del juego: ");
        int idjuego = scanner.nextInt();
        System.out.print("Introduce el ID del jugador: ");
        int playerId = scanner.nextInt();
        System.out.print("Introduce las monedas iniciales: ");
        int monedas = scanner.nextInt();
        System.out.print("Introduce la experiencia inicial: ");
        int exp = scanner.nextInt();
        System.out.print("Introduce el nivel inicial: ");
        int nivel = scanner.nextInt();
        System.out.print("Introduce la última conexión (formato YYYY-MM-DD): ");
        String fechaConexion = scanner.next();

        try {
            Date ultimaconexion = Date.valueOf(fechaConexion);
            Partida nuevaPartida = new Partida(idpartida, idjuego, playerId, monedas, exp, nivel, ultimaconexion);
            sqliteManager.addPartida(nuevaPartida);
            System.out.println("Nueva partida iniciada con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al iniciar nueva partida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Por favor, usa el formato YYYY-MM-DD.");
        }
    }

    private void continuarPartidaExistente(Scanner scanner) {
        System.out.println("\nSeleccionar Partida Existente:");
        System.out.print("Introduce el ID del jugador: ");
        int playerId = scanner.nextInt();

        try {
            List<Partida> partidas = sqliteManager.getPartidasByPlayer(playerId);
            if (partidas.isEmpty()) {
                System.out.println("No se encontraron partidas guardadas para el jugador con ID: " + playerId);
            } else {
                System.out.println("Selecciona la partida para continuar:");
                for (int i = 0; i < partidas.size(); i++) {
                    System.out.println((i + 1) + ". " + partidas.get(i));
                }
                System.out.print("Elige el número de la partida que deseas continuar: ");
                int seleccion = scanner.nextInt();

                if (seleccion >= 1 && seleccion <= partidas.size()) {
                    Partida partidaSeleccionada = partidas.get(seleccion - 1);
                    modificarPartidaExistente(scanner, partidaSeleccionada);
                } else {
                    System.out.println("Selección inválida.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar partidas: " + e.getMessage());
        }
    }

    private void modificarPartidaExistente(Scanner scanner, Partida partida) throws SQLException {
        System.out.println("\nModificando partida: " + partida);
        int opcion;
        do {
            System.out.println("\n¿Qué deseas modificar en la partida?");
            System.out.println("1. Aumentar nivel");
            System.out.println("2. Incrementar experiencia");
            System.out.println("3. Agregar monedas");
            System.out.println("4. Disminuir nivel");
            System.out.println("5. Reducir experiencia");
            System.out.println("6. Remover monedas");
            System.out.println("7. Volver al Menú Jugar");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nuevo nivel: ");
                    int nuevoNivel = scanner.nextInt();
                    partida.setNivel(nuevoNivel);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Nivel actualizado.");
                    break;
                case 2:
                    System.out.print("Introduce la nueva experiencia: ");
                    int nuevaExp = scanner.nextInt();
                    partida.setExp(nuevaExp);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Experiencia actualizada.");
                    break;
                case 3:
                    System.out.print("Introduce las monedas a agregar: ");
                    int monedasSumar = scanner.nextInt();
                    partida.setMonedas(partida.getMonedas() + monedasSumar);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Monedas actualizadas.");
                    break;
                case 4:
                    System.out.print("Introduce el nuevo nivel: ");
                    int nuevoNivelDecre = scanner.nextInt();
                    partida.setNivel(nuevoNivelDecre);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Nivel actualizado.");
                    break;
                case 5:
                    System.out.print("Introduce la nueva experiencia: ");
                    int nuevaExpReducida = scanner.nextInt();
                    partida.setExp(nuevaExpReducida);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Experiencia actualizada.");
                    break;
                case 6:
                    System.out.print("Introduce las monedas a remover: ");
                    int monedasRestar = scanner.nextInt();
                    partida.setMonedas(partida.getMonedas() - monedasRestar);
                    sqliteManager.updatePartida(partida);  // Actualizar en base de datos
                    System.out.println("Monedas actualizadas.");
                    break;
                case 7:
                    System.out.println("Volviendo al Menú Jugar...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 7);
    }

    public static void main(String[] args) throws IOException, SQLException {
        MenuClienteJugador menu = new MenuClienteJugador();
        menu.mostrarMenuPrincipal();
    }
}
