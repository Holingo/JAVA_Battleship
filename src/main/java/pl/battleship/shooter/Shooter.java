package pl.battleship.shooter;

import pl.battleship.model.Board;
import pl.battleship.model.Coordinate;

public interface Shooter {
    Coordinate shoot(Board board);
}
