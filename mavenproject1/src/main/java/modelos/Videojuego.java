/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author unaip
 */
public class Videojuego implements Serializable {
     private int game_id;
    private int isbn;
    private String title;
    private int player_count;
    private int total_sessions;
    private Date last_session;

    /**
     * @return the game_id
     */
    public int getGame_id() {
        return game_id;
    }

    /**
     * @param game_id the game_id to set
     */
    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    /**
     * @return the isbn
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the player_count
     */
    public int getPlayer_count() {
        return player_count;
    }

    /**
     * @param player_count the player_count to set
     */
    public void setPlayer_count(int player_count) {
        this.player_count = player_count;
    }

    /**
     * @return the total_sessions
     */
    public int getTotal_sessions() {
        return total_sessions;
    }

    /**
     * @param total_sessions the total_sessions to set
     */
    public void setTotal_sessions(int total_sessions) {
        this.total_sessions = total_sessions;
    }

    /**
     * @return the last_session
     */
    public Date getLast_session() {
        return last_session;
    }

    /**
     * @param last_session the last_session to set
     */
    public void setLast_session(Date last_session) {
        this.last_session = last_session;
    }

    public Videojuego(int game_id, int isbn, String title, int player_count, int total_sessions, Date last_session) {
        this.game_id = game_id;
        this.isbn = isbn;
        this.title = title;
        this.player_count = player_count;
        this.total_sessions = total_sessions;
        this.last_session = last_session;
    }
    
    
}

