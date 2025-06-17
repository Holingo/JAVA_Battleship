package pl.battleship;

import pl.battleship.model.*;
import pl.battleship.shooter.*;
import pl.battleship.exception.*;
import pl.battleship.stats.*;

import java.util.Scanner;

public class Game {
    private Board playerBoard;
    private Board aiBoard;
    private Shooter playerShooter;
    private Shooter aiShooter;

    private String username;
    private int shotsFired = 0;
    private int hits = 0;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your login: ");
        this.username = scanner.nextLine();

        this.playerBoard = new Board();
        this.aiBoard = new Board();
        this.playerShooter = new HumanShooter();
        this.aiShooter = new AiShooter();
    }

    public void start() {
        System.out.println("Welcome to Battleship!");
        playerBoard.placeAllShipsRandom();

        aiBoard.placeAllShipsRandom();
        aiBoard.saveBoardToFile(true, "ai_board.txt");
        while (true) {
            takeTurn(playerShooter, aiBoard, "Your");
            if (aiBoard.allShipsSunk()) { System.out.println("Congratulations! You won!"); break; }
            takeTurn(aiShooter, playerBoard, "AI");
            if (playerBoard.allShipsSunk()) { System.out.println("AI won. Better luck next time."); break; }
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
