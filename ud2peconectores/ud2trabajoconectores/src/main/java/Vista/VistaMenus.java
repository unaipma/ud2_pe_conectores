/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.util.Scanner;

public class VistaMenus {

    public void limpiarConsola() {
        for (int i = 0; i < 25; i++) {
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Gestión de Videojuegos");
            System.out.println("2. Gestión de Jugadores");
            System.out.println("3. Gestión de Partidas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuVideojuegos(scanner);
                    break;
                case 2:
                    menuJugadores(scanner);
                    break;
                case 3:
                    menuPartidas(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void menuVideojuegos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Gestión de Videojuegos ===");
            System.out.println("1. Alta de Videojuego");
            System.out.println("2. Baja de Videojuego");
            System.out.println("3. Modificación de Videojuego");
            System.out.println("4. Mostrar Información de Videojuego");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Código para alta de videojuego
                    System.out.println("Alta de Videojuego");
                    break;
                case 2:
                    // Código para baja de videojuego
                    System.out.println("Baja de Videojuego");
                    break;
                case 3:
                    // Código para modificación de videojuego
                    System.out.println("Modificación de Videojuego");
                    break;
                case 4:
                    // Código para mostrar información de videojuego
                    System.out.println("Mostrar Información de Videojuego");
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void menuJugadores(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Gestión de Jugadores ===");
            System.out.println("1. Alta de Jugador");
            System.out.println("2. Baja de Jugador");
            System.out.println("3. Modificación de Jugador");
            System.out.println("4. Consulta de Jugador");
            System.out.println("5. Listados de Jugadores");
            System.out.println("6. Top 10 Jugadores con Mayor Experiencia");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Código para alta de jugador
                    System.out.println("Alta de Jugador");
                    break;
                case 2:
                    // Código para baja de jugador
                    System.out.println("Baja de Jugador");
                    break;
                case 3:
                    // Código para modificación de jugador
                    System.out.println("Modificación de Jugador");
                    break;
                case 4:
                    // Código para consulta de jugador
                    System.out.println("Consulta de Jugador");
                    break;
                case 5:
                    // Código para listados de jugadores
                    System.out.println("Listados de Jugadores");
                    break;
                case 6:
                    // Código para top 10 jugadores con mayor experiencia
                    System.out.println("Top 10 Jugadores con Mayor Experiencia");
                    break;
                case 7:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 7);
    }

    public static void menuPartidas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Gestión de Partidas ===");
            System.out.println("1. Actualizar Nivel de Jugador");
            System.out.println("2. Actualizar Experiencia de Jugador");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Código para actualizar nivel de jugador
                    System.out.println("Actualizar Nivel de Jugador");
                    break;
                case 2:
                    // Código para actualizar experiencia de jugador
                    System.out.println("Actualizar Experiencia de Jugador");
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }
}
