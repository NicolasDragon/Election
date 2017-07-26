package election.resultat;

public class Vote<T> {

	private T choix;

	public Vote(T choix) {
		this.choix = choix;
	}

	public T getChoix() {
		return choix;
	}

}
