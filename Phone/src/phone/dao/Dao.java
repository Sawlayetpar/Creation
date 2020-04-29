package phone.dao;

import phone.dto.Phone;

public interface Dao {

	public static Dao getInstance() {
		return new DaoImpl();
	}
	
	void TopUp(Phone phone);
}
