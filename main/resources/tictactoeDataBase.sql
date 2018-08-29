DROP database if EXISTS tictactoe;

CREATE database tictactoe;
USE tictactoe;

CREATE TABLE Player(id int NOT NULL AUTO_INCREMENT, name varchar(10), PRIMARY KEY(id));

CREATE TABLE Games(game_id int NOT NULL AUTO_INCREMENT, player_id int, date_time datetime, result varchar(10), PRIMARY KEY(game_id), FOREIGN KEY(player_id) REFERENCES Player(id));