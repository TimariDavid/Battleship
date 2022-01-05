package hu.nye.progtech.service.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hu.nye.progtech.service.field.IGameField;
import hu.nye.progtech.service.field.WaterField;
import org.junit.jupiter.api.Test;

public class BoardTest {


    @Test
    public void testGetFieldWhenTheFieldIsWater() {
        //given
        Board board = new Board();
        WaterField waterField = new WaterField();

        //when
        IGameField field = board.getField(5, 5);

        //then
        assertEquals(field.getIcon(), waterField.getIcon());
    }

    @Test
    public void testGetFieldWhenTheFieldIsOutOfTheBoard() {
        //given
        Board board = new Board();

        //when - then
        assertThrows(IllegalArgumentException.class, () -> {
            board.getField(11, 5);
        });

    }

}

