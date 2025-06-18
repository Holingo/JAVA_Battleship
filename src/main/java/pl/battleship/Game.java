package pl.battleship;

import pl.battleship.model.*;
import pl.battleship.shooter.*;
import pl.battleship.exception.*;

import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n//////////////////////////////////////////////////////////");
        System.out.println("////                  Battleship                      ////");
        System.out.println("//////////////////////////////////////////////////////////\n");

        while (true) {
            System.out.println("Wybierz jak chcesz generować plansze");
            System.out.println("[0] Automatyczne generowanie\n[1] Manualne generowanie");

            try{
                int choice = scanner.nextInt();
                if(choice == 0) {
                    playerBoard.placeAllShipsRandom();
                    break;
                }
                else if(choice == 1) {
                    playerBoard.placeAllShipsManual();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }

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

            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (Exception e) {
                System.out.println("Unable to clear screen.");
            }

            System.out.println(name + " fires at " + coord + ": " + result);
            System.out.println("Your Board:"); playerBoard.print(true);
            System.out.println("Opponent Board:"); aiBoard.print(false);
        } catch (InvalidPositionException|AlreadyShotException e) {
            System.out.println(e.getMessage());
        }
    }
}
