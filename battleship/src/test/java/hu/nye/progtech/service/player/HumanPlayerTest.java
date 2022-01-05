package hu.nye.progtech.service.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import hu.nye.progtech.service.board.Board;
import org.junit.jupiter.api.Test;

public class HumanPlayerTest {

    private HumanPlayer underTest;
    private Board board;
    private final int number = 17;

    @Test
    public void testPlaceShipsWhenBoardIsNotNull() {
        // given
        HumanPlayer humanPlayer = new HumanPlayer(1);
        Board board = new Board();

        // when
        Board result = humanPlayer.getBoard();

        // then
        assertNotNull(result);

    }

    @Test
    public void testGetTotalLifeLeft() {
        //given
        HumanPlayer humanPlayer = new HumanPlayer(1);

        //when
        int totalLivesLeft = humanPlayer.getTotalLivesLeft();

        //then
        assertEquals(totalLivesLeft, number);
    }

}
