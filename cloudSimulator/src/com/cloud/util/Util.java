package com.cloud.util;

public class Util {

	public static String format(String str, int length){
		String result = "";
		String.format("%-"+length+"s", str);
		return result;
	}
}
