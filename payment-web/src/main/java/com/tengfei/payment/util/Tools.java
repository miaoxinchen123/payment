package com.tengfei.payment.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;

public class Tools {

	public static String formatMoney(BigDecimal money) {
		if(null == money) money = new BigDecimal(0);
		
		return "￥" + money.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static String generatorId() {
		String retVal = UUID.randomUUID().toString();
		
		return retVal.replaceAll("-", "");
	}
	
	public static Result buildOkResult() {
		return new Result(Status.OK, "操作成功!");
	}
	
	
	public static Result buildUpLoadResult(Status status,String filePath) {
		return new Result(Status.OK, filePath);
	}
	
	public static String defaultPwd() {
		return "111111";
	}
	
	public static Float defaultRate() {
		return 0.02f;
	}
	
	public static boolean isProxy(String userType) {
		return UserType.PROXY.code().equals(userType);
	}
	
	public static boolean isMerchant(String userType) {
		return UserType.MERCHANT.code().equals(userType);
	}
	
	public static String formatRate(Float rate) {
		if(null == rate) return "_";
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		return fnum.format(rate) + "";
	}
	
	public static String formatThousandRate(Float rate) {
		if(null == rate) return "_";
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		return fnum.format(rate) + "";
	}
	
	public static String formatName(String name) {
		if(Utility.isEmpty(name)) return name;
		
		return name.substring(0, 1) + "*";
	}
	
	public static String formatCardNo(String cardNo) {
		if(Utility.isEmpty(cardNo) || cardNo.length() <= 4) return cardNo;
		StringBuffer retVal = new StringBuffer();
		for(int i = 0; i < cardNo.length(); i++) {
			if(0 != i && 0 == i % 4) retVal.append("  ");
			if(i + 4 >= cardNo.length()) {
				retVal.append(cardNo.charAt(i));
				continue;
			}
			
			retVal.append("*");
		}
		
		return retVal.toString();
	}
	
	public static String getCurrentDate() {
		return sdf.format(new Date());
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
}
