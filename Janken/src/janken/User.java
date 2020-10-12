package janken;

import java.util.Scanner;

public class User {

	private static Scanner user = new Scanner(System.in);

	public static int getInt(String str) {
		return Integer.parseInt(getString(str));
	}
	
	public static String getString(String msg) {
		System.out.print(msg);
		return user.nextLine().trim();
	}
}
