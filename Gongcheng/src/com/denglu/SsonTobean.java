package com.denglu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SsonTobean {
	
	public static List<Object> NYGetBeanFromJson(String jsonss,Class<?> cls){
		List<Object> list=new ArrayList<Object>();
		JSONObject obj = JSON.parseObject(jsonss);
		list.add(obj.getString("result"));
		list.add(obj.getString("msg"));
		String content=obj.getString("list");
		
		list.add(JSON.parseArray(content,cls));
        
		
		return list;		
	}
}
