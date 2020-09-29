package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.Common;
import tc.common.CommonUtil;
import tc.common.Connection_Manager;
import tc.dao.Class_Student_Dao;
import tc.dto.Class;
import tc.dto.Class_Student;
import tc.dto.Student;

public class Class_Student_DaoImpl implements Class_Student_Dao {

	@Override
	public void create(Class_Student class_student) {
		String sql = "insert into class_student(class_id,student_id,fee,paid,registration_date) values(?,?,?,?,?)";

		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, Common.getClass_name().getId());
			statement.setInt(2, class_student.getStudent_id().getId());
			statement.setInt(3, Common.getClass_name().getCourse().getFees());
			statement.setInt(4, class_student.getPaid());
			statement.setDate(5, CommonUtil.setCurrentDate(class_student.getRegistration_date()));

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Class_Student> searchByClass(Class classes) {
//		String sql = "select class_student.fee,class_student.paid,class_student.registration_date,class.id,student.id,student.name "
//				+ "from class_student "
//				+ "inner join student on class_student.student_id = student.id "
//				+ "inner join on class_student.class_id = class.id ";
		
		String sql = "select class.id,student.id,student.name,fee,paid,registration_date "
				+ "from class_student "
				+ "inner join student on class_student.student_id = student.id "
				+ "inner join class on class_student.class_id = class.id ";
		
//		String sql = "select student.id,student.name,class.id,class_student.fee,class_student.paid,class_student.registration_date "
//				+ "from student,class,class_student "
//				+ "where class_student.student_id = student.id "
//				+ "and class_student.class_id = class.id";

		StringBuilder sb = new StringBuilder(sql);
		List<Object> obj = new ArrayList<>();
		List<Class_Student> class_student = new LinkedList<>();

		if (null != classes) {
			sb.append("and class_student.class_id = ? ");
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

				Student student = new Student();
				student.setId(rs.getInt("student.id"));
				student.setName(rs.getString("student.name"));

				Class_Student c_student = new Class_Student();
				c_student.setClass_id(class1);
				c_student.setStudent_id(student);
				c_student.setFee(CommonUtil.convertStringToInt(rs.getString("fee")));
				c_student.setPaid(CommonUtil.convertStringToInt(rs.getString("paid")));
				c_student.setRegistration_date(rs.getDate("registration_date"));
				

				class_student.add(c_student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return class_student;
	}

	@Override
	public int getCount() {
		
		return 0;
	}

}
