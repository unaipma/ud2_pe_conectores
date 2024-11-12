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
public class Partida implements Serializable {
    private int idpartida ;
    private int idjuego ;
    private int idjugador;
    private int monedas;
    private int exp;
    private int nivel;
    private Date ultimaconexion;

    public Partida() {
    }

    public Partida(int idpartida, int idjuego, int idjugador, int monedas, int exp, int nivel, Date ultimaconexion) {
        this.idpartida = idpartida;
        this.idjuego = idjuego;
        this.idjugador = idjugador;
        this.monedas = monedas;
        this.exp = exp;
        this.nivel = nivel;
        this.ultimaconexion = ultimaconexion;
    }
    
    
    
    /**
     * @return the idpartida
     */
    public int getIdpartida() {
        return idpartida;
    }

    /**
     * @param idpartida the idpartida to set
     */
    public void setIdpartida(int idpartida) {
        this.idpartida = idpartida;
    }

    /**
     * @return the idjuego
     */
    public int getIdjuego() {
        return idjuego;
    }

    /**
     * @param idjuego the idjuego to set
     */
    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    /**
     * @return the idjugador
     */
    public int getIdjugador() {
        return idjugador;
    }

    /**
     * @param idjugador the idjugador to set
     */
    public void setIdjugador(int idjugador) {
        this.idjugador = idjugador;
    }

    /**
     * @return the monedas
     */
    public int getMonedas() {
        return monedas;
    }

    /**
     * @param monedas the monedas to set
     */
    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    /**
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the ultimaconexion
     */
    public Date getUltimaconexion() {
        return ultimaconexion;
    }

    /**
     * @param ultimaconexion the ultimaconexion to set
     */
    public void setUltimaconexion(Date ultimaconexion) {
        this.ultimaconexion = ultimaconexion;
    }
                    
    
}


