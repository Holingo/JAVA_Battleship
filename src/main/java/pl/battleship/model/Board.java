package pl.battleship.model;

import pl.battleship.exception.*;
import java.util.*;

public class Board {
    private CellState[][] grid = new CellState[11][11];
    private List<Ship> ships = new ArrayList<>();

    public Board() {
        for(int i=1;i<=10;i++) for(int j=1;j<=10;j++) grid[i][j] = CellState.EMPTY;
    }

    public void placeAllShipsManual() {
        Scanner sc = new Scanner(System.in);
        for (Ship ship : Arrays.asList(new Battleship4(), new Cruiser3(), new Cruiser3(),
                                      new Destroyer2(), new Destroyer2(), new Destroyer2(),
                                      new Submarine1(), new Submarine1(), new Submarine1(), new Submarine1())) {
            while (true) {
                try {
                    System.out.println("Place your " + ship.getName() + " (size=" + ship.getSize() + ") orient 1-vert,0-hor");
                    int o = sc.nextInt(); System.out.print("X: "); int x = sc.nextInt(); System.out.print("Y: "); int y = sc.nextInt();
                    ship.setPosition(new Coordinate(x, y), o == 1);
                    validateAndPlaceShip(ship);
                    ships.add(ship);
                    print(true);
                    break;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public void placeAllShipsRandom() {
        Random r = new Random();
        for (Ship ship : Arrays.asList(new Battleship4(), new Cruiser3(), new Cruiser3(),
                                      new Destroyer2(), new Destroyer2(), new Destroyer2(),
                                      new Submarine1(), new Submarine1(), new Submarine1(), new Submarine1())) {
            while (true) {
                try {
                    int o = r.nextInt(2), x = r.nextInt(10) + 1, y = r.nextInt(10) + 1;
                    ship.setPosition(new Coordinate(x, y), o == 1);
                    validateAndPlaceShip(ship);
                    ships.add(ship);
                    break;
                } catch (Exception ignored) {}
            }
        }
    }

    private void validateAndPlaceShip(Ship ship) throws InvalidPositionException {
        for (Coordinate c : ship.getCoordinates()) {
            if (grid[c.getX()][c.getY()] != CellState.EMPTY)
                throw new InvalidPositionException("Occupied: " + c);
            for (int dx = -1; dx <= 1; dx++) for (int dy = -1; dy <= 1; dy++) {
                int nx = c.getX() + dx, ny = c.getY() + dy;
                if (nx >= 1 && nx <= 10 && ny >= 1 && ny <= 10 && grid[nx][ny] == CellState.SHIP)
                    throw new InvalidPositionException("Too close to another ship: (" + nx + "," + ny + ")");
            }
        }
        for (Coordinate c : ship.getCoordinates()) grid[c.getX()][c.getY()] = CellState.SHIP;
    }

    public CellState fire(Coordinate c) throws InvalidPositionException, AlreadyShotException {
        CellState s = grid[c.getX()][c.getY()];
        if (s == CellState.MISS || s == CellState.HIT) throw new AlreadyShotException("Already shot: " + c);
        if (s == CellState.SHIP) {
            grid[c.getX()][c.getY()] = CellState.HIT;
            ships.stream().filter(sh -> sh.hit(c) && sh.isSunk()).findFirst();
            return CellState.HIT;
        } else {
            grid[c.getX()][c.getY()] = CellState.MISS;
            return CellState.MISS;
        }
    }

    public boolean allShipsSunk() { return ships.stream().allMatch(Ship::isSunk); }

    public void print(boolean showShips) {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) System.out.print(i + " ");
        System.out.println();
        for (int y = 1; y <= 10; y++) {
            System.out.print(y + (y < 10 ? " " : ""));
            for (int x = 1; x <= 10; x++) {
                CellState s = grid[x][y];
                char ch = '.';
                if (s == CellState.MISS) ch = 'o';
                else if (s == CellState.HIT) ch = 'x';
                else if (s == CellState.SHIP && showShips) ch = 's';
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
