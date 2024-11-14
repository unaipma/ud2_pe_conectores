
package auxiliar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author unaip
 */
public class Json {
    
    private static  String FILE_NAME = "config.json";
    private static  Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Método para guardar la configuración en un archivo JSON
    public static void saveConfig(JuegoConf config) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(config, writer);
            System.out.println("Configuración guardada en " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error al guardar la configuración: " + e.getMessage());
        }
    }

    // Método para leer la configuración desde un archivo JSON
    public static JuegoConf loadConfig() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, JuegoConf.class);
        } catch (IOException e) {
            System.err.println("Error al leer la configuración: " + e.getMessage());
            return null;
        }
    }
    

    //ejemplo de uso
    public static void main(String[] args) {
        //se establecen parametros
        JuegoConf config = new JuegoConf("127.0.0.1", 8080, "admin", "12345", "player1");
        saveConfig(config);

        // Leer la configuración desde el archivo JSON
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

