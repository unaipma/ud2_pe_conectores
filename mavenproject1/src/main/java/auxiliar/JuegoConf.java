/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author unaip
 */
public class JuegoConf {

    private String host;
    private int port;
    private String user;
    private String pass;
    private String nick_name;

    private int partidaId;
    private int videojuegoId;

    // Constructor
    /**
     * Constructor para inicializar la configuración básica del juego.
     *
     * @param host      Dirección del servidor.
     * @param port      Puerto del servidor.
     * @param user      Nombre de usuario.
     * @param pass      Contraseña del usuario.
     * @param nick_name Apodo del jugador.
     */
    public JuegoConf(String host, int port, String user, String pass, String nick_name) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.nick_name = nick_name;
    }

    /**
     * Constructor para inicializar la configuración del juego con un ID de partida.
     *
     * @param host      Dirección del servidor.
     * @param port      Puerto del servidor.
     * @param user      Nombre de usuario.
     * @param pass      Contraseña del usuario.
     * @param nick_name Apodo del jugador.
     * @param partidaId ID de la partida.
     */
    public JuegoConf(String host, int port, String user, String pass, String nick_name, int partidaId) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.nick_name = nick_name;
        this.partidaId = partidaId;
    }

    /**
     * Constructor por defecto.
     */
    public JuegoConf() {
    }

    // Métodos Getters y Setters

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(int partidaId) {
        this.partidaId = partidaId;
    }

    public int getVideojuegoId() {
        return videojuegoId;
    }

    public void setVideojuegoId(int videojuegoId) {
        this.videojuegoId = videojuegoId;
    }

    /**
     * Verifica las credenciales de un jugador basándose en su ID y contraseña.
     *
     * @param playerId  ID del jugador.
     * @param password  Contraseña proporcionada.
     * @return {@code true} si las credenciales son válidas, de lo contrario {@code false}.
     */
    public boolean verificarCredenciales(String playerId, String password) {
        String playerIdStr = String.valueOf(playerId);
        return playerIdStr.equals(this.nick_name) && password.equals(this.pass);
    }
}