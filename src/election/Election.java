package election;

import java.util.ArrayList;
import java.util.List;

import election.resultat.ResultsElection;
import election.resultat.Vote;

/**
 * Election class
 * 
 * @author MSI
 *
 * @param <T>
 *            class representing the choice
 */
public class Election<T> {
	// we cant modify it
	private final List<T> choix;
	private List<Vote<T>> votes = new ArrayList<Vote<T>>();
	private ElectionStatus status = ElectionStatus.OPEN;
	private int ballotNumber = 1;
	private int currentBallotNumber = ballotNumber;

	public Election(List<T> choix) {
		this.choix = choix;
	}

	public Election(List<T> choix, int i) {
		this(choix);
		this.ballotNumber = i;
	}

	/**
	 * get all the choice for this election
	 * 
	 * @return
	 */
	public List<T> getChoix() {
		return choix;
	}

	/**
	 * 
	 * @param choix
	 * @throws ElectionClosedException
	 *             if election is closed
	 */
	public void vote(final T choix) throws ElectionClosedException {
		controlElectionStatus();
		Vote<T> vote = new Vote<T>(choix);
		votes.add(vote);
	}

	/**
	 * check if election is still open otherwise throw an exception
	 * 
	 * @throws ElectionClosedException
	 */
	private void controlElectionStatus() throws ElectionClosedException {
		if (status.equals(ElectionStatus.CLOSE)) {
			throw new ElectionClosedException();
		}
	}

	public List<Vote<T>> getVotes() {
		return votes;
	}

	/**
	 * renvoi le resultat des elections <br>
	 * 
	 * @return resultat election
	 */
	public ResultsElection<T> getResultat() {
		return new ResultsElection<T>(votes, choix);
	}

	public ElectionStatus getStatus() {
		return status;
	}

	public int getBallotNumber() {
		return ballotNumber;
	}

	/**
	 * modify the status of the election
	 */
	public void closeElection() {
		status = ElectionStatus.CLOSE;
	}

	/**
	 * close the current ballot <br>
	 * increment the current ballotNumber
	 */
	public void closeBallot() {
		if (isElectionWithOneBallot()) {
			closeElection();
		} else {
			currentBallotNumber++;
		}
	}

	private boolean isElectionWithOneBallot() {
		return ballotNumber == 1;
	}

	public int getCurrentBallotNumber() {
		return currentBallotNumber;
	}

}
