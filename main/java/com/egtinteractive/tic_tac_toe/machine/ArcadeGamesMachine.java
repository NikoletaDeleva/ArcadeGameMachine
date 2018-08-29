package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.games.GameTypes;
import com.egtinteractive.tic_tac_toe.games.GamesLoader;
import com.egtinteractive.tic_tac_toe.io.IO;
import com.egtinteractive.tic_tac_toe.utils.Utils;

public class ArcadeGamesMachine {
    private StateMachine state;
    private GameTypes gameType;
    private Game game;
    private long totalMoney;
    private long coins;
    private final IO io;
    private final Utils utils;

    public ArcadeGamesMachine(final IO io) {
	this.coins = 0L;
	this.totalMoney = 0L;
	this.state = StateMachine.STAND_BY;
	this.io = io;
	this.gameType = null;
	this.game = null;
	this.utils = new Utils();
    }

    public void waitToTurnOn() {
	String wait = io.read();

	if (wait.trim().toLowerCase().equals("turn on")) {
	    this.turnOnMachine();
	} else {
	    this.waitToTurnOn();
	}
    }

    private void turnOnMachine() {
	this.setState(StateMachine.STAND_BY);
	this.loadHomePage();

	this.io.write("Put coins!");

	String coinsToPut;
	do {
	    coinsToPut = io.read();
	} while (!utils.isNumeric(coinsToPut));

	this.putCoins(Integer.valueOf(coinsToPut));
    }

    public void turnOn() {
	this.turnOnMachine();
    }

    public void loadHomePage() {
	final StringBuilder sb = new StringBuilder();
	sb.append(System.lineSeparator() + "CREDIT:" + getCoins() + "-----------------------------------------"
		+ System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator());
	sb.append(String.format("%s %30s" + System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator(), "GAME:", "PRICE:"));

	for (GameTypes game : GameTypes.values()) {

	    final String name = game.getName();
	    final long price = game.getPrice();

	    sb.append(String.format("%s %33s" + System.lineSeparator(), name, price));
	}
	sb.append(
		"-------------------------------------------------" + System.lineSeparator() + System.lineSeparator());
	io.write(sb.toString());
    }

    long getCoins() {
	return this.coins;
    }

    public long putCoins(final long coins) {
	this.state.putCoins(this, coins);
	this.loadHomePage();
	return this.coins;
    }

    void addCoinsToMachine(final long coins) {
	this.coins += coins;
    }

    void takeCustomerCoins(final GameTypes specificGame) {
	this.coins -= specificGame.getPrice();
	this.setTotalMoney(specificGame.getPrice());
    }

    long returnCoinsToCustomer() {
	final long moneyToReturn = this.coins;
	this.coins = 0L;
	return moneyToReturn;
    }

    private GamesLoader selectGame(final String name) {
	this.gameType = getGameByName(name);

	boolean checkIF = this.state.selectGame(this, gameType);

	if (checkIF) {
	    this.game = gameType.getGame(this);
	    return gameType;
	}
	return null;
    }

    public void select() {
	String name = io.read();
	this.selectGame(name);
    }

    private GameTypes getGameByName(final String name) {
	for (GameTypes game : GameTypes.values()) {
	    if (game.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
		return game;
	    }
	}
	return null;
    }

    public void play() {
	this.playGame();
    }

    private boolean playGame() {
	return state.playGame(this, gameType, game);
    }

    public long service() {
	long moneyReturn = this.returnCoinsToCustomer();
	this.setState(StateMachine.SERVICE);
	return moneyReturn;
    }

    public long endService() {
	long takeTotalMoney = getTotalMoney();
	this.totalMoney = 0L;
	this.state.endService(this);
	return takeTotalMoney;
    }

    void setState(final StateMachine state) {
	this.state = state;
    }

    public void setTotalMoney(long totalGameMoney) {
	this.totalMoney += totalGameMoney;
    }

    private long getTotalMoney() {
	return totalMoney;
    }

    public IO getIo() {
	return io;
    }

    public Utils getUtils() {
	return utils;
    }

}
