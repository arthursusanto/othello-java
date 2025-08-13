package othello;
/**
 * Capture a move made at row, col.
 *
 * All OthelloControllers make use of move.
 */
public class Move {
	private int row, col;

	/**
	 * Constructs a new Move, at coordinates (row, col).
	 *
	 * @param row
	 * @param col
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 *
	 * @return the row of the Move.
	 */
	public int getRow() {
		return row;
	}

	/**
	 *
	 * @return the column of the Move.
	 */
	public int getCol() {
		return col;
	}

	/**
	 *
	 * @return a string representation of the Move as a coordinate.
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
