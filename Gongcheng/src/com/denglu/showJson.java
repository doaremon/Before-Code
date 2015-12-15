package com.denglu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class showJson {
	public static List<Object> show(String str){
		List<Object>list=new ArrayList<Object>();
		list.add(JSON.parseArray(JSON.parseObject(str).getString("list")));	           
		return list;
	}
}
