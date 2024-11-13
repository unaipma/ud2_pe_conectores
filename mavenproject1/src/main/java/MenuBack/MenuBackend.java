
package MenuBack;

import daos.*;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author eugeniolorentecristobal
 */
public class MenuBackend {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tipoBD;

        System.out.println("Bienvenido a la Aplicación de Backend");
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

        DAOFactory factory = new DAOFactory();

        while (true) {
                System.out.println("1. Gestionar Videojuegos");
                System.out.println("2. Gestionar Jugadores");
                System.out.println("3. Gestionar Partidas");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Implementar la lógica de alta, baja y modificación de videojuegos
                        try {
                            new VideojuegoMenu(factory.getVideojuegoDAO(tipoBD));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //HECHO
                        break;
                    case 2:
                        // Implementar lógica para la gestión de jugadores
                        try {
                            new JugadorMenu(factory.getJugadorDAO(tipoBD));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //HECHO
                        break;
                    case 3:
                        // Implementar lógica para gestionar el progreso de los jugadores
                        try {
                            new PartidaMenu(factory.getPartidaDAO(tipoBD));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //HECHO
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
}