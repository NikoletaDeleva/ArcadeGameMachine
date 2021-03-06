package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AI;
import com.egtinteractive.tic_tac_toe.boards.Board;
import com.egtinteractive.tic_tac_toe.boards.DrawBoard;
import com.egtinteractive.tic_tac_toe.db_conection.DBQueries;
import com.egtinteractive.tic_tac_toe.io.IO;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;
import com.egtinteractive.tic_tac_toe.player.Player;
import com.egtinteractive.tic_tac_toe.utils.Utils;

public class Game {
    private final static String firstSign = "O";
    private final static String secondSign = "X";
    private final IO io;
    private final Board board;
    private final DrawBoard drawBoard;
    private final AI ai;
    private final Player player;
    private GameStates gameState;
    private int position;
    private DBQueries dbQueries;
    private Utils utils;
    private ArcadeGamesMachine machine;
    private String current;

    public Game(final Board board, final DrawBoard drawBoard, final AI ai, final Player player, final IO io,
	    ArcadeGamesMachine machine) {
	this.board = board;
	this.gameState = GameStates.START_GAME;
	this.drawBoard = drawBoard;
	this.ai = ai;
	this.player = player;
	this.dbQueries = new DBQueries();
	this.io = io;
	this.utils = new Utils();
	this.machine = machine;
    }

    public boolean start() {
	return gameState.start(this);
    }

    public void movePlayer(final int position) {
	this.board.addMove(position, player.getSign());
    }

    public void moveAI(final int position) {
	this.board.addMove(position, ai.getSign());
    }

    public void move() {
	this.gameState.move(this);
    }

    public void end() {
	this.gameState.endGame(this);
    }

    public boolean isWinner() {
	return false;
    }

    public void setPosition(final int position) {
	this.position = position;
    }

    public int getPosition() {
	return this.position;
    }

    public void setGameState(GameStates gameState) {
	this.gameState = gameState;
    }

    public GameStates getGameState() {
	return this.gameState;
    }

    public IO getIO() {
	return this.io;
    }

    public DrawBoard getDrawBoard() {
	return this.drawBoard;
    }

    public AI getAi() {
	return this.ai;
    }

    public Player getPlayer() {
	return this.player;
    }

    public DBQueries getDbQueries() {
	return this.dbQueries;
    }

    public Board getBoard() {
	return this.board;
    }

    public Utils getUtils() {
	return utils;
    }

    public ArcadeGamesMachine getMachine() {
	return machine;
    }

    public String getCurrent() {
	return current;
    }

    public void setCurrent(String current) {
	this.current = current;
    }

    public String getFirstsign() {
	return firstSign;
    }

    public String getSecondsign() {
	return secondSign;
    }

}