
public class TimeThread extends Thread {
	private static int time = 0;
	private boolean finished = false;
	public static int getTime() {
		return time;
	}

	public void run() {
		while(!finished) {
			time++;
			System.out.println("Time: " + time);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void setFinish() {
		finished  = true;
	}
}
