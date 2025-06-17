package pl.battleship.model;

import pl.battleship.exception.InvalidPositionException;
import java.util.*;

public class Battleship4 extends Ship {
    private static final int SIZE = 4;

    @Override
    public int getSize() { return SIZE; }

    @Override
    protected List<Coordinate> calculateCoordinates(Coordinate start, boolean vertical) throws InvalidPositionException {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            int x = start.getX() + (vertical ? 0 : i);
            int y = start.getY() + (vertical ? i : 0);
            list.add(new Coordinate(x, y));
        }
        return list;
    }
}
