package vault;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	private final String str1 = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	private final String str2 = "0123456789";
	private final String str4 = "!@#$%^&";
	private Random rand = new Random();
	
	
	public String genString() {
		StringBuilder build = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			int num1 = rand.nextInt(str1.length());
			int num2 = rand.nextInt(str2.length());
			int num4 = rand.nextInt(str4.length());
			build.append(str1.charAt(num1));
			build.append(str2.charAt(num2));
			build.append(str4.charAt(num4));
		}
		return build.toString();
	}
	

	public boolean checkName(String password, String theReg) {
		Pattern checkRegex = Pattern.compile(theReg);
		Matcher regMatch = checkRegex.matcher(password);
		
		if (regMatch.find()) {
			return true;
		}
		return false;
	}
}
