package peggame;

import java.io.*;

public class ReadFile {
    private static PegGameException InvalidBoardInput = new PegGameException("File does not consist of the correct board format. Please change it and then run again.");

    /**
     * 
     * @param filePath whichever file we will use for the board
     * @return board
     * @throws IOException if file does not exist
     * @throws NumberFormatException if boardSize is anything other than an integer
     * @throws PegGameException if board is not complete or if any char in the board is not a "o" or "."
     */
    public static char[][] readSquareBoard(String filePath) throws IOException, NumberFormatException, PegGameException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        
        int boardSize = Integer.valueOf(reader.readLine());

        char[][] board = new char[boardSize][boardSize];
        String line;

        //reads the board
        for (int row = 0; row < boardSize; row++) {
            line = reader.readLine();
            if (line.length() == boardSize) {
                for (int col = 0; col < boardSize; col++) {
                    if (line.charAt(col) == 'o' || line.charAt(col) == '.') {
                        board[row][col] = line.charAt(col);
                    } else {
                        reader.close();
                        throw InvalidBoardInput;
                    }
                }
            } else {
                reader.close();
                throw InvalidBoardInput;
            }
        }
        reader.close();
        return board;
    }

    /**
     * 
     * @param filePath help and helpc
     * @throws IOException
     */
    public static void printFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
