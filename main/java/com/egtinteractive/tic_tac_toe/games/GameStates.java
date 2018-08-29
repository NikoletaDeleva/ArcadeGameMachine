package com.egtinteractive.tic_tac_toe.games;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tic_tac_toe.db_conection.DBQueries;
import com.egtinteractive.tic_tac_toe.player.Player;

public enum GameStates implements GameMethods {
    START_GAME {
	@Override
	public boolean start(final Game game) {

	    game.getDrawBoard().drawBoard(game.getBoard());

	    if (ThreadLocalRandom.current().nextInt(0, 100) < 50) {

		game.getArcadeGamesMachine().getIo().write("AI starts first");

		game.getPlayer().setSign("X");
		game.getAi().setSing("O");

		game.getArcadeGamesMachine().getIo()
			.write(String.format("%s %10s" + System.lineSeparator(), "AI : O", " Player : X"));

		game.setPosition(game.getAi().move(game.getBoard()));
		game.moveAI(game.getPosition());
		game.getDrawBoard().drawBoard(game.getBoard());

	    } else {
		game.getArcadeGamesMachine().getIo().write("Player starts first");

		game.getPlayer().setSign("O");
		game.getAi().setSing("X");

		game.getArcadeGamesMachine().getIo()
			.write(String.format("%s %10s" + System.lineSeparator(), "AI : X", " Player : O"));
	    }

	    game.setGameState(GameStates.PLAYER);
	    game.move();
	    return true;
	}
    },
    AI {
	@Override
	public boolean moveAI(final Game game) {
	    if (game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		game.end();
		return true;
	    }

	    game.setPosition(game.getAi().move(game.getBoard()));
	    game.moveAI(game.getPosition());
	    game.getDrawBoard().drawBoard(game.getBoard());

	    if (game.isWinner()) {

		game.getArcadeGamesMachine().getIo().write("AI wins!");
		addGameWithNoPlayer(game);
		game.setGameState(GameStates.END_GAME);
		game.end();

	    } else {
		if (game.getBoard().isFull()) {
		    game.setGameState(GameStates.END_GAME);
		    game.end();
		} else {
		    game.setGameState(GameStates.PLAYER);
		    game.move();
		}
	    }
	    return true;
	}
    },
    PLAYER {

	@Override
	public boolean movePlayer(final Game game) {
	    if (game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		game.end();
		return true;
	    }

	    String position;
	    do {
		position = game.getArcadeGamesMachine().getIo().read();
	    } while (!game.getArcadeGamesMachine().isNumeric(position)
		    || !game.getBoard().isFieldFree(Integer.valueOf(position)));

	    game.setPosition(Integer.valueOf(position));
	    game.movePlayer(game.getPosition());
	    game.getDrawBoard().drawBoard(game.getBoard());

	    if (game.isWinner()) {

		game.getArcadeGamesMachine().getIo().write("Player wins!");
		game.getArcadeGamesMachine().getIo().write("Set your name:");
		String name = game.getArcadeGamesMachine().getIo().read();
		giveName(name, game);

		game.setGameState(GameStates.END_GAME);
		game.end();

	    } else {
		if (game.getBoard().isFull()) {
		    game.setGameState(GameStates.END_GAME);
		    game.end();
		} else {
		    game.setGameState(GameStates.AI);
		    game.move();
		}

	    }
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
	public boolean endGame(final Game game) {
	    if (!game.isWinner()) {
		game.getArcadeGamesMachine().getIo().write("Equal game!");
	    }
	    game.getArcadeGamesMachine().getIo().listAll(showResult(game));
	    game.getArcadeGamesMachine().turnOn();
	    return true;
	}
    };

    void addGameWithNoPlayer(final Game game) {
	game.getDbQueries().addGameWithNoPlayer(game.getGameState().toString());
    }

    List<Player> showResult(final Game game) {
	return game.getDbQueries().showTopThreePlayers();
    }

    @Override
    public boolean start(final Game game) {
	return false;
    }

    @Override
    public boolean moveAI(final Game game) {
	return false;
    }

    @Override
    public boolean movePlayer(final Game game) {
	return false;
    }

    @Override
    public boolean endGame(Game game) {
	return false;
    }
}
