package election;

import java.util.ArrayList;
import java.util.List;

import election.resultat.ResultatElection;
import election.resultat.Vote;

/**
 * Election class
 * 
 * @author MSI
 *
 * @param <T>
 *            class of choice
 */
public class Election<T> {
	// we cant modify it
	private final List<T> choix;
	private List<Vote<T>> votes = new ArrayList<Vote<T>>();
	private ElectionStatus status = ElectionStatus.OPEN;
	private int ballotNumber = 1;

	public Election(List<T> choix) {
		this.choix = choix;
	}

	/**
	 * 
	 * @param choix
	 * @param i
	 */
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
	public ResultatElection<T> getResultat() {
		return new ResultatElection<T>(votes, choix);
	}

	public ElectionStatus getStatus() {
		return status;
	}

	public int getBallotNumber() {
		return ballotNumber;
	}

	public void closeElection() {
		status = ElectionStatus.CLOSE;
	}

}
