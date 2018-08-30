package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.games.GamesLoader;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public boolean putCoins(final ArcadeGamesMachine machine, long coins) {

	    if (machine.getCoins() >= 10) {
		machine.setState(StateMachine.SELECT_GAME);
		machine.getIo().write("Selct game!");
		machine.select();
	    } else {
		if (coins <= 0) {
		    machine.getIo().write("Put coins!");
		    String coinsToPut;
		    do {
			coinsToPut = machine.getIo().read();
		    } while (!machine.getUtils().isNumeric(coinsToPut));

		    machine.putCoins(Integer.valueOf(coinsToPut));

		} else {
		    machine.addCoinsToMachine(coins);
		    if (machine.getCoins() >= 10) {
			putCoins(machine, 0);
		    } else {
			machine.getIo().write("Put coins!");
			String coinsToPut;
			do {
			    coinsToPut = machine.getIo().read();
			} while (!machine.getUtils().isNumeric(coinsToPut));

			putCoins(machine, Integer.valueOf(coinsToPut));
		    }
		}
	    }
	    return true;
	}

	@Override
	public boolean service(final ArcadeGamesMachine machine) {
	    machine.setState(StateMachine.SERVICE);
	    return true;
	}
    },
    SELECT_GAME {
	@Override
	public boolean putCoins(final ArcadeGamesMachine machine, long coins) {
	    if (coins <= 0) {
		machine.getIo().write("Put coins!");
		String coinsToPut;
		do {
		    coinsToPut = machine.getIo().read();
		} while (!machine.getUtils().isNumeric(coinsToPut));

		machine.putCoins(Integer.valueOf(coinsToPut));
	    } else {
		machine.addCoinsToMachine(coins);
	    }
	    return true;
	}

	@Override
	public boolean selectGame(final ArcadeGamesMachine machine, final GamesLoader gameType) {

	    if (gameType == null) {
		machine.getIo().write("Selct game!");
		machine.select();
	    }

	    if (machine.getCoins() < gameType.getPrice()) {
		machine.getIo().write("Put coins!");

		String coinsToPut;
		do {
		    coinsToPut = machine.getIo().read();
		} while (!machine.getUtils().isNumeric(coinsToPut));

		putCoins(machine, Integer.valueOf(coinsToPut));
		selectGame(machine, gameType);
	    }

	    machine.takeCustomerCoins(gameType);
	    machine.setState(StateMachine.PLAY_GAME);
	    machine.play();
	    return true;
	}

	@Override
	public boolean service(final ArcadeGamesMachine machine) {
	    machine.setState(StateMachine.SERVICE);
	    return true;
	}

    },
    PLAY_GAME {
	@Override
	public boolean playGame(final ArcadeGamesMachine machine, final GamesLoader gameType, final Game game) {
	    if (machine.getCoins() > 0) {
		final long coinsToReturn = machine.returnCoinsToCustomer();
		machine.getIo().write("Return coins : " + coinsToReturn);
		machine.returnCoinsToCustomer();
	    }
	    gameType.load(machine);
	    return true;
	}

	@Override
	public boolean service(final ArcadeGamesMachine machine) {
	    machine.setState(StateMachine.SERVICE);
	    return true;
	}
    },
    SERVICE {
	@Override
	public boolean endService(final ArcadeGamesMachine machine) {
	    machine.setState(StateMachine.STAND_BY);
	    return true;
	}
    };

    @Override
    public boolean putCoins(final ArcadeGamesMachine machine, long money) {
	return false;
    }

    @Override
    public boolean selectGame(final ArcadeGamesMachine machine, final GamesLoader gameType) {
	return false;
    }

    @Override
    public boolean playGame(final ArcadeGamesMachine machine, final GamesLoader gameType, final Game game) {
	return false;
    }

    @Override
    public boolean service(final ArcadeGamesMachine machine) {
	return false;
    }

    @Override
    public boolean endService(final ArcadeGamesMachine machine) {
	return false;
    }

}
