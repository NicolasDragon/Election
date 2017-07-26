package election.resultat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import election.resultat.ChoiceResult;
import election.resultat.ResultatElection;

public class ResultatElectionTest {

	private static final String CHOIX_3 = "3";
	private static final String CHOIX_2 = "2";
	private static final String CHOIX_1 = "1";

	private final List<String> choix = Arrays.asList(CHOIX_1, CHOIX_2, CHOIX_3);

	/**
	 * cas d'un vote avec un gagnant <br>
	 * verifie qu'on recupere les résultats de l'election
	 * 
	 */
	@Test
	public void electionAvecUnGagnantGetResultat() throws Exception {

		/* GIVEN */
		List<Vote<String>> votes = new ArrayList<Vote<String>>();
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_2));

		/* WHEN */
		ResultatElection<String> resultatElection = new ResultatElection<String>(votes, choix);
		List<ChoiceResult<String>> result = resultatElection.getResult();

		/* THEN */
		assertEquals(CHOIX_1, result.get(0).getChoix());
		assertEquals(CHOIX_2, result.get(1).getChoix());
	}

	/**
	 * cas d'un vote avec un gagnant <br>
	 * verifie qu'on recupere les résultats de l'election
	 * 
	 */
	@Ignore
	@Test
	public void electionAvecEgalite() throws Exception {

		/* GIVEN */

		List<Vote<String>> votes = new ArrayList<Vote<String>>();
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_2));

		/* WHEN */
		ResultatElection<String> resultatElection = new ResultatElection<String>(votes, choix);

		/* THEN */
		assertEquals(true, resultatElection.aEgalite());
	}

	/**
	 * test de recuperation des N premiers dans le résultat d'une élection. verifie que : <br>
	 * - nombre récupéré est ok
	 * @throws Exception
	 */
	@Test
	public void recupererNChoixLesPlusVotes() throws Exception {
		/* GIVEN */
		int nombreDeChoix = 2;
		List<Vote<String>> votes = createVotes();

		/* WHEN */
		ResultatElection<String> resultatElection = new ResultatElection<String>(votes, choix);

		/* THEN */
		List<ChoiceResult<String>> choixLesPlusVotes = resultatElection.getNChoixLesPlusVotes(nombreDeChoix);
		assertEquals(2, choixLesPlusVotes.size());
	}

	/**
	 * recuperation des N choix les plus votés avec un paramètre plus grand que le nombre de choix différent .verifie :<br>
	 * - qu'on récupere toute la liste
	 * @throws Exception
	 */
	@Test
	public void recupererNChoixLesPlusVotesCasMauvaisParametre() throws Exception {
		/* GIVEN */
		int nombreDeChoix = 10;
		List<Vote<String>> votes = createVotes();

		/* WHEN */
		ResultatElection<String> resultatElection = new ResultatElection<String>(votes, choix);

		/* THEN */
		List<ChoiceResult<String>> choixLesPlusVotes = resultatElection.getNChoixLesPlusVotes(nombreDeChoix);
		assertEquals(3, choixLesPlusVotes.size());
	}
	
	
	
	
	
	private List<Vote<String>> createVotes() {
		List<Vote<String>> votes = new ArrayList<Vote<String>>();
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_1));
		votes.add(new Vote<String>(CHOIX_2));
		votes.add(new Vote<String>(CHOIX_2));
		votes.add(new Vote<String>(CHOIX_3));
		return votes;
	}
}
