/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuFront;

/**
 *
 * @author eugeniolorentecristobal
 */

import Conexiones.ConexionSelector;
import Conexiones.Mysqlconexion;
import Conexiones.PostgreConexion;
import auxiliar.Json;
import auxiliar.JuegoConf;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import auxiliar.Libreriaaux;
import modelos.Jugador;

import java.util.Scanner;

public class ConfiguracionServicio {
    private Json json = new Json();
    private JuegoConf juegoConf;
    private Jugador jugador;
    private String tipoBD;
    private static boolean credenciales;

    /**
     * Constructor de la clase ConfiguracionServicio.
     *
     * @param jugador El jugador actual.
     * @param juegoConf La configuración del juego.
     */
    public ConfiguracionServicio(Jugador jugador, JuegoConf juegoConf) {
        this.jugador = jugador;
        this.juegoConf = juegoConf;
    }

    public static boolean isCredenciales (){return credenciales;}
    public static void setCredenciales (boolean validado){credenciales = validado;}

    /**
     * Obtiene el jugador actual.
     *
     * @return El jugador.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Muestra el menú de configuración y sincronización.
     * Permite configurar la conexión con el servidor y verificar las credenciales del jugador.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    public void mostrarMenuConfiguracion(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nConfiguración Inicial y Sincronización:");
            System.out.println("1. Configurar conexión con el servidor");
            System.out.println("2. Verificar credenciales del jugador");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");
            opcion = Libreriaaux.compruebaNumero();
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

    /**
     * Configura la conexión al servidor de la base de datos.
     * Permite seleccionar entre MySQL o PostgreSQL y guarda la configuración en un archivo JSON.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
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
        Connection conexionData = ConexionSelector.obtenerConexion(tipoBD);

        try {
            juegoConf.setHost(conexionData.getMetaData().getURL().split("//")[1].split(":")[0]); // Extraer host de la URL
            juegoConf.setPort(obtenerPuertoDesdeURL(conexionData.getMetaData().getURL()));
            juegoConf.setUser(conexionData.getMetaData().getUserName()); // Obtener usuario
            juegoConf.setPass(""); // Asignar manualmente si no está disponible en metadata
            juegoConf.setNick_name(jugador.getNick());
        } catch (SQLException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error al configurar JuegoConf: " + e.getMessage());
        }

        json.saveConfig(juegoConf);
        System.out.println("\nConexión configurada correctamente:");
        System.out.println("Host: " + juegoConf.getHost());
        System.out.println("Puerto: " + juegoConf.getPort());
        System.out.println("Usuario: " + juegoConf.getUser());
        System.out.println("NickName: " + juegoConf.getNick_name());
    }

    /**
     * Verifica las credenciales del jugador ingresadas por el usuario.
     * Solicita el ID y la contraseña del jugador y valida las credenciales con las almacenadas en la configuración.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private void verificarCredencialesJugador(Scanner scanner) {
        boolean credencialesValidas;

            System.out.print("Introduce el nombre del jugador: ");
            String playerId = scanner.nextLine();
            System.out.print("Introduce la contraseña: ");
            String password = scanner.next();
            credencialesValidas = juegoConf.verificarCredenciales(playerId, password);
            System.out.println(credencialesValidas ? "Credenciales válidas." : "Credenciales incorrectas.");
            credenciales = credencialesValidas;

    }

    /**
     * Obtiene el puerto de la conexión a partir de la URL.
     * Si no se encuentra el puerto, devuelve el valor predeterminado según el esquema de la URL.
     *
     * @param url La URL de la conexión.
     * @return El puerto de la conexión.
     */
    private int obtenerPuertoDesdeURL(String url) {
        try {
            URI uri = new URI(url.replace("jdbc:", ""));
            return uri.getPort() != -1 ? uri.getPort() : obtenerPuertoPorDefecto(uri.getScheme());
        } catch (URISyntaxException e) {
            System.err.println("Error al analizar la URL de conexión: " + e.getMessage());
            return -1; // Indica que no se pudo obtener el puerto
        }
    }

    /**
     * Obtiene el puerto por defecto según el esquema de la URL de conexión.
     *
     * @param esquema El esquema de la URL (mysql o postgresql).
     * @return El puerto por defecto.
     */
    private int obtenerPuertoPorDefecto(String esquema) {
        switch (esquema) {
            case "mysql":
                return 3306;
            case "postgresql":
                return 5432;
            default:
                return -1; // Esquema desconocido
        }
    }
}