package tc.dao;

import java.util.List;

import tc.daoImpl.Student_DaoImpl;
import tc.dto.Student;

public interface Student_Dao {
	
	public static Student_Dao getInstance() {
		return new Student_DaoImpl();
	}

	void create(Student student);
	List<Student> findById(int id);
	long getCount();

}
