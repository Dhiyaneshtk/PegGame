package peggame;

import java.util.ArrayList;
import java.util.Collection;

public class SquarePegGame implements PegGame {
    public char[][] board;
    public int boardSize;
    public int numberOfPegs = this.getNumberOfPegs();
    public static final int[] changeInLocation = {-2, 0, 2};
    public Collection<Move> movesMade = new ArrayList<>();

    public SquarePegGame(char[][] board){
        this.board = board;
        this.boardSize = board.length;
    }

    @Override
    public boolean locationInBoard(int row, int col) {return row >= 0 && row < this.boardSize && col >= 0 && col < this.boardSize;}

    @Override
    public int getNumberOfPegs() {
        numberOfPegs = 0;
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (this.board[row][col] == peg){numberOfPegs += 1;}
            }
        }
        return numberOfPegs;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        Collection<Move> possibleMoves = new ArrayList<Move>();

        for (int fromRow = 0; fromRow < this.boardSize; fromRow++) {
            for (int fromCol = 0; fromCol < this.boardSize; fromCol++) {
                Location from = new Location(fromRow, fromCol);
                Collection<Location> possibleToLocations = getPossibleToLocations(from);
                for (Location to : possibleToLocations) {
                    possibleMoves.add(new Move(from, to));
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public GameState getGameState() {
        if (numberOfPegs == 1){
            CLI.print("Vamos! You won");
            return GameState.WON;
        } else if (this.getPossibleMoves().isEmpty()){
            CLI.print("Stalemate, no more possible moves");
            return GameState.STALEMATE;
        } else {
            return GameState.IN_PROGRESS;
        }
    }

    @Override
    public Collection<Location> getPossibleToLocations(Location from) {
        Collection<Location> possibleToLocations = new ArrayList<Location>();
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        if (this.board[fromRow][fromCol] == peg) {
            for (int changeInRow : changeInLocation) {
                for (int changeInCol : changeInLocation) {
                    if (!(changeInRow == 0 && changeInCol == 0)){
                        int toRow = fromRow - changeInRow;
                        int toCol = fromCol - changeInCol;
                        if (locationInBoard(toRow, toCol)){
                            int removePegRow = (fromRow + toRow)/2;
                            int removePegCol = (fromCol + toCol)/2;
                            if (this.board[toRow][toCol] == hole && this.board[removePegRow][removePegCol] == peg) {
                                possibleToLocations.add(new Location(toRow, toCol));
                            }
                        }
                    }
                }   
            }
        }
        return possibleToLocations;
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        if (this.getPossibleMoves().contains(move)) {
            this.board[move.getFrom().getRow()][move.getFrom().getCol()] = hole;
            this.board[(move.getFrom().getRow() + move.getTo().getRow())/2][(move.getFrom().getCol() + move.getTo().getCol())/2] = hole;
            this.board[move.getTo().getRow()][move.getTo().getCol()] = peg;
            this.movesMade.add(move);
        } else {
            throw MoveNotPossible;
        }
    }

    /**
     * Undos the previous moves made
     */
    public void undoMove() throws PegGameException {
        if (!(movesMade.isEmpty())) {
            Move move = (Move) (movesMade.toArray()[movesMade.size()-1]);
            movesMade.remove(move);
            this.board[move.getFrom().getRow()][move.getFrom().getCol()] = peg;
            this.board[(move.getFrom().getRow() + move.getTo().getRow())/2][(move.getFrom().getCol() + move.getTo().getCol())/2] = peg;
            this.board[move.getTo().getRow()][move.getTo().getCol()] = hole;
        } else {
            throw UndoNotPossible;
        }
    }

    @Override
    public String toString() {
        String boardAsString = "\n";
        for (char[] line : this.board) {
            for (char character : line) {
                boardAsString += character;
            }
            boardAsString += "\n";
        }
        return boardAsString;
    }
    
}
