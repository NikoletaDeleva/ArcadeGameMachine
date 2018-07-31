package egtinteractive.tic_tac_toe.boards;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard implements Board {
    private static final int SIZE = 3;
    private static final int SQUARE_SIZE = 9;
    private final String[][] fields;
    private final List<Integer> freeFields;

    public TicTacToeBoard() {
	this.fields = new String[SIZE][SIZE];
	this.freeFields = new ArrayList<Integer>();
	this.load();
    }

    private void load() {
	for (int index = 0; index < SQUARE_SIZE; index++) {
	    this.freeFields.add(index);
	}
	for (int index = 0; index < fields.length; index++) {
	    for (int pos = 0; pos < fields.length; pos++) {
		fields[index][pos] = " ";
	    }
	}
    }

    public String getField(final int position) {
	return this.fields[position / SIZE][position % SIZE];
    }

    public String[][] getAllFields() {
	return this.fields;
    }

    public boolean addMove(final int position,final String sign) {
	final int row = position / SIZE;
	final int column = position % SIZE;

	this.fields[row][column] = sign;
	this.freeFields.remove(Integer.valueOf(position));
	return true;
    }

    public List<Integer> getFreefields() {
	return this.freeFields;
    }

    public boolean isFull() {
	return this.freeFields.size() == 0;
    }

    public boolean isMoveValid(final int position) {
	return position >= 0 && position <= SQUARE_SIZE;
    }

    public boolean isFieldFree(int position) {
	return this.getField(position).equals(" ");
    }

}
