package com.qa.opencart.utils;

public class TimeUtil {

	public final static int DEFAULT_TIME = 500;
	public final static int SHORT_TIME = 2;
	public final static int MEDIUM_TIME = 5;
	public final static int LONG_TIME = 10;
	public final static int MAX_TIME = 15;
	
	
	public static void myCustomTime(int MyWaitTime) {
		try {
			Thread.sleep(MyWaitTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void defaultTime() {
		try {
			Thread.sleep(DEFAULT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void shortTime() {
		try {
			Thread.sleep(SHORT_TIME * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumTime() {
		try {
			Thread.sleep(MEDIUM_TIME * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void longTime() {
		try {
			Thread.sleep(LONG_TIME * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void maxTime() {
		try {
			Thread.sleep(MAX_TIME * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
