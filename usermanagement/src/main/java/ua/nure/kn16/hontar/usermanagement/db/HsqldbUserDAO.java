package ua.nure.kn16.hontar.usermanagement.db;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn16.hontar.usermanagement.User;

class HsqldbUserDAO implements UserDAO {
private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";
private static final String FIND_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
private static final String FIND_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
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

public void setConnectionFactory(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
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
            connection.close();
            return u;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

	@Override
	public User find(Long id) throws DatabaseException{
        try {
            User user=new User();
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Date birthDate = resultSet.getDate(4);
                user = new User(id, firstName, lastName, birthDate);
            } 

            resultSet.close();
            statement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            throw new  DatabaseException(e);
        }
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<>();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirthd(resultSet.getDate(4));
                result.add(user);

            }
            resultSet.close();
            statement.close();
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
        
    }
    
    @Override
    public void update(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_QUERY);
            statement.setLong(4, user.getId());
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateOfBirthd().getTime()));
            
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of updated raws: " + number);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
	public void delete(User user) throws DatabaseException {
		if(user.getId() == null) throw new DatabaseException("error");
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(DELETE_QUERY);
			statement.setLong(1, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}





}
