package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.ai.AITicTacToe;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeDrawBoard;
import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;
import com.egtinteractive.tic_tac_toe.player.Player;

public class TicTacToe extends Game {

    public TicTacToe(TicTacToeBoard ticTacToeBoard, TicTacToeDrawBoard ticTacToeDrawBoard, AITicTacToe aiTicTacToe,
	    Player player, ArcadeGamesMachine arcadeGamesMachine) {
	super(ticTacToeBoard, ticTacToeDrawBoard, aiTicTacToe, player, arcadeGamesMachine);
    }

    @Override
    public boolean isWinner() {
	return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
	for (int i = 0; i < 3; i++) {
	    if (checkRowCol(getBoard().getAllFields()[i][0], getBoard().getAllFields()[i][1],
		    getBoard().getAllFields()[i][2]) == true) {
		return true;
	    }
	}
	return false;
    }

    private boolean checkColumns() {
	for (int index = 0; index < 3; index++) {
	    if (checkRowCol(getBoard().getAllFields()[0][index], getBoard().getAllFields()[1][index],
		    getBoard().getAllFields()[2][index]) == true) {
		return true;
	    }
	}
	return false;
    }

    private boolean checkDiagonals() {
	return ((checkRowCol(getBoard().getAllFields()[0][0], getBoard().getAllFields()[1][1],
		getBoard().getAllFields()[2][2]) == true)
		|| (checkRowCol(getBoard().getAllFields()[0][2], getBoard().getAllFields()[1][1],
			getBoard().getAllFields()[2][0]) == true));
    }

    private boolean checkRowCol(String c1, String c2, String c3) {
	return ((c1 != " ") && (c1 == c2) && (c2 == c3));
    }

}
