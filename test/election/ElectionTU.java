package election;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import election.resultat.ResultatElection;

public class ElectionTU {

	/**
	 * creation d'une election verifie : <br>
	 * - creation instance <br>
	 * - right choiceNumber <br>
	 * - right election status <br>
	 * - right Ballot number <br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void creationElectionTest() throws Exception {
		/* GIVEN */
		int BALLOT_NUMBER = 2;
		Election<String> election = new Election<String>(ElectionConstantesTest.choix, BALLOT_NUMBER);

		assertEquals(3, election.getChoix().size());
		assertEquals(ElectionConstantesTest.CHOIX_1, election.getChoix().get(0));
		assertEquals(ElectionStatus.OPEN, election.getStatus());
		assertEquals(BALLOT_NUMBER, election.getBallotNumber());
	}

	/**
	 * cas de vote à une election
	 * verifie que: <br>
	 * - le vote est bien enregistré <br>
	 * - la valeur du vote est bonne <br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void voteTest() throws Exception {

		/* GIVEN */
		Election<String> result = new Election<String>(ElectionConstantesTest.choix);

		/* WHEN */
		result.vote(ElectionConstantesTest.CHOIX_1);

		/* THEN */
		assertEquals(1, result.getVotes().size());
		assertEquals(ElectionConstantesTest.CHOIX_1, result.getVotes().get(0).getChoix());
	}

	/**
	 * cas d'une election à plusieurs votes. on verifie que : <br>
	 * - on recupere bien une instance de resultat <br>
	 * - on a le bon nombre de resultat
	 * 
	 * @throws Exception
	 */
	@Test
	public void getResultatTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<String>(ElectionConstantesTest.choix);
		int nombreResultat = 3;

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOIX_1);
		election.vote(ElectionConstantesTest.CHOIX_1);
		election.vote(ElectionConstantesTest.CHOIX_2);

		/* THEN */
		ResultatElection<String> result = election.getResultat();
		Assert.assertNotNull(result);
		assertEquals(nombreResultat, result.getResult().size());
	}

	/**
	 * cas de fermeture d'une election. verifie que <br>
	 * - le statut de l'election est à <ElectionStatus>CLOSE <br>
	 * 
	 */
	@Test
	public void fermetureElectionTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<>(ElectionConstantesTest.choix);

		/* WHEN */
		election.closeElection();

		/* THEN */
		assertEquals(ElectionStatus.CLOSE, election.getStatus());
	}

	/**
	 * case of a close election when someone try to vote. verify <br>
	 * - an <ElectionClosedException> exception is thrown
	 * 
	 * @throws Exception
	 */
	@Test(expected = ElectionClosedException.class)
	public void voteWhenElectionClosedTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<>(ElectionConstantesTest.choix);

		/* WHEN */
		election.closeElection();
		election.vote(ElectionConstantesTest.CHOIX_1);

	}
}
