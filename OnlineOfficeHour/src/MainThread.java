import java.util.Scanner;

public class MainThread {

	static Lab lab;
	static TeacherThread teacher;
	static TimeThread t = new TimeThread();
	static int stu;
	
	public static void main(String[] args) {
		int capacity = 8;
		lab = new Lab(capacity);
		System.out.print("Number of students: ");
		Scanner input = new Scanner(System.in);
		stu = input.nextInt();
		input.close();
		System.out.println("Student= "+ stu);
		System.out.println("Lab is open, students enter the lab.");
		for(int i=1; i<=stu;i++) {
			StudentThread student = new StudentThread(i);
			student.start();
		}
		lab.setOpen(true);
		t.start();
		
		teacher = new TeacherThread();
		teacher.setPriority(10);
		teacher.start();
		
		
		

	}

}
