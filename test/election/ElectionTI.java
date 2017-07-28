package election;

import org.junit.Test;

import election.resultat.ResultsElection;

import static org.junit.Assert.*;

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
		assertEquals(ElectionConstantesTest.CHOICE_1, resultElection.getResult().get(0).getChoix());
		assertEquals(ElectionConstantesTest.CHOICE_2, resultElection.getResult().get(1).getChoix());
		assertEquals(ElectionConstantesTest.CHOICE_3, resultElection.getResult().get(2).getChoix());
	}

}
