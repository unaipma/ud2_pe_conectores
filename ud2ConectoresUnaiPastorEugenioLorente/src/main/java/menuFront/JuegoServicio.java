
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */
import Conexiones.ConexionSelector;
import auxiliar.Json;
import auxiliar.JuegoConf;
import auxiliar.Libreriaaux;
import daos.*;
import modelos.Jugador;
import modelos.Partida;
import modelos.PlayerProgress;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class JuegoServicio {
    private Jugador jugador;
    private Partida partida;
    private SQLitePlayerProgressDAO sqliteManager;
    private JuegoConf juegoconf;
    private PartidaDAO partidaDAO;
    private VideojuegoDAO videojuegoDAO;
    private JugadorDAO jugadorDAO;
    private PlayerProgress playerProgress;

    public JuegoServicio(Jugador jugador, Partida partida, PlayerProgress playerProgress) throws SQLException {
        this.jugador = jugador;
        this.partida = partida;
        this.sqliteManager = new SQLitePlayerProgressDAO();
        this.playerProgress = playerProgress;
    }

    /**
     * Muestra el menú de opciones para jugar, permitiendo al jugador iniciar una nueva partida,
     * continuar una partida existente o volver al menú principal.
     *
     * @param scanner El objeto Scanner para leer la entrada del jugador.
     */
    public void menuJugar(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nJugar - ¿Qué deseas hacer?");
            System.out.println("1. Jugar partida actual");
            System.out.println("2. Continuar partida existente(online)");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = Libreriaaux.compruebaNumero();

            switch (opcion) {
                case 1:
                    iniciarNuevaPartida(scanner);
                    break;
                case 2:
                    if (ConfiguracionServicio.isCredenciales()) {
                        continuarPartidaExistente(scanner);
                        break;
                    } else {
                        System.out.println("Tienes que verificar credenciales");
                        System.out.println("Ve a 1. ConfiguracionServicio --> 2. Verificar credenciales del jugador");
                        break;
                    }
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    /**
     * Inicia una nueva partida para el jugador, solicitando datos como las monedas, experiencia
     * y nivel de vida, y registrando la nueva partida en la base de datos.
     *
     * @param scanner El objeto Scanner para leer la entrada del jugador.
     */
    private void iniciarNuevaPartida(Scanner scanner) {

        System.out.println("\nIniciar Nueva Partida:");

        try {

            System.out.print("Introduce las monedas iniciales: ");
            int coins = Libreriaaux.compruebaNumero();

            System.out.print("Introduce la experiencia inicial: ");
            int experience = Libreriaaux.compruebaNumero();

            System.out.print("Introduce el nivel de vida inicial: ");
            int lifeLevel = Libreriaaux.compruebaNumero();

            Date lastLogin = Date.valueOf(LocalDate.now());

            playerProgress.setExperience(experience);
            playerProgress.setLifeLevel(lifeLevel);
            playerProgress.setCoins(coins);
            playerProgress.setSessionCount(playerProgress.getSessionCount() + 1);

            PlayerProgress progresoExistente = sqliteManager.getPlayerProgressById2(partida.getIdjuego(), jugador.getNick());
            if (progresoExistente != null) {
                // Si existe, actualizar el progreso
                playerProgress.setPartidaId(progresoExistente.getPartidaId()); // Asegurarse de mantener el mismo ID
                sqliteManager.updatePlayerProgress(playerProgress);
                System.out.println("Progreso existente actualizado con éxito.");
            } else {
                // Si no existe, agregar nuevo progreso
                sqliteManager.addPlayerProgress(playerProgress);
                System.out.println("Nueva partida iniciada con éxito.");
            }

            System.out.println("Nueva partida iniciada con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al iniciar nueva partida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de datos inválido. Por favor, verifica la entrada.");
        }
    }

    /**
     * Permite al jugador continuar una partida existente. Si no hay partidas guardadas,
     * el jugador es notificado y se le da la opción de elegir una para continuar.
     *
     * @param scanner El objeto Scanner para leer la entrada del jugador.
     */
    private void continuarPartidaExistente(Scanner scanner) {

        try {
            if (menuConexion(scanner)) {
                int playerId = jugador.getId();

                try {
                    modificarPartidaExistente(scanner, playerProgress);
                } catch (SQLException e) {
                    System.out.println("Error al recuperar partidas: " + e.getMessage());
                }
            } else {
                System.out.println("Intentelo mas tarde o continue jugando su juego");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Modifica los detalles de una partida existente, permitiendo al jugador aumentar el nivel,
     * incrementar la experiencia o agregar monedas.
     *
     * @param scanner El objeto Scanner para leer la entrada del jugador.
     * @param playerProgress El objeto PlayerProgress que contiene el progreso de la partida.
     * @throws SQLException Si ocurre un error al actualizar la partida en la base de datos.
     */
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
            opcion = Libreriaaux.compruebaNumero();

            switch (opcion) {
                case 1:

                    System.out.print("Tiene:"+ playerProgress.getLifeLevel()+"\nIntroduce el nuevo nivel: ");
                    int nuevoNivel = Libreriaaux.compruebaNumero();
                    playerProgress.setLifeLevel(nuevoNivel);
                    sqliteManager.updatePlayerProgress(playerProgress);  // Actualizar en base de datos
                    System.out.println("Nivel actualizado.");
                    break;
                case 2:
                    System.out.print("Tiene:"+ playerProgress.getExperience()+"\nIntroduce la nueva experiencia: ");
                    int nuevaExp = Libreriaaux.compruebaNumero();
                    playerProgress.setExperience(nuevaExp);
                    sqliteManager.updatePlayerProgress(playerProgress);  // Actualizar en base de datos
                    System.out.println("Experiencia actualizada.");
                    break;
                case 3:
                    System.out.print("Tiene:"+ playerProgress.getCoins() +"\n Introduce las monedas a agregar: ");
                    int monedasSumar = Libreriaaux.compruebaNumero();
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


    /**
     * Muestra el menú de conexión y permite al jugador elegir qué base de datos usar (MySQL o PostgreSQL).
     *
     * @param scanner El objeto Scanner para leer la entrada del jugador.
     * @return true si la conexión es exitosa, false si no lo es.
     * @throws SQLException Si ocurre un error al conectar con la base de datos.
     */
    private boolean menuConexion (Scanner scanner) throws SQLException {
        juegoconf = new JuegoConf();

        System.out.println("Indica que base de datos quieres utilizar : 1.Mysql 2. Postgres");
        int n = Libreriaaux.compruebaNumero();
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
        if (conexionSelector.obtenerConexion(tipoBD) != null) {
            jugadorDAO= DAOFactory.getJugadorDAO(tipoBD);
            jugador = jugadorDAO.getJugador(jugador.getNick());
            partidaDAO = DAOFactory.getPartidaDAO(tipoBD);
            videojuegoDAO=DAOFactory.getVideojuegoDAO(tipoBD);
            listarTop10Partidas();

            System.out.println("Escriba la partida deseada");
            int partidaElegida = Libreriaaux.compruebaNumero();
            this.partida = partidaDAO.getPartidasByPlayer(jugador.getId()).get(partidaElegida - 1);

            playerProgress = new PlayerProgress(jugador.getId(), jugador.getNick(), partida.getExp(), partida.getNivel(), partida.getMonedas(), partida.getUltimaconexion(), partida.getIdjuego());

            // Comprobar si ya existe progreso del jugador
            PlayerProgress progresoExistente = sqliteManager.getPlayerProgressById2(partida.getIdjuego(), jugador.getNick());
            if (progresoExistente != null) {
                // Si existe, actualizar el progreso
                playerProgress.setPartidaId(progresoExistente.getPartidaId()); // Asegurarse de mantener el mismo ID
                sqliteManager.updatePlayerProgress(playerProgress);
                System.out.println("Progreso existente actualizado con éxito.");
            } else {
                // Si no existe, agregar nuevo progreso
                sqliteManager.addPlayerProgress(playerProgress);
                System.out.println("Nueva partida iniciada con éxito.");
            }
            juegoconf.setPartidaId(partida.getIdjuego());

            Json.saveConfig(juegoconf);
            return true;
        } else {
            return false;
        }
    }



    /**
     * Muestra el top 10 de jugadores según su progreso.
     */
    private void listarTop10Partidas() {
        System.out.println("\n--- Listar Partidas ---");
        int cont=1;
        try {
            List<Partida> top10JPartidas = partidaDAO.getPartidasByPlayer(jugador.getId());
            if (!top10JPartidas.isEmpty()) {
                for (Partida paridas : top10JPartidas) {
                    System.out.println(cont+". Juego:"+videojuegoDAO.getVideojuego(paridas.getIdjuego()).getTitle()+" Info partida:"+paridas);
                    cont++;
                }
            } else {
                System.out.println("No hay jugadores en el top 10.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los jugadores: " + e.getMessage());
        }
    }

}
