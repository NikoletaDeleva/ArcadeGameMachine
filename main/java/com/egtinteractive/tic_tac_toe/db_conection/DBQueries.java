package com.egtinteractive.tic_tac_toe.db_conection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBQueries {
    private final DBCredentials credentials;

    public DBQueries() {
	this.credentials = new DBCredentials();
    }

    public String getPlayer(final String name) {
	final String selectStatement = "SELECT * FROM Players WHERE name = ?;";

	try (final Connection connection = DBConnection.getConnection(credentials.getURL(), credentials.getUSER(),
		credentials.getPASSWORD());
		final PreparedStatement statement = connection.prepareStatement(selectStatement)) {

	    statement.setString(1, name);

	    try (final ResultSet resulSet = statement.executeQuery()) {
		if (resulSet.next()) {
		    return resulSet.getString("id");
		}
	    }
	    return null;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }

    public boolean doesPlayerExists(final String name) {
	return this.getPlayer(name) != null;
    }

    public void addPlayer(final String name) {
	final String insertStatement = "INSERT INTO Player(name) VALUES (?)";
	try (final Connection connection = DBConnection.getConnection(credentials.getURL(), credentials.getUSER(),
		credentials.getPASSWORD());
		final PreparedStatement statement = connection.prepareStatement(insertStatement)) {
	    
	    statement.setString(1, name);
	    statement.executeQuery();
	    
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public void addGameWithNoPlayer(final String result) {
   	final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
   	final String insertStatement = "INSERT INTO games(date_time, result) VALUES (?,?)";

   	try (final Connection connection = DBConnection.getConnection(credentials.getURL(),
   		credentials.getUSER(), credentials.getPASSWORD());
   		final PreparedStatement stmt = connection.prepareStatement(insertStatement);) {
   	    
   	    stmt.setString(1, timeStamp);
   	    stmt.setString(2, result);
   	    stmt.executeUpdate();
   	
   	} catch (SQLException e) {
   	    throw new RuntimeException(e);
   	}

       }

    public void addGameWithPlayer(final String name, final String result) {
	
    }
}
