package hu.nye.progtech.service.field;

import hu.nye.progtech.model.Ship;

/**
 * A játék mezőn a hajók mezői.
 */
public class ShipField implements IGameField {
    private final Ship ship;

    public ShipField(Ship ship) {
        this.ship = ship;
    }

    @Override
    public char getIcon() {
        char icon;
        Result shipState = ship.getState();
        switch (shipState) {
            case SHIP: icon = 'S';
                break;
            case HIT: icon = '+';
                break;
            case DESTROYED: icon = '#';
                break;
            case NO_HIT: icon = 'X';
                break;
            default: icon = ' ';
                break;
        }
        return icon;
    }

    @Override
    public Result shootAt() {
        ship.hit();
        return ship.getState();
    }
}