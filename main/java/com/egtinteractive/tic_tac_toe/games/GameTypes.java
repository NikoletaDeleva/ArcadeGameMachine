package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AITicTacToe;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeDrawBoard;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;
import com.egtinteractive.tic_tac_toe.player.Player;

public enum GameTypes {
    TIC_TAC_TOE("TicTacToe", 10) {
	@Override
	public void load(final ArcadeGamesMachine arcadeGamesMachine) {
	    getGame(arcadeGamesMachine).start();
	}

	@Override
	public Game getGame(final ArcadeGamesMachine arcadeGamesMachine) {
	    TicTacToe newTicTacToeGame = new TicTacToe(new TicTacToeBoard(),
		    new TicTacToeDrawBoard(arcadeGamesMachine.getIo()), new AITicTacToe(), new Player(null, 0),
		    arcadeGamesMachine.getIo(), arcadeGamesMachine);

	    return newTicTacToeGame;
	}
    },

    DUMMY("Dummy", 0) {

	@Override
	public void load(ArcadeGamesMachine arcadeGamesMachine) {
	    arcadeGamesMachine.endService();
	}

	@Override
	public Game getGame(ArcadeGamesMachine arcadeGamesMachine) {
	    return null;
	}

    };
    
    private final String name;
    private final long price;

    GameTypes(final String name, final long price) {
	this.name = name;
	this.price = price;
    }

    public long getPrice() {
	return price;
    }

    public String getName() {
	return name;
    }

    public abstract void load(final ArcadeGamesMachine arcadeGamesMachine);

    public abstract Game getGame(final ArcadeGamesMachine arcadeGamesMachine);
}
