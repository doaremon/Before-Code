package com.yonghuliuyan;

import java.util.HashMap;

import com.denglu.StringInfor;
import com.gongyong.Config;
import com.work.Work2;

public class Map {
	static Work2 work2=new Work2("yisibo", "yisibo");
	public static String qingqiuJson(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("id", json);
		String sss=  ZuheJson.pinjson(hashMap);
		return sss;
	}

	public static String qingqiuJson1(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("fatherid", json);
		String sss=  ZuheJson.pinjson(hashMap);
		return sss;
	}
	public static String doctorJsonBack(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("Content", json);
		hashMap.put("Fatherid",Config.workss.getFatherid());
		hashMap.put("recipient",Config.workss.getRecipient());
		hashMap.put("recipientid",Config.workss.getRecipientid());
		hashMap.put("sender",Config.workss.getSender());
		hashMap.put("senderid",Config.workss.getSenderid());
		hashMap.put("sims_type","02");
		hashMap.put("title",Config.workss.getTitle());
		String aaa=	ZuheJson.pinjson(hashMap);
		return aaa;



	}
	public static String GuahaoOrder(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("doctorid", Config.infor.getDoctorid());

		hashMap.put("jztime", json);
		hashMap.put("nettest", work2);
		hashMap.put("pagenum", "30");
		hashMap.put("registtype", "zj");
		hashMap.put("showpage", "1");
		String s=ZuheJson.pinjson(hashMap);
		return s;
	}
	//咨询订单拼接json
	public static String ZxddOrder(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("doctorid", Config.infor.getDoctorid());
		hashMap.put("registertime", json);
		hashMap.put("state", "0");
		String sss=ZuheJson.pinjson(hashMap);
		return sss;
	}
	//账单查询json
	public static String ZdcxOrder(int json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("doctorid", Config.infor.getDoctorid());
		hashMap.put("month", json);
		String str3=ZuheJson.pinjson(hashMap);
		return str3;
	}
	//时间设置
	public static String Timeshezhi(String json){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("id", Config.infor.getDoctorid());
		hashMap.put("worktime", json);
		String str= ZuheJson.pinjson(hashMap);
		return str;

	}
	//修改密码
	public static String Updpassword(String ps1,String ps2,String ps3){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("doctorid", Config.infor.getDoctorid());
		hashMap.put("nettest", work2);
		hashMap.put("newpwd", ps2);
		hashMap.put("oldpwd", ps1);
		hashMap.put("userid", ps3);
		String str= ZuheJson.pinjson(hashMap);
		return str;
	}
	//问题反馈
	public static String wentifankui(String str){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("feedback", str);
		hashMap.put("type","0");
		hashMap.put("userid", Config.infor.getDoctorid());
		String str1= ZuheJson.pinjson(hashMap);
		return str1;
	}
	//资格变更
	public static String zigeviangeng(){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("id", Config.infor.getDoctorid());
		String str= ZuheJson.pinjson(hashMap);
		return str;
	} 
	//费用设置
	public static String feiyongshezhi(){
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("id", Config.infor.getDoctorid());
		hashMap.put("nettest", work2);
		String str= ZuheJson.pinjson(hashMap);
		return str;
	}
}