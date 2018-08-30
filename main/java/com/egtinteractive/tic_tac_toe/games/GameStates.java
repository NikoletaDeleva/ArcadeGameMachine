package com.egtinteractive.tic_tac_toe.games;

import java.util.List;

import com.egtinteractive.tic_tac_toe.db_conection.DBQueries;
import com.egtinteractive.tic_tac_toe.player.Player;

public enum GameStates implements GameMethods {
    START_GAME {
	@Override
	public boolean start(final Game game) {

	    game.getDrawBoard().drawBoard(game.getBoard());

	    if (game.getUtils().choseRandom()) {

		game.setCurrent("AI");

	    } else {
		game.setCurrent("Player");
	    }

	    game.getIO().write(game.getCurrent() + " starts first");

	    if (game.getCurrent().equals("Player")) {
		game.getPlayer().setSign(game.getFirstsign());
		game.getAi().setSing(game.getSecondsign());
	    } else {
		game.getAi().setSing(game.getFirstsign());
		game.getPlayer().setSign(game.getSecondsign());
	    }

	    game.getIO().write(String.format("%s %10s" + System.lineSeparator(), "AI :" + game.getAi().getSign(),
		    " Player : " + game.getPlayer().getSign()));

	    game.setGameState(GameStates.MOVE);
	    game.move();
	    return true;
	}
    },
    MOVE {

	@Override
	public boolean move(final Game game) {
	    if (endIfBoardIsFull(game)) {
		return true;
	    }

	    if (game.getCurrent().equals("Player")) {

		String position;
		do {
		    position = game.getIO().read();
		} while (!game.getUtils().isNumeric(position)
			|| !game.getBoard().isFieldFree(Integer.valueOf(position)));

		game.setPosition(Integer.valueOf(position));
		game.movePlayer(game.getPosition());
	    } else {
		game.setPosition(game.getAi().move(game.getBoard()));
		game.moveAI(game.getPosition());
		game.getDrawBoard().drawBoard(game.getBoard());
	    }

	    if (game.isWinner()) {
		
		game.getIO().write(game.getCurrent() +" wins!");
		if (game.getCurrent().equals("Player")) {

		    game.getDrawBoard().drawBoard(game.getBoard());

		    game.getIO().write("Set your name:");
		    String name = game.getIO().read();
		    giveName(name, game);

		} else {

		    game.getIO().write("AI wins!");
		    addGameWithNoPlayer(game);

		}

		game.setGameState(GameStates.END_GAME);
		game.end();

	    } else {
		if (!endIfBoardIsFull(game)) {
		    if (game.getCurrent().equals("Player")) {
			game.setCurrent("AI");
		    } else {
			game.setCurrent("Player");
		    }
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
		game.getIO().write("Equal game!");
	    }
	    game.getIO().listAll(showResult(game));
	    game.getMachine().turnOn();
	    return true;
	}
    };

    void addGameWithNoPlayer(final Game game) {
	game.getDbQueries().addGameWithNoPlayer(game.getGameState().toString());
    }

    List<Player> showResult(final Game game) {
	return game.getDbQueries().showTopThreePlayers();
    }

    boolean endIfBoardIsFull(final Game game) {
	if (game.getBoard().isFull()) {
	    game.setGameState(GameStates.END_GAME);
	    game.end();
	    return true;
	}
	return false;
    }

    @Override
    public boolean start(final Game game) {
	return false;
    }

    @Override
    public boolean move(final Game game) {
	return false;
    }

    @Override
    public boolean endGame(Game game) {
	return false;
    }
}
