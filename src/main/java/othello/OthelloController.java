package othello;

abstract class OthelloController {
    protected Othello othello;
	Player player1, player2;

    /**
	 * Starts a new Othello game, alternating turns between player 1 and player 2, reporting what
	 * the board looks like at the start of each player's turn, how many tokens on the board
	 * each player has, and who's turn it is at that moment. Also asks the human player for their
	 * inputted move when it's their turn. When the game is over, it reports what the final board
	 * looks like, how many tokens are on the board for each player, and who won the game.
	 */
    public char play() {
		while (!othello.isGameOver()) {
			this.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1){
				move = player1.getMove();
			} else if (whosTurn == OthelloBoard.P2){
				move = player2.getMove();
			}

			this.reportMove(whosTurn, move);
			othello.move(move.getRow(), move.getCol());
		}
		this.reportFinal();
		return OthelloBoard.EMPTY;
	}

    protected void reportMove(char whosTurn, Move move) {
		System.out.println(whosTurn + " makes move " + move + "\n");
	}

	protected void report() {
		String s = othello.getBoardString() + OthelloBoard.P1 + ":"
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
				+ othello.getWhosTurn() + " moves next";
		System.out.println(s);
	}

	protected void reportFinal() {
		String s = othello.getBoardString() + OthelloBoard.P1 + ":"
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
				+ "  " + othello.getWinner() + " won\n";
		System.out.println(s);
	}
}
