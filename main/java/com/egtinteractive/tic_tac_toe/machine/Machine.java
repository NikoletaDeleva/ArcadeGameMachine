package com.egtinteractive.tic_tac_toe.machine;

import com.egtinteractive.tic_tac_toe.games.Game;
import com.egtinteractive.tic_tac_toe.games.GameTypes;
import com.egtinteractive.tic_tac_toe.games.GamesLoader;

public interface Machine {
    public abstract boolean putCoins(final ArcadeGamesMachine machine, long money);

    public abstract boolean selectGame(final ArcadeGamesMachine machine, final GameTypes gameType);

    public abstract boolean playGame(final ArcadeGamesMachine machine, final GamesLoader gameType, final Game game);

    public abstract boolean service(final ArcadeGamesMachine machine);

    public abstract boolean endService(final ArcadeGamesMachine machine);
}
