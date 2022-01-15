package com.heroku.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		while (rs.next()) {
			System.out.println("Read from DB: " + rs.getTimestamp("tick"));
		}
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
//		String dbUrl = "jdbc:postgres://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		
		String dbUrl ="jdbc:postgres://elrwexgnaixdbs:a12f909465266552bb23e17d6087dc88e3e3286d2f501402a5e580dfd59da263@ec2-3-232-22-121.compute-1.amazonaws.com:5432/da5nf2bf0co74q";

		return DriverManager.getConnection(dbUrl);
	}

}