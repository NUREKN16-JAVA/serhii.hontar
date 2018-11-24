package ua.nure.kn16.hontar.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirthd;
	public User(String firstName, String lastName, Date dateofBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirthd = dateofBirth;
	}
	public User(Long id, String firstName, String lastName, Date dateOfBirthd) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirthd = dateOfBirthd;
	}
	public User(User user) {
		 this(user.getId(), user.getFirstName(), user.getLastName(), user.getDateOfBirthd()); 

	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirthd() {
		return dateOfBirthd;
	}
	public void setDateOfBirthd(Date dateOfBirthd) {
		this.dateOfBirthd = dateOfBirthd;
	}
	public Object getFullName() {
		
		return getLastName()+", "+getFirstName();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		final int currentYear = calendar.get(Calendar.YEAR);
		final int currentMonth = calendar.get(Calendar.MONTH);
		final int currentDate = calendar.get(Calendar.DATE);
		
		calendar.setTime(dateOfBirthd);
		
		final int birthYear = calendar.get(Calendar.YEAR);
		final int birthMonth = calendar.get(Calendar.MONTH);
		final int birthDate = calendar.get(Calendar.DATE);
		
		int age =currentYear - birthYear;
		if ((currentMonth < birthMonth ) || (currentMonth == birthMonth && currentDate < birthDate))
		{
			age--;
		}
		
		return age;
	}
	
	

}
