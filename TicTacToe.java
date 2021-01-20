package jan20.tic_tac_toe;

import java.util.*;

public class TicTacToe {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		TicTacToe game = new TicTacToe();
		game.go(scanner); // start game

		scanner.close();
	}

	private void go(Scanner s) {

		// initial values in each grid = { 1, 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 }
		char[] gridValue = { 49, 50, 51, 52, 53, 54, 55, 56, 57 };

		// ask for number of players - determines the need for computer player
		int playerCount = Utilities.getPlayerCount(s);
		// create players
		Player p1 = new Player('X');
		Player p2 = new Player('O');
		// if need computer
		if (playerCount == 1) {
			p2 = new Computer();
		}

		// display empty grid
		Utilities.displayBoard(gridValue);

		p1.play(s, gridValue, p2);

		// restart game?
		if (Utilities.restartPrompt(s)) {
			// if restart, create a new game
			TicTacToe g = new TicTacToe();
			g.go(s);
		} else {
			// else print ending statement and end program
			System.out.println();
			System.out.println("Goodbye!");
		}
	}
}
