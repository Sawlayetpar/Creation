package phone.common;

import phone.dto.Phone;

public final class Security {

	private static Phone number;
	private static Phone balance;
	
	public static Phone getBalance() {
		return balance;
	}

	public static void setBalance(Phone bal) {
		Security.balance = bal;
	}

	public static void setNumber(Phone no) {
		number = no;
	}
	
	public static Phone getNumber() {
		return number;
	}
	
}
