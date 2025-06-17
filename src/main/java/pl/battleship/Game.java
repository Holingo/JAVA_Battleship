package pl.battleship;

import pl.battleship.model.*;
import pl.battleship.shooter.*;
import pl.battleship.exception.*;

public class Game {
    private Board playerBoard;
    private Board aiBoard;
    private Shooter playerShooter;
    private Shooter aiShooter;

    public Game() {
        this.playerBoard = new Board();
        this.aiBoard = new Board();
        this.playerShooter = new HumanShooter();
        this.aiShooter = new AiShooter();
    }

    public void start() {
        System.out.println("Welcome to Battleship!");
        playerBoard.placeAllShipsRandom();
        aiBoard.placeAllShipsRandom();
        while (true) {
            takeTurn(playerShooter, aiBoard, "Your");
            if (aiBoard.allShipsSunk()) { System.out.println("Congratulations! You won!"); break; }
            takeTurn(aiShooter, playerBoard, "AI");
            if (playerBoard.allShipsSunk()) { System.out.println("AI won. Better luck next time."); break; }
        }
    }

    private void takeTurn(Shooter shooter, Board targetBoard, String name) {
        System.out.println(name + "'s turn.");
        try {
            Coordinate coord = shooter.shoot(targetBoard);
            CellState result = targetBoard.fire(coord);
            System.out.println(name + " fires at " + coord + ": " + result);
            System.out.println("Your Board:"); playerBoard.print(true);
            System.out.println("Opponent Board:"); aiBoard.print(false);
        } catch (InvalidPositionException|AlreadyShotException e) {
            System.out.println(e.getMessage());
        }
    }
}
