package com.egtinteractive.tic_tac_toe.boards;

import java.util.List;

public interface Board {

    public String getField(final int position);

    public String[][] getAllFields();

    public boolean addMove(final int position, final String sign);

    public List<Integer> getFreefields();

    public boolean isFull();

    public boolean isFieldFree(final int position);

    public boolean isMoveValid(final int position);

}
