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

import cn.sharesdk.onekeyshare.CustomerLogo;

import com.ffbao.activity.R;
import com.ffbao.entity.Building;
import com.ffbao.entity.ReportDetails;
import com.ffbao.entity.ReportedInfo;
import com.ffbao.entity.WantCity;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.widget.time.JudgeDate;
import com.ffbao.ui.widget.time.ScreenInfo;
import com.ffbao.ui.widget.time.WheelMain;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.AddCustomerUtils;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
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
public class CustomTeamBuyActivity extends BaseActivity implements
		OnClickListener {

	private static int GO_MODE_COUNT = 0;
	private static int BUY_FLY_COUNT = 0;
	private static int BUY_HOTEL_COUNT = 0;

	// private static Boolean BOO = true;

	@ID(resId = R.id.recommended_input_name)
	private EditText name;// recommended_input_name

	@ID(resId = R.id.recommended_input_phone)
	private EditText phone;// recommended_input_phone

	@ID(resId = R.id.recommended_city_intention)
	private TextView city_intention;// recommended_city_intention

	@ID(resId = R.id.recommended_buy_kind)
	private TextView buy_kind;// recommended_buy_kind

	@ID(resId = R.id.recommended_remark)
	private EditText remark;// recommended_remark

	@ID(resId = R.id.recommended_houses_intention)
	private TextView houses_intention;// recommended_houses_intention

	@ID(resId = R.id.recommended_city)
	private TextView city;// recommended_city

	@ID(resId = R.id.recommended_job)
	private EditText job;// recommended_job

	@ID(resId = R.id.recommended_age)
	private EditText age;// recommended_age

	@ID(resId = R.id.recommended_sex)
	private TextView sex;// recommended_sex

	@ID(resId = R.id.recommended_address)
	private EditText address;// recommended_address

	@ID(resId = R.id.recommended_asset)
	private TextView asset;// recommended_asset

	@ID(resId = R.id.recommended_buy_use)
	private TextView buyUse;// recommended_buy_use

	@ID(resId = R.id.recommended_buy_possibility)
	private TextView buy_possibility;// recommended_buy_possibility

	@ID(resId = R.id.recommended_country)
	private EditText country;// recommended_country

	@ID(resId = R.id.recommended_buy_budget)
	private EditText buy_budget;// recommended_buy_budget

	@ID(resId = R.id.recommended_people_count)
	private EditText people_count;// recommended_people_count

	@ID(resId = R.id.recommended_go_day)
	private TextView go_day;// recommended_go_day

	@ID(resId = R.id.recommended_arrive_day)
	private TextView arrive_day;// recommended_arrive_day

	@ID(resId = R.id.recommended_go_mode)
	private TextView go_mode;// recommended_go_mode

	@ID(resId = R.id.recommended_buy_fly)
	private TextView can_buy_fly;// recommended_buy_fly

	@ID(resId = R.id.recommended_buy_hotel)
	private TextView can_buy_hotel;// recommended_buy_hotel

	@ID(resId = R.id.houses_submit)
	private Button houses_submit;

	@ID(resId = R.id.scroll_view)
	private View parentView;

//	private CombinationControlsView combination;
//	List<CombinationBean> beans = new ArrayList<CombinationBean>();
	private String reportID;

	private int positionCity = 0;

	private ReportDetails report;

	private List<WantCity> wantCitys = null;
	private List<Building> buildings = null;

	private String wantCity_id = "";
	private String wantCity_level = "";
	private String building_id = "";
	private RichfitAlertDialog dialog1;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_custom_team_buying);
		ExitActivity.addActivity(this);
		initView();
		initData();
		// getIntentCityArray();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:初始化数据
	 */
	private void initData() {

		Intent intent = getIntent();
		if (intent != null) {
			String phone_name = intent.getStringExtra("name");
			String phone_number = intent.getStringExtra("number");

			if (phone_name != null) {
				name.setText(phone_name.trim());
			} else {
				name.setText("");
			}
			if (phone_number != null) {
				phone.setText(phone_number.trim());
			} else {
				phone.setText("");
			}
			reportID = intent.getStringExtra("reportID");
			if (reportID != null) {
				getReportDetail(reportID);
				setActionBarTitle("编辑报备");
				phone.setEnabled(false);
			} else {
				setActionBarTitle("添加报备");
				Bundle bundle = intent.getExtras();
				if (bundle != null) {

					wantCity_id = bundle.getString("wantCity_id", "");
					wantCity_level = bundle.getString("wantCity_level", "");
					building_id = bundle.getString("building_id", "");
					String wantCity_content = bundle.getString(
							"wantCity_content", "");
					String building_content = bundle.getString(
							"building_content", "");
					city_intention.setText(wantCity_content);
					houses_intention.setText(building_content);

				}

			}
		}
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param reportID
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:访问接口 获取报备单相亲接口
	 */
	private void getReportDetail(String reportID) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetReportDetail);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportId, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				report = ExecuteJSONUtils.getReportDtails(
						CustomTeamBuyActivity.this, msg);

				if (report != null) {

					name.setText(report.getCustomername());
					phone.setText(report.getCustomerphone());

					city_intention.setText(report.getIntention_cityvalue());
					buy_kind.setText(report.getTypevalue());
					if (StringUtils.isNull(report.getIntention_buildingvalue())) {

						houses_intention.setText(report
								.getIntention_buildingvalue());
					} else {
						houses_intention.setText("请选择");
					}

					city.setText(report.getCity());
					job.setText(report.getVocation());
					age.setText(report.getAge());
					sex.setText(report.getSexvalue());
					sex.setTag(report.getSex());
					address.setText(report.getAddress());
					if (StringUtils.isNull(report.getPossibilitvalue())) {
						buy_possibility.setText(report.getPossibilitvalue());
					} else {
						buy_possibility.setText(AddCustomerUtils
								.canBuyHousesData().get(0));
					}
					if (StringUtils.isNull(report.getPurposevalue())) {
						buyUse.setText(report.getPurposevalue());
					} else {
						buyUse.setText(AddCustomerUtils.buyHousesData().get(0));
					}
					if (StringUtils.isNull(report.getPropertyvalue())) {
						asset.setText(report.getProperty());
					} else {
						asset.setText(AddCustomerUtils.assetData().get(0));
					}
					asset.setTag(report.getProperty());
					country.setText(report.getCountry());
					buy_budget.setText(report.getBudget());
					people_count.setText(report.getHeadcount());
					String departureDate = report.getDeparture_date();
					if (departureDate != null && departureDate.length()!=0)
						go_day.setText(report.getDeparture_date());
					else
						go_day.setText("请选择出发日期");
					String arrivalDate = report.getArrival_date();
					if (arrivalDate != null && arrivalDate.length()!=0)
						arrive_day.setText(report.getArrival_date());
					else
						arrive_day.setText("请选择到达日期");
					go_mode.setText(report.getTrip_typevalue());
					can_buy_fly.setText(report.getNeed_ticketvalue());
					can_buy_hotel.setText(report.getNeed_hotelvalue());
					String level = report.getIntention_city_level();
					// if (level != null && !level.isEmpty()) {
					// getIntentBuildingArray(
					// report.getIntention_city_level(),
					// report.getIntention_city());
					// }
					wantCity_id = report.getIntention_city();
					wantCity_level = report.getIntention_city_level();
					building_id = report.getIntention_building();

					remark.setText(report.getRemark());
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:设置界面需要 listener
	 */
	private void initView() {

//		combination = (CombinationControlsView) findViewById(R.id.combination);
		ViewHelper.init(this);

		buy_kind.setOnClickListener(this);
		houses_intention.setOnClickListener(this);
		city_intention.setOnClickListener(this);
		asset.setOnClickListener(this);
		buyUse.setOnClickListener(this);
		buy_possibility.setOnClickListener(this);
		go_day.setOnClickListener(this);
		arrive_day.setOnClickListener(this);
		houses_submit.setOnClickListener(this);
		sex.setOnClickListener(this);
		can_buy_hotel.setOnClickListener(this);
		can_buy_fly.setOnClickListener(this);
		go_mode.setOnClickListener(this);
		parentView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				dismissPopup(v);
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {

		dismissPopup(v);
		Intent intent = new Intent(this, CustomAddSelectActivity.class);
		Bundle bundle = new Bundle();
		switch (v.getId()) {
		case R.id.custom_addAndEdit:

			parentView.setFocusable(true);
			parentView.setFocusableInTouchMode(true);
			parentView.requestFocus();
			break;
		case R.id.recommended_sex:

			bundle.putInt(Constant.requestCode, Constant.recommended_sex);
			bundle.putString(Constant.target, sex.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_sex);
			break;
		case R.id.recommended_buy_hotel:

			bundle.putInt(Constant.requestCode, Constant.recommended_buy_hotel);
			bundle.putString(Constant.target, can_buy_hotel.getText()
					.toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_buy_hotel);
			break;
		case R.id.recommended_buy_fly:

			bundle.putInt(Constant.requestCode, Constant.recommended_buy_fly);
			bundle.putString(Constant.target, can_buy_fly.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_buy_fly);
			break;
		case R.id.recommended_go_mode:

			bundle.putInt(Constant.requestCode, Constant.recommended_go_mode);
			bundle.putString(Constant.target, go_mode.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_go_mode);
			break;
		case R.id.recommended_city_intention:

			bundle.putInt(Constant.requestCode,
					Constant.recommended_city_intention);
			bundle.putString(Constant.target, city_intention.getText()
					.toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_city_intention);
			break;
		case R.id.recommended_asset:

			bundle.putInt(Constant.requestCode, Constant.recommended_asset);
			bundle.putString(Constant.target, asset.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_asset);
			break;
		case R.id.recommended_buy_use:

			bundle.putInt(Constant.requestCode, Constant.recommended_buy_use);
			bundle.putString(Constant.target, buyUse.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_buy_use);
			break;
		case R.id.recommended_buy_possibility:
			bundle.putInt(Constant.requestCode,
					Constant.recommended_buy_possibility);
			bundle.putString(Constant.target, buy_possibility.getText()
					.toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_buy_possibility);
			break;
		case R.id.recommended_houses_intention:

			if (StringUtils.isNull(wantCity_id)
					&& StringUtils.isNull(wantCity_level)) {
				bundle.putInt(Constant.requestCode,
						Constant.recommended_houses_intention);
				bundle.putString(Constant.target, houses_intention.getText()
						.toString());
				bundle.putString(Constant.id, wantCity_id);
				bundle.putString(Constant.level, wantCity_level);
				intent.putExtras(bundle);
				startActivityForResult(intent,
						Constant.recommended_houses_intention);
			} else {
				ToastUtils.showToast(context, "请选择意向城市");
			}
			break;
		case R.id.recommended_buy_kind:

			bundle.putInt(Constant.requestCode, Constant.recommended_buy_kind);
			bundle.putString(Constant.target, buy_kind.getText().toString());
			intent.putExtras(bundle);
			startActivityForResult(intent, Constant.recommended_buy_kind);
			break;
		case R.id.houses_submit:
			OperaterCustomer();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		super.onActivityResult(requestCode, responseCode, intent);
		String content = "";
		if (intent != null) {
			content = intent.getStringExtra("content");
		}
		if (!StringUtils.isNull(content)) {
			return;
		}
		switch (requestCode) {
		case Constant.recommended_sex:
			sex.setText(content);
			break;
		case Constant.recommended_buy_hotel:
			can_buy_hotel.setText(content);
			break;
		case Constant.recommended_buy_fly:
			can_buy_fly.setText(content);
			break;
		case Constant.recommended_go_mode:
			go_mode.setText(content);
			break;
		case Constant.recommended_city_intention:

			city_intention.setText(content);
			String wantCityid = intent.getStringExtra(Constant.id);
			String wantCityLevel = intent.getStringExtra(Constant.level);

			if (wantCity_id.equals(wantCityid)
					&& wantCity_level.equals(wantCityLevel)) {

			} else {
				building_id = "";
				houses_intention.setText("请选择");
			}
			if (StringUtils.isNull(wantCityid))
				wantCity_id = wantCityid;
			if (StringUtils.isNull(wantCityLevel))
				wantCity_level = wantCityLevel;

			break;
		case Constant.recommended_asset:
			asset.setText(content);
			break;
		case Constant.recommended_buy_use:

			buyUse.setText(content);
			break;
		case Constant.recommended_buy_possibility:

			buy_possibility.setText(content);
			break;
		case Constant.recommended_houses_intention:

			houses_intention.setText(content);
			String buildingID = intent.getStringExtra(Constant.id);
			if (StringUtils.isNull(buildingID))
				building_id = buildingID;
			break;
		case Constant.recommended_buy_kind:

			buy_kind.setText(content);
			break;
		default:
			break;
		}

	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:进行操作报备单添加或者编辑 ， 并且验证必需数据
	 */
	private void OperaterCustomer() {

		String soName = name.getText().toString().trim();
		String soPhone = phone.getText().toString().replace(" ", "").trim();
		String soCity = city_intention.getText().toString().trim();
		// if (!StringUtils.isNull(soName)) {
		//
		// Toast.makeText(CustomTeamBuyActivity.this, "用户名不能为空", 0).show();
		// return;
		// } else if (!StringUtils.isNull(soPhone)
		// && !StringUtils.isNumber(soPhone)) {
		// Toast.makeText(CustomTeamBuyActivity.this, "电话不能为空或者电话格式不正确",
		// 0).show();
		// return;
		// } else
		if ((soPhone.length() < 5 || soPhone.length() > 19)
				&& !StringUtils.isNumber(soPhone)) {
			Toast.makeText(CustomTeamBuyActivity.this, "电话格式不正确", 0).show();
			return;
			// } else if ("请选择".equals(soCity)) {
			// Toast.makeText(CustomTeamBuyActivity.this, "请选择意向城市", 0).show();
			// return;
			// } else if ("请选择".equals(buy_kind.getText().toString())) {
			// Toast.makeText(CustomTeamBuyActivity.this, "请选择类型", 0).show();
			// return;
			// } else if ("城市地产".equals(buy_kind.getText().toString())
			// && "请选择".equals(houses_intention.getText().toString())) {
			//
			// Toast.makeText(CustomTeamBuyActivity.this, "城市地产需要选择意向楼盘",
			// 0).show();
			// return;
		} else {

			if (!VerifyLength()) {
				return;
			}
			final Map<String, Object> map = new HashMap<String, Object>();

			setParam(map);

			if (arrive_day.getText().toString().equals("请选择到达日期")) {

				map.put("arrival_date", "");

			} else {

				if (go_day.getText().toString().equals("请选择出发日期")) {
					map.put("arrival_date", arrive_day.getText().toString());
				} else if (judgeTime(go_day.getText().toString(), arrive_day
						.getText().toString())) {
					map.put("arrival_date", arrive_day.getText().toString());
				} else {
					Toast.makeText(this, "出发日期不能大于到达日期", 1).show();
					return;
				}

			}

			selectReportState(map);

			if (!"请选择".equals(houses_intention.getText().toString())) {
				if (StringUtils.isNull(building_id)) {
					map.put("intention_buildingid", building_id);
				} else {
					ToastUtils.showToast(context, "意向楼盘需要重新选择");
					// map.put("intention_buildingid", "");
					return;
				}
			} else {
				map.put("intention_buildingid", "");
			}
			if (StringUtils.isNull(wantCity_id)
					&& StringUtils.isNull(wantCity_level)) {
				map.put("intention_city", wantCity_id);
				map.put("intention_city_level", wantCity_level);
			} else {
				ToastUtils.showToast(context, "意向城市需要重新选择");
				map.put("intention_city", "");
				map.put("intention_city_level", "");
				return;
			}
			// if (reportID != null) {
			// editCustomer(map);
			// } else {
			// if (!StringUtils.isPhone(soPhone)) {
			// notifyCoumterPhone(map);
			// return;
			// }
			// addCustomer(map);
			// }

			groupPurchase(map);
		}
	}

	/**
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-24
	 * @author:lee
	 * @Funtion:团购
	 */
	private void groupPurchase(Map<String, Object> map) {

		map.put(Constant.commandText, UrlUtil.userGroupPurchase);

//		map.put("intention_city", wantCity_id);
//		map.put("intention_city_level", wantCity_level);
//		map.put("intention_buildingid", building_id);
//		map.put("customername", name.getText().toString());
//		map.put("customerphone", phone.getText().toString());
		map.put(Constant.cityid, map.get("intention_city"));
		map.put(Constant.buildingid, map.get("intention_buildingid"));
		map.put(Constant.name, map.get("customername"));
		map.put(Constant.sex, map.get("sex"));
		map.put(Constant.phone, map.get("customerphone"));

		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.simpleUpdateInfo(context, msg)) {

					ToastUtils.showToast(context, "团购成功");
					finish();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}

	/**
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 非必需验证数据 （选择）
	 */
	private void selectReportState(final Map<String, Object> map) {

		Object tag2 = sex.getTag();
		if (tag2 != null) {
			int str_selest_sex = Integer.valueOf(tag2.toString());
			if (str_selest_sex != -1) {
				map.put("sex", str_selest_sex);
			} else {
				map.put("sex", 0);
			}
		} else {
			map.put("sex", 0);
		}

		if (asset.getText().toString() != null
				&& asset.getText().toString().length()!=0) {

			map.put("property",
					getPosition(AddCustomerUtils.assetData(), asset.getText()
							.toString()));
		} else {
			map.put("property", "0");
		}

		if (buyUse.getText().toString() != null
				&& buyUse.getText().toString().length()!=0) {
			map.put("purpose",
					getPosition(AddCustomerUtils.buyHousesData(), buyUse
							.getText().toString()));
		} else {
			map.put("purpose", "0");
		}

		if (buy_possibility.getText().toString() != null
				&& buy_possibility.getText().toString().length()!=0) {
			map.put("possibility",
					getPosition(AddCustomerUtils.canBuyHousesData(),
							buy_possibility.getText().toString()));
		} else {
			map.put("possibility", "0");
		}

		if (go_mode.getText().equals("房房宝安排")) {
			GO_MODE_COUNT = 0;
			String go1 = String.valueOf(GO_MODE_COUNT);
			map.put("trip_type", go1);
		} else {
			GO_MODE_COUNT = 1;
			String go2 = String.valueOf(GO_MODE_COUNT);
			map.put("trip_type", go2);
		}

		if (can_buy_fly.getText().equals("待定")) {
			BUY_FLY_COUNT = 0;
			String str_buy_fly_count = String.valueOf(BUY_FLY_COUNT);
			map.put("need_ticket", str_buy_fly_count);
		} else if (can_buy_fly.getText().equals("需要")) {
			BUY_FLY_COUNT = 1;
			String str_buy_fly_count = String.valueOf(BUY_FLY_COUNT);
			map.put("need_ticket", str_buy_fly_count);
		} else {
			BUY_FLY_COUNT = 2;
			String str_buy_fly_count = String.valueOf(BUY_FLY_COUNT);
			map.put("need_ticket", str_buy_fly_count);
		}

		if (can_buy_hotel.getText().equals("待定")) {
			BUY_HOTEL_COUNT = 0;
			String str_buy_hotel_count = String.valueOf(BUY_HOTEL_COUNT);
			map.put("need_hotel", str_buy_hotel_count);
		} else if (can_buy_hotel.getText().equals("需要")) {
			BUY_HOTEL_COUNT = 1;
			String str_buy_hotel_count = String.valueOf(BUY_HOTEL_COUNT);
			map.put("need_hotel", str_buy_hotel_count);
		} else {
			BUY_HOTEL_COUNT = 2;
			String str_buy_hotel_count = String.valueOf(BUY_HOTEL_COUNT);
			map.put("need_hotel", str_buy_hotel_count);
		}

	}

	/**
	 * 
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:填写报备信息
	 */
	private void setParam(final Map<String, Object> map) {

		map.put("customername", name.getText().toString());
		map.put("customerphone", phone.getText().toString());

		map.put("type",
				getPosition(AddCustomerUtils.buyKindData(), buy_kind.getText()
						.toString()));

		map.put("city", city.getText().toString());

		map.put("vocation", job.getText().toString());

		map.put("age", age.getText().toString().trim());

		map.put("address", address.getText().toString());

		map.put("country", country.getText().toString());

		map.put("budget", buy_budget.getText().toString().trim());

		map.put("headcount", people_count.getText().toString().trim());

		if (go_day.getText().toString().equals("请选择出发日期")) {
			map.put("departure_date", "");
		} else {
			map.put("departure_date", go_day.getText().toString());
		}
		map.put("remark", remark.getText().toString().trim());
	}

	/**
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:客户号码 不是手机号 提示
	 */
	private void notifyCoumterPhone(final Map<String, Object> map) {
		if (dialog1 == null)
			dialog1 = new RichfitAlertDialog(context);
		if (!dialog1.isShow()) {
			dialog1.show();
			dialog1.setContent("客户电话号码不是手机号码，是否继续提交？");
			dialog1.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {

					dialog1.close();
				}
			});
			dialog1.setNegativeButton("确认", new OnClickListener() {

				@Override
				public void onClick(View v) {
					addCustomer(map);
					dialog1.close();
				}
			});
		}
	}

	/**
	 * @Deprecatred:
	 * @return
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:验证 手动输入数据长度
	 */
	private boolean VerifyLength() {
		String soName = name.getText().toString().trim();
		String soPhone = phone.getText().toString().replace(" ", "").trim();
		String soCountry = country.getText().toString();
		String soCity = city.getText().toString();
		String soVocation = job.getText().toString();
		String soAge = age.getText().toString();
		String soAddress = address.getText().toString();
		String soBuget = buy_budget.getText().toString();
		String sopeopleCount = people_count.getText().toString();
		String soRemark = remark.getText().toString();
		if (soName.length() > 10) {

			ToastUtils.showToast(context, "输入用户名超长");
			return false;
		}
		if (soPhone.length() > 20) {

			ToastUtils.showToast(context, "输入电话超长");
			return false;
		}
		if (soCountry.length() > 20) {

			ToastUtils.showToast(context, "输入国家超长");
			return false;
		}
		if (soCity.length() > 20) {

			ToastUtils.showToast(context, "输入城市超长");
			return false;
		}
		if (StringUtils.isNull(soAge)) {
			if (Integer.valueOf(soAge) > 120) {

				ToastUtils.showToast(context, "输入年龄不能超过120");
				return false;
			}
		}
		if (soVocation.length() > 20) {

			ToastUtils.showToast(context, "输入职业超长");
			return false;
		}
		if (soAddress.length() > 50) {

			ToastUtils.showToast(context, "输入地址超长");
			return false;
		}
		if (soBuget.length() > 20) {

			ToastUtils.showToast(context, "输入预算超长");
			return false;
		}
		if (sopeopleCount.length() > 10) {

			ToastUtils.showToast(context, "输入出行人数超长");
			return false;
		}
		if (soRemark.length() > 200) {

			ToastUtils.showToast(context, "备注信息超长");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 访问编辑报备接口
	 */
	private void editCustomer(Map<String, Object> map) {

		// TODO 手机号码，
		map.remove("customerphone");
		map.put(Constant.commandText, UrlUtil.userUpdateReportListl);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put("customerid", report.getCustomerid());
		map.put(Constant.reportid, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				// TODO Auto-generated method stub
				boolean boo = ExecuteJSONUtils.getAddReportResult(
						CustomTeamBuyActivity.this, msg);
				if (boo) {
					finish();
					// 需要刷新
					DisplayUtils.setUnRefresh();
					ToastUtils.showToast(context, "报备单编辑成功");
				} else {
					ToastUtils.showToast(context, "报备单编辑失败");
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	/**
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:访问添加报备单接口
	 */
	private void addCustomer(Map<String, Object> map) {

		map.put(Constant.commandText, UrlUtil.userAddReportList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put("companyid", SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.companyid));
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				boolean boo = ExecuteJSONUtils.getAddReportResult(
						CustomTeamBuyActivity.this, msg);
				if (boo) {
					finish();
					Toast.makeText(CustomTeamBuyActivity.this, "报备单添加成功", 0)
							.show();
					// 需要刷新
					DisplayUtils.setUnRefresh();
				} else {
					// Toast.makeText(CustomAddActivity.this,
					// "报备单添加失败", 0).show();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}

	private int getPosition(List<String> list, String tagert) {
		if (list != null && tagert != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(tagert)) {
					return i;
				}
			}
		}
		return 0;
	}

	/**
	 * @Deprecatred:
	 * @param start
	 * @param end
	 * @return
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:验证时间格式
	 */
	private static boolean judgeTime(String start, String end) {
		String format = null;

		if (start.length() > "yyyy-MM-dd HH:mm".length()) {

			format = "yyyy-MM-dd HH:mm:ss";
		} else {
			format = "yyyy-MM-dd HH:mm";
		}
		DateFormat df = new SimpleDateFormat(format, Locale.US);
		try {
			Date startDate = df.parse(start);
			Date endDate = df.parse(end);

			if (startDate.before(endDate)) {
				return true;
			}
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		return false;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param group
	 * @return
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取focuse 控件
	 */
	private View getFocusablesView(ViewGroup group) {
		for (int i = 0; i < group.getChildCount(); i++) {
			View childAt = group.getChildAt(i);
			if (childAt.isFocused()) {
				return childAt;
			}
			if (childAt instanceof ViewGroup) {
				View result = getFocusablesView((ViewGroup) childAt);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * @Deprecatred:
	 * @param v
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:隐藏弹出界面
	 */
	private void dismissPopup(View v) {
		if (v instanceof EditText) {
			EditText edit = (EditText) v;
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
		} else if (v instanceof TextView) {
			View view = getFocusablesView((ViewGroup) parentView);
			if (view instanceof EditText) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		} else if (v instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) v;
			View view = getFocusablesView(group);
			if (view != null && view instanceof EditText) {
				EditText edit = (EditText) view;
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
			}
		}
	}

}
