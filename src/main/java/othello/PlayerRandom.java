package othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 *
 */
public class PlayerRandom extends Player {

	private Random rand = new Random();

	/**
	 * Constructs a new PlayerRandom playing in the game, othello, as player.
	 *
	 * @param othello
	 * @param player
	 */
	public PlayerRandom(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * Return a random move from all the possible moves on the board.
	 *
	 * @return a random move.
	 */
	public Move getMove() {
		ArrayList<Move> moves = new ArrayList<>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				if (othello.board.validMove(row, col, player)) {
					moves.add(new Move(row, col));
				}
			}
		}
		return moves.get(rand.nextInt(moves.size()));
	}
}
