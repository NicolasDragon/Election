package election.resultat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 
 * @author MSI
 *
 * @param <T>
 *            choix
 */
public class ResultatElection<T> {

	/**/
	private final List<ChoiceResult<T>> listeOrdonneeDesResultatsParNombreDeVote;
	private Comparator<ChoiceResult> resultatComparator = new ResultatComparator();

	/**
	 * 
	 * @param votes
	 * @param choix
	 */
	public ResultatElection(List<Vote<T>> votes, List<T> choix) {
		List<ChoiceResult<T>> resultats = getListeResultatNonOrdonnee(votes, choix);
		resultats.sort(resultatComparator);
		this.listeOrdonneeDesResultatsParNombreDeVote = resultats;
	}

	/**
	 * calcule le resultat pour chaque choix
	 * 
	 * @param votes
	 * @param choix
	 * @return
	 */
	private List<ChoiceResult<T>> getListeResultatNonOrdonnee(List<Vote<T>> votes, List<T> choix) {
		List<ChoiceResult<T>> resultats = new ArrayList<ChoiceResult<T>>();
		for (T ch : choix) {
			Long countChoice = calculerNombreVotePourCeChoix(votes, ch);
			ChoiceResult<T> resultat = new ChoiceResult<T>(countChoice, ch,Long.valueOf(votes.size()));
			resultats.add(resultat);
		}
		return resultats;
	}

	private Long calculerNombreVotePourCeChoix(List<Vote<T>> votes, T ch) {
		Predicate<Vote<T>> predicat = vote -> vote.getChoix().equals(ch);
		Long countChoice = votes.stream().filter(predicat).count();
		return countChoice;
	}

	public List<ChoiceResult<T>> getResult() {
		return listeOrdonneeDesResultatsParNombreDeVote;
	}

	/**
	 * indique s'il y a deux choix avec le même nombre de vote
	 * 
	 * @return
	 */
	public boolean aEgalite() {
		// resultats.stream().filter(predicate)
		return false;
	}

	/**
	 * 
	 * @param nombreDeChoix
	 * @return
	 */
	public List<ChoiceResult<T>> getNChoixLesPlusVotes(int nombreDeChoix) {
		int workingNumber = determineRightNumberOfChoice(nombreDeChoix);
		return listeOrdonneeDesResultatsParNombreDeVote.subList(0, workingNumber);
	}

	private int determineRightNumberOfChoice(int nombreDeChoix) {
		int workingNumber = nombreDeChoix;
		int listSize = listeOrdonneeDesResultatsParNombreDeVote.size();
		if (nombreDeChoix > listSize) {
			workingNumber = listSize;
		}
		return workingNumber;
	}
}
