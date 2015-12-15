package com.denglu;

import com.alibaba.fastjson.JSON;

public class zuheJson {
 public static String zuhe(Object json){
	 
	 return JSON.toJSONString(json);
 }
}
