package ua.nure.kn16.hontar.usermanagement.db;

import java.util.List;
import ua.nure.kn16.hontar.usermanagement.User;

public interface UserDAO {
/**
 * Add user to DB table USER
 * @param user with null id field
 * @return user modified record exemplar with DB auto generated id field
 */
	public User create(final User user) throws DatabaseException;
	/**
	 * 
	 * @param id
	 * @return 
	 */
	public User find(final Long id);
	/**
	 * 
	 * @param
	 * @return 
	 */
	public List<User> findAll();
	/**
	 * 
	 * @param 
	 * @return 
	 */
	public void update(final User user);
	/**
	 * 
	 * @param 
	 * @return 
	 */
	public void delete(final User user);
}
