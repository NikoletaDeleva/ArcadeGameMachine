package com.egtinteractive.tic_tac_toe.machine;

import java.text.NumberFormat;
import java.text.ParsePosition;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.games.Games;
import com.egtinteractive.tic_tac_toe.io.ConsoleIO;
import com.egtinteractive.tic_tac_toe.io.IO;
import com.egtinteractive.tic_tac_toe.player.Player;

public class ArcadeGamesMachine {
    private static ArcadeGamesMachine arcadeGamesMachineInstance = null;
    private StateMachine state;
    private Games gameType;
    private Game game;
    private Player player;
    private long totalMoney;
    private long coins;
    protected IO io;

    private ArcadeGamesMachine() {
	this.coins = 0L;
	this.totalMoney = 0L;
	this.state = StateMachine.STAND_BY;
	this.io = new ConsoleIO();
	this.gameType = null;
	this.game = null;
    }

    public static ArcadeGamesMachine getInstance() {
	if (arcadeGamesMachineInstance == null) {
	    arcadeGamesMachineInstance = new ArcadeGamesMachine();
	}
	return arcadeGamesMachineInstance;
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
	this.loadHomePage();

	this.io.write("Put coins!");

	String coinsToPut;
	do {
	    coinsToPut = io.read();
	} while (!isNumeric(coinsToPut));
	
	this.putCoins(Integer.valueOf(coinsToPut));
    }

    public void loadHomePage() {
	final StringBuilder sb = new StringBuilder();
	sb.append(System.lineSeparator() + "CREDIT:" + getCoins() + "-----------------------------------------"
		+ System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator());
	sb.append(String.format("%s %30s" + System.lineSeparator() + "-------------------------------------------------"
		+ System.lineSeparator(), "GAME:", "PRICE:"));

	for (Games game : Games.values()) {

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

    void takeCustomerCoins(final Games specificGame) {
	this.coins -= specificGame.getPrice();
	this.setTotalMoney(specificGame.getPrice());
    }

    long returnCoinsToCustomer() {
	final long moneyToReturn = this.coins;
	this.coins = 0L;
	return moneyToReturn;
    }

    public long getGamePrice() {
	return gameType.getPrice();
    }

    private boolean playGame() {
	return state.playGame(this, gameType, game);
    }

    private Games selectGame(final String name) {
	this.gameType = getGameByName(name);

	boolean checkIF = this.state.selectGame(this, gameType);

	if (checkIF) {
	    this.game = gameType.getGame();
	    return gameType;
	}
	return null;
    }

    private Games getGameByName(String name) {
	for (Games game : Games.values()) {
	    if (game.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
		return game;
	    }
	}
	return null;
    }

    public void select() {
	String name = io.read();
	this.selectGame(name);
    }

    public void play() {
	this.playGame();
    }

    public long service() {
	long moneyReturn = this.returnCoinsToCustomer();
	this.setState(StateMachine.SERVICE);
	return moneyReturn;
    }

    public boolean endService() {
	return this.state.endService(this);
    }

    public Games getGame() {
	return gameType;
    }

    public void setGame(Games game) {
	this.gameType = game;
    }

    public void setState(final StateMachine state) {
	this.state = state;
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

    public long getTotalMoney() {
	return totalMoney;
    }

    public void setTotalMoney(long totalGameMoney) {
	this.totalMoney += totalGameMoney;
    }

    public static boolean isNumeric(final String str) {
	if (str.equals("")) {
	    return false;
	}
	final NumberFormat formatter = NumberFormat.getInstance();
	final ParsePosition pos = new ParsePosition(0);
	formatter.parse(str, pos);
	return str.length() == pos.getIndex();
    }
}
