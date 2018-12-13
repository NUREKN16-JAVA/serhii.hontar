package ua.nure.kn16.hontar.usermanagement.db;

import com.mockobjects.dynamic.Mock;
import  ua.nure.kn16.hontar.usermanagement.db.*;
public class MockDaoFactory extends DaoFactory {
    
	private Mock mockUserDao;

    public MockDaoFactory() {
    	mockUserDao = new Mock(UserDAO.class);
    }
    
    public Mock getMockUserDao() {
		return mockUserDao;
	}
    
	public UserDAO getUserDao() {
		return (UserDAO)mockUserDao.proxy();
	}


}