package election;

import org.junit.Ignore;
import org.junit.Test;

import election.resultat.ResultatElection;

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
	public void testElectionA1Tour() throws Exception {

		/* GIVEN */
		Election<String> election = new Election<>(ElectionConstantesTest.choix);

		/* WHEN */
		election.vote(ElectionConstantesTest.CHOIX_1);
		election.vote(ElectionConstantesTest.CHOIX_2);
		election.vote(ElectionConstantesTest.CHOIX_1);
		election.closeElection();

		/* THEN */
		ResultatElection<String> resultElection = election.getResultat();
		assertEquals(3, resultElection.getResult().size());
		assertEquals(ElectionConstantesTest.CHOIX_1, resultElection.getResult().get(0).getChoix());
		assertEquals(ElectionConstantesTest.CHOIX_2, resultElection.getResult().get(1).getChoix());
		assertEquals(ElectionConstantesTest.CHOIX_3, resultElection.getResult().get(2).getChoix());
	}

}
