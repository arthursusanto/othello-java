package othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a random strategy. 
 * 
 *
 */
public class OthelloControllerHumanVSRandom extends OthelloController {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with one user at the console, and a bot who uses a random strategy.
	 */
	public OthelloControllerHumanVSRandom() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a random strategy, that is, it randomly picks 
	 * one of its possible moves.

	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSRandom oc = new OthelloControllerHumanVSRandom();
		oc.play();
	}
}
