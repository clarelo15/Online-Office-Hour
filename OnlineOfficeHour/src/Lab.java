
import java.util.TreeSet;

public class Lab {
	private int capacity;
	private boolean open = false;
	public final int startTime= 3;
	public final int endTime =  60;
	private boolean full = false;
	private TreeSet<StudentThread> stu;
	
	public Lab(int i) {
		this.capacity = i;
		stu = new TreeSet<StudentThread>();
	}

	public synchronized boolean isFull() {
		return full;
	}
	
	public synchronized void addStudent(StudentThread s) {
		stu.add(s);
		if(stu.size()>=capacity) 
			full = true;
	}
	

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public TreeSet<StudentThread> getStu() {
		return stu;
	}

	
}
