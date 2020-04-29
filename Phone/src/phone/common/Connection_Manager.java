package phone.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection_Manager {

	private static String url;
	private static String user;
	private static String password;
	
	static {
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("database.properties"));
			url = pro.getProperty("database.url");
			user = pro.getProperty("database.username");
			password = pro.getProperty("database.password");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection_Manager() {}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
