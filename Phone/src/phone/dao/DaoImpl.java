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
		String sql = "update phone set balance = ? where id = ? ";
		try (Connection con = Connection_Manager.getConnection();
				PreparedStatement stat = con.prepareStatement(sql)){
			
			stat.setString(1, phone.getBalance());
			stat.setInt(2, phone.getId());
			
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				objectFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Phone login(String number) {
		try(Connection connection = Connection_Manager.getConnection();
				PreparedStatement statement = connection.prepareStatement(MYSQL.concat(" and ph_no = ?"))) {
			
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
		phone.setId(rs.getInt("idphone"));
		phone.setPhone_no(rs.getString("ph_no"));
		phone.setBalance(rs.getString("balance"));
		return phone;
	}



	
	


}
