package tc.dao;

import java.util.List;

import tc.daoImpl.Course_DaoImpl;
import tc.dto.Course;

public interface Course_Dao {
	
	public static Course_Dao getIntstance() {
		return new Course_DaoImpl();
	}
	
	void create(Course course);
	List<Course> findByName(String name);
	List<Course> searchByCourse(String name);
	int getCount();
}
