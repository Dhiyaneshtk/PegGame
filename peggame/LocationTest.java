package peggame;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import static org.junit.Assert.assertEquals;

@Testable
public class LocationTest {

    @Test
    public void TestgetRow() {
        Location location = new Location(4, 2);
        assertEquals(4, location.getRow()); // Testing getRow() method
    }

    @Test
    public void TestgetCol() {
        Location location = new Location(1, 3);
        assertEquals(3, location.getCol()); // Testing getCol() method
    }
}