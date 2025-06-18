package pl.battleship;

import pl.battleship.events.*;
import pl.battleship.model.*;
import pl.battleship.shooter.*;
import pl.battleship.exception.*;
import pl.battleship.stats.*;

import java.util.*;

public class Game {
    private Board playerBoard;
    private Board aiBoard;
    private Shooter playerShooter;
    private Shooter aiShooter;

    // Statystyki
    private String username;
    private int shotsFired = 0;
    private int hits = 0;

    // Eventy
    private boolean skipPlayerTurn = false;
    private boolean skipAiTurn = false;
    public void setSkipPlayerTurn(boolean skip) { this.skipPlayerTurn = skip; }
    public void setSkipAiTurn(boolean skip) { this.skipAiTurn = skip; }


    private boolean playerDoubleShot = false;
    private boolean aiDoubleShot = false;
    public void setPlayerDoubleShot(boolean val) { this.playerDoubleShot = val; }
    public void setAiDoubleShot(boolean val) { this.aiDoubleShot = val; }

    private int round = 0;
    private List<GameEvent> events = Arrays.asList(
            new SkipTurnEvent(),
            new DoubleShotEvent()
    );

    private Random rand = new Random();

    public Game(String username) {
        this.username = username;
        this.playerBoard = new Board();
        this.aiBoard = new Board();
        this.playerShooter = new HumanShooter();
        this.aiShooter = new SmartAiShooter();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.println("Welcome to Battleship!\n");

        while (true) {
            System.out.println("Wybierz jak chcesz generować plansze");
            System.out.println("[0] Automatyczne generowanie\n[1] Manualne generowanie");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0 || choice == 1) {
                    break;
                } else {
                    System.out.println("Błąd: wybierz 0 lub 1.");
                }
            } else {
                System.out.println("Błąd: wpisz cyfrę 0 lub 1.");
                scanner.next();
            }
        }

        // Generowanie plansze, ktora zostala wybrana
        if (choice == 0) {
            playerBoard.placeAllShipsRandom();
        } else {
            playerBoard.placeAllShipsManual();
        }

        // Tworzenie planszy AI - do testow by wiedziec jak wyglada
        aiBoard.placeAllShipsRandom();
        aiBoard.saveBoardToFile(true, "ai_board.txt");

        // Etap koncowy gdy ktos wygra
        while (true) {
            round++;

            // Co 3 rundy szansa na losowe wydarzenie
            if (round % 3 == 0 && rand.nextBoolean()) {
                GameEvent event = events.get(rand.nextInt(events.size()));
                event.trigger(this);
            }

            if (!skipPlayerTurn) {
                takeTurn(playerShooter, aiBoard, "Your");
                if (playerDoubleShot) {
                    System.out.println("Gracz strzela drugi raz!");
                    takeTurn(playerShooter, aiBoard, "Your");
                    playerDoubleShot = false;
                }
                if (aiBoard.allShipsSunk()) {
                    System.out.println("Congratulations! You won!");
                    break;
                }
            } else {
                System.out.println("Gracz opuszcza swoją turę.");
                skipPlayerTurn = false;
            }

            if (!skipAiTurn) {
                takeTurn(aiShooter, playerBoard, "AI");
                if (aiDoubleShot) {
                    System.out.println("AI strzela drugi raz!");
                    takeTurn(aiShooter, playerBoard, "AI");
                    aiDoubleShot = false;
                }
                if (playerBoard.allShipsSunk()) {
                    System.out.println("AI won. Better luck next time.");
                    break;
                }
            } else {
                System.out.println("AI opuszcza swoją turę.");
                skipAiTurn = false;
            }
        }

        boolean playerWon = aiBoard.allShipsSunk();
        GameStats stats = new GameStats(username, shotsFired, hits, playerWon);
        StatsSaver.saveStats(stats);
    }

    private void takeTurn(Shooter shooter, Board targetBoard, String name) {
        System.out.println(name + "'s turn.");
        try {
            Coordinate coord = shooter.shoot(targetBoard);
            CellState result = targetBoard.fire(coord);
            System.out.println(name + " fires at " + coord + ": " + result);
            if (shooter instanceof HumanShooter) {
                shotsFired++;
                if (result == CellState.HIT) {
                    hits++;
                }
            }
            System.out.println("Your Board:"); playerBoard.print(true);
            System.out.println("Opponent Board:"); aiBoard.print(false);
        } catch (InvalidPositionException|AlreadyShotException e) {
            System.out.println(e.getMessage());
        }
    }
}
