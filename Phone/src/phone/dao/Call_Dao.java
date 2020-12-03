package phone.dao;

import java.util.List;

import phone.dto.Phone_Call;

public interface Call_Dao {

	public static Call_Dao instance() {
		return new Call_DaoImpl();
	}
	void save(Phone_Call call);
	List<Phone_Call> history(String ph);
}
