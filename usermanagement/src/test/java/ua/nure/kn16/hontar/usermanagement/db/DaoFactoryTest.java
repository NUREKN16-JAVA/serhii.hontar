package ua.nure.kn16.hontar.usermanagement.db;

import org.junit.Test;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	@Test
    public void testGetUserDao() {

        try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDAO userDao = daoFactory.getUserDao();
			assertNotNull("userDao instance is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
