package tc.dao;

import java.util.List;

import tc.daoImpl.Class_DaoImpl;
import tc.dto.Class;
import tc.dto.Course;

public interface Class_Dao {

	public static Class_Dao getInstance() {
		return new Class_DaoImpl();
	}
	
	void create(Class classes);
	List<Class> findByName(Course course);
}
