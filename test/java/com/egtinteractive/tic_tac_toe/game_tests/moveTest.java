package com.egtinteractive.tic_tac_toe.game_tests;

import static org.testng.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.tic_tac_toe.games.TicTacToe;

public class moveTest {
    @DataProvider(name = "game")
    public Object[][] getData() {
	return MyDataProviderTicTacToeGame.getNewTicTacToeGame();
    }

    @Test(dataProvider = "game")
    public void checkIfPutSign(final TicTacToe game) {
	for (int i = 0; i < 9; i++) {
	    game.getPlayer().setSign("X");
	    game.movePlayer(i);
	    final String a = game.getBoard().getField(i);
	    assertEquals(a, "X");
	}
    }

    @Test(dataProvider = "game")
    public void isWinnerRow(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	for (int i = 0; i <= 3; i++) {
	    game.movePlayer(i);
	}
	for (int i = 3; i <= 8; i += 5) {
	    game.moveAI(i);
	}

	assertTrue(game.isWinner());
    }

    @Test(dataProvider = "game")
    public void isWinnerCol(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	for (int i = 0; i <= 8; i += 3) {
	    game.movePlayer(i);
	}
	for (int i = 1; i <= 8; i += 5) {
	    game.moveAI(i);
	}

	assertTrue(game.isWinner());

    }

    @Test(dataProvider = "game")
    public void isWinnerDiagonal(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	for (int i = 0; i <= 8; i += 4) {
	    game.movePlayer(i);
	}
	for (int i = 1; i <= 8; i += 5) {
	    game.moveAI(i);
	}

	assertTrue(game.isWinner());
    }

    @Test(dataProvider = "game")
    public void isNotWinner(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	for (int i = 0; i <= 1; i++) {
	    game.movePlayer(i);
	}
	for (int i = 2; i <= 4; i++) {
	    game.moveAI(i);
	}
	for (int i = 5; i <= 7; i++) {
	    game.movePlayer(i);
	}

	game.moveAI(8);

	assertFalse(game.isWinner());
    }

}
