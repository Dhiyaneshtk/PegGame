package peggame;

import java.util.Collection;

public interface PegGame {
    public static final char peg = 'o';
    public static final char hole = '.';
    public static final PegGameException MoveNotPossible = new PegGameException("Move is not possible");
    public static final PegGameException UndoNotPossible = new PegGameException("Undo not possible");

    public enum GameState {
        NOT_STARTED,
        IN_PROGRESS,
        STALEMATE,
        WON
    }

    /**
     * methods to be implemented
     */
    public int getNumberOfPegs();
    public boolean locationInBoard(int row, int col);
    public Collection<Location> getPossibleToLocations(Location from);
    public Collection<Move> getPossibleMoves();
    public GameState getGameState();
    public void makeMove(Move move) throws PegGameException;
    public void undoMove() throws PegGameException;
}