package hu.nye.progtech.service.game;

import hu.nye.progtech.service.player.HumanPlayer;

/**
 *  A játék menetét és lépéseit végző osztály.
 */
public class Game {
    private HumanPlayer[] players;

    public Game(HumanPlayer[] players) {
        this.players = players;
    }

    public Game() {
        this.players = new HumanPlayer[] {
                new HumanPlayer(1),
                new HumanPlayer(2)
        };
    }

    /**
     * A játék kezdése után torténő lépések.
     */
    public void start() {
        int i = 0;
        int j = 1;
        int length = players.length;
        HumanPlayer player = null;

        this.players[i].placeShips();
        this.players[j].placeShips();

        while (players[0].getTotalLivesLeft() > 0 &&
                players[1].getTotalLivesLeft() > 0) {
            players[i++ % length].fireAt(players[j++ % length]);
            players[i % length].getBoard().printBoard();
            player = (players[0].getTotalLivesLeft() < players[1].getTotalLivesLeft()) ? players[1] : players[0];
        }

        if (players[0].getTotalLivesLeft() == 0) {
            System.out.print("Congrats " + players[0].getPlayerName() + " you won!");
        } else {
            System.out.print("Congrats " + players[1].getPlayerName() + " you won!");
        }

    }
}