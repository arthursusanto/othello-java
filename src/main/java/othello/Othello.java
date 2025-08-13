package othello;

import java.util.Random;

/**
 * Captures an Othello game, which includes an OthelloBoard, how many moves have 
 * been made, and whosTurn is next (OthelloBoard.P1 or OthelloBoard.P2). Knows 
 * how to make a move using the board and statistics about the game, such as 
 * how many tokens P1 has and how many tokens P2 has. Also knows who the winner 
 * of the game is, and when the game is over.
 * 
 */
public class Othello {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private char whosTurn = OthelloBoard.P1; // P1 moves first!
	public OthelloBoard board = new OthelloBoard(DIMENSION);


	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		if (board.move(row, col, whosTurn)){
			char otherPlayer = OthelloBoard.otherPlayer(whosTurn);
			if (board.hasMove() == OthelloBoard.BOTH || board.hasMove() == otherPlayer){
				whosTurn = otherPlayer;
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		return board.getCount(player);
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if (isGameOver()){
			if (board.getCount(OthelloBoard.P1) > board.getCount(OthelloBoard.P2)) {
				return OthelloBoard.P1;
			} else if (board.getCount(OthelloBoard.P2) > board.getCount(OthelloBoard.P1)) {
				return OthelloBoard.P2;
			} else {
				return OthelloBoard.EMPTY;
			}
		}
		return OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return board.hasMove() == OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return board.toString();
	}

	/**
	 * tests the current class with a completely random game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random rand = new Random();

		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while (!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if (o.move(row, col)) {
				System.out.println("makes move (" + row + "," + col + ")");
				System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
			}
		}

	}
}