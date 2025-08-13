package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class MiscTests {
    OthelloBoard board;

    @BeforeEach
    public void setUp() throws Exception {
        board = new OthelloBoard(Othello.DIMENSION); // 8x8 board
        board.move(2, 4, OthelloBoard.P1);
        board.move(2, 5, OthelloBoard.P2);
        board.move(2, 6, OthelloBoard.P1);
        board.move(2, 3, OthelloBoard.P2);
        board.move(4, 2, OthelloBoard.P1);
        board.move(1, 4, OthelloBoard.P2);

        // Board now looks like
        //   0 1 2 3 4 5 6 7
        //  +-+-+-+-+-+-+-+-+
        // 0| | | | | | | | |0
        //  +-+-+-+-+-+-+-+-+
        // 1| | | | |O| | | |1
        //  +-+-+-+-+-+-+-+-+
        // 2| | | |O|O|X|X| |2
        //  +-+-+-+-+-+-+-+-+
        // 3| | | |X|O| | | |3
        //  +-+-+-+-+-+-+-+-+
        // 4| | |X|X|X| | | |4
        //  +-+-+-+-+-+-+-+-+
        // 5| | | | | | | | |5
        //  +-+-+-+-+-+-+-+-+
        // 6| | | | | | | | |6
        // +-+-+-+-+-+-+-+-+
        // 7| | | | | | | | |7
        //  +-+-+-+-+-+-+-+-+
        //   0 1 2 3 4 5 6 7
    }

    @Test
    public void testValidMove() {
        assertFalse(board.validMove(2, 2, OthelloBoard.P2));
        assertEquals(board.get(2,2), OthelloBoard.EMPTY);

        assertTrue(board.validMove(2, 2, OthelloBoard.P1));
        assertEquals(board.get(2,2), OthelloBoard.EMPTY);

        assertTrue(board.validMove(5, 4, OthelloBoard.P2));
        assertEquals(board.get(5,4), OthelloBoard.EMPTY);

        assertFalse(board.validMove(0, 0, OthelloBoard.P1));
        assertFalse(board.validMove(0, 0, OthelloBoard.P2));
        assertEquals(board.get(0,0), OthelloBoard.EMPTY);

        assertFalse(board.validMove(2,3, OthelloBoard.P1));
        assertFalse(board.validMove(2,3, OthelloBoard.P2));
    }

    @Test
    public void testCloneBoard() {
        char[][] tempBoard = new char[Othello.DIMENSION][Othello.DIMENSION];
        for (int row = 0; row < Othello.DIMENSION; row++) {
            for (int col = 0; col < Othello.DIMENSION; col++) {
                tempBoard[row][col] = board.get(row, col);
            }
        }
        char[][] tempBoard2 = board.cloneBoard(tempBoard);
        assertNotEquals(tempBoard, tempBoard2);

        tempBoard2[5][5] = 'L';
        assertNotEquals(tempBoard[5][5], 'L');
    }

    @Test
    public void testGreediestMove() {
        int[] move = board.greediestMove(OthelloBoard.P1);
        assertEquals(0, move[0]);
        assertEquals(4, move[1]);
        assertEquals(4, move[2]);
        assertEquals(OthelloBoard.EMPTY, board.get(0, 4));

        move = board.greediestMove(OthelloBoard.P2);
        assertEquals(2, move[0]);
        assertEquals(7, move[1]);
        assertEquals(3, move[2]);
        assertEquals(OthelloBoard.EMPTY, board.get(2, 7));

        assertTrue(board.move(0,4, OthelloBoard.P1));
        assertTrue(board.move(2, 7, OthelloBoard.P2));

        move = board.greediestMove(OthelloBoard.P1);
        assertEquals(1, move[0]);
        assertEquals(2, move[1]);
        assertEquals(2, move[2]);

        move = board.greediestMove(OthelloBoard.P2);
        assertEquals(5, move[0]);
        assertEquals(1, move[1]);
        assertEquals(3, move[2]);
    }

    @Test
    public void testPlayerGreedyAndPlayerRandom() {
        Othello othello = new Othello();
        othello.move(2, 4);
        othello.move(2, 5);
        othello.move(2, 6);
        othello.move(2, 3);
        othello.move(4, 2);
        othello.move(1, 4);

        // Board now looks like
        //   0 1 2 3 4 5 6 7
        //  +-+-+-+-+-+-+-+-+
        // 0| | | | | | | | |0
        //  +-+-+-+-+-+-+-+-+
        // 1| | | | |O| | | |1
        //  +-+-+-+-+-+-+-+-+
        // 2| | | |O|O|X|X| |2
        //  +-+-+-+-+-+-+-+-+
        // 3| | | |X|O| | | |3
        //  +-+-+-+-+-+-+-+-+
        // 4| | |X|X|X| | | |4
        //  +-+-+-+-+-+-+-+-+
        // 5| | | | | | | | |5
        //  +-+-+-+-+-+-+-+-+
        // 6| | | | | | | | |6
        // +-+-+-+-+-+-+-+-+
        // 7| | | | | | | | |7
        //  +-+-+-+-+-+-+-+-+
        //   0 1 2 3 4 5 6 7

        PlayerGreedy playergreedy = new PlayerGreedy(othello, OthelloBoard.P1);
        PlayerRandom playerrandom = new PlayerRandom(othello, OthelloBoard.P2);

        assertEquals(OthelloBoard.P1, othello.getWhosTurn());
        Move move = playergreedy.getMove();
        assertEquals(0, move.getRow());
        assertEquals(4, move.getCol());
        assertTrue(othello.move(0, 4));

        assertEquals(OthelloBoard.P2, othello.getWhosTurn());

        move = playerrandom.getMove();
        int moveRow = move.getRow();
        int moveCol = move.getCol();

        assertTrue(othello.board.validMove(moveRow, moveCol, OthelloBoard.P2));
        assertEquals(OthelloBoard.EMPTY, othello.board.get(moveRow, moveCol));
        assertTrue(othello.move(moveRow, moveCol));
    }


}
