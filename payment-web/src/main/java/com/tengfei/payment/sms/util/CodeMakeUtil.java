package com.tengfei.payment.sms.util;

import java.util.Random;

public class CodeMakeUtil {

	private static int DEFAULT_LEN = 6;
	
	public static String makeCode(int legnth) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < legnth; i++) {
			sb.append(random.nextInt(10));
		}
		
		return sb.toString();
	}
	
	public static String makeCode() {
		return makeCode(DEFAULT_LEN);
	}
	
}
