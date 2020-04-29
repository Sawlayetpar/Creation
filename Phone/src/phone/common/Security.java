package phone.common;

import phone.dto.Phone;

public class Security {

	private static Phone phoneNo;
	private static Phone phoneBal;

	public static Phone getPhoneBal() {
		return phoneBal;
	}

	public static void setPhoneBal(Phone bal) {
		phoneBal = bal;
	}

	public static Phone getPhoneNo() {
		return phoneNo;
	}

	public static void setPhoneNo(Phone phone_no) {
		phoneNo = phone_no;
	}

	
}
