package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AI;
import com.egtinteractive.tic_tac_toe.boards.Board;
import com.egtinteractive.tic_tac_toe.boards.DrawBoard;
import com.egtinteractive.tic_tac_toe.player.Player;

public abstract class Game {

    protected Board board;
    protected GameStates gameState;
    protected DrawBoard drawBoard;
    protected AI ai;
    protected Player player;

    public Game(Board board, GameStates gameStates, DrawBoard drawBoard, AI ai, Player player) {
	this.board = board;
	this.gameState = gameStates;
	this.drawBoard = drawBoard;
	this.ai = ai;
	this.player = player;
    }

    public GameStates getGameState() {
	return gameState;
    }
    
    public boolean start() {
	return false;
    }

    public void setGameState(GameStates gameState) {
	this.gameState = gameState;
    }

    public Board getBoard() {
	return board;
    }

    public void setBoard(Board board) {
	this.board = board;
    }
}