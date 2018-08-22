package com.egtinteractive.tic_tac_toe.io;

import java.util.List;

import com.egtinteractive.tic_tac_toe.player.Player;

public interface IO extends AutoCloseable {

    public void write(final String str);

    public String read();

    public void listAll(List<Player> listPlayers);
}
