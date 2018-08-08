package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.games.Games;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public boolean putCoins(final ArcadeGamesMachine machine, long coins) {

	    if (machine.getCoins() >= 10) {
		machine.setState(StateMachine.SELECT_GAME);
		machine.io.write("Selct game!");
		machine.select();
	    } else {
		if (coins <= 0) {
		    machine.io.write("Put coins!");
		    String coinsToPut;
		    do {
			coinsToPut = machine.io.read();
		    } while (!machine.isNumeric(coinsToPut));

		    machine.putCoins(Integer.valueOf(coinsToPut));

		} else {
		    machine.addCoinsToMachine(coins);
		    if (machine.getCoins() >= 10) {
			putCoins(machine, 0);
		    } else {
			machine.io.write("Put coins!");
			String coinsToPut;
			do {
			    coinsToPut = machine.io.read();
			}while (!machine.isNumeric(coinsToPut));
			
			putCoins(machine, Integer.valueOf(coinsToPut) );
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
	    machine.addCoinsToMachine(coins);
	    return true;
	}

	@Override
	public boolean selectGame(final ArcadeGamesMachine machine, final Games gameType) {

	    if (gameType == null) {
		machine.io.write("Selct game!");
		machine.select();
	    }

	    if (machine.getCoins() < gameType.getPrice()) {
		machine.io.write("Put coins!");

		String coinsToPut;
		do {
		    coinsToPut = machine.io.read();
		} while (!machine.isNumeric(coinsToPut));

		putCoins(machine, Integer.valueOf(coinsToPut));
		selectGame(machine, gameType);
	    }

	    machine.takeCustomerCoins(gameType);
	    machine.setState(StateMachine.PLAY_GAME);
	    machine.play();
	    return true;
	}

	@Override
	public boolean returnMoney(final ArcadeGamesMachine machine) {
	    machine.returnCoinsToCustomer();
	    machine.setState(StateMachine.STAND_BY);
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
	public boolean playGame(final ArcadeGamesMachine machine, final Games gameType, final Game game) {
	    if (machine.getCoins() > 0) {
		machine.returnCoinsToCustomer();
	    }
	    gameType.load();
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
    public boolean selectGame(final ArcadeGamesMachine machine, final Games gameType) {
	return false;
    }

    @Override
    public boolean playGame(final ArcadeGamesMachine machine, final Games gameType, final Game game) {
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

    @Override
    public boolean returnMoney(final ArcadeGamesMachine machine) {
	return false;
    }

}
