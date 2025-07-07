package com.openmrs.utils;

import java.util.TimerTask;

public class ConsoleTimer extends TimerTask {
	
	private static int TIMER_DURATION = 30;

	@Override
	public void run() {

		if (TIMER_DURATION > 0) {

			System.out.print("Timer: " + TIMER_DURATION + " sec");
			for (int i = 0; i <= TIMER_DURATION; i++) {
				System.out.print(" .");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
			TIMER_DURATION--;
		} else {
			System.out.println("Timer expired! Entire Test case on the project is going to execute.");
			cancel(); // Stop the TimerTask
		}
	}

	public void printWithDelay(String inputString, int delay) {
		System.out.println("                                                                             ");
        System.out.println("#############################################################################");

		for (char c : inputString.toCharArray()) {
			System.out.print(c);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(); // Add this line
		System.out.println("                                                                             ");
        System.out.println("#############################################################################");
	}

}
