package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.Connection_Manager;
import tc.dao.Teacher_Dao;
import tc.dto.Teacher;

public class Teacher_DaoImpl implements Teacher_Dao {

	private static final String SELECT = "select * from teacher where 2 > 1 ";

	@Override
	public void create(Teacher teacher) {

		String sql = "insert into teacher(name, phone) values(?,?)";
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, teacher.getName());
			statement.setString(2, teacher.getPhone());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Teacher> findById(int id) {

		StringBuilder sb = new StringBuilder(SELECT);
		List<Teacher> teacher = new LinkedList<>();
		List<Object> obj = new ArrayList<>();

		if (id > 0) {
			sb.append(" and id = ? ");
			obj.add(id);
		}

		try (Connection con = Connection_Manager.getConnection();
				PreparedStatement statement = con.prepareStatement(sb.toString())) {

			for (int i = 0; i < obj.size(); i++) {
				statement.setObject(i + 1, obj.get(i));
			}

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				teacher.add(objectResultSet(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacher;

	}

	private Teacher objectResultSet(ResultSet rs) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(rs.getInt("id"));
		teacher.setName(rs.getString("name"));
		teacher.setPhone(rs.getString("phone"));
		return teacher;
	}

	@Override
	public int getCount() {

		String sql = "select count(*) from teacher where id > 0";
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
