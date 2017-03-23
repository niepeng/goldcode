package com.niepeng.goldcode.util;

public class SystemClock {

	public static void sleepRandom(long min, long max) {
		sleepRandomAndRetrun(min, max);
	}
	public static long sleepRandomAndRetrun(long min, long max) {
		long sleepTime = (long) (Math.random() * (max - min)) + min;
		sleep(sleepTime);
		return sleepTime;
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			sleepRandom(5000, 15000);
		}
	}
}