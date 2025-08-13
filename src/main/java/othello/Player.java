package othello;

abstract class Player {
    protected Othello othello;
	protected char player;

    public Player(Othello othello, char player) {
        this.othello = othello;
        this.player = player;
    }

    public abstract Move getMove();
}
