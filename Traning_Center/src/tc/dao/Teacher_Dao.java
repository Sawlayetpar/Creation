package tc.dao;

import java.util.List;

import tc.daoImpl.Teacher_DaoImpl;
import tc.dto.Teacher;

public interface Teacher_Dao {
	
	public static Teacher_Dao getInstance() {
		return new Teacher_DaoImpl();
	}

	void create(Teacher teacher);
	List<Teacher> findById(int id);
	int getCount();
}
