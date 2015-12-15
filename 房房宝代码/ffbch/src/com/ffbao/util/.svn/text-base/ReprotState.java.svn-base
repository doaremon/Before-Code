package com.ffbao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @FileName:ReprotState.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ReprotState.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: report moblie display state
 */
public class ReprotState {

	static Map<Integer, String> map = new HashMap<Integer, String>();

	public static Map<Integer, String> init() {
 
		map.put(0, "报备成功");
		map.put(1, "等待验证");
		map.put(2, "已经验证");
		map.put(3, "本地接洽");
		map.put(4, "签约看房");
		map.put(5, "已经订票");
		map.put(6, "已订酒店");
		map.put(7, "安排接待");
		map.put(8, "已经接待");
		map.put(9, "已经看房");
		map.put(10, "申购");
		map.put(11, "申购确认");
		map.put(12, "合同已签");
		map.put(13, "付款");
		map.put(14, "成交");
		map.put(15, "申请结佣");
		map.put(16, "确认结佣");
		map.put(17, "已开发票");
		map.put(18, "结佣");
		map.put(98, "行程有变");
		map.put(99, "作废");
		/**
		 * @[@"报备成功",@"等待验证",@"验证通过",@"本地接洽",@"签约看房",@"已经订票",@"已订酒店",@"安排接待",@"已经接待",@"已经看房",@"申购",@"申购确认",@"合同已签",@"付款",@"成交",@"申请结佣",@"已开发票",@"结佣",@"行程有变",@"已经作废"]

		 */
		return map;
	}

	public static String getMap(Integer key) {
		if (map.isEmpty()) {
			init();
		}

		return map.get(key);
	}
}
