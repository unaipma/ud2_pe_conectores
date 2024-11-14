/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

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

    // Constructor
    public JuegoConf(String host, int port, String user, String pass, String nick_name) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.nick_name = nick_name;
    }

    // Getters y Setters
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

    public boolean verificarCredenciales(int playerId, String password) {
        // Convertimos el playerId a String y lo comparamos con el usuario almacenado
        String playerIdStr = String.valueOf(playerId);

        // Comprobamos si el playerId coincide con el usuario y si la contraseña es correcta
        return playerIdStr.equals(this.user) && password.equals(this.pass);
    }

}
