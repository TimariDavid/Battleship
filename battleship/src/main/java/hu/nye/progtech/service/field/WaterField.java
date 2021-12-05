package hu.nye.progtech.service.field;

/**
 * A játék mezőn a víz mezői.
 */
public class WaterField implements IGameField {
    private boolean isThisFieldHit = false;

    @Override
    public char getIcon() {
        return isThisFieldHit ? 'X' : '~';
    }

    @Override
    public Result shootAt() {
        isThisFieldHit = true;
        return Result.NO_HIT;
    }
}