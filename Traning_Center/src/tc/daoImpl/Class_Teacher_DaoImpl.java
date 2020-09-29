package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.Common;
import tc.common.Connection_Manager;
import tc.dao.Class_Teacher_Dao;
import tc.dto.Class;
import tc.dto.Class_Teacher;
import tc.dto.Teacher;

public class Class_Teacher_DaoImpl implements Class_Teacher_Dao {
	@Override
	public void create(Class_Teacher class_teacher) {
		String sql = "insert into class_teacher(class_id,techer_id,incharge) values(?,?,?)";

		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, Common.getClass_name().getId());
			statement.setInt(2, class_teacher.getTeacher_id().getId());
			statement.setBoolean(3, class_teacher.isIncharge());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Class_Teacher> searchByClass(Class classes) {
		String sql = "select class.id,teacher.id,teacher.name,incharge "
				+ "from class_teacher "
				+ "inner join teacher on class_teacher.techer_id = teacher.id "
				+ "inner join class on class_teacher.class_id = class.id ";

		StringBuilder sb = new StringBuilder(sql);
		List<Object> obj = new ArrayList<>();
		List<Class_Teacher> class_teacher = new LinkedList<>();

		if (null != classes) {
			sb.append("and class_teacher.class_id = ? ");
			obj.add(classes.getId());
		}

		try (Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {

			for (int i = 0; i < obj.size(); i++) {
				stmt.setObject(i + 1, obj.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Class class1 = new Class();
				class1.setId(rs.getInt("class.id"));
//				class1.setName(rs.getString("course.name"));

				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("teacher.id"));
				teacher.setName(rs.getString("teacher.name"));

				Class_Teacher c_teacher = new Class_Teacher();
				c_teacher.setClass_id(class1);
				c_teacher.setTeacher_id(teacher);
				c_teacher.setIncharge(rs.getBoolean("incharge"));

//				classes.setCourse(courses);
//				classes.setId(rs.getInt("id"));
//				classes.setStart_date(rs.getDate("start_date"));
//				classes.setEnd_date(rs.getDate("end_date"));

				class_teacher.add(c_teacher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return class_teacher;
	}

	@Override
	public int count() {

		String sql = "select count(*) from class_teacher where teacher_id > 0";
		try (Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

}
