package com.egtinteractive.tic_tac_toe.games;

public interface GameMethods {
    public boolean start(final Game game);

    public boolean moveAI(final Game game);

    public boolean movePlayer(final Game game);

    public boolean endGame(final Game game);
}
