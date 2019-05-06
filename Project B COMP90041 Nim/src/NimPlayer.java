
public abstract class NimPlayer {
	// Below are the properties of the players
	private String userName;
	private String givenName;
	private String familyName;
	private int numberOfPlayed;
	private int numberOfWon;

	// methods are used to add, remove, edit or reset players
	public void addPlayer(String userName, String familyName, String givenName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
	}

	public void removePlayer() {
		this.userName = "";
	}

	public void editPlayer(String familyName, String givenName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}

	public void resetPlayer() {
		this.numberOfPlayed = 0;
		this.numberOfWon = 0;
	}

	public void displayPlayer() {
		System.out.println(userName + "," + givenName + "," + familyName + "," + numberOfPlayed + " games,"
				+ numberOfWon + " wins");
	}

	// methods set and get
	public String getUserName() {
		return this.userName;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public double getWinningRate() {
		double winningRate = 0;
		if (numberOfPlayed != 0) {
			winningRate = (double) numberOfWon / numberOfPlayed;
		} else {
			winningRate = 0;
		}
		return winningRate;
	}

	public int getWinningRateDisplayed() {
		int winningRateDisplayed = 0;
		if (numberOfPlayed != 0) {
			double winningRate = (double) numberOfWon / numberOfPlayed;
			winningRateDisplayed = (int) Math.round((winningRate * 100));
		} else {
			winningRateDisplayed = 0;
		}
		return winningRateDisplayed;
	}

	public int getNumberOfPlayed() {
		return numberOfPlayed;
	}

	public String getPlayerDetail() {
		String detail = userName + "," + givenName + "," + familyName + "," + numberOfPlayed + "," + numberOfWon + ",";
		return detail;
	}

	public void setPlayerDetail(String detail) { // this method is only for file reading
		String[] details = detail.split(",");
		userName = details[0];
		givenName = details[1];
		familyName = details[2];
		numberOfPlayed = Integer.parseInt(details[3]);
		numberOfWon = Integer.parseInt(details[4]);
	}

	public void setNumberOfPlayed() {
		numberOfPlayed++;
	}

	public void setNumberOfWon() {
		numberOfWon++;
	}

	public int move(int upperStone, int rest) {
		return 0;
	}
}
