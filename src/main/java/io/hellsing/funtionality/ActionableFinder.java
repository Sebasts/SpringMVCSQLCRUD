package io.hellsing.funtionality;

public class ActionableFinder {

	public static void main(String[] args) throws InterruptedException {
		//while (true) {
			System.out.println("");
			Runnable thread = new TimeResearcher();
			new Thread(thread).start();
			Thread.sleep(400);
	//	}

	}

}
