package othello;

/**
 * Prints out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy.
 *
 */
public class OthelloControllerRandomVSGreedy extends OthelloController {

	/**
	 * Constructs a new OthelloController with a new Othello game, played by a random,
	 * and a greedy player, as player1, and player2 respectively.
	 */
	public OthelloControllerRandomVSGreedy() {
		this.othello = new Othello();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
	}

	/**
	 * Starts a new Othello game between a random, and a greedy player,
	 * as player1, and player2 respectively.
	 * When the game is over, the winner is returned.
	 *
	 * @return the winner of the game.
	 */
	@Override
	public char play() {
		while (!othello.isGameOver()) {

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1){
				move = player1.getMove();
			} else if (whosTurn == OthelloBoard.P2){
				move = player2.getMove();
			}

			othello.move(move.getRow(), move.getCol());
		}
		return othello.getWinner();
	}

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {

		int p1wins = 0, p2wins = 0, numGames = 10000;
		for (int i = 0; i < numGames; i++) {
			OthelloControllerRandomVSGreedy oc = new OthelloControllerRandomVSGreedy();
			char winner = oc.play();
			if (winner == OthelloBoard.P1){
				p1wins++;
			} else if (winner == OthelloBoard.P2){
				p2wins++;
			}
		}
		System.out.println("Probability Random wins=" + (float) p1wins / numGames);
		System.out.println("Probability Greedy wins=" + (float) p2wins / numGames);
	}
}
