package othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 *
 */
public class OthelloControllerHumanVSGreedy extends OthelloController {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with one user at the console, and a bot who uses a greedy strategy.
	 */
	public OthelloControllerHumanVSGreedy() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
		oc.play();
	}
}
