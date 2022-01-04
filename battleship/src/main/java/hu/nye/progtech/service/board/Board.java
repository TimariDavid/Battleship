package hu.nye.progtech.service.board;

import java.util.Locale;
import java.util.Scanner;

import hu.nye.progtech.model.Ship;
import hu.nye.progtech.model.ShipSize;
import hu.nye.progtech.service.field.IGameField;
import hu.nye.progtech.service.field.ShipField;
import hu.nye.progtech.service.field.WaterField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Ebben az osztályban kezelem a pályát.
 */
public class Board {
    private static final char WATER = '~';
    private static final int BOARD_SIZE = 10;
    private static final char[] BOARD_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";

    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);

    private Scanner scanner;
    private IGameField[][] shipsTable;
    private IGameField[][] hitsTable;
    private static final Ship[] ships;

    static {
        ships = new Ship[] {
                new Ship("Carrier", ShipSize.CARRIER),
                new Ship("Battleship", ShipSize.BATTLESHIP),
                new Ship("Cruiser", ShipSize.CRUISER),
                new Ship("Submarine", ShipSize.SUBMARINE),
                new Ship("Destroyer", ShipSize.DESTROYER)
        };
    }

    public Board() {
        this.scanner = new Scanner(System.in);
        this.shipsTable = new IGameField[BOARD_SIZE][BOARD_SIZE];
        this.hitsTable = new IGameField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                shipsTable[i][j] = new WaterField();
                hitsTable[i][j] = new WaterField();
            }
        }
    }

    /**
     *  A hajók elhelyezésének interakcióját csináltam meg.
     */
    public void placeShipsOnBoard() {
        for (Ship ship : ships) {
            boolean horizontal = askValidShipDirection();
            int[] startingPoint = askValidStartingPoint(ship, horizontal);
            placeValidShip(ship, startingPoint, horizontal);

            printBoard();
        }
    }

    /**
     * Lekérem egy koordinátának a helyét.
     *
     * @param x koordináta első paramétere
     * @param y koordináta második paramétere
     * @return a játéktérnek a kért koordinátájú mezője
     */
    public IGameField getField(int x, int y) {
        if (!isInsideBoard(x, y)) {
            throw new IllegalArgumentException("Outside board - try again: ");
        }
        return shipsTable[y][x];
    }

    /**
     *  A játéktér kiiratása történik itt.
     */
    public void printBoard() {
        System.out.print("\t");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(BOARD_LETTERS[i] + "\t");
        }

        System.out.print("\t\t");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(BOARD_LETTERS[i] + "\t");
        }

        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%d\t", i + 1);
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf("%s\t", shipsTable[i][j].getIcon());
            }

            System.out.print("\t" + (1 + i) + "\t");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf("%s\t", hitsTable[i][j].getIcon());
            }

            System.out.println();
        }
    }


    private boolean askValidShipDirection() {
        System.out.printf("%nDo you want to place the ship horizontally (H) or vertically (V)?");
        String direction;
        do {
            direction = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        } while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

        return HORIZONTAL.equals(direction);
    }

    private int[] askValidStartingPoint(Ship ship, boolean horizontal) {

        int[] point = new int[2];

        do {
            System.out.printf("%nEnter position of %s (Example: 'A 1' or 'D 9')(length  %d): ", ship.getName(), ship.getSize());

            String xs = scanner.next();
            int y = scanner.nextInt();

            int x;
            switch (xs) {
                case "A": x = 1;
                    break;
                case "B": x = 2;
                    break;
                case "C": x = 3;
                    break;
                case "D": x = 4;
                    break;
                case "E": x = 5;
                    break;
                case "F": x = 6;
                    break;
                case "G": x = 7;
                    break;
                case "H": x = 8;
                    break;
                case "I": x = 9;
                    break;
                case "J": x = 10;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + xs);
            }

            point[0] = x;
            point[1] = y;

        } while (!isValidStartingPoint(point, ship.getSize(), horizontal));

        return point;
    }

    private boolean isValidStartingPoint(int[] point, int length, boolean horizontal) {
        int xdifference = 0;
        int ydifference = 0;
        if (horizontal) {
            xdifference = 1;
        } else {
            ydifference = 1;
        }

        int x = point[0] - 1;
        int y = point[1] - 1;
        if (!isInsideBoard(x, y) ||
                (!isInsideBoard(x + length, y) && horizontal) ||
                (!isInsideBoard(x, y + length) && !horizontal)
        ) {
            LOGGER.warn("The coordinate is incorrect");
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (shipsTable[point[1] + i * ydifference - 1][point[0] + i * xdifference - 1].getIcon() != WATER) {
                return false;
            }
        }
        return true;
    }

    private void placeValidShip(Ship ship, int[] startingPoint, boolean horizontal) {
        int xdifference = 0;
        int ydifference = 0;
        if (horizontal) {
            xdifference = 1;
        } else {
            ydifference = 1;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            shipsTable[startingPoint[1] + i * ydifference - 1][startingPoint[0] + i * xdifference - 1] = new ShipField(ship);
        }
    }

    private boolean isInsideBoard(int x, int y) {
        return x <= BOARD_SIZE && x >= 0
                && y <= BOARD_SIZE && y >= 0;
    }
}