package tc.dao;

import java.util.List;

import tc.daoImpl.Class_Student_DaoImpl;
import tc.dto.Class;
import tc.dto.Class_Student;

public interface Class_Student_Dao {
	
	public static Class_Student_Dao getInstance() {
		return new Class_Student_DaoImpl();
	}

	void create(Class_Student class_student);
	List<Class_Student> searchByClass(Class classes);
	int getCount();
}
