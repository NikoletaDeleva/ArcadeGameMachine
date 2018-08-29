package com.egtinteractive.tic_tac_toe.games;

import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public interface GamesLoader {

    void load(ArcadeGamesMachine arcadeGamesMachine);

    Game getGame(ArcadeGamesMachine arcadeGamesMachine);

}