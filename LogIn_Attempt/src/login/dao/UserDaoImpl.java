package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.common.Connection_Manager;
import login.dto.User;

public class UserDaoImpl implements UserDao {

	private static final String SELECT = "select * from user where  2 > 1";//here 2 > 1 is for conjuction of two sentence

	@Override
	public User login(String name, String password) {
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT.concat(" and name = ? and password = ?"))) {

			statement.setString(1, name);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				return objectResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(User user) {
		String sql = "insert into user(name,password) value( ?, ? )";
		
		try(Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, user.getName());
			statement.setString(2, user.getPassword());
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getCount() {
		String sql = "select count (*) from user ";
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				return rs.getLong(1);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private User objectResultSet(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("iduser"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		return user;

	}

}
