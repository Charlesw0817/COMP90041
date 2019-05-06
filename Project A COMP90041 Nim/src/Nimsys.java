
/* This project, Nimsys, wrote by Chao WANG on 11.03.2018
whose student number is 854501. */
import java.util.Scanner;

public class Nimsys {
	static Scanner keyboard = new Scanner(System.in);
	static NimPlayer player1 = new NimPlayer(); // create player1 instance
	static NimPlayer player2 = new NimPlayer(); // create player2 instance

	public static void main(String[] args) {
		String againFlag = "Y"; // Use to judge the if players want to play again
		System.out.println("Welcome to Nim");
		System.out.println("\nPlease enter Player 1's name:");
		player1.name = keyboard.next();
		System.out.println("\nPlease enter Player 2's name:");
		player2.name = keyboard.next();
		while (againFlag.equalsIgnoreCase("Y")) {
			int winFlag = nimGames(); // call the nimGames method
			System.out.println("\nGame Over");
			whoWins(winFlag); // call the whoWins method
			againFlag = keyboard.next();
		}
		keyboard.close();
	}

	public static int nimGames() { // it is the game setting method
		System.out.println("\nPlease enter upper bound of stone removal:");
		int upperBound = keyboard.nextInt();
		System.out.println("\nPlease enter initial number of stones:");
		int numberOfStones = keyboard.nextInt();
		int winFlag = nimOperation(upperBound, numberOfStones); // call the method of gaming procedure
		return winFlag;
	}

	public static int nimOperation(int upperBound, int numberOfStones) { // it is the gaming procedure method
		final int MIN_STONES = 0;
		int turnFlag = 1; // Use to judge which player's turn
		while (numberOfStones > MIN_STONES) {
			String AsteriskPrinter = printStones(numberOfStones);
			System.out.println("\n" + numberOfStones + " stones left:" + AsteriskPrinter);
			switch (turnFlag) { // judge which player's turn
			case 1:
				System.out.println(player1.name + "'s turn - remove how many?");
				int number = keyboard.nextInt();
				int numberOfReomves = player1.removeStone(number, upperBound); // call the method removeStone
				while (numberOfReomves == 0) {// if players enter an invalid number, ask them to enter again
					System.out.println("Invalid input, enter again:");
					number = keyboard.nextInt();
					numberOfReomves = player1.removeStone(number, upperBound);
				}
				numberOfStones = numberOfStones - numberOfReomves;
				turnFlag = 2; // change to next player
				break;
			case 2: // player2' turn, same as player 1
				System.out.println(player2.name + "'s turn - remove how many?");
				number = keyboard.nextInt();
				numberOfReomves = player2.removeStone(number, upperBound);
				while (numberOfReomves == 0) {
					System.out.println("Invalid input, enter again:");
					number = keyboard.nextInt();
					numberOfReomves = player2.removeStone(number, upperBound);
				}
				numberOfStones = numberOfStones - numberOfReomves;
				turnFlag = 1;
				break;
			}
		}
		return turnFlag;
	}

	public static void whoWins(int winFlag) { // use to output which player wins
		final int PLAYER_1_WINS = 1;
		if (winFlag == PLAYER_1_WINS) { // judge which player wins
			System.out.println(player1.name + " wins!");
		} else {
			System.out.println(player2.name + " wins!");
		}
		System.out.print("\nDo you want to play again (Y/N):");
	}

	public static String printStones(int numberOfStones) { // use to print the number of stones
		StringBuilder printer = new StringBuilder(numberOfStones);
		for (int i = 0; i < numberOfStones; i++) {
			printer.append(" *");
		}
		return printer.toString();
	}
}
