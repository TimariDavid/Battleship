package hu.nye.progtech.service.game;

import static org.mockito.Mockito.verifyNoInteractions;

import hu.nye.progtech.service.player.HumanPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test for {@link Game}.
 */
@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock
    private HumanPlayer[] humanPlayers;

    private Game underTest;

    @Test
    public void testStartShouldLoopTheGameUntilTheUserDoesNotForceExit() {
        //given
        humanPlayers = new HumanPlayer[0];
        underTest = new Game(humanPlayers);

        //when
        underTest.start();

        //then
        verifyNoInteractions(humanPlayers);
    }

}
