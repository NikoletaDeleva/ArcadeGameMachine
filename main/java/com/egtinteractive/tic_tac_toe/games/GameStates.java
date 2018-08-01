package com.egtinteractive.tic_tac_toe.games;

import java.util.concurrent.ThreadLocalRandom;

public enum GameStates implements GameMethods {
    START_GAME{
	@Override
	public boolean start(Game game) {
	    
	    game.drawBoard.drawBoard(game.board);
	    
	    if(ThreadLocalRandom.current().nextInt(0,100) < 50) {
		game.setGameState(GameStates.AI);
		game.player.setSign("X");
		game.ai.setSing("O");
	    }else {
		game.setGameState(GameStates.PLAYER);
		game.player.setSign("O");
		game.ai.setSing("X");
	    }
	    return true;
	}
    },
    AI{
	@Override
	public boolean move(Game game) {
	    if(game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		return true;
	    }
	    
	    game.setGameState(GameStates.PLAYER);
	    return true;
	}
    },
    PLAYER{
	
	@Override
	public boolean move(Game game) {
	    
	    if(game.getBoard().isFull()) {
		game.setGameState(GameStates.END_GAME);
		return true;
	    }
	    
	    game.setGameState(GameStates.AI);
	    return true;
	}
    },
    END_GAME{
	
    };
    
    @Override
    public boolean start(Game game) {
	return false;
    }
    
    @Override
    public boolean move(Game game) {
	return false;
    }
    
}
