package phone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import phone.common.Connection_Manager;
import phone.dto.Phone;

public class DaoImpl implements Dao {

	@Override
	public void TopUp(Phone phone) {
		String sql = "update phone set balance = ? where id = ? ";
		try (Connection con = Connection_Manager.getConnection();
				PreparedStatement stat = con.prepareStatement(sql)){
			
			stat.setString(1, phone.getBalance());
			stat.setInt(2, phone.getId());
			
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
