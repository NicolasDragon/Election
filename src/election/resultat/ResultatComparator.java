package election.resultat;
import java.util.Comparator;

public class ResultatComparator implements Comparator<ChoiceResult> {

	@Override
	public int compare(ChoiceResult o1, ChoiceResult o2) {
		return o2.getNombreVote().compareTo(o1.getNombreVote());
	}

}
