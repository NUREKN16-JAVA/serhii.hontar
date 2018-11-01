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
	
	//����1 ����� ����� �� ��������
	private static final int DAY_OF_BIRTH_1 = 15;
	private static final int MONTH_OF_BIRTH_1 = Calendar.DECEMBER;
	
	//����2 ����� ����� ��������, � ���� - �� ��������
	private static final int DAY_OF_BIRTH_2 = 3;
	private static final int MONTH_OF_BIRTH_2 = Calendar.NOVEMBER;
	
	//����3 ����� ���� �������� � ���� �� ����
	private static final int DAY_OF_BIRTH_3 = 1;
	private static final int MONTH_OF_BIRTH_3 = Calendar.NOVEMBER;
	
	//����4 ����� ����� ��� ��, �� ���� ������
	private static final int DAY_OF_BIRTH_4 = 1;
	private static final int MONTH_OF_BIRTH_4 = Calendar.NOVEMBER;
	
	//����5 ����� ����� ������
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
	//�������� 
	public void testGetFullName() {
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		assertEquals("Ivanov, Ivan",user.getFullName());
	}
	@Test
	//����1 ����� ����� �� ��������
	public void testGetAge1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE-1, user.getAge());
	}
	
	@Test
	//����2 ����� ����� ��������, � ���� - �� ��������
	public void testGetAge2() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE-1, user.getAge());
	}
	
	@Test
	//����3 ����� ���� �������� � ���� �� ����
	public void testGetAge3() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}
	
	@Test
	//����4 ����� ����� ��� ��, �� ���� ������
	public void testGetAge4() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}
	
	@Test
	//����5 ����� ����� ������
	public void testGetAge5() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
		dateOfBirthd = calendar.getTime();
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(ETALONE_AGE, user.getAge());
	}



}
