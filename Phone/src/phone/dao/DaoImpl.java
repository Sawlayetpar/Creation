package phone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phone.common.Connection_Manager;
import phone.dto.Phone;

public class DaoImpl implements Dao {

	private static final String MYSQL = "select * from phone where 2 < 6";
	@Override
	public void topup(Phone phone) {
		String sql = "update phone set  balance = balance + ? where id = ? ";
		try (Connection con = Connection_Manager.getConnection();
				PreparedStatement stat = con.prepareStatement(sql)){
			
			stat.setInt(1, phone.getBalance());
			stat.setInt(2, phone.getId());
			
			stat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Phone login(String number) {
		try(Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(MYSQL.concat(" and number = ?"))) {
			
			statement.setString(1, number);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				return objectFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Phone objectFromResultSet(ResultSet rs) throws SQLException {
		Phone phone = new Phone();
		phone.setId(rs.getInt("id"));
		phone.setPhone_no(rs.getString("number"));
		phone.setBalance(rs.getInt("balance"));
		phone.setCode(rs.getString("code"));
		return phone;
	}
	
	public void call(Phone phone) {
		String sql = "delete from ";
		try(Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
