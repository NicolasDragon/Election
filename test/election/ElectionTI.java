package election;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import election.resultat.ResultsElection;

public class ElectionTI {

	/**
	 * integration test to test the combinason of the different classes <br>
	 * verify :<br>
	 * - count of vote is ok <br>
	 * - the order of result is ok <br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void electionWithOneBallotTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<String>(ElectionConstantesTest.choices);

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.vote(ElectionConstantesTest.CHOICE_2);
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.closeElection();

		/* THEN */
		ResultsElection<String> resultElection = election.getResultat();
		assertEquals(3, resultElection.getResult().size());
		assertEquals(ElectionConstantesTest.CHOICE_1, resultElection.getResult().get(0).getChoice());
		assertEquals(ElectionConstantesTest.CHOICE_2, resultElection.getResult().get(1).getChoice());
		assertEquals(ElectionConstantesTest.CHOICE_3, resultElection.getResult().get(2).getChoice());
	}

	/**
	 * election with two ballots . verify : <br>
	 * - we get the right winner based on the second ballot votes
	 * 
	 * @throws Exception
	 */
	@Test
	public void resultElectionTwoBallotsTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<String>(ElectionConstantesTest.choices, 2);

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.vote(ElectionConstantesTest.CHOICE_2);
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.closeBallot();
		election.vote(ElectionConstantesTest.CHOICE_2);
		/* THEN */
		ResultsElection<String> resultElection = election.getResultat();
		assertEquals(ElectionConstantesTest.CHOICE_2, resultElection.getResult().get(0).getChoice());
	}

	/**
	 * get list choice on second ballot . verify : <br>
	 * -size is correct <br>
	 * - list is correct <br>
	 */
	@Test
	public void choicesListSecondBallotTest() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<String>(ElectionConstantesTest.choices, 2);

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.vote(ElectionConstantesTest.CHOICE_2);
		election.vote(ElectionConstantesTest.CHOICE_1);
		election.closeBallot();
		/* THEN */
		List<String> currentBallotChoices = election.getChoicesCurrentBallot();
		assertEquals(2, currentBallotChoices.size());
	}
}
