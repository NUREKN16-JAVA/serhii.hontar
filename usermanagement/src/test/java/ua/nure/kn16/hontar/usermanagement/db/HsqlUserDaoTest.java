package ua.nure.kn16.hontar.usermanagement.db;



import java.util.Collection;
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
		//connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver","jdbc:hsqldb:file:db/usermanagement","sa", "");
		getConnection();
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
	  @Test
	   public void testFindAll() throws DatabaseException {
	    	
	        	final int  count =1;
	        	User userResult = dao.create(user);
	        	assertNotNull(userResult.getId());// check id!=null
				Collection collection = dao.findAll();
				assertNotNull("Collection is null", collection);
				assertEquals("Collection size.", count, collection.size());

	    }
	  
	    @Test
	    public void testFind() throws DatabaseException {
	    	User userResult = dao.create(user);
        	assertNotNull(userResult.getId());// check id!=null
	        User testUser = dao.find(1L);
	        assertEquals(userResult.getId(),testUser.getId());
	    }
	    
	    @Test
	    public void testUpdate() throws DatabaseException  {
	        User testUser = new User(1L, "Petya", "Pupkin", new Date());        
	        dao.update(testUser);
	    	User createUser = dao.create(user);
        	assertNotNull(createUser.getId());// check id!=null
	        User testUser2 = dao.find(1L);
	        assertNotNull(testUser2.getId());
	        assertEquals(testUser.getFirstName(), testUser2.getFirstName());
	        assertEquals(testUser.getLastName(), testUser2.getLastName());
	        
	        
	        User testUser1 = new User(2L, "Peta", "Pupin", new Date());
	        dao.update(testUser1);
	    	User createUser2 = dao.create(user);
        	assertNotNull(createUser2.getId());// check id!=null
	        User testUser21 = dao.find(2L);
	        assertNotNull(testUser21.getId());
	        assertEquals(testUser1.getFirstName(), testUser21.getFirstName());
	        assertEquals(testUser1.getLastName(), testUser21.getLastName());
	    }
	    @Test
	    public void testDelete() throws DatabaseException {
	    	
	    	final int  count1 =4;
	    	final int  count2 =5;
        	User createUser1 = dao.create(user);
        	User createUser2 = dao.create(user);
        	assertNotNull(createUser1.getId());// check id!=null
        	assertNotNull(createUser2.getId());// check id!=null
			Collection collection1 = dao.findAll();
			assertNotNull("Collection is null", collection1);
			assertEquals("Collection size.", count2, collection1.size());
			dao.delete(createUser2);
			Collection collection2 = dao.findAll();
			assertNotNull("Collection is null", collection2);
			assertEquals("Collection size.", count1, collection2.size());
			

	    }

}
