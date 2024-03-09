package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class ReadFileTest {

    @Test
    public void testReadSquareBoard() throws NumberFormatException, IOException, PegGameException {
        char[][] expectedSquareBoard = {{'o','o','o','o','o'},
        {'.','o','o','o','o'},
        {'o','.','o','o','o'},
        {'o','o','.','o','o'},
        {'o','o','o','.','o'}};
        assertEquals(expectedSquareBoard, ReadFile.readSquareBoard("fiveByFive.txt"));
    }
}