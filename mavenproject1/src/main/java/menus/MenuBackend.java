
package menus;

import daos.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelos.Videojuego;

public class MenuBackend {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //MySQLDAO mySQLDAO = DAOFactory.getMySQLDAO();

        System.out.println("Bienvenido a la Aplicación de Backend");

        while (true) {
            System.out.println("1. Gestionar Videojuegos");
            System.out.println("2. Gestionar Jugadores");
            System.out.println("3. Gestionar Partidas");
            System.out.println("4. Ver Estadísticas de Juegos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implementar la lógica de alta, baja y modificación de videojuegos
                    mostrarMenuGestionVideoJuegos();
                    break;
                case 2:
                    // Implementar lógica para la gestión de jugadores
                    mostrarMenuGestionJugadores();
                    break;
                case 3:
                    // Implementar lógica para gestionar el progreso de los jugadores
                    mostrarMenuGestionPartidas();
                    break;
                case 4:
                    //mySQLDAO.getGameStats();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
    
    public static void mostrarMenuGestionVideoJuegos() {
        
    }
    
    public static void mostrarMenuGestionJugadores() {
        
    }
    
    public static void mostrarMenuGestionPartidas() {
        
    }
}