package pl.battleship.stats;

import java.beans.XMLDecoder;
import java.io.*;

public class StatsLoader {

    public static GameStats loadStats(String username) {
        String filePath = "stats/stats_" + username + ".xml";
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Stats file not found");
            return null;
        }

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            return (GameStats) decoder.readObject();
        } catch (IOException | ClassCastException e) {
            System.err.println("Błąd podczas wczytywania statystyk: " + e.getMessage());
            return null;
        }
    }
}
