package com.fromjson;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.work.Work;

public class Firstutil {
 public static List<Work> jiexiJson(String json,Class<Work> cls){
	 List<Work> list=new ArrayList<Work>();
	 JSONObject jsonObject = JSON.parseObject(json);
	 String str = jsonObject.getString("list").toString();
	 list=JSON.parseArray(str,cls);
	 Log.i("www", list+"");
    return list;
 }
}
