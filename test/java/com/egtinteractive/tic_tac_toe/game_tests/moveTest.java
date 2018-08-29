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
    public void testOne(final TicTacToe game) {
	for (int i = 0; i < 9; i++) {
	    game.getPlayer().setSign("X");
	    game.movePlayer(i);
	    final String a = game.getBoard().getField(i);
	    assertEquals(a, "X");
	}
    }

    @Test(dataProvider = "game")
    public void playMove(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	game.movePlayer(0);
	game.moveAI(3);
	game.movePlayer(1);
	game.moveAI(7);
	game.movePlayer(2);

	assertTrue(game.isWinner());

    }

    @Test(dataProvider = "game")
    public void playMoveTwo(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	game.movePlayer(0);
	game.moveAI(3);
	game.movePlayer(4);
	game.moveAI(7);
	game.movePlayer(8);

	assertTrue(game.isWinner());

    }

    @Test(dataProvider = "game")
    public void playMoveThree(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	game.movePlayer(0);
	game.moveAI(2);
	game.movePlayer(3);
	game.moveAI(7);
	game.movePlayer(6);

	assertTrue(game.isWinner());

    }

    @Test(dataProvider = "game")
    public void playMoveFour(final TicTacToe game) {
	game.getPlayer().setSign("O");
	game.getAi().setSing("X");

	game.movePlayer(0);
	game.moveAI(2);
	game.movePlayer(1);
	game.moveAI(3);
	game.movePlayer(5);
	game.moveAI(4);
	game.movePlayer(6);
	game.moveAI(8);
	game.movePlayer(7);

	assertFalse(game.isWinner());
    }
}
