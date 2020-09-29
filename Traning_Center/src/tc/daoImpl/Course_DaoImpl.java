package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import tc.common.Connection_Manager;
import tc.dao.Course_Dao;
import tc.dto.Course;

public class Course_DaoImpl implements Course_Dao {

	private static final String SELECT = "select * from course where 2 > 1 ";

	@Override
	public void create(Course course) {

		String sql = "insert into course(name, level,duration,fees) values(?,?,?,?)";
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, course.getName());
			statement.setInt(2, course.getLevel());
			statement.setInt(3, course.getDuration());
			statement.setInt(4, course.getFees());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Course> findByName(String name) {
		StringBuilder sb = new StringBuilder(SELECT);
		List<Course> course = new LinkedList<>();
		List<Object> obj = new ArrayList<>();

		if (null != name && !name.isEmpty()) {
			sb.append("and name regexp ? ");
			obj.add(name);
		}

		try (Connection con = Connection_Manager.getConnection();
				PreparedStatement statement = con.prepareStatement(sb.toString())) {

			for (int i = 0; i < obj.size(); i++) {
				statement.setObject(i + 1, obj.get(i));
			}

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				course.add(objectResultSet(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;

	}

	private Course objectResultSet(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt("id"));
		course.setName(rs.getString("name"));
		course.setLevel(rs.getInt("level"));
		course.setDuration(rs.getInt("duration"));
		course.setFees(rs.getInt("fees"));
		
		return course;
	}

	@Override
	public int getCount() {

		String sql = "select count(*) from course where id > 0";
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

	@Override
	public List<Course> searchByCourse(String name) {

		String sql = "select * from course where 2 > 1 ";
		Predicate<String> pred = str -> null != str && !str.isEmpty();
		List<Course> course = new LinkedList<>();
		
		try(Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(pred.test(name) ? sql.concat("and name regexp ?") : sql)){
			
			if(pred.test(name)) {
				stmt.setString(1, name);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				course.add(objectResultSet(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return course;
	
	}

}
