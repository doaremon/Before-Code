package com.denglu;

import java.util.HashMap;
import java.util.Map;

public class Hashmaps {

	static Spinjie sp = new Spinjie("yisibo", "yisibo");
	private static Map<String, Object> hashMap = new HashMap<String, Object>();

	public static String pinjieJson(String username, String password) {
		hashMap.put("nettest", sp);
		hashMap.put("uname", username);
		hashMap.put("upwd", password);
		String sss = zuheJson.zuhe(hashMap);
		return sss;

	}
}
