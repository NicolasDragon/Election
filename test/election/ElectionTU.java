package election;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import election.resultat.ResultsElection;

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
		Election<String> election = new Election<String>(ElectionConstantesTest.choices, BALLOT_NUMBER);

		assertEquals(3, election.getChoix().size());
		assertEquals(ElectionConstantesTest.CHOICE_1, election.getChoix().get(0));
		assertEquals(ElectionStatus.OPEN, election.getStatus());
		assertEquals(BALLOT_NUMBER, election.getBallotNumber());
	}

	/**
	 * cas de vote à une election verifie que: <br>
	 * - le vote est bien enregistré <br>
	 * - la valeur du vote est bonne <br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void voteTest() throws Exception {

		/* GIVEN */
		Election<String> result = new Election<String>(ElectionConstantesTest.choices);

		/* WHEN */
		result.vote(ElectionConstantesTest.CHOICE_1);

		/* THEN */
		assertEquals(1, result.getVotes().size());
		assertEquals(ElectionConstantesTest.CHOICE_1, result.getVotes().get(0).getChoix());
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
		Election<String> election = new Election<String>(ElectionConstantesTest.choices);
		int nombreResultat = 3;

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.vote(ElectionConstantesTest.CHOICE_2);

		/* THEN */
		ResultsElection<String> result = election.getResultat();
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
		Election<String> election = new Election<>(ElectionConstantesTest.choices);

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
		Election<String> election = new Election<>(ElectionConstantesTest.choices);

		/* WHEN */
		election.closeElection();
		election.vote(ElectionConstantesTest.CHOICE_1);

	}

	/**
	 * election with two ballots <br>
	 * verify : <br>
	 * - by closing, the current Ballot Number change
	 *
	 * @throws Exception
	 */
	@Test
	public void closeFirstBallotTest() throws Exception {
		/* GIVEN */
		int CURRENT_BALLOT_NUMBER_EXPECTED = 2;
		Election<String> election = new Election<>(ElectionConstantesTest.choices, 2);

		/* WHEN */
		election.closeBallot();

		/* THEN */
		assertEquals(CURRENT_BALLOT_NUMBER_EXPECTED, election.getBallotNumber());
	}

	/**
	 * election with one ballot. verifiy : <br>
	 * - the close of the only ballot close the election <br>
	 * - the close of the ballot doesn't increment
	 * {@link Election#getCurrentBallotNumber()}
	 * 
	 * @throws Exception
	 */
	@Test
	public void closeBallotForElectionWithOneBallotTest() throws Exception {

		/* GIVEN */
		int TOTAL_BALLOT_NUMBER = 1;
		Election<String> election = new Election<>(ElectionConstantesTest.choices, TOTAL_BALLOT_NUMBER);
		/* WHEN */
		election.closeBallot();

		/* THEN */
		assertEquals(1, election.getCurrentBallotNumber());
		assertEquals(ElectionStatus.CLOSE, election.getStatus());
	}
}
