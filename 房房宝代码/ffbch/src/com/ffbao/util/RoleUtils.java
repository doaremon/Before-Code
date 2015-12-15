package com.ffbao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.ffbao.entity.Role;
/**
 * 
 * @FileName:RoleUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RoleUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 用户角色处理
 */
public class RoleUtils {

	public static Map<String, Role> map = new HashMap<String, Role>();
	private static List<String> roleidArray = new ArrayList<String>(); // 存放

	private static Map<String,String> dit  = new HashMap<String, String>();
	// RoleID

	public static void setRole(Role role) {

		map.put(role.getRoleid(), role);
		roleidArray.add(role.getRoleid());

	}

	public static void clear() {

		map.clear();
		roleidArray.clear();
	}

	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:是否 可以报备权限
	 */
	public static boolean getAuth() {

		if (roleidArray.size() > 0) {
			return true;
		}

		return false;
	}
	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:current user offer role name
	 */
	public static String getRoleName(){

		if(map.containsKey("10")){
			return "VIP";
		}else if(map.containsKey("9")){
			return "经纪人";
		}else if(map.containsKey("11")){
			return "经纪管理员";
		}else if(map.containsKey("22")){
			return "独立经纪人";
		}else{
			Log.i("chenghao", "出现问题，返回默认值独立经纪人");
			return "null";
		}
		//这要做出修改
	}


	public static void setMap(){

		dit.put("9", "经纪人");
		dit.put("10", "VIP");
		dit.put("11", "经纪管理员");
		dit.put("22", "独立经纪人");
		//这里需要做出修改

	}
	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:验证是否 VIP
	 */
	public static boolean isVip(){

		if(map.containsKey("10")){

			return true;
		}
		return false;
	}

	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:验证是否经纪人
	 */
	public static boolean isAnget(){

		if(map.containsKey("9")){

			return true;
		}
		return false;
	}

	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:验证是否管理员
	 */
	public static boolean isManager(){

		if(map.containsKey("11")){

			return true;
		}
		return false;
	}
	//这要添加验证



}
