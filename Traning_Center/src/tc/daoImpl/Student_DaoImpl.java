package tc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tc.common.Connection_Manager;
import tc.dao.Student_Dao;
import tc.dto.Address;
import tc.dto.Gender;
import tc.dto.Student;

public class Student_DaoImpl implements Student_Dao {

	private static final String SELECT = "select * from student where 2 > 1 ";
	@Override
	public void create(Student student) {
		String sql = "insert into student(name, phone, email, address, gender) values(?,?,?,?,?)";
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, student.getName());
			statement.setString(2, student.getPhone());
			statement.setString(3, student.getEmail());
			statement.setNString(4, student.getAddress().getClass().getName());
			statement.setString(5, student.getGender().name());
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Student> findById(int id) {
		StringBuilder sb = new StringBuilder(SELECT);
		List<Student> std = new LinkedList<>();
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
				std.add(objectResultSet(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return std;
	}

	private Student objectResultSet(ResultSet rs) throws SQLException {
		Student std = new Student();
		
		std.setId(rs.getInt("id"));
		std.setName(rs.getString("name"));
		std.setPhone(rs.getString("phone"));
		std.setEmail(rs.getString("email"));
		
		Address address = new Address();
		address.setStreet(rs.getString("address"));
		address.setTownship(rs.getString("address"));
		address.setCity(rs.getString("address"));
		
		std.setAddress(address);
		std.setGender(Gender.valueOf(rs.getString("gender")));
		return std;
	}
	
	@Override
	public long getCount() {
		String sql = "select count(*) from student where id > 0";
		try (Connection conn = Connection_Manager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				return rs.getLong(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
