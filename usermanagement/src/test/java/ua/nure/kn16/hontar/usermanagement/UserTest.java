package ua.nure.kn16.hontar.usermanagement;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private static final String NAME = "Ivan";
	private static final String SURNAME = "Ivanov";
	private static final long ID = 1L;
	private User user;
	
	private static final int CURRENT_YEAR = 2018;
	private static final int YEAR_OF_BIRTH = 1984;
	private static final int ETALONE_AGE = CURRENT_YEAR-YEAR_OF_BIRTH;
	
	//Тест1 Когда месяц не наступил
	private static final int DAY_OF_BIRTH_1 = 15;
	private static final int MONTH_OF_BIRTH_1 = Calendar.DECEMBER;
	
	//Тест2 Когда месяц наступил, а день - не наступил
	private static final int DAY_OF_BIRTH_2 = 3;
	private static final int MONTH_OF_BIRTH_2 = Calendar.NOVEMBER;
	
	//Тест3 Когда день рождения в этот же день
	private static final int DAY_OF_BIRTH_3 = 1;
	private static final int MONTH_OF_BIRTH_3 = Calendar.NOVEMBER;
	
	//Тест4 Когда месяц тот же, но день прошел
	private static final int DAY_OF_BIRTH_4 = 1;
	private static final int MONTH_OF_BIRTH_4 = Calendar.NOVEMBER;
	
	//Тест5 Когда месяц прошел
	private static final int DAY_OF_BIRTH_5 = 2;
	private static final int MONTH_OF_BIRTH_5 = Calendar.JULY;
	

	private Date dateOfBirthd;
	@Before
	public void setUp() throws Exception {

		user = new User(ID, NAME, SURNAME, new Date());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Проверка 
	public void testGetFullName() {
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		assertEquals("Ivanov, Ivan",user.getFullName());
	}
	@Test
	//Тест1 Когда месяц не наступил
	public void testGetAge1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE-1, user.getAge());
	}
	
	@Test
	//Тест2 Когда месяц наступил, а день - не наступил
	public void testGetAge2() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE-1, user.getAge());
	}
	
	@Test
	//Тест3 Когда день рождения в этот же день
	public void testGetAge3() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}
	
	@Test
	//Тест4 Когда месяц тот же, но день прошел
	public void testGetAge4() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}
	
	@Test
	//Тест5 Когда месяц прошел
	public void testGetAge5() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}



}
