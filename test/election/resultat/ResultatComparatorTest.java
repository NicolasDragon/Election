package election.resultat;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import election.resultat.ChoiceResult;
import election.resultat.ResultatComparator;

public class ResultatComparatorTest {

	private static final int PREMIER_PARAM_AVANT_SECOND = -1;

	private static final int PREMIER_PARAM_APRES_SECOND = 1;
	@Test
	public void comparatorResultat() throws Exception {

		/* GIVEN */
		ResultatComparator resultatComparator = new ResultatComparator();

		ChoiceResult<String> resultat2 = new ChoiceResult<String>(1L, null,1L);
		ChoiceResult resultat1 = new ChoiceResult<String>(2L, null,1L);
		/* WHEN */
		int result = resultatComparator.compare(resultat1, resultat2);
		
		/* THEN */
		Assert.assertEquals(PREMIER_PARAM_AVANT_SECOND, result);
	}
	@Test
	public void comparatorResultatCase2() throws Exception {

		/* GIVEN */
		ResultatComparator resultatComparator = new ResultatComparator();

		ChoiceResult<String> resultat2 = new ChoiceResult<String>(2L, null,1L);
		ChoiceResult resultat1 = new ChoiceResult<String>(1L, null,1L);
		/* WHEN */
		int result = resultatComparator.compare(resultat1, resultat2);
		
		/* THEN */
		Assert.assertEquals(PREMIER_PARAM_APRES_SECOND, result);
	}
}
