package com.egtinteractive.tic_tac_toe.io;

import java.util.List;

import com.egtinteractive.tic_tac_toe.player.Player;

public class DumpIO implements IO {

    @Override
    public void close() throws Exception {

    }

    @Override
    public void write(String str) {

    }

    @Override
    public String read() {
	return "Dummy";
    }

    @Override
    public void listAll(List<Player> listPlayers) {
    }

}
