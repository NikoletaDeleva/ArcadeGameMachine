package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AITicTacToe;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeDrawBoard;
import com.egtinteractive.tic_tac_toe.io.ConsoleIO;
import com.egtinteractive.tic_tac_toe.io.IO;
import com.egtinteractive.tic_tac_toe.player.Player;

public class TicTacToe extends Game {

    private static IO io = new ConsoleIO();

    public TicTacToe() {
	super(new TicTacToeBoard(), GameStates.START_GAME, new TicTacToeDrawBoard(io), new AITicTacToe(), new Player());
    }

    @Override
    public boolean start() {
	return gameState.start(this);
    }

    public GameStates getGameStates() {
	return gameState;
    }

    public void setGameState(GameStates gameState) {
	this.gameState = gameState;
    }

    @Override
    public void movePlayer(int position) {
	board.addMove(position, player.getSign());
    }

    @Override
    public void moveAI(int position) {
	board.addMove(position, ai.getSign());
    }

    public int getPosition() {
	return position;
    }

    public void setPosition(int position) {
	this.position = position;
    }

    public void move() {
	if (gameState == gameState.AI) {
	    this.gameState.moveAI(this);
	} else {
	    this.gameState.movePlayer(this);
	}

    }

    @Override
    public boolean isWinner() {
	return (checkRows() || checkColumns() || checkDiagonals());
    }

    @Override
    public void showResult() {
	this.gameState.endGame(this);
    }

    private boolean checkRows() {
	for (int i = 0; i < 3; i++) {
	    if (checkRowCol(board.getAllFields()[i][0], board.getAllFields()[i][1],
		    board.getAllFields()[i][2]) == true) {
		return true;
	    }
	}
	return false;
    }

    private boolean checkColumns() {
	for (int index = 0; index < 3; index++) {
	    if (checkRowCol(board.getAllFields()[0][index], board.getAllFields()[1][index],
		    board.getAllFields()[2][index]) == true) {
		return true;
	    }
	}
	return false;
    }

    private boolean checkDiagonals() {
	return ((checkRowCol(board.getAllFields()[0][0], board.getAllFields()[1][1],
		board.getAllFields()[2][2]) == true)
		|| (checkRowCol(board.getAllFields()[0][2], board.getAllFields()[1][1],
			board.getAllFields()[2][0]) == true));
    }

    private boolean checkRowCol(String c1, String c2, String c3) {
	return ((c1 != " ") && (c1 == c2) && (c2 == c3));
    }

}
