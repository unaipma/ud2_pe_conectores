/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */
import daos.SQLitePlayerProgressDAO;
import modelos.Jugador;
import modelos.PlayerProgress;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class JuegoServicio {
    private Jugador jugador;
    private SQLitePlayerProgressDAO sqliteManager;

    public JuegoServicio(Jugador jugador) throws SQLException {
        this.jugador = jugador;
        this. sqliteManager = new SQLitePlayerProgressDAO();
    }

    public void menuJugar(Scanner scanner) {
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

           
            sqliteManager.addPlayerProgress(nuevaPartida);

            System.out.println("Nueva partida iniciada con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al iniciar nueva partida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de datos inválido. Por favor, verifica la entrada.");
        }
    }

    private void continuarPartidaExistente(Scanner scanner) {
       
        int playerId = jugador.getId();

        try {
            List<PlayerProgress> playerProgresses = sqliteManager.getAllPlayerProgress(jugador);
            if (playerProgresses.isEmpty()) {
                System.out.println("No se encontraron partidas guardadas para el jugador : " + jugador.getNick());
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

}
