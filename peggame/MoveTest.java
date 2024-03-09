package peggame;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class MoveTest {
    @Test
    public void testGetTo() {
        Location from = new Location(1, 2);
        Location to = new Location(3, 4);
        Move move = new Move(from, to);
        assertEquals(to, move.getTo()); // Testing getTo() method
    }

    @Test
    public void testGetFrom() {
        Location from = new Location(1, 2);
        Location to = new Location(3, 4);
        Move move = new Move(from, to);
        assertEquals(from, move.getFrom()); // Testing getFrom() method
    }
}
