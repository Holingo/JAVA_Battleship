package pl.battleship.shooter;

import pl.battleship.model.*;
import pl.battleship.exception.*;
import java.util.*;

public class AiShooter implements Shooter {
    private Random rand = new Random();

    @Override
    public Coordinate shoot(Board board) {
        while (true) {
            try {
                int x = rand.nextInt(10) + 1;
                int y = rand.nextInt(10) + 1;
                return new Coordinate(x, y);
            } catch (InvalidPositionException e) {
                // retry
            }
        }
    }
}
