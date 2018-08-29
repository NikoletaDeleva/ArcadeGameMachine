package com.egtinteractive.tic_tac_toe.io;

import java.util.List;

import com.egtinteractive.tic_tac_toe.player.Player;

public class AnotherDumpIO implements IO{
    int position = -1;
    
    @Override
    public String read() {
	if(position++ > 9) {
	    position = 0;
	}
	return "" + position;
    }

    @Override
    public void close() throws Exception {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void write(String str) {
	System.out.println(str);
    }

    @Override
    public void listAll(List<Player> listPlayers) {
	// TODO Auto-generated method stub
	
    }


}
