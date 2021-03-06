package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DaoFactory;
import ua.nure.kn16.hontar.usermanagement.db.DaoFactoryImpl;
import ua.nure.kn16.hontar.usermanagement.db.MockDaoFactory;
import ua.nure.kn16.hontar.usermanagement.db.MockUserDao;

public class MainFrameTest extends JFCTestCase {
	
	private MainFrame mainFrame;

	private Mock mockUserDao;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		try {
		Properties properties = new Properties();

		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		
		DaoFactory.init(properties);
		mockUserDao = ((MockDaoFactory) DaoFactory.getInstance())
				.getMockUserDao();
		mockUserDao.expectAndReturn("findAll",new ArrayList<>());
	      setHelper(new JFCTestHelper());
	      mainFrame =  new MainFrame();
	      
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	@After
	public void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	 private Component find(Class componentClass, String name) {
		  NamedComponentFinder finder =new NamedComponentFinder(componentClass, name);
		 finder.setWait(0);
		 Component component = finder.find(mainFrame,0);
		 assertNotNull("Could not find component '"+name +"'", component);
		 return component;
	}
	
	  @Test
	  public void testBrowseControls() {
	   
	find(JPanel.class,"browsePanel");
	   JTable table = (JTable) find(JTable.class,"userTable");
	   
	   assertEquals(3, table.getColumnCount());
	   assertEquals("ID", table.getColumnName(0));//International
	   assertEquals("Name", table.getColumnName(1));
	   assertEquals("Surname", table.getColumnName(2));
	  
	  find(JButton.class,"addButton");
	  find(JButton.class,"editButton");
	  find(JButton.class,"deleteButton");
	  find(JButton.class,"detailsButton");
	  find(JTable.class,"userTable");
	  }
	  @Test
	  public void testAddUser() {
		  try {
			Date now = new Date();
			String firstName = "Ivan";
			String lastName = "Ivanov";
			
			User user = new User(firstName,lastName,now);
			
			User expectedUser = new User (new Long(1),firstName,lastName,now);
			mockUserDao.expectAndReturn("create", user,expectedUser);
			
			ArrayList<User> users = new ArrayList<>();
	        users.add(expectedUser);
	        mockUserDao.expectAndReturn("findAll", users);
	        
			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(0, table.getRowCount());
			
			JButton addButton = (JButton) find(JButton.class, "addButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
			
			find(JPanel.class, "addPanel");
			
			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
			
			JButton okButton = (JButton) find(JButton.class, "okButton");
			
			find(JButton.class, "cancelButton");
			getHelper().sendString(new StringEventData(this, firstNameField, firstName));
			getHelper().sendString(new StringEventData(this, lastNameField, lastName));
			
			DateFormat formater = DateFormat.getDateInstance();
			String date = formater.format(now);
			
			getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
			getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
			
			find(JPanel.class, "browsePanel");
			
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
		} catch (Exception e) {
			fail(e.toString());
			
		}
	  }

}
