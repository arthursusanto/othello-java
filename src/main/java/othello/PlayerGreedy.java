package othello;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * In case of a tie, between two moves, (row1,column1) and (row2,column2),
 * the one with the smallest row wins. In case both moves have the same row, 
 * then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 *
 */

public class PlayerGreedy extends Player {

	/**
	 * Constructs a new PlayerGreedy playing in the game, othello, as player.
	 *
	 * @param othello
	 * @param player
	 */
	public PlayerGreedy(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * Return the Move on the board which flips the most tokens in player's favour.
	 *
	 * @return the greediest Move.
	 */
	public Move getMove() {
		int[] move = othello.board.greediestMove(player);
		return new Move(move[0], move[1]);
	}
}
