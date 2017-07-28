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
	private final List<T> initialChoices;
	private ElectionStatus status = ElectionStatus.OPEN;
	private ElectionBallots<T> electionBallots;

	public Election(final List<T> choix) {
		this.initialChoices = choix;
		electionBallots = new ElectionBallots<>(choix);
	}

	public Election(final List<T> choix, final int i) {
		this.initialChoices = choix;
		electionBallots = new ElectionBallots<>(choix, i);
	}

	/**
	 * get all the choice for this election
	 * 
	 * @return
	 */
	public List<T> getChoix() {
		return initialChoices;
	}

	/**
	 * 
	 * @param choice
	 * @throws ElectionClosedException
	 *             if election is closed
	 */
	public void vote(final T choice) throws ElectionClosedException {
		controlElectionStatus();
		Ballot<T> currentBallot = getCurrentBallot();
		currentBallot.vote(choice);
	}

	private Ballot<T> getCurrentBallot() {
		return electionBallots.getCurrentBallot();
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

	public List<Vote<T>> getVotesOnTheCurrentBallot() {
		return getCurrentBallot().getVotes();
	}

	/**
	 * renvoi le resultat des elections <br>
	 * 
	 * @return resultat election
	 */
	public ResultsElection<T> getResultat() {
		List<Vote<T>> votesFromCurrentBallot = getCurrentBallot().getVotes();
		return new ResultsElection<T>(votesFromCurrentBallot, initialChoices);
	}

	public ElectionStatus getStatus() {
		return status;
	}

	public int getBallotNumber() {
		return electionBallots.getBallotNumber();
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
		if (electionBallots.isElectionWithOneBallot()) {
			closeElection();
		} else {
			electionBallots.incrementBallotNumber();
		}
	}

	public int getCurrentBallotNumber() {
		return electionBallots.getCurrentBallotNumber();
	}

	public List<String> getChoicesCurrentBallot() {
		return null;
		
	}

}
