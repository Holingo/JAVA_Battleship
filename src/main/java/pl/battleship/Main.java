package pl.battleship;

import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Logger root = Logger.getLogger("");
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        root.addHandler(consoleHandler);
        LOGGER.info("Starting Battleship game");
        Game game = new Game();
        game.start();
    }
}
