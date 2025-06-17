package pl.battleship.model;

import pl.battleship.exception.InvalidPositionException;
import java.util.*;

public class Submarine1 extends Ship {
    private static final int SIZE = 1;

    @Override
    public int getSize() { return SIZE; }

    @Override
    protected List<Coordinate> calculateCoordinates(Coordinate start, boolean vertical) throws InvalidPositionException {
        return Collections.singletonList(start);
    }
}
