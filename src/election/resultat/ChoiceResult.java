package election.resultat;

/**
 * resultat class for a <T> choice
 * 
 * @author MSI
 *
 * @param <T>
 */
public class ChoiceResult<T> {

	private final T choice;
	private final Long votesNumber;
	private final Long totalVotesNumber;

	public ChoiceResult(final Long votesNumberForThatChoice, final T choix, final Long totalVotesNumber) {
		this.votesNumber = votesNumberForThatChoice;
		this.choice = choix;
		this.totalVotesNumber = totalVotesNumber;
	}

	public T getChoix() {
		return choice;
	}

	public Long getNombreVote() {
		return votesNumber;
	}

	/**
	 * return percentage of this choice
	 * @return percentage
	 */
	public float getChoiceProportion() {
		return (votesNumber.longValue() * 100.0f) / totalVotesNumber.longValue();
	}
}
