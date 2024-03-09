package peggame;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import peggame.PegGame.GameState;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

@Testable
public class SquarePegGameTest {

    @Test
    public void testGetNumberOfPegs() {
        char[][] board = {{'o', '.', 'o'}, {'.', 'o', '.'}, {'o', '.', 'o'}};
        SquarePegGame game = new SquarePegGame(board);
        int expectedNumberOfPegs = 5; // Assuming the board has 5 pegs
        assertEquals(expectedNumberOfPegs, game.getNumberOfPegs());
    }

    @Test
    public void testGetPossibleMoves() {
        char[][] board = {{'o', '.', 'o'}, {'.', 'o', '.'}, {'o', '.', 'o'}};
        SquarePegGame game = new SquarePegGame(board);
        Collection<Move> expectedPossibleMoves = game.getPossibleMoves();
        // Assuming some logic to determine possible moves, so checking if it's not null
        assertEquals(new ArrayList<Move>(), expectedPossibleMoves);
    }

    @Test
    public void testGetGameState() {
        char[][] board = {{'o', '.', 'o'}, {'.', 'o', '.'}, {'o', '.', 'o'}};
        SquarePegGame game = new SquarePegGame(board);
        PegGame.GameState gameState = game.getGameState();
        // Assuming some logic to determine game state, so checking if it's not null
        assertEquals(GameState.STALEMATE, gameState);
    }

    // Add more test methods for other methods in SquarePegGame class as needed
}
