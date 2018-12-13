package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {
	
	private MainFrame mainFrame;

	@Before
	public void setUp() throws Exception {
		super.setUp();
	      setHelper(new JFCTestHelper());
	      mainFrame =  new MainFrame();
	      mainFrame.setVisible(true);
	}

	@After
	public void tearDown() throws Exception {
	    mainFrame.setVisible(false);
	    getHelper().cleanUp(this);
	    super.tearDown();
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
	    find(JPanel.class,"browserPanel");
	   // JTable table = (JTable) find(JTable.class,"userTable");
	   //assertEquals(3, table.getColumnCount());
	    //assertEquals("id", table.getColumnName(0));//International
	   // assertEquals("First Name", table.getColumnName(1));
	    //assertEquals("Last Name", table.getColumnName(2));
	  find(JButton.class,"addButton");
	  find(JButton.class,"editButton");
	  find(JButton.class,"deleteButton");
	  find(JButton.class,"detailsButton");
	  find(JTable.class,"userTable");
	  }
	  @Test
	  public void testAddUser() {
		  JButton addButton = (JButton)find(JButton.class, "addButton");
		  getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
		  
		  find(JPanel.class, "addPanel");
		  
		  find(JTextField.class, "firstNameField");
		  find(JTextField.class, "lastNameField");
		  find(JTextField.class, "dateOfBirthField");
		  find(JButton.class,"okButton");
		  find(JButton.class,"cancelButton");
	  }

}
