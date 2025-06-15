package pl.battleship.model;

import pl.battleship.exception.InvalidPositionException;
import java.util.*;

public abstract class Ship {
    protected List<Coordinate> coords;
    protected List<Coordinate> hits;

    public abstract int getSize();

    public void setPosition(Coordinate start, boolean vertical) throws InvalidPositionException {
        coords = calculateCoordinates(start, vertical);
        hits = new ArrayList<>();
    }

    protected abstract List<Coordinate> calculateCoordinates(Coordinate start, boolean vertical) throws InvalidPositionException;

    public List<Coordinate> getCoordinates() { return coords; }
    public String getName() { return getClass().getSimpleName(); }
    public boolean hit(Coordinate c) {
        if (coords.contains(c)) { hits.add(c); return true; }
        return false;
    }
    public boolean isSunk() { return hits != null && hits.containsAll(coords); }
}
