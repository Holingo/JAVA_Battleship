package pl.battleship;

import java.util.logging.*;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting Battleship game");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swój login: ");
        String username = scanner.nextLine();

        MainMenu.show(username);
    }
}
