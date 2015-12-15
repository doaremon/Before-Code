package com.ffbao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;
import com.ffbao.entity.Purchase;

import com.ffbao.entity.Building;
import com.ffbao.entity.ComissionListEntity;
import com.ffbao.entity.CommissionNoteInfo;
import com.ffbao.entity.Comission;
import com.ffbao.entity.EditReportedInfo;
import com.ffbao.entity.HouseDetail;
import com.ffbao.entity.HouseImage;
import com.ffbao.entity.HouseParamter;
import com.ffbao.entity.HouseUnits;
import com.ffbao.entity.NewBuilding;
import com.ffbao.entity.Report;
import com.ffbao.entity.ReportDetails;
import com.ffbao.entity.ReportMessage;
import com.ffbao.entity.ResultReportInfo;
import com.ffbao.entity.HouseSimple;
import com.ffbao.entity.Topic;
import com.ffbao.entity.UpdateEntity;
import com.ffbao.entity.UpdateResultEntity;
import com.ffbao.entity.Role;
import com.ffbao.entity.Travel;
import com.ffbao.entity.UpdateVersion;
import com.ffbao.entity.User;
import com.ffbao.entity.WantCity;
import com.ffbao.ui.RegiestMessageActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;

/**
 * 
 * @FileName:ExecuteJSONUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ExecuteJSONUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: parse callback listener success
 */
