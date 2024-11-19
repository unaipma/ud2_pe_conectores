package modelos;

public class PlayerProgress {
    private int playerId;
    private String nickName;
    private int experience;
    private int lifeLevel;
    private int coins;
    private int sessionCount;
    private String lastLogin; // Debe estar en formato "YYYY-MM-DD HH:MM:SS"

    // Constructor, getters y setters
    public PlayerProgress(int playerId, String nickName, int experience, int lifeLevel, int coins, int sessionCount, String lastLogin) {
        this.playerId = playerId;
        this.nickName = nickName;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.sessionCount = sessionCount;
        this.lastLogin = lastLogin;
    }

    public int getPlayerId() { return playerId; }
    public String getNickName() { return nickName; }
    public int getExperience() { return experience; }
    public int getLifeLevel() { return lifeLevel; }
    public int getCoins() { return coins; }
    public int getSessionCount() { return sessionCount; }
    public String getLastLogin() { return lastLogin; }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    @Override
    public String toString() {
        return "PlayerProgress{" + "playerId=" + playerId + ", nickName=" + nickName + ", experience=" + experience + ", lifeLevel=" + lifeLevel + ", coins=" + coins + ", sessionCount=" + sessionCount + ", lastLogin=" + lastLogin + '}';
    }
    
    
}

