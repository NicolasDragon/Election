package election.resultat;
import static org.junit.Assert.*;

import org.junit.Test;

public class VoteTest {

	private static final String CHOIX = "E";

	@Test
	public void voteTest() throws Exception {
		/* GIVEN */
		Vote<String> vote = new Vote<String>(CHOIX);

		/* WHEN */
		String result =  vote.getChoix();
		/* THEN */
		assertEquals(CHOIX, result);
	}
}
