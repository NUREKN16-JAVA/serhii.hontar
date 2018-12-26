package ua.nure.kn16.hontar.usermanagement.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn16.hontar.usermanagement.User;

public class DetailsServletTest extends MockServletTestCase {

    private static final String ATTR_USER = "user";
    private static final String BACK_OPTION = "Back";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testBack() {
        addRequestParameter("backButton", BACK_OPTION);
        User nullUser = (User) getWebMockObjectFactory().getMockSession().getAttribute(ATTR_USER);
        assertNull("User must not exist in session scope", nullUser);
    }
}