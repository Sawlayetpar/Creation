package tc.dao;

import java.util.List;

import tc.daoImpl.Class_Teacher_DaoImpl;
import tc.dto.Class;
import tc.dto.Class_Teacher;

public interface Class_Teacher_Dao {
	
	public static Class_Teacher_Dao getInstance() {
		return new Class_Teacher_DaoImpl();
	}

	void create(Class_Teacher class_teacher);
	List<Class_Teacher> searchByClass(Class classes);
	int count();
}
