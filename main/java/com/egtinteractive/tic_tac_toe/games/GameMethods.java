package com.egtinteractive.tic_tac_toe.games;

public interface GameMethods {
    public boolean start(Game game);

    public boolean moveAI(Game game);

    public boolean movePlayer(Game game);

    public boolean endGame(Game game);
}
