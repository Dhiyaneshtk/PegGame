package peggame;

import java.io.IOException;

import peggame.PegGame.GameState;

public class Project1Main {
    public static String filePath;
    public static SquarePegGame game;
    public static Object userInput;
    public static boolean cheatmode = false;

    /**
     * Runs everything through the CLI
     */
    public static void main(String[] args) throws IOException, NumberFormatException, PegGameException {
        while (true) {
            filePath = CLI.getFilePath();

            try {
                game = new SquarePegGame(ReadFile.readSquareBoard(filePath));
            } catch (NumberFormatException e) {
                CLI.print("Board length in the file is invalid. Change it and run again.");
                break;
            } catch (PegGameException e) {
                CLI.print(e);
                break;
            }

            while (game.getGameState() == GameState.IN_PROGRESS) {
                CLI.print(game);
                userInput = CLI.userCommand();
                if (userInput instanceof Move) {
                    Move userMove = (Move) (userInput);
                    try {
                        game.makeMove(userMove);
                    } catch (PegGameException e) {
                        CLI.print("\n" + e);
                    }
                } else if (userInput instanceof Location) {
                    Location from = (Location) (userInput);
                    if (!(game.getPossibleToLocations(from).isEmpty())) {
                        userInput = CLI.userCommand();
                        if (userInput instanceof Location) {
                            Location to = (Location) (userInput);
                            try {
                                game.makeMove(new Move(from, to));
                            } catch (PegGameException e) {
                                CLI.print("\n" + e);
                            }
                        } 
                    }
                } else if (userInput instanceof Integer && cheatmode) {
                    int numberOfUndo = (int) (userInput);
                    boolean undoPossible = true;
                    for (int i = 0; i < numberOfUndo && undoPossible; i++) {
                        try {
                            game.undoMove();
                            undoPossible = false;
                        } catch (PegGameException e) {
                            CLI.print(e);
                        }
                    }
                } else if (userInput instanceof Boolean) {
                    cheatmode = (boolean) (userInput);
                } else if (userInput.equals("reset")) {
                    game.board = ReadFile.readSquareBoard(filePath);
                } else if (userInput.equals("hint") && cheatmode) {
                    for (Move move : game.getPossibleMoves()) {
                        CLI.print(move);
                    }
                } else if (userInput.equals("help")) {
                    if (!cheatmode) {
                        ReadFile.printFile("peggame/help.txt");
                    } else {
                        ReadFile.printFile("peggame/helpc.txt");
                    }
                } else if (userInput.equals("quit")) {
                    break;
                }
            }
        }
    }
}
