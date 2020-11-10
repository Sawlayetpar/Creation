package phone.common;

import phone.dto.Phone;

public class TopUp_Number {

	private static Phone number;

	public static Phone getNumber() {
		return number;
	}

	public static void setNumber(Phone number) {
		TopUp_Number.number = number;
	}

}
