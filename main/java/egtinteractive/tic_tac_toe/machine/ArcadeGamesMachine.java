package egtinteractive.tic_tac_toe.machine;

import egtinteractive.tic_tac_toe.games.Game;
import egtinteractive.tic_tac_toe.player.Player;

public class ArcadeGamesMachine {
    private static ArcadeGamesMachine arcadeGamesMachineInstance = null;
    private StateMachine state;
    private Game game;
    private Player player;
    private long totalMoney;
    private long coins;

    private ArcadeGamesMachine() {

    }

    public static ArcadeGamesMachine getInstance() {
	if (arcadeGamesMachineInstance == null) {
	    arcadeGamesMachineInstance = new ArcadeGamesMachine();
	}
	return arcadeGamesMachineInstance;
    }

    void setState(final StateMachine state) {
	this.state = state;
    }

    long getCoins() {
	return this.coins;
    }

    void addCoinsToMachine(final long coins) {
	this.coins += coins;
    }

    void takeCustomerCoins(final Game specificGame) {
	// changes
	this.coins -= specificGame.getPrice();
	this.setTotalMoney(this.getTotalMoney() + specificGame.getPrice());
    }

    long returnCoinsToCustomer() {
	final long moneyToReturn = this.coins;
	this.coins = 0L;
	return moneyToReturn;
    }

    public long putCoins(final long coins) {
	this.state.putCoins(this, coins);
	return this.coins;
    }

    public long getTotalMoney() {
	return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
	this.totalMoney = totalMoney;
    }

    public long service() {
	long moneyReturn = this.returnCoinsToCustomer();
	this.setState(StateMachine.SERVICE);
	return moneyReturn;
    }

    public boolean endService() {
	return this.state.endService(this);
    }

    public StateMachine getState() {
	return this.state;
    }
}
