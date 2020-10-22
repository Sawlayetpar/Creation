package watch;

public class Time_Class {

	private static int hour;
	private static int minute;
	private static int second;

	public static int getHour() {
		return hour;
	}

	public static void setHour(int hour) {
		hour = ((hour>=0 && hour<24) ? hour:0);
		Time_Class.hour = hour;
	}

	public static int getMinute() {
		return minute;
	}

	public static void setMinute(int minute) {
		minute = ((minute>=0 && minute<60) ? minute:0);
		Time_Class.minute = minute;
	}

	public static int getSecond() {
		return second;
	}

	public static void setSecond(int second) {
		second = ((second>=0 && second<60) ? second:0);
		Time_Class.second = second;
	}

}
