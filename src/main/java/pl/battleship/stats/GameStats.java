package pl.battleship.stats;

public class GameStats {
    private String username;
    private int shotsFired;
    private int hits;
    private boolean won;

    public GameStats() {} // Dla XMLEncoder

    public GameStats(String username, int shotsFired, int hits, boolean won) {
        this.username = username;
        this.shotsFired = shotsFired;
        this.hits = hits;
        this.won = won;
    }

    public String getUsername() { return username; }
    public int getShotsFired() { return shotsFired; }
    public int getHits() { return hits; }
    public boolean isWon() { return won; }

    public void setUsername(String username) { this.username = username; }
    public void setShotsFired(int shotsFired) { this.shotsFired = shotsFired; }
    public void setHits(int hits) { this.hits = hits; }
    public void setWon(boolean won) { this.won = won; }
}
