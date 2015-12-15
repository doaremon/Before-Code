package com.ffbao.ui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ffbao.activity.R;
import com.ffbao.entity.Building;
import com.ffbao.entity.ReportDetails;
import com.ffbao.entity.ReportedInfo;
import com.ffbao.entity.WantCity;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.CoustomSelectAdapter;
import com.ffbao.ui.widget.time.JudgeDate;
import com.ffbao.ui.widget.time.ScreenInfo;
import com.ffbao.ui.widget.time.WheelMain;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.AddCustomerUtils;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.ID;
import com.ffbao.util.ReprotState;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.ViewHelper;
import com.google.gson.Gson;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;

/**
 * 
 * @FileName:CustomAddActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomAddActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 添加修改报备单
 */
public class CustomAddSelectActivity extends BaseActivity implements
OnItemClickListener {

	private ListView listView; 
	private int requestCode = -1;

	private List<WantCity> wantCitys = null;
	private List<Building> buildings = null;
	private List<String> targetList = new ArrayList<String>();
	private String id;
	private String level;
	private String target;
	private Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_customerselect);
		ExitActivity.addActivity(this);
		Intent intent = getIntent();
		requestCode =intent.getExtras().getInt(Constant.requestCode);
		Log.i("chenghao", "得到的requestCode为="+requestCode);
		if (Constant.recommended_houses_intention == requestCode) {
			id = intent.getStringExtra(Constant.id);
			level = intent.getStringExtra(Constant.level);
		}
		if (requestCode != -1) {
			target = intent.getStringExtra(Constant.target);
		}

		listView = (ListView) findViewById(R.id.lv_houses);
		getStringList();
		setAdapter();
		intent = new Intent();
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: set Adapter 设置适配器
	 */
	private void setAdapter() {

		CoustomSelectAdapter adapter = new CoustomSelectAdapter(context,
				targetList, target);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(this);

	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 根据requestCode 判断是否 targetList
	 */
	private void getStringList() {

		switch (requestCode) {
		case Constant.recommended_withlooktype:
			targetList = AddCustomerUtils.withlooktype();
			setActionBarTitle("选择带看方式");
			break;
		case Constant.recommended_sex:
			targetList = AddCustomerUtils.generData();
			setActionBarTitle("选择性别");
			break;
		case Constant.recommended_buy_hotel:

			targetList = AddCustomerUtils.hotelAndFlyData();
			setActionBarTitle("是否需要预订酒店");
			break;
		case Constant.recommended_buy_fly:

			targetList = AddCustomerUtils.hotelAndFlyData();
			setActionBarTitle("是否需要订票");
			break;
		case Constant.recommended_go_mode:

			targetList = AddCustomerUtils.goModeData();
			setActionBarTitle("选择出行方式");
			break;
		case Constant.recommended_city_intention:

			getIntentCityArray();
			setActionBarTitle("选择意向城市");
			break;
		case Constant.recommended_asset:

			targetList = AddCustomerUtils.assetData();
			setActionBarTitle("选择资产情况");
			break;
		case Constant.recommended_buy_use:

			targetList = AddCustomerUtils.buyHousesData();
			setActionBarTitle("选择购房用途");
			break;
		case Constant.recommended_buy_possibility:

			targetList = AddCustomerUtils.canBuyHousesData();
			setActionBarTitle("选择购房可能性");
			break;
		case Constant.recommended_houses_intention:
			break;
		case Constant.recommended_buy_kind:

			targetList = AddCustomerUtils.buyKindData();
			setActionBarTitle("选择类型");
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param level
	 * @param id
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取意向楼盘
	 */
//	private void getIntentBuildingArray(String level, String id) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put(Constant.commandText, UrlUtil.userGetWantBuildings);
//		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
//				SharedPrefConstance.userid));
//		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
//				SharedPrefConstance.UUID));
//		map.put(Constant.id, id);
//		map.put(Constant.level, level);
//
//		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {
//
//			@Override
//			public void onSuccess(String msg) {
//
//				try {
//					buildings = ExecuteJSONUtils.getBuildings(context, msg);
//					for (int i = 0; i < buildings.size(); i++) {
//						Building building = buildings.get(i);
//						targetList.add(building.getValue());
//					}
//					if (targetList.size() == 0) {
//
//						ToastUtils.showToast(context, "意向楼盘未进行维护");
//					}
//					setAdapter();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//
//			@Override
//			public void onFailure(Exception error, String msg) {
//
//			}
//		});
//
//	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取意向城市
	 */
	private void getIntentCityArray() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetWantCitys);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));

		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				try {
					wantCitys = ExecuteJSONUtils.getWantCitys(context, msg);
					for (int i = 0; i < wantCitys.size(); i++) {
						WantCity wantCity = wantCitys.get(i);
						targetList.add(wantCity.getValue());
					}

					if (targetList.size() == 0) {

						ToastUtils.showToast(context, "意向城市未进行维护");
					}
					setAdapter();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (Constant.recommended_city_intention == requestCode) {
			// intent.putExtra(Constant.position, position+"");
			intent.putExtra(Constant.level, wantCitys.get(position).getLevel());
			intent.putExtra(Constant.id, wantCitys.get(position).getId());

		} else if (Constant.recommended_houses_intention == requestCode) {
			intent.putExtra(Constant.id, buildings.get(position).getId());
		}

		intent.putExtra("content", targetList.get(position));

		setResult(-1, intent);
		finish();
	}

}
