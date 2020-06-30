package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import login.common.Connection_Manager;
import login.common.Security;
import login.dto.User;

public class AttemptDaoImpl implements AttemptDao {

	@Override
	public User save() {
		String sql = "insert into attempt(attrmpt_name,attempt_time) value(?,?)";
		
		try(Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, Security.getLoginUser().getName());
			statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			
			statement.executeUpdate();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}

}
