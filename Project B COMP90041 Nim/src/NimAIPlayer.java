/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.
	
	public NimAIPlayer(){
		
	}
	
	
	public int move(int upperStone, int restStone) {
		if (upperStone > restStone) {
			upperStone = restStone;
		}
		int numberOfMove = 0;
		if (upperStone == restStone) {
			numberOfMove = restStone - 1;
		}
		if (numberOfMove == 0) {
			for (int i = 1; i <= upperStone; i++) {
				if ((restStone - i - 1) % (upperStone + 1) == 0) {
					numberOfMove = i;
				}
			}
		}
		if (numberOfMove == 0) {
			numberOfMove = 1; //if ai can't win this turn, just move 1 stone,makes human more easy to make mistake
		}
		return numberOfMove;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}
}