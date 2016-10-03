package org.machek.elro.shared;

public class Util {

	public static String concatStringArray(String[] parts) {
		String res = "";
		for (int i=0; i < parts.length; i++) {
			res += parts[i] + " ";
		}
		
		return res;
	}
	
}
