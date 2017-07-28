package election;

import java.util.ArrayList;
import java.util.List;

public class ElectionBallots<T> {

	private List<T> choices;
	private int totalNumberOfBallot = 1;
	private int currentBallotNumber = totalNumberOfBallot;
	private List<Ballot<T>> ballots = new ArrayList<>();

	public ElectionBallots(List<T> choices) {
		this.choices = choices;
		ballots.add(new Ballot<>(choices));
	}

	public ElectionBallots(List<T> choices, int totalNumberOfBallot) {
		ballots.add(new Ballot<>(choices));
		this.totalNumberOfBallot = totalNumberOfBallot;
	}

	public Ballot<T> getCurrentBallot() {
		if (ballots.size() < currentBallotNumber) {
			// TODO handle new choices for new ballot
			ballots.add(new Ballot<>(choices));
		}
		return ballots.get(currentBallotNumber - 1);
	}

	public boolean isElectionWithOneBallot() {
		return totalNumberOfBallot == 1;
	}
	public int getCurrentBallotNumber() {
		return currentBallotNumber;
	}

	public void incrementBallotNumber(){
		currentBallotNumber++;
	}
	public int getBallotNumber() {
		return totalNumberOfBallot;

	}

}
