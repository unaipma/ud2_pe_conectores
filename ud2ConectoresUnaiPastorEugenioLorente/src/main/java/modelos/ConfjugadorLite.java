/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author unaip
 */
public class ConfjugadorLite {

    private String resolucion;
    private String idioma;
    private boolean sound_enabled;
    /**
     * Constructor que inicializa los valores de configuración del jugador.
     *
     * @param resolucion La resolución de pantalla seleccionada por el jugador.
     * @param idioma El idioma seleccionado por el jugador.
     * @param sound_enabled Indica si el sonido está habilitado o no.
     */
    public ConfjugadorLite(int id, int player_id, String resolucion, String idioma, boolean sound_enabled) {

        this.resolucion = resolucion;
        this.idioma = idioma;
        this.sound_enabled = sound_enabled;
    }

    public ConfjugadorLite() {
    }

    /**
     * @return the resolucion
     */
    public String getResolucion() {
        return resolucion;
    }

    /**
     * @param resolucion the resolucion to set
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the sound_enabled
     */
    public boolean isSound_enabled() {
        return sound_enabled;
    }

    /**
     * @param sound_enabled the sound_enabled to set
     */
    public void setSound_enabled(boolean sound_enabled) {
        this.sound_enabled = sound_enabled;
    }

    @Override
    public String toString() {
        return "ConfjugadorLite{" + "resolucion=" + resolucion + ", idioma=" + idioma + ", sound_enabled=" + sound_enabled + '}';
    }

}