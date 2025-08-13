package othello;

/**
 * Keep track of all of the tokens on the board. Knows what the players tokens 
 * look like ('X' and 'O'), whether given coordinates are on the board, whether 
 * either of the players have a move somewhere on the board, and what happens 
 * when a player makes a move at a specific location (the opposite players 
 * tokens are flipped).
 * 
 * Othello makes use of the OthelloBoard.
 *
 */
public class OthelloBoard {
	
	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	/**
	 * Constructs a new OthelloBoard of size dim, with a row containing (X, O)
	 *                               followed by another row containing (O, X)
	 *                               at the centre of the board.
	 *
	 * @param dim
	 */
	public OthelloBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	/**
	 * Return whether a move for player P1, or P2, at position row, col is valid.
	 *
	 * @param row
	 * @param col
	 * @param player P1, or P2
	 * @return whether the move is valid.
	 */
	public boolean validMove(int row, int col, char player) {
		for (int drow = -1; drow < 2; drow++) {
			for (int dcol = -1; dcol < 2; dcol++) {
				if (hasMove(row, col, drow, dcol) == player) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *
	 * @param board
	 * @return a hard copy of the original board.
	 */
	public char[][] cloneBoard(char[][] board) {
		char[][] clone = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				clone[row][col] = board[row][col];
			}
		}
		return clone;
	}

	/**
	 * Returns where the move which maximizes the number of tokens owned by this player is,
	 * along with how many tokens are gained as a result, all stored in an array of form
	 * {row, col, tokens gained after move}. In case of a tie between two moves,(row1,column1)
	 * and (row2,column2) the one with the smallest row wins. In case both moves have the same row,
	 * then the move with the smaller column wins.
	 *
	 * @param player P1, or P2
	 * @return an array made of {row, col, tokens gained after move} for the greediest move.
	 */
	public int[] greediestMove(char player) {
		char[][] originalBoard = cloneBoard(this.board);
		int maxFlip = 0;
		int[] result = new int[3];
		result[0] = this.dim;
		result[1] = this.dim;
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				int originalCount = getCount(player);
				if (move(row, col, player)){
					int newCount = getCount(player);
					int difference = newCount - originalCount;
					if (difference > maxFlip){
						maxFlip = difference;
						result[0] = row;
						result[1] = col;
						result[2] = maxFlip;
					}
					this.board = cloneBoard(originalBoard);
				}
			}
		}
		return result;
	}

	/**
	 *
	 * @return the dimension of the board.
	 */
	public int getDimension() {
		return this.dim;
	}

	/**
	 * 
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == P1) {
			return P2;
		} else if (player == P2) {
			return P1;
		} else {
			return EMPTY;
		}
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		if (validCoordinate(row, col)) {
			return this.board[row][col];
		} else {
			return EMPTY;
		}
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
		return row >= 0 && col >= 0 && row < dim && col < dim;
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. If alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {
		if ((drow == 0 && dcol == 0) || !validCoordinate(row, col)) {
			return EMPTY;
		}
		int crow = row;
		int ccol = col;
		char first = this.board[crow][ccol];
		while (validCoordinate(crow, ccol) && !(this.board[crow][ccol] == EMPTY)){
			if (first != this.board[crow][ccol]) {
				return otherPlayer(first);
			}
			crow += drow;
			ccol += dcol;
		}
		return EMPTY;
	}


	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	private int flip(int row, int col, int drow, int dcol, char player) {
		char whoCanMove = alternation(row, col, drow, dcol);
		if (whoCanMove == EMPTY) {
			return -1;
		} else if (whoCanMove != player) {
			return 0;
		} else {
				int crow = row, ccol = col, changed = 0;
				while (this.board[crow][ccol] != player) {
					this.board[crow][ccol] = player;
					changed ++;
					crow += drow;
					ccol += dcol;
				}
				return changed;
		}
	}


	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	private char hasMove(int row, int col, int drow, int dcol) {
		if (!validCoordinate(row, col)) {return EMPTY;}

		char mover = alternation(row + drow, col + dcol, drow, dcol);
		if (mover != EMPTY && this.board[row][col] == EMPTY) {
			return mover;
		}
		return EMPTY;
	}

	/**
	 * 
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	public char hasMove() {
		boolean p1 = false, p2 = false;
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				for (int drow = -1; drow < 2; drow++) {
					for (int dcol = -1; dcol < 2; dcol++) {
						p1 = ((p1) || (hasMove(row, col, drow, dcol) == P1));
						p2 = ((p2) || (hasMove(row, col, drow, dcol) == P2));

					}
				}
			}
		}
		if (p1 && p2) {
			return BOTH;
		} else if (p1) {
			return P1;
		} else if (p2) {
			return P2;
		} else {
			return EMPTY;
		}
	}

	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		boolean successfulMove = false;
		for (int drow = -1; drow < 2; drow++) {
			for (int dcol = -1; dcol < 2; dcol++) {
				if (hasMove(row, col, drow, dcol) == player) {
					flip(row + drow, col + dcol, drow, dcol, player);
					successfulMove = true;
				}
			}
		}
		if (successfulMove){
			this.board[row][col] = player;
		}
		return successfulMove;
	}


	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		int count = 0;
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				if (this.board[row][col] == player) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information.
	 */
	public String toString() {
		String s = "";

		// Column headers (top)
		s += "   ";
		for (int col = 0; col < this.dim; col++) {
			s += String.format(" %d  ", col);
		}
		s += '\n';

		// Top border
		s += "  +";
		for (int col = 0; col < this.dim; col++) {
			s += "---+";
		}
		s += '\n';

		// Rows
		for (int row = 0; row < this.dim; row++) {
			// Left row label
			s += String.format("%2d|", row);

			// Cells
			for (int col = 0; col < this.dim; col++) {
				s += " " + this.board[row][col] + " |";
			}

			// Right row label
			s += String.format("%2d\n", row);

			// Row border
			s += "  +";
			for (int col = 0; col < this.dim; col++) {
				s += "---+";
			}
			s += '\n';
		}

		// Column headers (bottom)
		s += "   ";
		for (int col = 0; col < this.dim; col++) {
			s += String.format(" %d  ", col);
		}
		s += '\n';

		return s;
	}

	/**
	 * A quick test of OthelloBoard.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloBoard ob = new OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));

		// Should all be blank
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());

	}
}
