package hu.nye.progtech.service.player;

/**
 * A játékosok interakciói.
 */
public interface IPlayer {
    void placeShips();

    void fireAt(IPlayer opponent);

    int getTotalLivesLeft();

    String yourName();
}