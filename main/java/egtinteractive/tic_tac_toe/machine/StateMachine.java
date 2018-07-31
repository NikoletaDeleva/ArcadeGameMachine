package egtinteractive.tic_tac_toe.machine;

import egtinteractive.tic_tac_toe.games.Game;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public boolean putCoins(final ArcadeGamesMachine machine, long coins) {
	    if (coins <= 0) {
		return false;
	    }
	    machine.addCoinsToMachine(coins);
	    machine.setState(StateMachine.SELECT_GAME);
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
	public boolean selectGame(final ArcadeGamesMachine machine, String name) {
	    // changes
	    Game specificGame = machine.getInventory().getItemByName(name);

	    if (specificGame == null) {
		return false;
	    }
	    if (machine.getCoins() < specificGame.getPrice()) {
		return false;
	    }
	    if (machine.getInventory().getAmountOfItem(specificGame) > 0) {
		machine.getInventory().getOneSpecificItemFromInventory(specificGame);
		machine.takeCustomerCoins(specificGame);
		machine.setState(StateMachine.PLAY_GAME);
		return true;
	    }
	    return false;
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
	public boolean playGame(final ArcadeGamesMachine machine) {
	    if (machine.getCoins() > 0) {
		machine.returnCoinsToCustomer();
	    }
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

    public boolean putCoins(ArcadeGamesMachine machine, long money) {
	return false;
    }

    public boolean selectGame(ArcadeGamesMachine machine, String name) {
	return false;
    }

    public boolean playGame(ArcadeGamesMachine machine) {
	return false;
    }

    public boolean service(ArcadeGamesMachine machine) {
	return false;
    }

    public boolean endService(ArcadeGamesMachine machine) {
	return false;
    }

    public boolean returnMoney(ArcadeGamesMachine machine) {
	return false;
    }

}
