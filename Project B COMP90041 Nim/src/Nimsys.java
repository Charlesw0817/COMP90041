
/*
 * NIM Version 1.2
 * Written by CHAO WANG on 16th.04.2018
 * Last edited on 23rd.04.2018
 */
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Nimsys {
	static Scanner keyboard = new Scanner(System.in);
	private static int playerNumber = 0; // player number is used to identify the player
	private static int player1Number = 0; // these are the two players play the game
	private static int player2Number = 0;
	private static NimPlayer[] player = new NimPlayer[100]; // an array store all the players

	public static void main(String[] args) {
		System.out.println("Welcome to Nim");
		boolean exitFlag = false; // judge if players want to exit
		try {
			read();
		} catch (Exception e) {
		}
		while (exitFlag == false) {
			System.out.print("\n$");
			String commandLine = keyboard.nextLine(); // read the command
			String[] command = commandLine.split(" ");
			if (command[0].equals("exit")) {
				exitFlag = true;
				try {
					outPut(); // write into the file
				} catch (Exception e) {
				}
			} else if (command[0].equals("addplayer")) {
				try { // judge arguments
					addPlayer(command[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Incorrect number of arguments supplied to command.");
				}
			} else if (command[0].equals("addaiplayer")) {
				try { // judge arguments
					addAiPlayer(command[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Incorrect number of arguments supplied to command.");
				}

			} else if (command[0].equals("removeplayer")) {
				if (command.length == 1) { // if there is no player name, delete all
					System.out.println("Are you sure you want to remove all players? (y/n)");
					String confirmation = keyboard.nextLine();
					if (confirmation.equals("y")) {
						removeAllPlayer();
					}
				} else
					removePlayer(command[1]);
			} else if (command[0].equals("editplayer")) {
				try { // judge arguments
					editPlayer(command[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Incorrect number of arguments supplied to command.");
				}
			} else if (command[0].equals("resetstats")) {
				if (command.length == 1) {
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					String confirmation = keyboard.nextLine();
					if (confirmation.equals("y")) {
						resetAllPlayer();
					}
				} else
					resetPlayer(command[1]);
			} else if (command[0].equals("displayplayer")) {
				if (command.length == 1) { // if there is no player name, display all
					displayAllPlayer();
				} else {
					displayPlayer(command[1]);
				}
			} else if (command[0].equals("rankings")) {
				if (command.length == 1) { // if there is no other command, sort descend
					dessorting();
				} else if (command[1].equals("asc")) {
					ascsorting();
				} else if (command[1].equals("des")) {
					dessorting();
				}
			} else if (command[0].equals("startgame")) {
				game(command[1]);
			} else {
				try {
					throw new Exception();
				} catch (Exception e) {
					System.out.println("'" + command[0] + "' is not a valid command.");
				}
			}
		}
		System.out.println("");
	}

	public static void addPlayer(String names) {
		String[] splitNames = names.split(",");
		try {
			for (int i = 0; i < playerNumber; i++) {
				if (splitNames[0].equals(player[i].getUserName())) {
					System.out.println("The player already exists.");
					return;
				}
			}
			player[playerNumber] = new NimHumanPlayer();
			player[playerNumber].addPlayer(splitNames[0], splitNames[1], splitNames[2]);
			playerNumber++;
		} catch (ArrayIndexOutOfBoundsException e) { // judge arguments
			System.out.println("Incorrect number of arguments supplied to command.");
		}
	}

	public static void addAiPlayer(String names) {
		String[] splitNames = names.split(",");
		try {
			for (int i = 0; i < playerNumber; i++) {
				if (splitNames[0].equals(player[i].getUserName())) {
					System.out.println("The player already exists.");
					return;
				}
			}
			player[playerNumber] = new NimAIPlayer();
			player[playerNumber].addPlayer(splitNames[0], splitNames[1], splitNames[2]);
			playerNumber++;
		} catch (ArrayIndexOutOfBoundsException e) { // judge arguments
			System.out.println("Incorrect number of arguments supplied to command.");
		}
	}

	public static void removePlayer(String userName) {
		for (int i = 0; i < playerNumber; i++) {
			if (userName.equals(player[i].getUserName())) {
				player[i].removePlayer();
				return;
			}
		}
		System.out.println("The player does not exist.");
	}

	public static void removeAllPlayer() {
		for (int i = 0; i < playerNumber; i++) {
			player[i].removePlayer();
		}
	}

	public static void editPlayer(String names) {
		try {
			String[] splitNames = names.split(",");
			for (int i = 0; i < playerNumber; i++) {
				if (splitNames[0].equals(player[i].getUserName())) {
					player[i].editPlayer(splitNames[1], splitNames[2]);
					return;
				}
			}
			System.out.println("The player does not exist.");
		} catch (ArrayIndexOutOfBoundsException e) { // judge arguments
			System.out.println("Incorrect number of arguments supplied to command.");
		}
	}

	public static void resetPlayer(String userName) {
		for (int i = 0; i < playerNumber; i++) {
			if (userName.equals(player[i].getUserName())) {
				player[i].resetPlayer();
				return;
			}
		}
		System.out.println("The player does not exist.");
	}

	public static void resetAllPlayer() {
		for (int i = 0; i < playerNumber; i++) {
			player[i].resetPlayer();
		}
	}

	public static void displayPlayer(String userName) {
		for (int i = 0; i < playerNumber; i++) {
			if (userName.equals(player[i].getUserName())) {
				player[i].displayPlayer();
				return;
			}
		}
		System.out.println("The player does not exist.");
	}

	public static void displayAllPlayer() {
		String[] names = new String[playerNumber];
		for (int i = 0; i < playerNumber; i++) {
			if (player[i].getUserName().equals("")) {
			} else {
				for (int j = 0; j < playerNumber; j++) {
					names[j] = player[j].getUserName();
				}
				Arrays.sort(names);
			}
		}
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < playerNumber; j++) {
				if (player[j].getUserName() == names[i]) {
					if (player[j].getUserName().equals("")) {
					} else {
						player[j].displayPlayer();
					}
				}
			}
		}
	}

	public static void ascsorting() {
		double[] order = new double[playerNumber];
		int totalNumber = 0;
		for (int i = 0; i < playerNumber; i++) {
			if (player[i].getUserName().equals("")) {
			} else {
				order[i] = player[i].getWinningRate();
				totalNumber++;
			}
		}
		Arrays.sort(order);
		if (totalNumber > 10) {
			for (int i = 0; i < 9; i++) {
				if (order[i] == order[i + 1]) {
					order[i] = -1;
				}
			}
			for (int i = 0; i < 10; i++) {
				printSort(i, order);
			}
		} else {
			for (int i = 0; i < totalNumber - 1; i++) {
				if (order[i] == order[i + 1]) {
					order[i] = -1;
				}
			}
			for (int i = 0; i < totalNumber; i++) {
				printSort(i, order);
			}
		}
	}

	public static void dessorting() {
		double[] order = new double[playerNumber];
		int totalNumber = 0;
		for (int i = 0; i < playerNumber; i++) {
			if (player[i].getUserName().equals("")) {
			} else {
				order[i] = player[i].getWinningRate();
				totalNumber++;
			}
		}
		Arrays.sort(order);

		for (int start = 0, end = order.length - 1; start < end; start++, end--) {
			double temp = order[end];
			order[end] = order[start];
			order[start] = temp;
			if (end < start) {
				temp = order[end];
				order[end] = order[start];
				order[start] = temp;
			}
		}

		if (totalNumber > 10) {
			for (int i = 0; i < 9; i++) {
				if (order[i] == order[i + 1]) {
					order[i] = -1;
				}
			}
			for (int i = 0; i < 10; i++) {
				printSort(i, order);
			}
		} else {
			for (int i = 0; i < totalNumber - 1; i++) {
				if (order[i] == order[i + 1]) {
					order[i] = -1;
				}
			}
			for (int i = 0; i < totalNumber; i++) {
				printSort(i, order);
			}
		}
	}

	public static void printSort(int i, double[] order) {
		String[] playerCache = new String[10];
		int k = 0;
		for (int j = 0; j < playerNumber; j++) {
			if (player[j].getWinningRate() == order[i]) {
				playerCache[k] = player[j].getUserName();
				k++;
			}
		}
		Arrays.sort(playerCache, 0, k);
		for (int l = 0; l < k; l++) {
			for (int j = 0; j < playerNumber; j++) {
				if (player[j].getUserName().equals(playerCache[l])) {
					if (player[j].getWinningRateDisplayed() == 100) {
						System.out.print("100% | ");
					} else if (player[j].getWinningRateDisplayed() >= 10) {
						System.out.print(player[j].getWinningRateDisplayed() + "%  | ");
					} else {
						System.out.print(player[j].getWinningRateDisplayed() + "%   | ");
					}
					System.out.print(new DecimalFormat("00").format(player[j].getNumberOfPlayed()));
					System.out.println(" games | " + player[j].getGivenName() + " " + player[j].getFamilyName());
				}
			}
		}
	}

	public static boolean checkPlayer(String player1UserName, String player2UserName) {
		boolean foundFlag1 = false;
		boolean foundFlag2 = false;
		for (int i = 0; i < playerNumber; i++) {
			if (player1UserName.equals(player[i].getUserName())) {
				player1Number = i;
				foundFlag1 = true;
				break;
			}
		}
		for (int i = 0; i < playerNumber; i++) {
			if (player2UserName.equals(player[i].getUserName())) {
				player2Number = i;
				foundFlag2 = true;
				break;
			}
		}
		if (foundFlag1 == false || foundFlag2 == false) {
			System.out.println("One of the players does not exist.");
			return false;
		}
		return true;
	}

	private static void game(String command) {
		NimGame game = new NimGame();
		String[] splitSettings = command.split(",");
		try {
			int stones = Integer.parseInt(splitSettings[0]);
			int upperBound = Integer.parseInt(splitSettings[1]);
			String player1UserName = splitSettings[2];
			String player2UserName = splitSettings[3];
			boolean check = checkPlayer(player1UserName, player2UserName);
			if (check == true) {
				game.gamePlay(stones, upperBound, player[player1Number], player[player2Number]);
				int turn;
				int numberOfRemoves = 0;
				while (stones != 0) {
					turn = game.printStones(stones);
					if (turn == 1) {
						if (player[player1Number] instanceof NimAIPlayer) { // judge if player is an AI
							numberOfRemoves = player[player1Number].move(upperBound, stones);
						} else {
							String stoneOfRemoves = keyboard.nextLine();
							numberOfRemoves = Integer.parseInt(stoneOfRemoves);
						}
					}
					if (turn == 2) {
						if (player[player2Number] instanceof NimAIPlayer) { // judge if player is an AI
							numberOfRemoves = player[player2Number].move(upperBound, stones);
						} else {
							String stoneOfRemoves = keyboard.nextLine();
							numberOfRemoves = Integer.parseInt(stoneOfRemoves);
						}
					}
					stones = game.removeStones(numberOfRemoves, upperBound, stones);
				}
				if (stones == 0) {
					player[player1Number].setNumberOfPlayed();
					player[player2Number].setNumberOfPlayed();
					System.out.println("\nGame Over");
					if (game.getTurn() == 1) { // get the turn to judge who wins
						System.out.println(player[player1Number].getGivenName() + " "
								+ player[player1Number].getFamilyName() + " wins!");
						player[player1Number].setNumberOfWon();
					} else {
						System.out.println(player[player2Number].getGivenName() + " "
								+ player[player2Number].getFamilyName() + " wins!");
						player[player2Number].setNumberOfWon();
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incorrect number of arguments supplied to command.");
		}
	}

	private static void outPut() throws Exception { // method for writing into a file
		String ai = "ai = true\n";
		String human = "ai = false\n";
		String fileName = "players.dat";
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		OutputStream output = new FileOutputStream(file);
		for (int i = 0; i <= player.length; i++) {
			if (player[i].getUserName().equals("")) {
			} else {
				byte[] detail = player[i].getPlayerDetail().getBytes();
				output.write(detail);
				if (player[i] instanceof NimAIPlayer) {
					output.write(ai.getBytes());
				} else {
					output.write(human.getBytes());
				}
			}
		}
		output.close();
	}

	private static void read() throws Exception { // method for reading from a file
		final int comma = 5;
		String fileName = "players.dat";
		File file = new File(fileName);
		InputStream in = new FileInputStream(file);
		byte[] playersData = new byte[(int) file.length()];
		for (int i = 0; i < playersData.length; i++) {
			playersData[i] = (byte) in.read();
		}
		in.close();
		String playerDetails = new String(playersData);
		int count = 0; // count the number of ',' to calculate how many players included
		char[] chars = playerDetails.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ',') {
				count++;
			}
		}
		count = count / comma;
		/*
		 * every player's details are username, familyname, givenname, number of played
		 * and number of won. use 5 commas to split them, so commas divided by 5 is the
		 * number of players
		 */
		String[] eachPlayerDetails = playerDetails.split("\n");
		for (int i = 0; i < count; i++) {
			String[] details = eachPlayerDetails[i].split(",");
			if (details[5].equals("ai = true")) {
				player[playerNumber] = new NimAIPlayer();
				player[playerNumber].setPlayerDetail(eachPlayerDetails[i]);
			}else {
				player[playerNumber] = new NimHumanPlayer();
				player[playerNumber].setPlayerDetail(eachPlayerDetails[i]);
			}
			playerNumber++;
		}
	}
}
