package phone.common;

import phone.dto.Phone;

public final class Security {

	private static Phone phone;
	private static long number;

	public static long getNumber() {
		return number;
	}

	public static void setNumber(long number) {
		Security.number = number;
	}

	public static Phone getPhone() {
		return phone;
	}

	public static void setPhone(Phone phone) {
		Security.phone = phone;
	}

}
