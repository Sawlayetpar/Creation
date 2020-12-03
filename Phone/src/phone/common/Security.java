package phone.common;

import phone.dto.Phone;

public final class Security {

	private static Phone phone;
	private static String number;
	private static int duration;

	public static int getDuration() {
		return duration;
	}

	public static void setDuration(int duration) {
		Security.duration = duration;
	}

	public static String getNumber() {
		return number;
	}

	public static void setNumber(String number) {
		Security.number = number;
	}

	public static Phone getPhone() {
		return phone;
	}

	public static void setPhone(Phone phone) {
		Security.phone = phone;
	}

	public static int amount(int time) {
		int cost = 10;
		time = Security.duration * cost;
		return time;
	}
}
