package com.egtinteractive.tic_tac_toe.boards;

import com.egtinteractive.tic_tac_toe.io.IO;

public class TicTacToeDrawBoard implements DrawBoard {

    private final IO io;

    public TicTacToeDrawBoard(final IO io) {
	this.io = io;
    }

    @Override
    public void drawBoard(Board board) {
	final String[][] fields = board.getAllFields();
	final StringBuilder sb = new StringBuilder();
	final int size = fields.length;

	for (int index = 0; index < size; index++) {
	    sb.append("  ").append(fields[index][0]).append(" | ").append(fields[index][1]).append(" |  ")
		    .append(fields[index][2]).append(System.lineSeparator());
	    if (index != size - 1)
		sb.append("-------------").append(System.lineSeparator());
	}

	io.write(sb.toString());
    }

}
