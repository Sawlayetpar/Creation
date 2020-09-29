package tc.common;

import java.util.Date;

public class CommonUtil {

	public static int convertStringToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static java.sql.Date setCustomDate(Date date) {
	    return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date setCurrentDate(Date date) {
		date = new Date();
	    return new java.sql.Date(date.getTime());
	}
}
