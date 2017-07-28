package election;

import java.util.ArrayList;
import java.util.List;

import election.resultat.Vote;

/**
 * class representing a ballot of an <Election>
 */
public class Ballot<T> {

	private final List<T> choix;
	private List<Vote<T>> votes = new ArrayList<Vote<T>>();

	public Ballot(List<T> choices) {
		choix = choices;
	}

	public void vote(T choice) {
		votes.add(new Vote(choice));
	}

	public List<Vote<T>> getVotes() {
		return votes;
	}

	public List<T> getChoix() {
		return choix;
	}

}
