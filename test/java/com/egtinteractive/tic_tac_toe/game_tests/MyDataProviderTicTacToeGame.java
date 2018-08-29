package com.egtinteractive.tic_tac_toe.game_tests;

import com.egtinteractive.tic_tac_toe.ai.AITicTacToe;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeBoard;
import com.egtinteractive.tic_tac_toe.boards.TicTacToeDrawBoard;
import com.egtinteractive.tic_tac_toe.games.TicTacToe;
import com.egtinteractive.tic_tac_toe.io.AnotherDumpIO;
import com.egtinteractive.tic_tac_toe.io.IO;
import com.egtinteractive.tic_tac_toe.player.Player;

public class MyDataProviderTicTacToeGame {
    public static Object[][] getNewTicTacToeGame() {

	final IO io = new AnotherDumpIO();

	return new Object[][] { { new TicTacToe(new TicTacToeBoard(), new TicTacToeDrawBoard(io), new AITicTacToe(),
		new Player(null, 0), io , null) } };
    }
}
