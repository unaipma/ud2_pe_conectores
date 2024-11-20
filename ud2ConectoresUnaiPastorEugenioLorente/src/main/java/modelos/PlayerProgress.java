package modelos;

import java.sql.Date;
import java.time.LocalDate;

public class PlayerProgress {
    private int playerId;
    private String nickName;
    private int experience;
    private int lifeLevel;
    private int coins;
    private int sessionCount;
    private Date lastLogin; // Debe estar en formato "YYYY-MM-DD HH:MM:SS"
    private int partidaId;

    /**
     * Constructor para crear un nuevo objeto {@link PlayerProgress} con todos los atributos excepto
     * la partidaId.
     *
     * @param playerId El identificador del jugador.
     * @param nickName El nombre del jugador.
     * @param experience La experiencia del jugador.
     * @param lifeLevel El nivel de vida del jugador.
     * @param coins La cantidad de monedas del jugador.
     * @param lastLogin La fecha del último inicio de sesión del jugador.
     */
    public PlayerProgress(int playerId, String nickName, int experience, int lifeLevel, int coins, Date lastLogin) {
        this.playerId = playerId;
        this.nickName = nickName;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.lastLogin = lastLogin;
    }
    /**
     * Constructor para crear un nuevo objeto {@link PlayerProgress} con todos los atributos, incluyendo
     * la partidaId.
     *
     * @param playerId El identificador del jugador.
     * @param nickName El nombre del jugador.
     * @param experience La experiencia del jugador.
     * @param lifeLevel El nivel de vida del jugador.
     * @param coins La cantidad de monedas del jugador.
     * @param lastLogin La fecha del último inicio de sesión del jugador.
     * @param partidaId El identificador de la partida asociada al progreso.
     */
    public PlayerProgress(int playerId, String nickName, int experience, int lifeLevel, int coins, Date lastLogin, int partidaId) {
        this.playerId = playerId;
        this.nickName = nickName;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.lastLogin = lastLogin;
        this.partidaId = partidaId;
    }

    /**
     * Obtiene el identificador del jugador.
     *
     * @return El identificador del jugador.
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Obtiene la experiencia del jugador.
     *
     * @return La experiencia del jugador.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Obtiene el nivel de vida del jugador.
     *
     * @return El nivel de vida del jugador.
     */
    public int getLifeLevel() {
        return lifeLevel;
    }

    /**
     * Obtiene la cantidad de monedas del jugador.
     *
     * @return La cantidad de monedas del jugador.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Obtiene el número de sesiones jugadas por el jugador.
     *
     * @return El número de sesiones.
     */
    public int getSessionCount() {
        return sessionCount;
    }

    /**
     * Obtiene la fecha del último inicio de sesión del jugador.
     *
     * @return La fecha del último inicio de sesión.
     */
    public Date getLastLogin() {
        return lastLogin = Date.valueOf(LocalDate.now());
    }

    /**
     * Obtiene el número del ID de la partida por el jugador.
     *
     * @return El número de sesiones.
     */
    public int getPartidaId() {
        return partidaId;
    }
    /**
     * Establece el identificador del jugador.
     *
     * @param playerId El identificador del jugador.
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nickName El nombre del jugador.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Establece la experiencia del jugador.
     *
     * @param experience La experiencia del jugador.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Establece el nivel de vida del jugador.
     *
     * @param lifeLevel El nivel de vida del jugador.
     */
    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    /**
     * Establece la cantidad de monedas del jugador.
     *
     * @param coins La cantidad de monedas del jugador.
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Establece el número de sesiones jugadas por el jugador.
     *
     * @param sessionCount El número de sesiones jugadas.
     */
    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    /**
     * Establece la fecha del último inicio de sesión del jugador.
     *
     * @param lastLogin La fecha del último inicio de sesión.
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Establece el número de sesiones jugadas por el jugador.
     *
     * @param partidaId El número de sesiones jugadas.
     */
    public void setPartidaId(int partidaId) {
        this.partidaId = partidaId;
    }
    /**
     * Devuelve una representación en formato de cadena de texto del progreso del jugador.
     *
     * @return La representación del progreso del jugador.
     */
    @Override
    public String toString() {
        return  "PlayerProgress{" + "playerId=" + playerId + ", nickName=" + nickName + ", experience=" + experience + ", lifeLevel=" + lifeLevel + ", coins=" + coins + ", sessionCount=" + sessionCount + ", lastLogin=" + lastLogin + '}';
    }
}
