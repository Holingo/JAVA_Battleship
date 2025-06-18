package pl.battleship;

import pl.battleship.stats.*;

import java.util.Scanner;

public class MainMenu {
    public static void show(String username) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n== MENU GŁÓWNE ==");
            System.out.println("[1] Rozpocznij grę");
            System.out.println("[2] Zobacz statystyki");
            System.out.println("[3] Twórcy gry");
            System.out.println("[4] Wyjście");
            System.out.print("Wybierz opcję: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    Game game = new Game(username);
                    game.start();
                    break;
                case "2":
                    GameStats stats = StatsLoader.loadStats(username);
                    System.out.println(stats != null ? stats : "Brak statystyk dla użytkownika.");
                    break;
                case "3":
                    System.out.println("Twórcy: Oskar Świątek, Bartek Szołdrowski, Mateusz Więcek, 2025");
                    break;
                case "4":
                    System.out.println("Do zobaczenia, " + username + "!");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
