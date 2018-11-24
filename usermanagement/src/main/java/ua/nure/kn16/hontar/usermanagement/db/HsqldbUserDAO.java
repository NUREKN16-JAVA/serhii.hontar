package ua.nure.kn16.hontar.usermanagement.db;

import java.sql.*;
import java.util.Collection;


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
public ConnectionFactory getConnectionFactory() {
    return connectionFactory;
}
	@Override
	public User create(User user) throws DatabaseException {
        
        try {
        	Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateOfBirthd().getTime()));
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of inserted rows: " + number);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY()");
            User u = new User(user);
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()){
                u.setId(keys.getLong(1));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            return u;
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
	public Collection<User> findAll() {
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
