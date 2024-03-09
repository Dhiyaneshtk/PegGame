package peggame;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CLI {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Pattern movePattern = Pattern.compile("move r[1-9]\\d* c[1-9]\\d* r[1-9]\\d* c[1-9]\\d*");
    public static final Pattern fromPattern = Pattern.compile("from r[1-9]\\d* c[1-9]\\d*");
    public static final Pattern toPattern = Pattern.compile("to r[1-9]\\d* c[1-9]\\d*");
    public static final Pattern undo = Pattern.compile("undo [1-9]\\d*");
    
    /**
     * Gets the path to the board file
     */
    public static String getFilePath() {
        while (true) {
            System.out.print("Enter game board file name: ");
            String filePath = scanner.nextLine();
            if ((new File(filePath)).exists()) {
                return filePath;
            } else {
                System.out.println("File does not exist. If file is in the package 'peggame' make sure to enter 'peggame/' before the file name.");
            }
        }
    }

    /**
     * 
     * @param object is th board that will be printed
     */
    public static void print(Object object) {
        System.out.println(object);
    }

    /**
     * 
     * Takes the user inputs as commands such as Move, To, and From
     */
    public static Object userCommand(){
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println();
            String[] command = userInput.split(" ");
            if (command[0].equals("move") && command.length == 5) {
                try {
                    int fromRow = Integer.valueOf(command[1].substring(1, command[1].length()));
                    int fromCol = Integer.valueOf(command[2].substring(1, command[2].length()));
                    int toRow = Integer.valueOf(command[3].substring(1, command[3].length()));
                    int toCol = Integer.valueOf(command[4].substring(1, command[4].length()));
    
                    return new Move(new Location(fromRow, fromCol), new Location(toRow, toCol));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid command. Type 'help' to get list of commands");
                }

            } else if((command[0].equals("from") || command[0].equals("to") && command.length == 3)){
                try {
                    int row = Integer.valueOf(command[1].substring(1, command[1].length()));
                    int col = Integer.valueOf(command[2].substring(1, command[2].length()));
                    return new Location(row, col);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid command. Type 'help' to get list of commands");
                }
            
            } else if ((command[0].equals("undo") && command.length == 2)) {
                return Integer.valueOf(command[1]);
            } else if (command[0].equals("undo")) {
                return 1;
            } else if (command[0].equals("quit") || command[0].equals("reset") || command[0].equals("hint") || command[0].equals("help")) {
                return command[0];
            } else if (command[0].equals("cheatmode") && command[1].equals("on")) {
                System.out.println("Cheat Mode on");
                return true;
            } else if (command[0].equals("cheatmode") && command[1].equals("off")) {
                System.out.println("Cheat Mode off");
                return false;
            } else {
                System.out.println("Invalid command. Type 'help' to get list of commands");
            }
        }
    }
}
