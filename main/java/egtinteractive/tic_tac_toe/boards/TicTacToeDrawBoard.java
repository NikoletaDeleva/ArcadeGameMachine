package egtinteractive.tic_tac_toe.boards;

public class TicTacToeDrawBoard implements DrawBoard {

    public void drawBoard(Board board) {
	final String[][] fields = board.getAllFields();
	final StringBuilder sb = new StringBuilder();
	final int size = fields.length;
	for (int i = 0; i < size; i++) {
	    sb.append("  ").append(fields[i][0]).append(" | ").append(fields[i][1]).append(" |  ").append(fields[i][2])
		    .append(System.lineSeparator());
	    if (i != size - 1)
		sb.append("-------------").append(System.lineSeparator());
	}
	System.out.println(sb.toString());
    }

}
