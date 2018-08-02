package com.egtinteractive.tic_tac_toe.games;

public enum Games {
    TIC_TAC_TOE("TicTacToe", 10) {
	@Override
	public void load() {
	    getGame().start();
	}
	
	@Override
	public Game getGame() {
	    TicTacToe newTicTacToeGame = new TicTacToe();
	    return newTicTacToeGame;
	}
    };

    private final String name;
    private final long price;

    Games(String name, long price) {
	this.name = name;
	this.price = price;
    }

    public long getPrice() {
	return price;
    }

    public String getName() {
	return name;
    }
    
    public abstract void load();
    public abstract Game getGame();
}
