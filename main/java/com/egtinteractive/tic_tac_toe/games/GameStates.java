package com.egtinteractive.tic_tac_toe.games;

import java.sql.Connection;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tic_tac_toe.db_conection.DBQueries;

public enum GameStates implements GameMethods {
    START_GAME {
	@Override
	public boolean start(Game game) {

	    game.drawBoard.drawBoard(game.board);

	    if (ThreadLocalRandom.current().nextInt(0, 100) < 50) {
		game.io.write("AI starts first");
		game.player.setSign("X");
		game.ai.setSing("O");
		game.setPosition(game.ai.move(game.board));
		game.moveAI(game.getPosition());
		game.drawBoard.drawBoard(game.board);
	    } else {
		game.io.write("Player starts first");
		game.player.setSign("O");
		game.ai.setSing("X");
	    }
	    game.setGameState(GameStates.PLAYER);
	    game.move();
	    return true;
	}
    },
    AI {
	@Override
	public boolean moveAI(Game game) {
	    if (game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		game.showResult();
		return true;
	    }

	    game.setPosition(game.ai.move(game.board));
	    game.moveAI(game.getPosition());
	    game.drawBoard.drawBoard(game.board);

	    if (game.isWinner()) {
		game.io.write("AI wins!");
		addGameWithNoPlayer(game);
		game.setGameState(GameStates.END_GAME);
		return true;
	    }

	    game.setGameState(GameStates.PLAYER);
	    game.move();
	    return true;
	}
    },
    PLAYER {

	@Override
	public boolean movePlayer(Game game) {
	    if (game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		game.showResult();
		return true;
	    }

	    int position;

	    do {
		position = game.io.readPosition();
	    } while (!game.board.isFieldFree(position));

	    game.setPosition(position);
	    game.movePlayer(game.getPosition());
	    game.drawBoard.drawBoard(game.board);

	    if (game.isWinner()) {
		game.io.write("Player wins!");
		game.io.write("Set your name:");

		String name = game.io.read();

		giveName(name,game);

		game.setGameState(GameStates.END_GAME);
		return true;
	    }

	    game.setGameState(GameStates.AI);
	    game.move();
	    return true;

	}

	private void addGameWithPlayer(final String name, final Game game) {
	    final DBQueries dbQueries = game.getDbQueries();

	    if (!dbQueries.doesPlayerExists(name)) {
		dbQueries.addPlayer(name);
	    }

	    dbQueries.addGameWithPlayer(name, game.getGameState().toString());
	}

	private void giveName(String name, final Game game) {
	    if (name.trim() == null) {
		addGameWithNoPlayer(game);
	    } else {
		addGameWithPlayer(name, game);
	    }
	}

    },
    END_GAME {
	@Override
	public boolean endGame(Game game) {
	    if (!game.isWinner()) {
		game.io.write("Equal game!");
	    }
	    return true;
	}
    };

    public void addGameWithNoPlayer(final Game game) {
	game.dbQueries.addGameWithNoPlayer(game.getGameState().toString());

    }

    @Override
    public boolean start(Game game) {
	return false;
    }

    @Override
    public boolean moveAI(Game game) {
	return false;
    }

    @Override
    public boolean movePlayer(Game game) {
	return false;
    }

    @Override
    public boolean endGame(Game game) {
	return false;
    }
}
