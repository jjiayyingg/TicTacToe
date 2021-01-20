package jan20.tic_tac_toe;

import java.util.Scanner;

public class Utilities {

	// check if int is not already present in String
	public static boolean isAvailable(int toCheck, String notAvail1, String notAvail2) {
		String toCheckString = "" + toCheck;
		boolean avail1 = !notAvail1.contains(toCheckString);
		boolean avail2 = !notAvail2.contains(toCheckString);
		return (avail1 && avail2);
	}

	// check if input is within the given range
	public static boolean isWithinRange(int toCheck, int lowerBound, int upperBound) {
		return ((toCheck >= lowerBound && toCheck <= upperBound));
	}

	public static void errorMessage() {
		System.out.println("Invalid values entered, please try again.");
		System.out.println();
	}

	// mark the selected grid with the current player's mark
	public static void markGrid(char[] gridValue, int gridIndex, char mark) {
		gridValue[gridIndex] = mark;
		displayBoard(gridValue);
	}

	// display the current game board
	public static void displayBoard(char[] gridValue) {

		System.out.println();

		for (int i = 1; i <= 9; i++) {
			// char present in the grid
			char current = gridValue[i - 1];

			// above row 2 and 3
			if (i == 4 || i == 7) {
				System.out.println("---+---+---");
			}

			if (i % 3 == 1) {
				// column 1
				System.out.print(" " + current);

			} else if (i % 3 == 2) {
				// column 2
				System.out.print(" | " + current + " | ");

			} else {
				// column 3
				System.out.println(current);
			}
		}

		System.out.println();
		System.out.println();
		System.out.println();
	}

	// ask for number of players
	public static int getPlayerCount(Scanner s) {
		System.out.println("Please enter the number of players (1-2): ");
		int playerCount = 0;

		try {
			playerCount = s.nextInt();
		} catch (Exception e) {
			// if non-integer values entered
			s.nextLine(); // skipping one scanner line
			System.out.println("Invalid values entered, please try again.");
			playerCount = getPlayerCount(s);
		}

		// if values outside of allowed range of 1-2 entered
		if (playerCount < 1 || playerCount > 2) {
			System.out.println("Invalid values entered, please try again.");
			playerCount = getPlayerCount(s);
		}

		return playerCount;
	}

	// checks if the game ended and if so, did anybody win
	public static int end(String marked1, String marked2) {
		// end = 0 -> not ended, continue playing
		// end = 1 -> winning conditions reached
		// end = 2 -> ended, but winning conditions NOT reached
		int end = 0;

		// winning conditions
		boolean con1 = marked1.contains("1") && marked1.contains("2") && marked1.contains("3");
		boolean con2 = marked1.contains("4") && marked1.contains("5") && marked1.contains("6");
		boolean con3 = marked1.contains("7") && marked1.contains("8") && marked1.contains("9");
		boolean con4 = marked1.contains("1") && marked1.contains("4") && marked1.contains("7");
		boolean con5 = marked1.contains("2") && marked1.contains("5") && marked1.contains("8");
		boolean con6 = marked1.contains("3") && marked1.contains("6") && marked1.contains("9");
		boolean con7 = marked1.contains("1") && marked1.contains("5") && marked1.contains("9");
		boolean con8 = marked1.contains("3") && marked1.contains("5") && marked1.contains("7");

		if (con1 || con2 || con3 || con4 || con5 || con6 || con7 || con8) {
			// won
			end = 1;

		} else if ((marked1 + marked2).length() >= 9) {
			// all slots on the grid has been marked
			end = 2;
		}
		return end;
	}

	public static void displayWinner(int endCondition, Player winner) {
		// player won
		if (endCondition == 1) {
			System.out.println("Congratulations! " + winner.getName() + " wins!");
		} else {
			// draw
			System.out.println("This game ended in a draw.");
		}
	}

	public static boolean restartPrompt(Scanner s) {

		boolean restart = false;

		System.out.println();
		System.out.println("Enter 'y' to play again");
		System.out.println("else, enter any other key to quit");
		s.nextLine(); // skipping one scanner line
		String input = s.nextLine();
		System.out.println();

		char c = ' ';
		try {
			c = input.charAt(0);
		} catch (Exception e) {
			// if non-valid value entered, make c a valid char (space)
			c = ' ';
		}
		if (c == 'y') {
			restart = true;
		}

		return restart;
	}
}
