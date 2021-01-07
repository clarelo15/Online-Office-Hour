import java.util.ArrayList;

public class TeacherThread extends Thread {
	public static long time = System.currentTimeMillis();
	public final int officeStart = 25;
	public final int officeEnd = 70;
	public final int chatStart = 35;
	public final int chatEnd = 55;
	public boolean isOpenOffice = false;
	public static ArrayList<StudentThread> ques = new ArrayList<>();
	public static ArrayList<StudentThread> quesB = new ArrayList<>();

	public TeacherThread() {
		setName("teacherThread");
	}
	public boolean isOpenOffice() {
		return isOpenOffice;
	}
	public void run() {
		while(true) {
			System.out.print("");
			if(isOpenOffice == false && TimeThread.getTime() > officeStart) {
				isOpenOffice = true;
			}
			if(officeEnd < TimeThread.getTime()) {
				msg("Office hour is end");
				isOpenOffice = false;
				break;
			}
			if(chatStart < TimeThread.getTime() && chatEnd>  TimeThread.getTime()&& !quesB.isEmpty()) {
				msg("is chatting about questionB with "+ quesB.get(0).getName());
				answerQuestionB();
				quesB.remove(0).interrupt();
				continue;
			}
			if(officeStart< TimeThread.getTime() && officeEnd>  TimeThread.getTime()&& !ques.isEmpty()) {
				msg("is replying questionA of "+ ques.get(0).getName());
				answerQuestionA();
				ques.remove(0);
			}
		}
		MainThread.t.setFinish();
		msg("Teacher leaves the office.");
		
	}

	
	public synchronized void receiveQuestionA(StudentThread s) {
		ques.add(s);
	}

	private void answerQuestionA() {
		try {
			sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void receiveQuestionB(StudentThread s) {
		quesB.add(s);
	}
	private void answerQuestionB() {
		try {
			sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "]" + getName() + ": " + m);
	}

}
