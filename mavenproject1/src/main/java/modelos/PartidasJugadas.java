/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.*;
import java.text.*;
import java.util.*;
/**
 *
 * @author eugeniolorentecristobal
 */
public class PartidasJugadas {
    private String gameId;
    private String playerId;
    private int experience;
    private int lifeLevel;
    private int coins;
    private Date sessionDate;

    // Constructor
    public PartidasJugadas(String gameId, String playerId, int experience, int lifeLevel, int coins, Date sessionDate) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.sessionDate = sessionDate != null ? sessionDate : new Date(); // Si no se pasa la fecha, se usa la fecha actual
    }

    // Método para actualizar la partida
    public void actualizarPartida(Integer experience, Integer lifeLevel, Integer coins) {
        if (experience != null) {
            this.experience += experience;
        }
        if (lifeLevel != null) {
            this.lifeLevel += lifeLevel;
        }
        if (coins != null) {
            this.coins += coins;
        }
    }

    // Método para guardar la partida en un archivo
    public void guardarPartida(String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            writer.write(String.format("%s,%s,%d,%d,%d,%s\n", gameId, playerId, experience, lifeLevel, coins, sdf.format(sessionDate)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método estático para cargar las partidas desde un archivo
    public static List<PartidasJugadas> cargarPartidas(String archivo) {
        List<PartidasJugadas> partidas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String gameId = data[0];
                String playerId = data[1];
                int experience = Integer.parseInt(data[2]);
                int lifeLevel = Integer.parseInt(data[3]);
                int coins = Integer.parseInt(data[4]);
                Date sessionDate = sdf.parse(data[5]);

                partidas.add(new PartidasJugadas(gameId, playerId, experience, lifeLevel, coins, sessionDate));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return partidas;
    }

    // Método para representar la partida en formato legible
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return String.format("PartidaJugadas(gameId=%s, playerId=%s, experience=%d, lifeLevel=%d, coins=%d, sessionDate=%s)",
                gameId, playerId, experience, lifeLevel, coins, sdf.format(sessionDate));
    }

    // Getters y Setters (opcional si necesitas acceder/modificar los atributos)
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }
}

