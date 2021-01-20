package jan20.tic_tac_toe;

import java.util.Random;
import java.util.Scanner;

public class Computer extends Player {

	public Computer() {
		super('O');
		super.name += " Computer";
		System.out.println();
		System.out.println("Computer joined the game...");
	}

	public int getPlayerInput(Scanner s, char[] gridValue, String notAvail) {

		// computer marks random grid
		Random r = new Random();

		// generate random number between 1-9
		int compIndex = r.nextInt(8) + 1;

		// if chosen grid index already previously marked
		if (!Utilities.isAvailable(compIndex, super.markedIndex, notAvail)) {
			compIndex = this.getPlayerInput(s, gridValue, notAvail);
		}

		return compIndex;
	}

	public int getGridIndex(int compIndex) {
		// printing...
		System.out.println("[ Player " + super.mark + " ] Computer");
		System.out.print("The following grid is marked: ");
		System.out.println(compIndex);

		super.markedIndex += compIndex; // record selected grid index

		int gridIndex = compIndex - 1; // converting from 1-9 to 0-8 for array index
		return gridIndex;
	}

}
