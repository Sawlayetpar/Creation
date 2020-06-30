package login.common;

import login.dto.User;

public final class Security {

	private static User loginUser;

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Security.loginUser = loginUser;
	}
	
	
}
