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

    public void movePlayer(int position) {
	gameState.move(this);
	board.addMove(position, player.getSign());
    }

    public void moveAI() {

	int position = ai.move(board);
	board.addMove(position, ai.getSign());
    }

}
