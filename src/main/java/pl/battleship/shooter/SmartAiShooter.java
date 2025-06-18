package pl.battleship.shooter;

import pl.battleship.model.Board;
import pl.battleship.model.CellState;
import pl.battleship.model.Coordinate;

import java.util.*;

public class SmartAiShooter implements Shooter {

    private final List<Coordinate>  huntTargets = new ArrayList<>();
    private final Queue<Coordinate>  targetQueue  = new ArrayDeque<>();
    private final Set<Coordinate> tried = new HashSet<>();

    public SmartAiShooter() {
        // Szachwonica ( x + y ) % 2 == 0
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                if ((x + y) % 2 == 0) {
                    huntTargets.add(Coordinate.unsafe(x, y));
                }
            }
        }
        Collections.shuffle(huntTargets); // losowy porządek
    }

    @Override
    public Coordinate shoot(Board targetBoard) {
        Coordinate shot;

        while (true) {
            if (!targetQueue.isEmpty()) {
                shot = targetQueue.poll();
            } else if (!huntTargets.isEmpty()) {
                shot = huntTargets.remove(huntTargets.size() - 1);
            } else {
                // fallback, jakby wszystko sie zawiodlo
                shot = randomUntried();
            }

            if (shot != null && !tried.contains(shot)) {
                tried.add(shot);
                break;
            }
        }

        // Jesli trafiono statek, dodaj sasiednie pola do kolejki
        CellState state = targetBoard.peek(shot);
        if (state == CellState.SHIP) {
            for (Coordinate neighbor : getNeighbors(shot)) {
                if (!tried.contains(neighbor)) {
                    targetQueue.add(neighbor);
                }
            }
        }

        return shot;
    }

    private Coordinate randomUntried() {
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                Coordinate c = Coordinate.unsafe(x, y);
                if (!tried.contains(c)) { return c; }
            }
        }
        return null; // wszystko wykorzystane
    }

    private List<Coordinate> getNeighbors(Coordinate c) {
        List<Coordinate> neighbors = new ArrayList<>();
        int x = c.getX();
        int y = c.getY();

        if (x > 1) neighbors.add(Coordinate.unsafe(x - 1, y));
        if (x < 10) neighbors.add(Coordinate.unsafe(x + 1, y));
        if (y > 1) neighbors.add(Coordinate.unsafe(x, y - 1));
        if (y < 10) neighbors.add(Coordinate.unsafe(x, y + 1));

        return neighbors;
    }
}
