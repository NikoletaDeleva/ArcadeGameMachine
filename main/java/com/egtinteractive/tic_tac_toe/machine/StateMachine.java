package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Games;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public boolean putCoins(final ArcadeGamesMachine machine, long coins) {
	    if (coins <= 0) {
		return false;
	    }
	    machine.addCoinsToMachine(coins);
	    if (machine.getCoins() >= 10) {
		machine.setState(StateMachine.SELECT_GAME);
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
	public boolean selectGame(final ArcadeGamesMachine machine, final Games game) {

	    if (game == null) {
		return false;
	    }

	    if (machine.getCoins() < game.getPrice()) {
		return false;
	    }

	    machine.takeCustomerCoins(game);
	    machine.setState(StateMachine.PLAY_GAME);
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
	public boolean playGame(final ArcadeGamesMachine machine, final Games game) {
	    if (machine.getCoins() > 0) {
		machine.returnCoinsToCustomer();
	    }
	    game.load();
	    machine.setState(StateMachine.STAND_BY);
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
    public boolean selectGame(final ArcadeGamesMachine machine, final Games game) {
	return false;
    }

    @Override
    public boolean playGame(final ArcadeGamesMachine machine, final Games game) {
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
