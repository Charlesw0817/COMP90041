
public class NimPlayer {
	String name;
	
	public int removeStone(int number, int upperBound) {
		final int MIN_OPERATION = 0;
		int numberOfRemove = number;
		if (numberOfRemove > upperBound || numberOfRemove <= MIN_OPERATION) { // judge if the input smaller than the upper bound
			numberOfRemove = 0;
		}
		return numberOfRemove;
	}
}
