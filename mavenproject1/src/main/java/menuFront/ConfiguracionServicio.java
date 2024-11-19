/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */

import Conexiones.Mysqlconexion;
import Conexiones.PostgreConexion;
import auxiliar.Json;
import auxiliar.JuegoConf;
import java.sql.SQLException;
import modelos.Jugador;

import java.util.Scanner;

public class ConfiguracionServicio {
    private Json json = new Json();
    private JuegoConf juegoConf;
    private Jugador jugador;
    private String tipoBD;

    public ConfiguracionServicio() {
        // Inicializar la configuración del jugador
        this.jugador = new Jugador();  // Sujeto a cómo se carga el jugador
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void mostrarMenuConfiguracion(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nConfiguración Inicial y Sincronización:");
            System.out.println("1. Configurar conexión con el servidor");
            System.out.println("2. Verificar credenciales del jugador");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

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
        scanner.nextLine();
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
                PostgreConexion.getConnectionNube();
            }
            
            
        }catch (SQLException e){
            System.out.println("Error");
            
        }
        juegoConf = new JuegoConf(host, puerto, usuario, host, nickName);
        json.saveConfig(juegoConf);
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
            credencialesValidas = juegoConf.verificarCredenciales(playerId, password);
            System.out.println(credencialesValidas ? "Credenciales válidas." : "Credenciales incorrectas.");
        } while (!credencialesValidas);
    }
}