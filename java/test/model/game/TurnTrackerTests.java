package model.game;

import org.junit.Test;
import shared.model.game.TurnTracker;

import static org.junit.Assert.*;

/**
 * @author Derek Argueta
 */
public class TurnTrackerTests {

    @Test
    public void testDefaultConstruction() throws Exception {
        TurnTracker t = new TurnTracker();

        assertEquals(0, t.getCurrentTurn());
        assertTrue(t.isSetupPhase());

        t.nextTurn();
        // should be player 1 setup
        assertTrue(t.isSetupPhase());
        assertEquals(1, t.getCurrentTurn());

        t.nextTurn();
        // should be player 2 setup
        assertTrue(t.isSetupPhase());
        assertEquals(2, t.getCurrentTurn());

        t.nextTurn();
        // should be player 3 setup
        assertTrue(t.isSetupPhase());
        assertEquals(3, t.getCurrentTurn());

        t.nextTurn();
        // should be player 3 again
        assertTrue(t.isSetupPhase());
        assertEquals(3, t.getCurrentTurn());

        t.nextTurn();
        // should be player 2 setup
        assertTrue(t.isSetupPhase());
        assertEquals(2, t.getCurrentTurn());

        t.nextTurn();
        // should be player 1 setup
        assertTrue(t.isSetupPhase());
        assertEquals(1, t.getCurrentTurn());

        t.nextTurn();
        // should be player 0 setup
        assertTrue(t.isSetupPhase());
        assertEquals(0, t.getCurrentTurn());

        /////// REGULAR GAMEPLAY
        for(int i = 0; i < 100; i++) {
            t.nextTurn();
            assertFalse(t.isSetupPhase());
            assertEquals(i % 4, t.getCurrentTurn());
        }
    }
}
