package hu.nye.progtech.service.player;

import java.util.Locale;
import java.util.Scanner;

import hu.nye.progtech.service.board.Board;
import hu.nye.progtech.service.field.Result;

/**
 * A játékosok adatai és lehetséges interakciói.
 */
public class HumanPlayer implements IPlayer {
    private int totalLivesLeft = 17;

    private int id;
    private Board board;
    private Scanner scanner;
    private String playerName;

    public HumanPlayer(int id) {
        this.id = id;
        this.board = new Board();
        this.scanner = new Scanner(System.in);
        this.playerName = playerName;
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void placeShips() {
        System.out.printf("%n======== " + yourName() + " place out your ships ========%n");
        board.placeShipsOnBoard();
    }

    @Override
    public void fireAt(IPlayer opponent) {
        System.out.printf("%n Alright %s - Enter coordinates for your attack: (Example: 'A 1' or 'D 9')", playerName);

        boolean isPointValid = false;
        while (!isPointValid) {
            try {
                Scanner scanner = new Scanner(System.in);

                String xs = scanner.next().toUpperCase(Locale.ROOT);
                int y = scanner.nextInt() - 1;

                int x;
                switch (xs) {
                    case "A": x = 0;
                        break;
                    case "B": x = 1;
                        break;
                    case "C": x = 2;
                        break;
                    case "D": x = 3;
                        break;
                    case "E": x = 4;
                        break;
                    case "F": x = 5;
                        break;
                    case "G": x = 6;
                        break;
                    case "H": x = 7;
                        break;
                    case "I": x = 8;
                        break;
                    case "J": x = 9;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + xs);
                }

                Result result = ((HumanPlayer) opponent).getBoard().getField(x, y).shootAt();

                if (result == Result.HIT || result == Result.DESTROYED) {
                    totalLivesLeft--;
                }

                isPointValid = true;

            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public int getTotalLivesLeft() {
        return totalLivesLeft;
    }

    @Override
    public String yourName() {
        System.out.println("What's your name?");
        playerName = scanner.next();

        return playerName;
    }

}