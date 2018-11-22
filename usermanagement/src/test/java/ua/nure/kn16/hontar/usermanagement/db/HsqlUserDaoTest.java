package ua.nure.kn16.hontar.usermanagement.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.kn16.hontar.usermanagement.User;

public class HsqlUserDaoTest extends DatabaseTestCase{
  private User user;
  private UserDAO dao;
  private ConnectionFactory connectionFactory;

  @Before
  public void setUp() throws Exception {
    user = new User(1L,"Ivan", "Ivanov", new Date());
  }

  @After
  public void tearDown() throws Exception {
  }

  
  @Test
  public void testCreate() throws  DatabaseException{
    assertNull(user.getId());
    User userResult = dao.create(user);
    assertNotNull(userResult);
    assertNotNull(user.getId());
    assertEquals(user.getFirstName(),userResult.getFirstName());
    assertEquals(user.getLastName(),userResult.getLastName());
    assertEquals(user.getDateOfBirthd(),userResult.getDateOfBirthd());
  }
  @Override
  protected IDatabaseConnection getConnection() throws Exception {
	    connectionFactory = new ConnectionFactoryImpl();
	    return new DatabaseConnection(connectionFactory.createConnection());
	  }
  
	@Override
	  protected IDataSet getDataSet() throws Exception {
	    // TODO Auto-generated method stub
	    return null;
	  }

}
