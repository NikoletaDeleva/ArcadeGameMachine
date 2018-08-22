package com.egtinteractive.tic_tac_toe.db_conection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.egtinteractive.tic_tac_toe.player.Player;

public class DBQueries {
    private final DBCredentials credentials;

    public DBQueries() {
	this.credentials = new DBCredentials();
    }

    public String getPlayer(final String name) {
	final String selectStatement = "SELECT * FROM Player WHERE name = ?;";

	try (final Connection connection = DBConnection.getConnection(credentials);
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
	final String insertStatement = "INSERT INTO Player(name) VALUES (?);";

	try (final Connection connection = DBConnection.getConnection(credentials);
		final PreparedStatement statement = connection.prepareStatement(insertStatement)) {

	    statement.setString(1, name);
	    statement.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void addGameWithNoPlayer(final String result) {
	final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	final String insertStatement = "INSERT INTO Games(date_time, result) VALUES (?,?);";
	try (final Connection connection = DBConnection.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(insertStatement);) {

	    stmt.setString(1, timeStamp);
	    stmt.setString(2, result);
	    stmt.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }

    public void addGameWithPlayer(final String name, final String result) {
	final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	final String playerId = getPlayer(name);
	final String insertStatement = "INSERT INTO Games(player_id, date_time, result) VALUES (?,?,?);";

	try (final Connection connection = DBConnection.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(insertStatement);) {

	    stmt.setString(1, playerId);
	    stmt.setString(2, timeStamp);
	    stmt.setString(3, result);
	    stmt.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Player> showTopThreePlayers() {
	final List<Player> players = new LinkedList<>();
	final String selectStatement = "SELECT count(*) as all_wins, player.name " + "FROM Player as player " + ""
		+ "JOIN Games as games ON player.id = games.player_id " + "" + "WHERE games.result = 'PLAYER' " + ""
		+ "GROUP BY games.player_id " + "ORDER BY all_wins desc, player.name ASC " + "" + "LIMIT 3;";

	try (final Connection connection = DBConnection.getConnection(credentials);
		final Statement stmt = connection.createStatement();
		final ResultSet resultSet = stmt.executeQuery(selectStatement)) {

	    if (resultSet != null) {
		while (resultSet.next()) {
		    players.add(new Player(resultSet.getString("name"), resultSet.getInt("all_wins")));
		}
	    }

	    return players;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }
}
