package ua.nure.kn16.hontar.usermanagement.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	public DatabaseException(SQLException e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2048557009270353238L;

}
