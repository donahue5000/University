import java.util.ArrayList;

public class Roster
{
	private static ArrayList<Student> students = new ArrayList<>();
	
	public static void main(String[] args)
	{
		//Build Arraylist of Student objects
		newStudent("1", "John", "Smith", "John1989@gmail.com", 20, 
			new int[]{88, 79, 59});
		newStudent("2", "Suzan", "Erickson", "Erickson_1990@gmailcom", 19,
			new int[]{91, 72, 85});
		newStudent("3", "Jack", "Napoli", "The_lawyer99yahoo.com", 19,
			new int[]{85, 84, 87});
		newStudent("4", "Erin", "Black", "Erin.black@comcast.net", 22,
			new int[]{91, 98, 82});
		newStudent("5", "Brian", "Donahue", "bdonah9@wgu.edu", 35,
			new int[]{100, 100, 100});
		
		
		//Unit Test
		System.out.println();
		print_all();
		System.out.println();
		print_invalid_emails();
		System.out.println();
		for (Student iterator: students)
		{
			print_average_grade(iterator.getStudentID());
		}
		System.out.println();
		remove("3");
		remove("3");
	}
	
	public static void newStudent(String studentID, String firstName, 
		String lastName, String emailAddress, int age, int[] grades)
		{
			Student tempStudent = new Student(studentID, firstName, 
				lastName, emailAddress, age, grades);
				
			students.add(tempStudent);
		}
		
	public static void remove(String studentID)
	{
		for (int i = 0; i < students.size(); i++)
		{
			if (studentID.equals(students.get(i).getStudentID()))
			{
				students.remove(i);
				break;
			}else if (i == students.size() - 1)
			{
				System.out.println("Student ID " + studentID + " not found.");
			}
		}
	}
	
	public static void print_all()
	{
		for (Student iterator: students)
		{
			iterator.print();
		}
	}
	
	public static void print_average_grade(String studentID)
	{
		for (int i = 0; i < students.size(); i++)
		{
			if (studentID.equals(students.get(i).getStudentID()))
			{
				int[] grades = students.get(i).getGrades();
				int total = 0;
				for (int grade: grades)
				{
					total += grade;
				}
				System.out.println("Student ID: " + studentID + " Average: "
					+ ((double)total / grades.length));
				break;
			}else if (i == students.size() - 1)
			{
				System.out.println("Student ID " + studentID + " not found.");
			}
		}
	}
	
	public static void print_invalid_emails()
	{
		for (Student iterator: students)
		{
			String email = iterator.getEmailAddress();
			boolean hasAtMark = false;
			boolean hasDot = false;
			boolean hasSpace = false;
			for (int i = 0; i < email.length(); i++)
			{
				if (email.charAt(i) == '@')
				{
					hasAtMark = true;
				}else if (email.charAt(i) == '.')
				{
					hasDot = true;
				}else if (email.charAt(i) == ' ')
				{
					hasSpace = true;
				}
			}
			if (!hasAtMark || !hasDot || hasSpace)
			{
				System.out.println("Student ID: " + iterator.getStudentID()
					+ " Invalid Email: " + email);
			}
		}
	}
}