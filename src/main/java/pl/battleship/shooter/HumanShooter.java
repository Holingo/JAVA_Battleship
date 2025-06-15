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
                System.out.print("Enter X: "); int x = scanner.nextInt();
                System.out.print("Enter Y: "); int y = scanner.nextInt();
                return new Coordinate(x, y);
            } catch (InvalidPositionException e) {
                System.out.println("Invalid coordinate: " + e.getMessage());
            }
        }
    }
}
