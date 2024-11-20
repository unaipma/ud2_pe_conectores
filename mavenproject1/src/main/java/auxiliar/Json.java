
package auxiliar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Json {
    private static final String FILE_NAME = System.getProperty("user.dir") + "/config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructor que verifica si el archivo de configuración existe, y lo crea si no.
     */
    public Json() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Archivo " + FILE_NAME + " creado exitosamente.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo JSON: " + e.getMessage());
        }
    }

    /**
     * Guarda un objeto de configuración en el archivo JSON.
     *
     * @param config Objeto de tipo {@link JuegoConf} que contiene la configuración a guardar.
     */
    public static void saveConfig(JuegoConf config) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(config, writer);
            System.out.println("Configuración guardada en: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error al guardar la configuración: " + e.getMessage());
        }
    }

    /**
     * Carga un objeto de configuración desde el archivo JSON.
     *
     * @return Un objeto de tipo {@link JuegoConf}, o {@code null} si ocurre un error
     *         o el archivo no existe o está vacío.
     */
    public static JuegoConf loadConfig() {
        File file = new File(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            System.err.println("El archivo de configuración no existe o está vacío.");
            return null;
        }

        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, JuegoConf.class);
        } catch (IOException e) {
            System.err.println("Error al leer la configuración: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método principal que sirve como ejemplo de uso para guardar y cargar configuraciones.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        JuegoConf config = new JuegoConf("127.0.0.1", 8080, "admin", "12345", "player1");
        saveConfig(config);

        JuegoConf loadedConfig = loadConfig();
        if (loadedConfig != null) {
            System.out.println("Configuración cargada:");
            System.out.println("Host: " + loadedConfig.getHost());
            System.out.println("Port: " + loadedConfig.getPort());
            System.out.println("User: " + loadedConfig.getUser());
            System.out.println("Pass: " + loadedConfig.getPass());
            System.out.println("Nick Name: " + loadedConfig.getNick_name());
        }
    }
}