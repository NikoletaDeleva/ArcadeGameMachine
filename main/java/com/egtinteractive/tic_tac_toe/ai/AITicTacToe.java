package com.egtinteractive.tic_tac_toe.ai;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tic_tac_toe.boards.Board;

public class AITicTacToe implements AI {

    @Override
    public Integer move(final Board board) {
	final List<Integer> freeFields = board.getFreefields();
	final int size = freeFields.size();
	
	if(size == 0) {
	    return null;
	}
	
	return freeFields.get(ThreadLocalRandom.current().nextInt(0,size));
    }

}
