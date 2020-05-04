package phone.common;

import phone.dto.Phone;

public final class Security {

	private static Phone phone_no;
	private static Phone phone_bal;
	
	public static Phone getPhone_no() {
		phone_no = new Phone();
		phone_no.getPhone_no();
		return phone_no;
	}
	public static Phone getPhone_bal() {
		phone_bal = new Phone();
		phone_bal.getBalance();
		return phone_bal;
	}
	
	

	
}
