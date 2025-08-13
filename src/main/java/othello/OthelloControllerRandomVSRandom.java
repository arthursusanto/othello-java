package othello;

/**
 * Determines whether the first player or second player has the advantage when
 * both are playing a Random Strategy over the span of 10000 games.
 *
 */
public class OthelloControllerRandomVSRandom extends OthelloController {
	protected Othello othello;
	PlayerRandom player1;
	PlayerRandom player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with one user at the console.
	 */
	public OthelloControllerRandomVSRandom() {

		this.othello = new Othello();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}

	/**
	 * Starts a new Othello game between 2 random players.
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
			OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
			char winner = oc.play();
			if (winner == OthelloBoard.P1){
				p1wins++;
			} else if (winner == OthelloBoard.P2){
				p2wins++;
			}
		}
		System.out.println("Probability first to move wins=" + (float) p1wins / numGames);
		System.out.println("Probability second to move wins=" + (float) p2wins / numGames);
	}
}
