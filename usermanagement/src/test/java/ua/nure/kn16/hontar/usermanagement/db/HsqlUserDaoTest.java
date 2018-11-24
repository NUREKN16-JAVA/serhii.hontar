package ua.nure.kn16.hontar.usermanagement.db;




import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ua.nure.kn16.hontar.usermanagement.User;

public class HsqlUserDaoTest extends DatabaseTestCase{
 

	  private User user;
	  private UserDAO dao;
	  private ConnectionFactory connectionFactory;
	  
	  
	  @Override
	  protected IDatabaseConnection getConnection() throws Exception {
		    connectionFactory = new ConnectionFactoryImpl();
		    return new DatabaseConnection(connectionFactory.createConnection());
		  }
	  @Override
	  protected IDataSet getDataSet() throws Exception {
	    IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
	    		.getResourceAsStream("userDataSet.xml"));
	    return dataSet;
	  		}
 
	  @Before
	  public void setUp() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver","jdbc:hsqldb:file:db/usermanagement","sa", "");
		//getConnection();
        user = new User("Ivan","Ivanov",new Date());
        dao = new HsqldbUserDAO(connectionFactory);
	  }

	  @After
	  public void tearDown() throws Exception {
	  }

	  
	  @Test
	  public void testCreate() throws  DatabaseException{
	    assertNull(user.getId());  
	    User userResult = dao.create(user);
	    assertNotNull(userResult);
	    assertNotNull(userResult.getId());
	    assertEquals(user.getFirstName(),userResult.getFirstName());
	    assertEquals(user.getLastName(),userResult.getLastName());
	    assertEquals(user.getDateOfBirthd(),userResult.getDateOfBirthd());
	  }

}
