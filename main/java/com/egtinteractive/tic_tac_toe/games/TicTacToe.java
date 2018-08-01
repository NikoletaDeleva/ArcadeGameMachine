package com.egtinteractive.tic_tac_toe.games;

import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tic_tac_toe.ai.AI;
import com.egtinteractive.tic_tac_toe.ai.AITicTacToe;
import com.egtinteractive.tic_tac_toe.boards.Board;
import com.egtinteractive.tic_tac_toe.boards.DrawBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeDrawBoard;
import com.egtinteractive.tic_tac_toe.io.ConsoleIO;
import com.egtinteractive.tic_tac_toe.io.IO;

public class TicTacToe {

    private final IO io;
    private final Board board;
    private final GameStates gameState;
    private final DrawBoard drawBoard;
    private final AI ai;

    public TicTacToe() {
	this.io = new ConsoleIO();
	this.gameState = GameStates.START_GAME;
	this.board = new TicTacToeBoard();
	this.drawBoard = new TicTacToeDrawBoard(io);
	this.ai = new AITicTacToe();
    }

    public void start() {
	if (ThreadLocalRandom.current().nextInt(0, 100) < 50) {
	    
	}
    }

    public TicTacToeBoard getBoard() {
	return (TicTacToeBoard) this.board;
    }

    public GameStates getGameStates() {
	return gameState;
    }

}
