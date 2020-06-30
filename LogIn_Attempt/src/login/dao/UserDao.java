package login.dao;

import login.dto.User;

public interface UserDao {
	
	public static UserDao getInstance() {
		return new UserDaoImpl();
	}

	User login(String name,String password);
	void save(User user);
	long getCount();
}
