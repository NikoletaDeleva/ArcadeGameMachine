package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public interface GamesLoader {

    public void load(ArcadeGamesMachine arcadeGamesMachine);

    public Game getGame(ArcadeGamesMachine arcadeGamesMachine);

    public long getPrice();

    public String getName();
}