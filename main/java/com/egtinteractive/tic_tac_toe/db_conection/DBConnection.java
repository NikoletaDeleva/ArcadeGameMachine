package com.egtinteractive.tic_tac_toe.db_conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(final String url, final String user, final String pass) {
	try {
	    return DriverManager.getConnection(url, user, pass);
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

}
