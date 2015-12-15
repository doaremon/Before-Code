package com.ffbao.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.SharedPreferences;

public class StringUtils {
	
	/**
	 * 
	 * @Deprecatred:
	 * @param phone
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:验证手机格式
	 */
	public static boolean isPhone(String phone) {

		if (phone != null && phone.length()!=0) {
			Pattern pattern = Pattern.compile("1[3|4|5|7|8|][0-9]{9}");
			Matcher matcher = pattern.matcher(phone);
			return matcher.matches();

		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param phone
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:验证手机验证码格式
	 */
	public static boolean sendPhoneCode(Context context ,String phone) {
		
		if (phone != null && phone.length()!=0) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			if (phone.equals(sp.getString(SharedPrefConstance.regiestPhone, ""))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param str
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:非空处理
	 */
	public static boolean isNull(String str) {
		if(str != null)
			str = str.trim();
		if (str != null && str.length()!=0)
			return true;
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param phone
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: 数字格式验证
	 */
	public static boolean isNumber(String phone) {
		
		if (isNull(phone)) {
			Pattern pattern = Pattern.compile("[0-9]{5,20}");
			Matcher matcher = pattern.matcher(phone);
			return matcher.matches();
		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param code
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:验证 注册手机号的验证码
	 */
	public static boolean isPhoneCode(Context context, String code) {

		if (isNull(code)) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			if (code.equals(sp.getString(SharedPrefConstance.regiestPhoneCode, ""))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param code
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:修改绑定手机号 验证码格式
	 */
	public static boolean isPhoneCodeChange(Context context, String code) {
		
		if (isNull(code)) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			if (code.equals(sp.getString(SharedPrefConstance.changePhoneCode, ""))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param code
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:忘记密码 验证手机验证格式
	 */
	public static boolean isPhoneCodeFotget(Context context, String code) {
		
		if (isNull(code)) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			if (code.equals(sp.getString(SharedPrefConstance.forgettPhoneCode, ""))) {
				return true;
			}
		}
		return false;
	}

//	public static boolean isPassword(String password) {
//
//		if (isNull(password)) {
//			if (password.length() >= 6 && password.length() < 20) {
//				return true;
//			}
//		}
//		return false;
//	}
	/**
	 * 
	 * @Deprecatred:
	 * @param password
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:验证密码格式
	 */
	public static boolean isPassword(String password) {
//		[a-zA-Z0-9]{5,19}
		if (isNull(password)) {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,20}");
			Matcher matcher = pattern.matcher(password);
			return matcher.matches();
		}
		return false;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param password
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:金额格式
	 */
	public static String isVerfyJE(String target) {

				
		double f = Double.valueOf(target);
//        BigDecimal bg = new BigDecimal(f);
//        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
		return String.format("%.2f", f);
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param password
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:佣金点金额
	 */
	public static String isVerfyYJD(String target) {
		
		
		double f = Double.valueOf(target);
//        BigDecimal bg = new BigDecimal(f);
//        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
		return String.format("%.1f", f);
	}
}
