package ua.nure.kn16.hontar.usermanagement.db;

import java.nio.channels.ConnectionPendingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.kn16.hontar.usermanagement.User;

public class HsqldbUserDAO implements UserDAO {
private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";
private ConnectionFactory connectionFactory;

public HsqldbUserDAO()
{
}

public HsqldbUserDAO (ConnectionFactory connectionFactory)
{
  this.connectionFactory = connectionFactory;
  
}
	@Override
	public User create(User user) throws DatabaseException {	
		try {
			Connection connection =connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			int number = statement.executeUpdate();
			if (number != 1){
				throw new DatabaseException("Number of the inserted rows is " + number);
			}
			CallableStatement callebleStatement = 
					connection.prepareCall("call IDENTITY()");
			ResultSet keys = callebleStatement.executeQuery();
					if (keys.next())
					{
					user.setId(new Long(keys.getLong(1)));	
						
					}
					keys.close();
					callebleStatement.close();
					statement.close();
					connection.close();
					return user;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

}
