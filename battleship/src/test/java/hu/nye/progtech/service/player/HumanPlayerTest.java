package hu.nye.progtech.service.player;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import hu.nye.progtech.service.board.Board;
import org.junit.jupiter.api.Test;

public class HumanPlayerTest {

    private HumanPlayer underTest;
    private Board board;

    @Test
    public void testPlaceShips() {
        // given
        HumanPlayer humanPlayer = new HumanPlayer(1);
        Board board = new Board();

        // when
        Board board1 = humanPlayer.getBoard();

        // then
        assertNotNull(board1);

    }

}
