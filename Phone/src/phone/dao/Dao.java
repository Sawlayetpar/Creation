package phone.dao;

import phone.dto.Phone;

public interface Dao {

	public static Dao getInstance() {
		return new DaoImpl();
	}
	
	void topup(Phone phone);
	Phone login(String number);
}
