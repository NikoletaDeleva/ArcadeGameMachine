package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.player.Player;

public class ArcadeGamesMachine {
    private static ArcadeGamesMachine arcadeGamesMachineInstance = null;
    private StateMachine state;
    private Game game;
    private Player player;
    private long totalMoney;
    private long coins;

    private ArcadeGamesMachine() {
	this.coins = 0L;
	this.setTotalMoney(0L);
	this.state = StateMachine.STAND_BY;
	this.game = null;
    }

    public static ArcadeGamesMachine getInstance() {
	if (arcadeGamesMachineInstance == null) {
	    arcadeGamesMachineInstance = new ArcadeGamesMachine();
	}
	return arcadeGamesMachineInstance;
    }

    public void loadHomePage() {
	final StringBuilder sb = new StringBuilder();
	sb.append(System.lineSeparator() + "CREDIT:" + getCoins() + "-----------------------------------------"
		+ System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator());
	sb.append(String.format("%s %30s" + System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator(), "GAME:", "PRICE:"));

	for (Game game : Game.values()) {

	    final String name = game.getName();
	    final long price = game.getPrice();

	    sb.append(String.format("%s %33s" + System.lineSeparator(), name, price));
	}
	sb.append(
		"-------------------------------------------------" + System.lineSeparator() + System.lineSeparator());
	System.out.println(sb);
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

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public Game getGame() {
	return game;
    }

    public void setGame(Game game) {
	this.game = game;
    }

    public long getGamePrice() {
	return game.getPrice();
    }

    public boolean playGame() {
	return state.playGame(this,game);
    }

    public Game selectGame(final String name) {
	this.game = getGameByName(name);
	
	return this.state.selectGame(this, game) ? game : null;
    }
    
    Game getGameByName(String name) {
	for(Game game : Game.values()) {
	    if(game.getName().equals(name)) {
		return game;
	    }
	}
	return null;
    }

}
