package egtinteractive.tic_tac_toe.games;

public enum Game {
    TIC_TAC_TOE("TicTacToe", 10) {
	@SuppressWarnings("unused")
	public void loadTicTacToeGame() {
	    TicTacToe newTicTacToeGame = new TicTacToe();
	    newTicTacToeGame.start();
	}
    };

    private final String name;
    private final long price;

    Game(String name, long price) {
	this.name = name;
	this.price = price;
    }

    public long getPrice() {
	return price;
    }

    public String getName() {
	return name;
    }

}
