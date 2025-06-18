package pl.battleship.model;

import pl.battleship.exception.InvalidPositionException;
import java.util.Objects;

public class Coordinate {
    private int x, y;

    // Glowny konstruktur z walidacja
    public Coordinate(int x, int y) throws InvalidPositionException {
        if (x < 1 || x > 10 || y < 1 || y > 10)
            throw new InvalidPositionException("Out of bounds: " + x + "," + y);
        this.x = x;
        this.y = y;
    }

    // Prywatny konstruktor bez walidacji
    private Coordinate() {}

    // Fabryczna metoda do tworzenia wspolrzednych bez walidacji (dla AI)
    public static Coordinate unsafe(int x, int y) {
        Coordinate c = new Coordinate();
        c.x = x;
        c.y = y;
        return c;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }

    @Override
    public String toString() { return "(" + x + "," + y + ")"; }

    @Override
    public boolean equals(Object o) {
        if ( this==o ) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate c = (Coordinate) o;
        return x == c.x && y == c.y;
    }

    @Override
    public int hashCode() { return Objects.hash(x,y); }
}
