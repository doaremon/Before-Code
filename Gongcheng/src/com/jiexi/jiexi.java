package com.jiexi;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.work.Work3;
import com.work.Work4;
import com.yonghuliuyan.Work2;

public class jiexi {
	public static List<Work2> jxJson(String str,Class<Work2> cls){
		List<Work2> list=new  ArrayList<Work2>();
		JSONObject jsonObject=JSON.parseObject(str);

		String cont= jsonObject.getString("body");
		String cont1= jsonObject.getString("header");
		list=  JSON.parseArray(cont, cls);
		//list.add(JSON.parseObject(cont1, cls));

		return list;
	}
	public static List jxDoctorBack(String str1){
		List list=new ArrayList();
		String con=JSON.parseObject(str1).getString("header");
		list.add(JSON.parseObject(con).getString("result"));
		return list;
	}
	public static List<Work3> JieXidingdan(String json,Class<Work3> cls){
		List<Work3> list=new ArrayList<Work3>();
		JSONObject jsonObject= JSON.parseObject(json);
		String con= jsonObject.getString("list");
		list=JSON.parseArray(con,cls);
		return list;


	}
	//×ÉÑ¯¶©µ¥½âÎö
	public static  List<Work4> JieXizixundd(String json, Class<Work4> cls){
		List<Work4> list=new ArrayList<Work4>();
		JSONObject jsonObject=JSON.parseObject(json);
		String co=jsonObject.getString("body");
		list=JSON.parseArray(co,cls);
		return list;



	}
}
