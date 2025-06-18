package pl.battleship.shooter;

import pl.battleship.model.*;
import pl.battleship.exception.*;
import java.util.*;

public class HumanShooter implements Shooter {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Coordinate shoot(Board board) {
        while (true) {
            try {
                System.out.print("Enter X: ");
                int x = readInt();
                System.out.print("Enter Y: ");
                int y = readInt();

                Coordinate coordinate = new Coordinate(x, y);
                return coordinate;

            } catch (InvalidPositionException e) {
                System.out.println("Invalid coordinate: " + e.getMessage());
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter an integer: ");
            }
        }
    }
}
