
public class Student
{
	private String studentID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int age;
	private int[] grades;
	
	//Constructor
	public Student(String studentID, String firstName, String lastName, 
		String emailAddress, int age, int[] grades)
	{
		setStudentID(studentID);
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(emailAddress);
		setAge(age);
		setGrades(grades);
	}
	
	//Setter methods
	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public void setGrades(int[] grades)
	{
		this.grades = grades;
	}
	
	//Getter methods
	public String getStudentID()
	{
		return studentID;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int[] getGrades()
	{
		return grades;
	}
	
	public void print()
	{
		System.out.printf(
			"Student ID: %-4s "
			+ "First Name: %-10s "
			+ "Last Name: %-10s "
			+ "E-mail: %-30s "
			+ "Age: %-3d ",
			getStudentID(),
			getFirstName(),
			getLastName(),
			getEmailAddress(),
			getAge());
		System.out.print("Grades: ");
		for (int i = 0; i < grades.length; i++)
		{
			System.out.print(grades[i]);
			if (i < grades.length - 1)
			{
				System.out.print(", ");
			}
		}
		System.out.println();
	}
}