public class ExecuteJSONUtils {

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: interface userGetDetail
	 */
	public static boolean getUserDetails(Context context, String msg) {
		// SharedPreferences sp = context.getSharedPreferences("user",
		// Context.MODE_PRIVATE);
		// {status:1,message:"成功",result:{
		// :"13910688234"
		// ,:"吴大力"，
		// :"http://f1.ffbao.net/img/13910688234/tbdarcode.png",
		// :"http://f1.ffbao.net/img/1111/11.png",
		// :"http://www.ffb.net/comminissionguide.html",
		// :"http://file2.ffb.net/100005/20140921101010.jpg"，
		// :"北京链接地产"}}
		// myselfname,"林大力"
		// ，myselfphone:"122343333",
		// sex:"男",
		// companyid:100002,

		try {
			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				Gson gson = new Gson();
				User user = gson.fromJson(json.getJSONObject("result")
						.toString(), User.class);
				//这里银行卡版本新增isBack保存全局变量
				SharedPrefConstance.setSharePref(context, SharedPrefConstance.isBank, user.getIsBank());

				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.servicephone,
						user.getServicephone());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.sex, user.getSex());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.servicename, user.getServicename());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.tdbarcode_path,
						user.getTdbarcode_path());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.ffb_tdbarcode_path,
						user.getFfb_tdbarcode_path());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.shareGuideUrl,
						user.getShareGuideUrl());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.companyname, user.getCompanyname());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.companyid, user.getCompanyid());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.myselfimg, user.getMyselfimg());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.myselfname, user.getMyselfname());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.myselfphone, user.getMyselfphone());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.version, user.getVersion());
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.rolecount, user.getRolecount());
				JSONArray roleArray = json.getJSONObject("result")
						.getJSONArray("role");

				RoleUtils.clear();
				for (int i = 0; i < roleArray.length(); i++) {

					Object object = roleArray.get(i);
					Role role = gson.fromJson(object.toString(), Role.class);
					RoleUtils.setRole(role);
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userLogin interface
	 */
	public static boolean login(Context context, String msg) {
		// SharedPreferences sp = context.getSharedPreferences("user",
		// Context.MODE_PRIVATE);
		try {
			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				// Editor edit = sp.edit();

				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.UUID, json.getJSONObject("result")
						.optString("UUID"));
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.userid, json
						.getJSONObject("result").optString("userid"));

				// edit.putString(SharedPrefConstance.UUID,
				// json.getJSONObject("result").optString("UUID"));
				// edit.putString(SharedPrefConstance.userid,
				// json.getJSONObject("result").optString("userid"));
				// edit.commit();
				return true;

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:比较简单 {status:1,message:"成功"}
	 */
	public static boolean simpleUpdateInfo(Context context, String msg) {
		// {status:1,message:"成功"}
		try {
			JSONObject json = new JSONObject(msg);
			int status = json.optInt("status");
			if (status == 1) {

				return true;
			} else {
				Toast.makeText(
						context,
						json.optString("message") != null ? json
								.optString("message") : "", Toast.LENGTH_LONG)
								.show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:比较简单 {status:1,message:"成功"}
	 */
	public static int getMessageState(Context context, String msg) {
		// {status:1,message:"成功"}
		try {
			if (msg != null && msg.length()!=0) {
				JSONObject json = new JSONObject(msg);
				int status = json.optInt("status");
				return status;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userSendCheckCode interface
	 */
	public static boolean phoneCode(Context context, String msg) {
		try {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			// Gson gson = new Gson();
			// User fromJson = gson.fromJson(msg,
			// User.class);
			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				// Editor edit = sp.edit();
				// edit.putString(SharedPrefConstance.regiestPhoneCode, json
				// .getJSONObject("result").optString("checkcode"));
				// edit.commit();
				SharedPrefConstance
				.setSharePref(
						context,
						SharedPrefConstance.regiestPhoneCode,
						json.getJSONObject("result").optString(
								"checkcode"));
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @param companyID
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userSearchCompany interface
	 */
	public static boolean SearchCompany(Context context, String msg,
			String companyID) {
		try {
			// SharedPreferences sp = context.getSharedPreferences("user",
			// Context.MODE_PRIVATE);
			// Gson gson = new Gson();
			// User fromJson = gson.fromJson(msg,
			// User.class);
			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				// Editor edit = sp.edit();
				// edit.putString(SharedPrefConstance.companyname, json
				// .getJSONObject("result").optString("companyname"));
				// edit.putString(SharedPrefConstance.companyid, companyID);
				// edit.commit();
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.companynameUpdate,
						json.getJSONObject("result").optString("companyname"));
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userUpdatePersonImg interface
	 */
	public static boolean UpdatePersonImg(Context context, String msg) {

		// {status:1,message:"成功",result:{persionimgpath:"http://file2.ffb.net/100005/20140921101010.jpg"}}
		try {
			// SharedPreferences sp = context.getSharedPreferences("user",
			// Context.MODE_PRIVATE);

			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				// Editor edit = sp.edit();
				// edit.putString(SharedPrefConstance.myselfimg, json
				// .getJSONObject("result")
				// .optString("persionimgpath"));
				// edit.commit();
				SharedPrefConstance.setSharePref(context,
						SharedPrefConstance.myselfimg,
						json.getJSONObject("result")
						.optString("persionimgpath"));
				return true;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetReportList interface
	 */
	public static List<Report> getReportList(Context context, String msg) {

		// {status:1,message:"成功",result:{recorid:100001,customerphone:"134433222123"，
		// customername:"沈东等"，country:"中国",city:"海南",age:40,vocation:"经理",property:"100万以下",
		// address:""朝阳区慧忠北路慧忠里123号楼(近北辰东路)",type:"外往内地发送",intention_city:"海南","
		// +
		// "intention_buildingname:"绿地国宝",budget:100,purpose:"为子女购房",possibility:"很想买而且很有实力","
		// +
		// ""trip_type":"房房宝安排",trip_plan_state："待定",need_ticket:"待定",need_hotel:"待定"
		// ,headcount:3,departure_date:“”,arrival_date:"",createdate:"2014-09-21 10:00:00"，state:"报备成功"}}

		List<Report> reqorts = new ArrayList<Report>();
		try {

			JSONObject json = new JSONObject(msg);
			if (simpleUpdateInfo(context, msg)) {
				JSONArray array = json.getJSONArray("result");
				for (int i = 0; i < array.length(); i++) {
					String message = array.get(i).toString();
					Gson gson = new Gson();
					Report report = gson.fromJson(message, Report.class);
					reqorts.add(report);
				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return reqorts;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetMessages interface
	 */
	public static List<ReportMessage> getReportMessageList(Context context,
			String msg) {

		// {"status":1,"message":"成功","total":2,"result":[{"messageid":2,"reportid":100001,
		// "messagecontent":"您的用户沈大力机票预订成功。","confirmed":1,"create_time":"2014/09/29 18:10:43"},
		// {"messageid":4,"reportid":100001,"messagecontent":"您的用户沈大力已经到达海南。","confirmed":1,
		// "create_time":"2014/09/28 18:11:05"}]}

		List<ReportMessage> reqorts = new ArrayList<ReportMessage>();
		try {

			if (simpleUpdateInfo(context, msg)) {
				JSONObject json = new JSONObject(msg);
				JSONArray array = json.getJSONArray("result");
				for (int i = 0; i < array.length(); i++) {
					String message = array.get(i).toString();
					Gson gson = new Gson();
					ReportMessage report = gson.fromJson(message,
							ReportMessage.class);
					reqorts.add(report);
				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return reqorts;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetMessagesCount interface
	 */
	public static int getMessageCount(Context context, String msg) {

		int count = 0;
		try {

			if (simpleUpdateInfo(context, msg)) {
				// if (msg != null && !msg.isEmpty()) {
				JSONObject json = new JSONObject(msg);
				int status = json.optInt("status");
				if (status == 1) {
					JSONObject jsonObject = json.getJSONObject("result");
					jsonObject.optString("total");

					// Editor edit = sp.edit();
					// edit.putString(SharedPrefConstance.reportCount,
					// jsonObject.optString("total"));
					// edit.commit();
					count = jsonObject.optInt("total");
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return count;
	}

	// public static boolean getAddReportResult(Context context, String msg) {
	// boolean boo = false;
	//
	// if (msg != null && !msg.isEmpty()) {
	// ResultInfo resultInfo = jsonByGsonByResultInfo(msg);
	// int yes_no = resultInfo.getStatus();
	// if (yes_no == 1) {
	// return true;
	// } else {
	// return false;
	// }
	// } else {
	// Toast.makeText(context, "网络有点慢??", 0).show();
	// }
	//
	// return boo;
	// }

	public static boolean getAddReportResult(Context context, String msg) {

		// if (msg != null && !msg.isEmpty()) {
		// ResultInfo resultInfo = jsonByGsonByResultInfo(msg);
		// int yes_no = resultInfo.getStatus();
		//
		// }

		return simpleUpdateInfo(context, msg);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userUpdateReportList interface
	 */
	public static EditReportedInfo getEditReportResult(Context context,
			String msg) {
		EditReportedInfo editReportedInfo = null;
		// status:1,message:"成功",result:
		if (msg != null && msg.length()!=0) {
			ResultReportInfo resultReportInfo = jsonByGsonByResultReportInfo(msg);
			int yes_no = resultReportInfo.getStatus();
			if (yes_no == 1) {
				editReportedInfo = resultReportInfo.getResult();
			} else {
				Toast.makeText(context, "失败", 0).show();
			}
		} else {
			Toast.makeText(context, "网络有点慢??", 0).show();
		}
		return editReportedInfo;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:UpdateEntity
	 */
	public static boolean getUpdateCode(Context context, String msg) {
		boolean boo = false;
		UpdateEntity updateEntity = jsonByGsonByUpdateEntity(msg);
		int status = updateEntity.getStatus();
		if (status == 1) {
			boo = true;
		} else {
			boo = false;
		}
		return boo;
	}

	private static ResultInfo jsonByGsonByResultInfo(String str) {
		Gson g = new Gson();
		return g.fromJson(str, ResultInfo.class);
	}

	private static ResultReportInfo jsonByGsonByResultReportInfo(String str) {
		Gson g = new Gson();
		return g.fromJson(str, ResultReportInfo.class);
	}

	private static UpdateEntity jsonByGsonByUpdateEntity(String str) {
		Gson g = new Gson();
		return g.fromJson(str, UpdateEntity.class);
	}

	private static ComissionListEntity jsonByGsonByComissionListEntity(
			String str) {
		Gson g = new Gson();
		return g.fromJson(str, ComissionListEntity.class);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetReportDetail interface
	 */
	public static ReportDetails getReportDtails(Context context, String msg) {
		ReportDetails report = null;
		try {

			JSONObject json = new JSONObject(msg);

			if (simpleUpdateInfo(context, msg)) {
				// if (msg != null && !msg.isEmpty()) {
				// int status = json.optInt("status");
				// if (status == 1) {
				JSONObject jsonObject = json.getJSONObject("result");
				Gson gson = new Gson();
				report = gson.fromJson(jsonObject.toString(),
						ReportDetails.class);

				// } else {
				// Toast.makeText(context, json.optString("message"),
				// Toast.LENGTH_LONG).show();
				// }
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return report;
	}

	public static CommissionNoteInfo getCommissionNoteInfoResult(
			Context context, String msg) {
		ComissionListEntity cle = jsonByGsonByComissionListEntity(msg);
		CommissionNoteInfo cni = null;
		int code = cle.getStatus();
		if (code == 1) {
			cni = cle.getResult();
		} else {
			Toast.makeText(context, "获取相应失败", 1).show();
		}
		return cni;
	}

	public static Comission getReportComission(Context context, String msg) {

		Comission comission = null;
		try {

			JSONObject json = new JSONObject(msg);

			if (simpleUpdateInfo(context, msg)) {
				// if (msg != null && !msg.isEmpty()) {
				// int status = json.optInt("status");
				// if (status == 1) {
				JSONObject jsonObject = json.getJSONObject("result");
				Gson gson = new Gson();
				comission = gson.fromJson(jsonObject.toString(),
						Comission.class);

				// } else {
				// Toast.makeText(context, json.optString("message"),
				// Toast.LENGTH_LONG).show();
				// }
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return comission;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetTripList interface
	 */
	public static List<Travel> getTravelList(Context context, String msg)
			throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONArray jsonArray = new JSONObject(msg).getJSONArray("result");
			List<Travel> travels = new ArrayList<Travel>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Gson gson = new Gson();
				Travel target = gson.fromJson(jsonArray.get(i).toString(),
						Travel.class);
				travels.add(target);
			}
			return travels;
		}

		return null;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @param flag
	 * @return
	 * @throws Exception
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetTopicList interface
	 */
	public static List<Topic> getTopicList(Context context, String msg,
			boolean flag) throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONArray jsonArray = new JSONObject(msg).getJSONArray("result");
			List<Topic> topics = new ArrayList<Topic>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Gson gson = new Gson();
				Topic target = gson.fromJson(jsonArray.get(i).toString(),
						Topic.class);
				topics.add(i, target);
			}
			if (flag) {

				Collections.reverse(topics);
			}
			return topics;
		}

		return null;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userGetPurchaseList interface
	 */
	public static Purchase getPurchase(Context context, String msg)
			throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONObject jsonObject = new JSONObject(msg).getJSONObject("result");

			if (jsonObject.keys().hasNext()) {
				Gson gson = new Gson();
				Purchase target = gson.fromJson(jsonObject.toString(),
						Purchase.class);
				return target;
			}
		}

		return null;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:userUpdateVersion interface
	 */
	public static UpdateVersion ParseVersion(Context context, String msg)
			throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONObject jsonObject = new JSONObject(msg).getJSONObject("result");

			if (jsonObject.keys().hasNext()) {
				Gson gson = new Gson();
				UpdateVersion target = gson.fromJson(jsonObject.toString(),
						UpdateVersion.class);
				return target;
			}
		}

		return null;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取个人头像
	 */
	public static boolean getMyselfImg(Context context, String msg)
			throws Exception {

		if (simpleUpdateInfo(context, msg)) {

			JSONObject json = new JSONObject(msg);
			String persionimgpath = json.getJSONObject("result").getString(
					"persionimgpath");
			SharedPrefConstance.setSharePref(context,
					SharedPrefConstance.myselfimg, persionimgpath);
			BitmapUtils utils = new BitmapUtils(context);
			utils.clearCache(persionimgpath);
			return true;
		}
		return false;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取服务器一些 设置参数
	 */
	public static boolean getOtherMessages(Context context, String msg)
			throws Exception {
		/**
		 * {"status":1, "result":{"smswaittime":120,"iphoneversion":"1.0.9",
		 * "iphonemustupdatelist":["1.0.0","1.0.1","1.0.2"]}}
		 */
		if (simpleUpdateInfo(context, msg)) {

			JSONObject json = new JSONObject(msg);
			String smswaittime = json.getJSONObject("result").getString(
					"smswaittime");
			String workguide = json.getJSONObject("result").getString(
					"workguide");
			String agreement = json.getJSONObject("result").getString(
					"agreement");
			String other = json.getJSONObject("result").getString(
					"other");
			String customerservices = json.getJSONObject("result").getString(
					"customerservices");
			SharedPrefConstance.setSharePrefPhone(context,
					SharedPrefConstance.smswaittime, smswaittime);
			SharedPrefConstance.setSharePrefPhone(context,
					SharedPrefConstance.workguide, workguide);
			SharedPrefConstance.setSharePrefPhone(context,
					SharedPrefConstance.agreement, agreement);
			SharedPrefConstance.setSharePrefPhone(context,
					SharedPrefConstance.other, other);
			SharedPrefConstance.setSharePrefPhone(context,
					SharedPrefConstance.customerservices, customerservices);
			return true;
		}
		return false;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:为报备单 提供意向城市
	 */
	public static List<WantCity> getWantCitys(Context context, String msg)
			throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONArray jsonArray = new JSONObject(msg).getJSONArray("result");
			List<WantCity> wantCitys = new ArrayList<WantCity>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Gson gson = new Gson();
				WantCity target = gson.fromJson(jsonArray.get(i).toString(),
						WantCity.class);
				wantCitys.add(target);
			}
			return wantCitys;
		}

		return null;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:为报备单提交 意向楼盘
	 */
	public static List<NewBuilding> getBuildings(Context context, String msg)
			throws Exception {

		/**
		 * {"status":1,"message":"成功","total":5,"result":[
		 * {"id":1,"value":"省-楼盘"}, {"id":2,"value":"市-楼盘"},
		 * {"id":4,"value":"县-楼盘1"}, {"id":5,"value":"县-楼盘2"},
		 * {"id":6,"value":"县-楼盘3"}]}
		 */
		if (simpleUpdateInfo(context, msg)) {
			JSONArray jsonArray = new JSONObject(msg).getJSONArray("result");
			List<NewBuilding> buildings = new ArrayList<NewBuilding>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Gson gson = new Gson();
				NewBuilding target = gson.fromJson(jsonArray.get(i).toString(),
						NewBuilding.class);
				buildings.add(target);
			}
			return buildings;
		}

		return null;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws Exception
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:获取 msgFragment获取列表
	 */
	public static List<HouseSimple> getBuildingListState(Context context,
			String msg) throws Exception {

		if (simpleUpdateInfo(context, msg)) {
			JSONArray jsonArray = new JSONObject(msg).getJSONArray("result");
			List<HouseSimple> simple = new ArrayList<HouseSimple>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Gson gson = new Gson();
				HouseSimple target = gson.fromJson(jsonArray.get(i).toString(),
						HouseSimple.class);
				simple.add(target);
			}
			return simple;
		}

		return null;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:解析 houseParamter
	 */
	public static HouseParamter getBuildingPatamer(Context context, String msg) {

		HouseParamter paramter = null;
		try {

			JSONObject json = new JSONObject(msg);

			if (simpleUpdateInfo(context, msg)) {
				// if (msg != null && !msg.isEmpty()) {
				// int status = json.optInt("status");
				// if (status == 1) {
				JSONObject jsonObject = json.getJSONObject("result");
				Gson gson = new Gson();
				paramter = gson.fromJson(jsonObject.toString(),
						HouseParamter.class);

				// } else {
				// Toast.makeText(context, json.optString("message"),
				// Toast.LENGTH_LONG).show();
				// }
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return paramter;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @throws JSONException
	 *             解析有问题
	 * @Funtion:解析 HouseImage
	 */
	public static HouseImage getBuildingImages(Context context, String msg)
			throws JSONException {

		/**
		 * "{ ""status"": 1, ""message"": ""成功"", ""result"": {
		 * ""effectPicture"": [ { ""url"":
		 * ""http://192.168.1.251:9999/images/7344d
		 * d988b2146ad815403f474a57999Jellyfish.jpg"" } ], ""planPicture"": [
		 * 
		 * ], ""realisticPicture"": [ { ""url"":
		 * ""http://192.168.1.251:9999/images
		 * /cef14a6fe873446faa8473ab9ab320ddHydrangeas.jpg"" }, { ""url"":
		 * ""http://192.168.1.251:9999/images/
		 * dbb02b1ca9154d8fb0f1cce21a3113e8Lighthouse.jpg"" } ],
		 * ""assortPicture"": [
		 * 
		 * ], ""templetPicture"": [ { ""url"":
		 * ""http://192.168.1.251:9999/images
		 * /5d5f9722c0464990a59d2ed82d964689Desert.jpg"" }, { ""url"":
		 * ""http://192.168
		 * .1.251:9999/images/e6976ed11b9e465d9a3cfd09f05a9129Penguins.jpg"" }
		 * ], ""housePicture"": [
		 * 
		 * ] } }"
		 */
		HouseImage image = new HouseImage();

		if (simpleUpdateInfo(context, msg)) {
			JSONObject json = new JSONObject(msg);
			JSONObject result = json.getJSONObject("result");
			JSONArray effectPicture = result.getJSONArray("effectPicture");
			JSONArray planPicture = result.getJSONArray("planPicture");
			JSONArray realisticPicture = result
					.getJSONArray("realisticPicture");
			JSONArray assortPicture = result.getJSONArray("assortPicture");
			JSONArray templetPicture = result.getJSONArray("templetPicture");
			JSONArray housePicture = result.getJSONArray("housePicture");
			int size = 0;
			for (int i = 0; i < effectPicture.length(); i++) {
				JSONObject object = (JSONObject) effectPicture.get(i);
				image.getEffectPicture().add(object.getString("url"));
				size++;
			}
			for (int i = 0; i < planPicture.length(); i++) {
				JSONObject object = (JSONObject) planPicture.get(i);
				image.getPlanPicture().add(object.getString("url"));
				size++;
			}
			for (int i = 0; i < realisticPicture.length(); i++) {
				JSONObject object = (JSONObject) realisticPicture.get(i);
				image.getRealisticPicture().add(object.getString("url"));
				size++;
			}
			for (int i = 0; i < assortPicture.length(); i++) {
				JSONObject object = (JSONObject) assortPicture.get(i);
				image.getAssortPicture().add(object.getString("url"));
				size++;
			}
			for (int i = 0; i < templetPicture.length(); i++) {
				JSONObject object = (JSONObject) templetPicture.get(i);
				image.getTempletPicture().add(object.getString("url"));
				size++;
			}
			for (int i = 0; i < housePicture.length(); i++) {
				JSONObject object = (JSONObject) housePicture.get(i);
				image.getHousePicture().add(object.getString("url"));
				size++;
			}
			image.setSize(size);

		}
		return image;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws JSONException
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取楼盘详情接口
	 */
	public static HouseDetail getHouseDetails(Context context, String msg)
			throws JSONException {

		// HouseDetail detail = new HouseDetail();
		Gson gson = new Gson();
		JSONObject jsonObject = new JSONObject(msg).getJSONObject("result");
		HouseDetail detail = gson.fromJson(jsonObject.toString(),
				HouseDetail.class);
		List<HouseUnits> units = new ArrayList<HouseUnits>();

		JSONArray array = new JSONObject(msg).getJSONObject("result")
				.getJSONArray("housetype");
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			HouseUnits unit = gson
					.fromJson(object.toString(), HouseUnits.class);
			units.add(unit);
		}
		detail.setHousetypes(units);
		return detail;
	}

	/**
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @throws JSONException
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取楼盘详情接口
	 */
	public static List<HouseUnits> getHouseUnits(Context context, String msg)
			throws JSONException {

		List<HouseUnits> units = new ArrayList<HouseUnits>();

		JSONArray array = new JSONObject(msg).getJSONObject("result")
				.getJSONArray("housetype");
		Gson gson = new Gson();
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			HouseUnits unit = gson
					.fromJson(object.toString(), HouseUnits.class);
			units.add(unit);
		}

		return units;
	}

	public static Map<String, Object> getReportAndTravelAndPurchaseDetail(
			Context context, String msg) {

		/**
		 * {status:1,message:"成功",
		 * resultReport:{recorid:100001,customerphone:"134433222123"
		 * ，customername
		 * :"沈东等"，country:"中国",city:"海南",age:40,vocation:"经理",property
		 * :"100万以下",address:""
		 * 朝阳区慧忠北路慧忠里123号楼(近北辰东路)",type:"外往内地发送",typevalue:0,intention_city:"
		 * 海南",intention_buildingname:"
		 * 绿地国宝",budget:100,purpose:"为子女购房",purposevalue:1,possibility:"
		 * 很想买而且很有实力","trip_type":"房房宝安排",trip_plan_state："待定",need_ticket:"待定
		 * ",need_hotel:"
		 * 待定",headcount:3,departure_date:“”,arrival_date:"",createdate:"
		 * 2014-09-21 10:00:00"，state:"报备成功"},
		 * resultPurchase:{recorid:100001,usernamename
		 * :"沈东等"，buildingname:"",agent_company
		 * :"",house_number:"",state:,purchase_city
		 * :"",purchase_district:"",purchase_address
		 * :"",price:,amout:,actiualTotal_price
		 * :,agent_brokerage_fee:,update_time:""},
		 * resultTrip:{recorid:100001,usernamename
		 * :"沈东等"，buildingname:"",agent_company
		 * :"",house_number:"",state:,purchase_city
		 * :"",purchase_district:"",purchase_address:"",
		 * price:,amout:,actiualTotal_price
		 * :,agent_brokerage_fee:,update_time:""}}
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		if (simpleUpdateInfo(context, msg)) {
			// JSONArray jsonArray = new
			// JSONObject(msg).getJSONArray("resultTrip");
			// if(){
			//
			// List<Travel> travels = new ArrayList<Travel>();
			// for (int i = 0; i < jsonArray.length(); i++) {
			// Gson gson = new Gson();
			// Travel target = gson.fromJson(jsonArray.get(i).toString(),
			// Travel.class);
			// travels.add(target);
			// }
			// }
			//
			// JSONObject jsonObject = new
			// JSONObject(msg).getJSONObject("resultPurchase");
			// Gson gson = new Gson();
			// Purchase purchase = gson.fromJson(jsonObject.toString(),
			// Purchase.class);
			// return purchase;

			// JSONObject jsonObject = json.getJSONObject("resultReport");
			// Gson gson = new Gson();
			// ReportDetails report = gson.fromJson(jsonObject.toString(),
			// ReportDetails.class);

		}
		return map;

	}

	class ResultInfo implements Serializable {

		/**
		 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
		 * 
		 * @since JDK 1.7
		 */
		private static final long serialVersionUID = 1L;
		private int status;
		private String message;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "ResultInfo [status=" + status + ", message=" + message
					+ "]";
		}

		public ResultInfo(int status, String message) {
			super();
			this.status = status;
			this.message = message;
		}

		public ResultInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
}
