package ca.sheridancollege.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players (2-4): ");
        int numPlayers = getValidPlayerCount(scanner);

        InputOutputHandler inputOutput = new InputOutputHandler();
        Game game = new Game(numPlayers, inputOutput);
        game.play();
    }

    // Prompts the user to enter a valid number of players (2-4)
    private static int getValidPlayerCount(Scanner scanner) {
        int numPlayers = 0;
        boolean validInput;
        do {
            validInput = false;
            try {
                numPlayers = Integer.parseInt(scanner.nextLine().trim());
                if (numPlayers >= 2 && numPlayers <= 4) {
                    validInput = true;
                } else {
                    System.out.print("Invalid number of players. Please enter a value between 2 and 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 2 and 4: ");
            }
        } while (!validInput);
        return numPlayers;
    }
}