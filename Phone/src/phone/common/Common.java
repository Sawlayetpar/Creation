package phone.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Common {

	public static boolean validNumber(String str) {
		Pattern pattern = Pattern.compile("(^09|^9|^01|^)(\\d{9}|\\d{7}|\\d{6})$");
		Matcher matcher = pattern.matcher(str);
		boolean b = matcher.matches();
		return b;
	}
}
