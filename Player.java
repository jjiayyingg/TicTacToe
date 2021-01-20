package jan20.tic_tac_toe;

import java.util.Scanner;

public class Player {
	protected String markedIndex;
	protected char mark;
	protected String name;

	public Player(char mark) {
		this.mark = mark;
		this.markedIndex = "";
		this.name = "Player " + mark;
	}

	public void play(Scanner s, char[] gridValue, Player other) {

		// get player input and then mark the corresponding grid
		int input = this.getPlayerInput(s, gridValue, other.getMarkedIndex());
		int gridIndex = this.getGridIndex(input);
		Utilities.markGrid(gridValue, gridIndex, this.getMark());

		// check if game has ended
		int checkEndCondition = Utilities.end(this.markedIndex, other.getMarkedIndex());
		if (checkEndCondition == 0) {
			// game not ended, other player continue playing
			other.play(s, gridValue, this);
		} else {
			// game has ended, display end of game
			Utilities.displayWinner(checkEndCondition, this);
		}
	}

	public int getPlayerInput(Scanner s, char[] gridValue, String notAvail) {
		System.out.println("[ Player " + this.mark + " ]");
		System.out.print("Please enter the corresponding grid index to mark: ");
		int gridIndex = 0;

		try {
			gridIndex = s.nextInt();
		} catch (Exception e) {
			// if non-integer values entered
			s.nextLine(); // skipping one scanner line
			Utilities.errorMessage();
			gridIndex = this.getPlayerInput(s, gridValue, notAvail);
		}

		// if values outside of allowed range of 1-9 entered
		// or if chosen grid index already previously marked
		if (!Utilities.isWithinRange(gridIndex, 1, 9) || !Utilities.isAvailable(gridIndex, markedIndex, notAvail)) {
			Utilities.errorMessage();
			gridIndex = this.getPlayerInput(s, gridValue, notAvail);
		}

		return gridIndex;
	}

	public int getGridIndex(int input) {
		markedIndex += input; // record selected grid index
		int gridIndex = input - 1; // converting from 1-9 to 0-8 for array index
		return gridIndex;
	}

	public char getMark() {
		return mark;
	}

	public String getMarkedIndex() {
		return markedIndex;
	}

	public String getName() {
		return name;
	}
}
