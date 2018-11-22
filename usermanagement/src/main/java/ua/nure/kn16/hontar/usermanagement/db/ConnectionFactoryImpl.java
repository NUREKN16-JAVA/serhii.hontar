package ua.nure.kn16.hontar.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import main.java.ua.nure.kn16.hontar.usermanagement.db.ConnectionFactory;

public class ConnectionFactoryImpl implements ConnectionFactory {

	
	private String driver = "org.hsqldb.jdbcDriver";
	private String url = "jdbc:hsqldb:file:db/UserManagment";
	private String user = "sa";
	private String password = "";
	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
			
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw  new DatabaseException(e);
			
		}
		
		}
		
}


