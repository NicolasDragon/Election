package election.resultat;

import static org.junit.Assert.*;

import org.junit.Test;

import election.ElectionConstantesTest;

public class ResultatTest {

	@Test
	public void getChoiceProportion() {
		/* GIVEN */
		long totalVotesCount = 10L;
		float waitedProportion = 10.0f;
		ChoiceResult<String> result = new ChoiceResult<String>(1L, ElectionConstantesTest.CHOIX_1, totalVotesCount);
		/* WHEN */
		float proportion=result.getChoiceProportion();
		/* THEN */
		boolean resul=Math.abs(proportion - waitedProportion) <= 0.0001f;
		assertTrue(resul);
	}
		

}
