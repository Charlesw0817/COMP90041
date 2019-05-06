
public class NimGame {
	private int turnFlag = 1; // used to tell which player's turn
	private NimPlayer player1; // two instances for the two players
	private NimPlayer player2;

	// below is used to set the game, including stones, upper bound, and two players
	public void gamePlay(int stones, int upperBound, NimPlayer player1, NimPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		turnFlag = 1; // always reset the flag for turn to player 1
		System.out.println("\nInitial stone count: " + stones);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1.getGivenName() + " " + player1.getFamilyName());
		System.out.println("Player 2: " + player2.getGivenName() + " " + player2.getFamilyName());
	}

	// display the number of stones
	public int printStones(int stones) {
		System.out.print("\n" + stones + " stones left:");
		StringBuilder printer = new StringBuilder(stones);
		for (int i = 0; i < stones; i++) {
			printer.append(" *");
		}
		System.out.println(printer);
		if (turnFlag == 1) {
			System.out.println(player1.getGivenName() + "'s turn - remove how many?");
		} else {
			System.out.println(player2.getGivenName() + "'s turn - remove how many?");
		}
		return turnFlag;
	}

	// execute removing stones, also judge if it removable
	public int removeStones(int stoneOfRemoves, int upperBound, int stones) {
		if (upperBound > stones) {
			upperBound = stones;
		}
		try {
			if (stoneOfRemoves <= upperBound && stoneOfRemoves > 0) {
				stones = stones - stoneOfRemoves;
				changeTurn();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Invalid move. You must remove between 1 and " + upperBound + " stones.");
		}
		return stones;
	}

	private void changeTurn() {
		if (turnFlag == 1) {
			turnFlag = 2; // player2's turn
		} else {
			turnFlag = 1;// player1's turn
		}
	}

	public int getTurn() {
		return turnFlag;
	}
}
