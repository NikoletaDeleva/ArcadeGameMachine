package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AI;
import com.egtinteractive.tic_tac_toe.boards.Board;
import com.egtinteractive.tic_tac_toe.boards.DrawBoard;
import com.egtinteractive.tic_tac_toe.db_conection.DBQueries;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;
import com.egtinteractive.tic_tac_toe.player.Player;

public abstract class Game {

    private final ArcadeGamesMachine arcadeGamesMachine;
    private final Board board;
    private final DrawBoard drawBoard;
    private final AI ai;
    private final Player player;
    private GameStates gameState;
    private int position;
    private DBQueries dbQueries;

    public Game(final Board board, final GameStates gameStates, final DrawBoard drawBoard, final AI ai,
	    final Player player, final ArcadeGamesMachine machine) {
	this.board = board;
	this.gameState = gameStates;
	this.drawBoard = drawBoard;
	this.ai = ai;
	this.player = player;
	this.dbQueries = new DBQueries();
	this.arcadeGamesMachine = machine;
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
	if (this.gameState == GameStates.AI) {
	    this.gameState.moveAI(this);
	} else {
	    this.gameState.movePlayer(this);
	}
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

    public ArcadeGamesMachine getArcadeGamesMachine() {
	return this.arcadeGamesMachine;
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
}