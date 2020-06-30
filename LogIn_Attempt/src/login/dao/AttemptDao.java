package login.dao;

import login.dto.User;

public interface AttemptDao {

	public static AttemptDao getInstance() {
		return new AttemptDaoImpl();
	}
	
	User save();
}
