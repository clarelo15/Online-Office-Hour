
public class StudentThread extends Thread implements Comparable<Object> {
	private int id;
	public static long time = System.currentTimeMillis();
	private int questionA = 4;
	private int questionB = 3;
	

	public StudentThread(int i) {
		this.id = i;
		setName("studentThread_" + i);
		msg("I am created.");
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void run() {
		enterTheLab();
	}

	private void enterTheLab() {
		try {
			sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			if(MainThread.lab.isOpen()) {
				break;
			}
		}
		if (MainThread.lab.isFull()) {
			msg("the lab is already full so I am terminated.");
			return;
		}
		
		MainThread.lab.addStudent(this);
		msg(" enters the lab");
		createQuestionA();
		createQuestionB();
		
		msg("I want to leave the lab but i need to wait.");
		
		leaveLab();
		
			
		

	}

	private void createQuestionA() {
		this.setPriority(1);
		while (questionA > 0) {
			yield();
			try {
				sleep((int) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msg(" have a questionA_" + getId());
			sendingQA();
			questionA--;
		}
	}

	private void sendingQA() {
		this.setPriority(5);
		try {
			sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainThread.teacher.receiveQuestionA(this);
		msg(getName() + " is sending email within " + age() + " millisecond");
		this.setPriority(NORM_PRIORITY);
	}

	private long age() {
		long timestamp = (System.currentTimeMillis() - time);
		return timestamp;
	}

	private void createQuestionB() {
		this.setPriority(1);
		if (Math.random() >= .5) {
			msg("I don't want to ask in chat session.");
			questionB = 0;
			return;
		}
		while (questionB > 0) {
			MainThread.teacher.receiveQuestionB(this);
			msg("is waiting the answerB");
			try {
				sleep(100000);
			}catch(InterruptedException e) {
				msg("I got answerB from teacher");;
				questionB--;
			}
		}
		this.setPriority(NORM_PRIORITY);

	}

	private void leaveLab() {
		StudentThread joinStudent = null;
		
		
		while(MainThread.teacher.isOpenOffice()
				&&(MainThread.teacher.ques.contains(this) || questionA > 0 || questionB >0) ) {
			//wait until all questions are replied. 
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
		joinStudent = MainThread.lab.getStu().higher(this);
		
		try {
			if(MainThread.lab.getStu().last()!=this && joinStudent!=null && joinStudent.isAlive()) {
				msg("is waiting until "+joinStudent.getName()+" is terminated.");
			    joinStudent.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainThread.lab.getStu().remove(this);
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		msg("I am leaving.");

	}
	public int compareTo(Object o){
		StudentThread target = (StudentThread) o;
		int result = id - (int)target.getId();
		return result;
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "]" + getName() + ": " + m);
	}

}
