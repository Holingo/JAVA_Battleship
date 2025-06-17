package pl.battleship.stats;

import java.beans.XMLEncoder;
import java.io.*;

public class StatsSaver {
    public static void saveStats(GameStats stats) {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream("stats/stats_" + stats.getUsername() + ".xml")))) {
            encoder.writeObject(stats);
            System.out.println("Saved stats XML to " + stats.getUsername());
        } catch (IOException e) {
            System.err.println("Error saving stats XML to " + stats.getUsername() + ", " + e.getMessage());
        }
    }
}
