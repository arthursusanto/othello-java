package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman makes a move by asking the user for an input for both row and col from the console.
 *
 */
public class PlayerHuman extends Player {
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));


	/**
	 * Constructs a new PlayerHuman playing in the game, othello, as player.
	 *
	 * @param othello
	 * @param player
	 */
	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 *
	 * @return the supplied Move from the user at (row, col).
	 */
	public Move getMove() {
		
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Ask the user for their input for row, or col, depending on the message.
	 *
	 * @param message either row, or col
	 * @return the input supplied by the user for message, either row or col.
	 */
	private int getMove(String message) {
		
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}
