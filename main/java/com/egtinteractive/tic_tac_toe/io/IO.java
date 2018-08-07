package com.egtinteractive.tic_tac_toe.io;

public interface IO extends AutoCloseable {

    public void write(final String str);

    public String read();
    
    public int readPosition();
    
    public String readString();
}
