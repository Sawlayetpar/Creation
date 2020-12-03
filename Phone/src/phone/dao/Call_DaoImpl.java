package phone.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import phone.common.Connection_Manager;
import phone.common.Security;
import phone.dto.Phone_Call;

public class Call_DaoImpl implements Call_Dao {

	@Override
	public void save(Phone_Call call) {
		String sql = "insert into call_number(phone_number,call_number,amount,duration_seconds,time,date) values(?,?,?,?,?,?)";
		try (Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, Security.getPhone().getPhone_no());
			statement.setString(2, Security.getNumber());
			statement.setInt(3, call.getAmount());
			statement.setInt(4, call.getDuration());
			statement.setTime(5, Time.valueOf(LocalTime.now()));
			statement.setDate(6, Date.valueOf(LocalDate.now()));

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private Phone_Call obj(ResultSet rs) throws SQLException {
		Phone_Call call = new Phone_Call();
		call.setId(rs.getInt("id"));
		call.setPhone_number(rs.getString("phone_number"));
		call.setCall_number(rs.getString("call_number"));
		call.setAmount(rs.getInt("amount"));
		call.setDuration(rs.getInt("duration_seconds"));
		call.setDate(rs.getDate("date").toLocalDate());
		call.setTime(rs.getTime("time").toLocalTime());
		return call;
	}
	
	@Override
	public List<Phone_Call> history(String ph){
		String sql = "select * from call_number where 2 > 1 ";
		Predicate<String> pred = str -> null != str && !str.isEmpty();
		List<Phone_Call> phone = new LinkedList<>();
		
		try(Connection conn = Connection_Manager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(pred.test(ph) ? sql.concat("and phone_number regexp ?") : sql)){
			
			if(pred.test(ph)) {
				stmt.setString(1, ph);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				phone.add(obj(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return phone;
	}

}
