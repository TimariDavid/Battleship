package hu.nye.progtech.service.field;

/**
 * A játék tér mezőihez használt interface.
 */
public interface IGameField {
    char getIcon();

    Result shootAt();
}