package com.ffbao.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.activity.R.layout;
import com.ffbao.activity.R.menu;
import com.ffbao.entity.EditReportedInfo;
import com.ffbao.entity.ReportedInfo;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;
//import com.ffbao.view.wheelview.NumericWheelAdapter;
//import com.ffbao.view.wheelview.OnWheelChangedListener;
//import com.ffbao.view.wheelview.WheelView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
@Deprecated
public class EditActivity extends BaseActivity implements OnClickListener {
	private static int THINK_CITY_COUNT = 0;
	private static int ASSET_COUNT = 0;
	private static int BUT_HOUSES_USE_COUNT = 0;
	private static int BUY_POSSIBILITY_COUNT = 0;
	private static int THINK_INTENTION_COUNT = 0;
	private static int BUY_KIND_COUNT = 0;
	private static int GO_MODE_COUNT = 0;
	// private static int GO_JOURNEY_COUNT = 0;
	private static int BUY_FLY_COUNT = 0;
	private static int BUY_HOTEL_COUNT = 0;

	private static int SEKEST_SEX = 0;

	private static int START_YEAR = 1990, END_YEAR = 2100;
	
	private static Boolean BOO = true;
	
	// ,出行方式,行程状态,是否需要定购机票,是否需要定购酒店
	private Switch go_mode_switch, can_buy_fly_switch, can_buy_hotel_switch;
	private TextView go_mode, journey, can_buy_fly, can_buy_hotel, go_buff,
			phone;

	private Dialog dialog;
	private Button houses_submit;
	// 意向城市,资产情况,购房用途,购房可能性,意向楼盘,类型,出发日期,到达日期
	private TextView city_intention, asset, buyUse, buy_possibility,
			houses_intention, buy_kind, go_day, arrive_day;
	// 客户姓名,客户电话,国家,所在城市,职业,年龄
	private EditText name, country, city, job, age;
	// 联系地址
	private EditText address;
	// 预算,出行人数
	private EditText buy_budget, people_count;
	private RadioGroup mRadioGroup, go_buy_fly, go_buy_hotel;
	// 性别: 男 女
	private RadioButton man, woman, buy_fly0, buy_fly1, buy_fly2, buy_hotel0,
			buy_hotel1, buy_hotel2;
	private ReportedInfo houses = new ReportedInfo();

	private View view = null;
	private ListView listView = null;
	private ArrayAdapter<String> aa = null;
	private Dialog alertDialog = null;

	private Intent intent = null;

	private EditReportedInfo editReportedInfo;

	private String edit_name;
	private String edit_phone;
	private String edit_country;
	private String edit_city;
	private String edit_job;
	private String edit_age;
	private String edit_sex;
	private String edit_address;
	private String edit_budget;
	private String edit_headCount;
	private String edit_goMode;
	private String edit_buy_ticket;
	private String edit_buy_hotel;
	private String edit_go_day;
	private String edit_arrive_day;
	private String edit_think_city;
	private String edit_think_buliding;
	private String edit_possible;
	private String edit_trip_type;
	private String edit_purpose;
	private String edit_property;
	private String edit_type;
	private String edit_customerid;
	private String edit_type_plan;
	private String edit_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ffb_activity_edit);
		setActionBarTitle("编辑报备单");
		ExitActivity.addActivity(this);
		initView();
		inputData();
	}

	private void inputData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetReportDetail);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		intent = getIntent();
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		String reportid = intent.getStringExtra("reportID");
		System.out.println(reportid + "                 111111111111111");
		map.put(Constant.reportid, reportid);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				System.out.println(msg);
				editReportedInfo = ExecuteJSONUtils.getEditReportResult(
						EditActivity.this, msg);
				if (editReportedInfo != null) {
					edit_type_plan = editReportedInfo.getTripplanstatevalue();
					go_buff.setText(edit_type_plan);
					edit_state = editReportedInfo.getState();
					edit_customerid = editReportedInfo.getCustomerid();
					buy_kind.setText(editReportedInfo.getTypevalue());
					edit_type = editReportedInfo.getTypevalue();
					name.setText(editReportedInfo.getCustomername());
					edit_name = editReportedInfo.getCustomername();
					phone.setText(editReportedInfo.getCustomerphone());
					edit_phone = editReportedInfo.getCustomerphone();
					country.setText(editReportedInfo.getCountry());
					edit_country = editReportedInfo.getCountry();
					city.setText(editReportedInfo.getCity());
					edit_city = editReportedInfo.getCity();
					job.setText(editReportedInfo.getVocation());
					edit_job = editReportedInfo.getVocation();
					age.setText(editReportedInfo.getAge());
					edit_age = editReportedInfo.getAge();
					address.setText(editReportedInfo.getAddress());
					edit_address = editReportedInfo.getAddress();
					buy_budget.setText(editReportedInfo.getBudget());
					edit_budget = editReportedInfo.getBudget();
					people_count.setText(editReportedInfo.getHeadcount());
					edit_headCount = editReportedInfo.getHeadcount();
					go_mode.setText(editReportedInfo.getTrip_typevalue());
					edit_goMode = editReportedInfo.getTrip_typevalue();
					can_buy_fly.setText(editReportedInfo.getNeed_ticketvalue());
					edit_buy_ticket = editReportedInfo.getNeed_ticketvalue();
					can_buy_hotel.setText(editReportedInfo.getNeed_hotelvalue());
					edit_buy_hotel = editReportedInfo.getNeed_hotelvalue();
					if (editReportedInfo.getDeparture_date().equals("")) {
						// go_day.setText("请选择");
						edit_go_day = editReportedInfo.getDeparture_date();
					} else {
						go_day.setText(editReportedInfo.getDeparture_date());
						edit_go_day = editReportedInfo.getDeparture_date();
					}
					if (editReportedInfo.getArrival_date().equals("")) {
						// arrive_day.setText("请选择");
						edit_arrive_day = editReportedInfo.getArrival_date();
					} else {
						arrive_day.setText(editReportedInfo.getArrival_date());
						edit_arrive_day = editReportedInfo.getArrival_date();
					}
					go_buff.setText(editReportedInfo.getTripplanstatevalue());
					city_intention.setText(editReportedInfo
							.getIntention_cityvalue());
					edit_think_city = editReportedInfo.getIntention_cityvalue();
					edit_property = editReportedInfo.getPropertyvalue();
					if (editReportedInfo.getPropertyvalue().equals("")) {
						// asset.setText("请选择");
					} else {
						asset.setText(editReportedInfo.getPropertyvalue());
					}
					edit_purpose = editReportedInfo.getPurposevalue();
					if (editReportedInfo.getPurposevalue().equals("")) {
						// buyUse.setText("请选择");
					} else {
						buyUse.setText(editReportedInfo.getPurposevalue());
					}
					edit_possible = editReportedInfo.getPossibilitvalue();
					if (editReportedInfo.getPossibilitvalue().equals("")) {
						// buy_possibility.setText("请选择");
					} else {
						buy_possibility.setText(editReportedInfo
								.getPossibilitvalue());
					}
					edit_think_buliding = editReportedInfo
							.getIntention_buildingvalue();
					if (editReportedInfo.getIntention_buildingvalue()
							.equals("")) {
						// houses_intention.setText("请选择");
					} else {
						houses_intention.setText(editReportedInfo
								.getIntention_buildingvalue());
					}
					int sex = Integer.parseInt(editReportedInfo.getSex());
					edit_sex = editReportedInfo.getSex();
					if (sex == 0) {
						man.setChecked(true);
					} else {
						woman.setChecked(true);
					}
					int trip_type = Integer.parseInt(editReportedInfo
							.getTrip_type());
					edit_trip_type = editReportedInfo.getTrip_typevalue();
					if (trip_type == 1) {
						go_mode_switch.setChecked(true);
					} else {
						go_mode_switch.setChecked(false);
					}
					// THINK_CITY_COUNT = 0;
					// ASSET_COUNT =
					// Integer.parseInt(editReportedInfo.getProperty()); //资产
					// BUT_HOUSES_USE_COUNT =
					// Integer.parseInt(editReportedInfo.getPurpose()); //用途
					// BUY_POSSIBILITY_COUNT =
					// Integer.parseInt(editReportedInfo.getPossibilit()); //可能性
					// THINK_INTENTION_COUNT =
					// Integer.parseInt(editReportedInfo.getIntention_building());
					// //意向楼盘
					// BUY_KIND_COUNT =
					// Integer.parseInt(editReportedInfo.getType()); //类型
					// GO_MODE_COUNT =
					// Integer.parseInt(editReportedInfo.getTrip_type()); //出行方式
					// BUY_FLY_COUNT =
					// Integer.parseInt(editReportedInfo.getNeed_ticket()); //订票
					// BUY_HOTEL_COUNT =
					// Integer.parseInt(editReportedInfo.getNeed_hotel()); //订酒店
				} else {
					Toast.makeText(EditActivity.this, "获取数据出问题了", 0).show();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void initView() {
		go_buff = (TextView) findViewById(R.id.edit_go_buff);
		go_buy_fly = (RadioGroup) findViewById(R.id.edit_radioGroup_buy_fly);
		go_buy_hotel = (RadioGroup) findViewById(R.id.edit_radioGroup_buy_hotel);
		buy_fly0 = (RadioButton) findViewById(R.id.edit_buy_fly0);
		buy_fly1 = (RadioButton) findViewById(R.id.edit_buy_fly1);
		buy_fly2 = (RadioButton) findViewById(R.id.edit_buy_fly2);
		buy_hotel0 = (RadioButton) findViewById(R.id.edit_buy_hotel0);
		buy_hotel1 = (RadioButton) findViewById(R.id.edit_buy_hotel1);
		buy_hotel2 = (RadioButton) findViewById(R.id.edit_buy_hotel2);
		mRadioGroup = (RadioGroup) findViewById(R.id.edit_radioGroup);
		man = (RadioButton) findViewById(R.id.edit_sex_man);
		woman = (RadioButton) findViewById(R.id.edit_sex_woman);
		name = (EditText) findViewById(R.id.edit_input_name);
		phone = (TextView) findViewById(R.id.edit_input_phone);
		city_intention = (TextView) findViewById(R.id.edit_city_intention);// 意向城市
		country = (EditText) findViewById(R.id.edit_country);
		city = (EditText) findViewById(R.id.edit_city);
		job = (EditText) findViewById(R.id.edit_job);
		age = (EditText) findViewById(R.id.edit_age);
		address = (EditText) findViewById(R.id.edit_address);// 联系地址
		asset = (TextView) findViewById(R.id.edit_asset);// 资产情况
		buyUse = (TextView) findViewById(R.id.edit_buy_use);// 购房用途
		buy_possibility = (TextView) findViewById(R.id.edit_buy_possibility);// 购房可能性
		houses_intention = (TextView) findViewById(R.id.edit_houses_intention);// 意向楼盘
		buy_budget = (EditText) findViewById(R.id.edit_buy_budget); // 预算
		buy_kind = (TextView) findViewById(R.id.edit_buy_kind); // 类型
		go_mode_switch = (Switch) findViewById(R.id.edit_go_mode_switch); // 出行方式
		// journey_switch = (Switch) findViewById(R.id.edit_journey_switch); //
		// 行程状态
		// can_buy_fly_switch = (Switch) findViewById(R.id.edit_buy_fly_switch);
		// // 是否需要定购机票
		// can_buy_hotel_switch = (Switch)
		// findViewById(R.id.edit_buy_hotel_switch); // 是否需要定购酒店
		go_mode = (TextView) findViewById(R.id.edit_go_mode); // 出行方式
		// journey = (TextView) findViewById(R.id.edit_journey); // 行程状态
		can_buy_fly = (TextView) findViewById(R.id.edit_buy_fly); // 是否需要定购机票
		can_buy_hotel = (TextView) findViewById(R.id.edit_buy_hotel); // 是否需要定购酒店
		people_count = (EditText) findViewById(R.id.edit_people_count); // 出行人数
		go_day = (TextView) findViewById(R.id.edit_go_day);// 出发日期
		arrive_day = (TextView) findViewById(R.id.edit_arrive_day);// 到达日期
		houses_submit = (Button) findViewById(R.id.edit_houses_submit);
		city_intention.setOnClickListener(this);
		asset.setOnClickListener(this);
		buyUse.setOnClickListener(this);
		buy_possibility.setOnClickListener(this);
		houses_intention.setOnClickListener(this);
		buy_kind.setOnClickListener(this);
		houses_submit.setOnClickListener(this);
		go_day.setOnClickListener(this);
		arrive_day.setOnClickListener(this);
		go_buy_fly.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.edit_buy_fly0:
					can_buy_fly.setText("待定");
					break;
				case R.id.edit_buy_fly1:
					can_buy_fly.setText("需要");
					break;
				case R.id.edit_buy_fly2:
					can_buy_fly.setText("不需要");
					break;
				}
			}
		});

		go_buy_hotel.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.edit_buy_hotel0:
					can_buy_hotel.setText("待定");
					break;
				case R.id.edit_buy_hotel1:
					can_buy_hotel.setText("需要");
					break;
				case R.id.edit_buy_hotel2:
					can_buy_hotel.setText("不需要");
					break;
				}
			}
		});
		go_mode_switch
				.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							go_mode.setText("自定义行程");
						} else {
							go_mode.setText("房房宝安排");
						}
					}

				});
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.edit_sex_man:
					SEKEST_SEX = 0;
					man.setChecked(true);
					break;
				case R.id.edit_sex_woman:
					SEKEST_SEX = 1;
					woman.setChecked(true);
					break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_city_intention:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, cityData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("意向城市").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = cityData().get(arg2);
					city_intention.setText(str);
					THINK_CITY_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_asset:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, assetData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("资产情况").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = assetData().get(arg2);
					asset.setText(str);
					ASSET_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_buy_use:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, buyHousesData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("购房用途").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = buyHousesData().get(arg2);
					buyUse.setText(str);
					BUT_HOUSES_USE_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_buy_possibility:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, canBuyHousesData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("购房可能性").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = canBuyHousesData().get(arg2);
					buy_possibility.setText(str);
					BUY_POSSIBILITY_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_houses_intention:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, housesIntentionData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("意向楼盘").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = housesIntentionData().get(arg2);
					houses_intention.setText(str);
					THINK_INTENTION_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_buy_kind:
			view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.item_recommend_guest, null);
			listView = (ListView) view
					.findViewById(R.id.recommend_guest_listView);
			aa = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, buyKindData());
			listView.setAdapter(aa);
			alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("类型").setView(view).create();
			alertDialog.show();
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String str = buyKindData().get(arg2);
					buy_kind.setText(str);
					BUY_KIND_COUNT = arg2 + 1;
					alertDialog.dismiss();
				}
			});
			break;
		case R.id.edit_go_day:
			showDateTimePicker(go_day);
			break;
		case R.id.edit_arrive_day:
			showDateTimePicker(arrive_day);
			break;
		case R.id.edit_houses_submit:
			String soName = name.getText().toString();
			String soPhone = phone.getText().toString();
			String soCity = city_intention.getText().toString();
			if (soName.equals("")) {
				Toast.makeText(EditActivity.this, "用户名不能为空", 0).show();
			} else if (soPhone.equals("") || soPhone.length() != 11) {
				Toast.makeText(EditActivity.this, "电话不能为空或者电话格式不正确", 0).show();
			} else if (soCity.equals("请选择")) {
				Toast.makeText(EditActivity.this, "请选择意向城市", 0).show();
			} else if (buy_kind.getText().toString().equals("请选择")) {
				Toast.makeText(EditActivity.this, "请选择出行类型", 0).show();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(Constant.commandText, UrlUtil.userUpdateReportListl);
				map.put(Constant.userid, SharedPrefConstance.getStringValue(
						this, SharedPrefConstance.userid));
				map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
						SharedPrefConstance.UUID));
				map.put("customerid", edit_customerid);
				if (edit_name.equals(name.getText().toString())) {
				} else {
					map.put("customername", name.getText().toString());
					Toast.makeText(this, "11111", 1).show();
				}
				if (edit_phone.equals(phone.getText().toString())) {
				} else {
					String ss = phone.getText().toString().replace(" ", "");
					map.put("customerphone", ss);
				}
				if (edit_country.equals(country.getText().toString())) {
				} else {
					map.put("country", country.getText().toString());
				}
				if (edit_city.equals(city.getText().toString())) {
				} else {
					map.put("city", city.getText().toString());
				}
				if (edit_age.equals(age.getText().toString())) {
				} else {
					map.put("age", age.getText().toString());
				}
				if (edit_job.equals(job.getText().toString())) {
				} else {
					map.put("vocation", job.getText().toString());
				}
				if (edit_property.equals(asset.getText().toString())) {
				} else {
					String str_asset_count = String.valueOf(ASSET_COUNT);
					map.put("property", str_asset_count);
				}
				if (edit_address.equals(address.getText().toString())) {
				} else {
					map.put("address", address.getText().toString());
				}
				if (edit_type.equals(buy_kind.getText().toString())) {
				} else {
					String str_buy_kind_count = String.valueOf(BUY_KIND_COUNT);
					map.put("type", str_buy_kind_count);
				}
				if (edit_think_city.equals(city_intention.getText().toString())) {
				} else {
					map.put("intention_city", city_intention.getText()
							.toString());
				}
				String str_selest_sex = String.valueOf(SEKEST_SEX);
				if (edit_sex.equals(str_selest_sex)) {
				} else {
					if (str_selest_sex == "0") {
						map.put("sex", str_selest_sex);
					} else {
						map.put("sex", str_selest_sex);
					}
				}
				if (edit_think_buliding.equals(houses_intention.getText()
						.toString())) {
				} else {
					String str_think_intention_count = String
							.valueOf(THINK_INTENTION_COUNT);
					map.put("intention_building", str_think_intention_count);
				}
				if (edit_budget.equals(buy_budget.getText().toString())) {
				} else {
					map.put("budget", buy_budget.getText().toString());
				}
				if (edit_purpose.equals(buyUse.getText().toString())) {
				} else {
					String str_but_houses_use_count = String
							.valueOf(BUT_HOUSES_USE_COUNT);
					map.put("property", str_but_houses_use_count);
				}
				if (edit_possible.equals(buy_possibility.getText().toString())) {
				} else {
					String str_buy_possibility_count = String
							.valueOf(BUY_POSSIBILITY_COUNT);
					map.put("possibility", str_buy_possibility_count);
				}
				if (edit_trip_type.equals(go_mode.getText().toString())) {
				} else {
					if (go_mode.getText().equals("房房宝安排")) {
						GO_MODE_COUNT = 0;
						String str_go_mode_count = String
								.valueOf(GO_MODE_COUNT);
						map.put("trip_type", str_go_mode_count);
					} else {
						GO_MODE_COUNT = 1;
						String str_go_mode_count = String
								.valueOf(GO_MODE_COUNT);
						map.put("trip_type", str_go_mode_count);
					}
				}
				if (edit_buy_ticket.equals(can_buy_fly.getText().toString())) {
				} else {
					if (can_buy_fly.getText().equals("待定")) {
						BUY_FLY_COUNT = 0;
						String str_buy_fly_count = String
								.valueOf(BUY_FLY_COUNT);
						map.put("need_ticket", str_buy_fly_count);
					} else if (can_buy_fly.getText().equals("需要")) {
						BUY_FLY_COUNT = 1;
						String str_buy_fly_count = String
								.valueOf(BUY_FLY_COUNT);
						map.put("need_ticket", str_buy_fly_count);
					} else {
						BUY_FLY_COUNT = 2;
						String str_buy_fly_count = String
								.valueOf(BUY_FLY_COUNT);
						map.put("need_ticket", str_buy_fly_count);
					}
				}
				if (edit_buy_hotel.equals(can_buy_hotel.getText().toString())) {
				} else {
					if (can_buy_hotel.getText().equals("待定")) {
						BUY_HOTEL_COUNT = 0;
						String str_buy_hotel_count = String
								.valueOf(BUY_HOTEL_COUNT);
						map.put("need_hotel", str_buy_hotel_count);
					} else if (can_buy_hotel.getText().equals("需要")) {
						BUY_HOTEL_COUNT = 1;
						String str_buy_hotel_count = String
								.valueOf(BUY_HOTEL_COUNT);
						map.put("need_hotel", str_buy_hotel_count);
					} else {
						BUY_HOTEL_COUNT = 2;
						String str_buy_hotel_count = String
								.valueOf(BUY_HOTEL_COUNT);
						map.put("need_hotel", str_buy_hotel_count);
					}
				}
				if (edit_headCount.equals(people_count.getText().toString())) {
				} else {
					map.put("headcount", people_count.getText().toString());
				}
				if (edit_go_day.equals(go_day.getText().toString())) {
				} else {
					map.put("departure_date", go_day.getText().toString());
				}
				if (edit_arrive_day.equals(arrive_day.getText().toString())) {
				} else {
					if (go_day.getText().toString().equals("")) {
						map.put("arrival_date", arrive_day.getText().toString());
					} else if (judgeTime(go_day.getText().toString(),
							arrive_day.getText().toString())) {
						map.put("arrival_date", arrive_day.getText().toString());
					} else {
						Toast.makeText(this, "出发日期不能大于到达日期", 1).show();
					}
				}

				intent = getIntent();
				String reportid = intent.getStringExtra("reportID");
				map.put(Constant.reportid, reportid);
				Toast.makeText(this, map + "", 1).show();
				System.out.println(map);
				if(BOO){
					HttpClientRequest.getHttpPost(this, map,
							new CallBackListener() {
						
						@Override
						public void onSuccess(String msg) {
							System.out.println(msg);
							Boolean boo = ExecuteJSONUtils
									.getAddReportResult(EditActivity.this,
											msg);
							if (boo) {
								finish();
								Toast.makeText(EditActivity.this,
										"报备单修改成功", 0).show();
							} else {
								Toast.makeText(EditActivity.this,
										"报备单修改失败", 0).show();
							}
						}
						
						@Override
						public void onFailure(Exception error, String msg) {
							
						}
					});
					BOO = false;
				}

				map = null;
			}
			break;
		}
	}

	private void showDateTimePicker(TextView arrive_day2) {
		
	}

	private static boolean judgeTime(String old, String now) {
		String s1 = old;
		String s2 = now;
		java.text.DateFormat df = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		if (result == 0) {
			System.out.println("c1相等c2");
		} else if (result < 0) {
			System.out.println("c1小于c2");
			return true;
		} else {
			System.out.println("c1大于c2");
			return false;
		}
		return false;
	}

	/**
	 * 弹出日期时间选择器
	 */
//	private void showDateTimePicker(final Object obj) {
//	private void showDateTimePicker(final Object obj) {
//		Calendar calendar = Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//		int day = calendar.get(Calendar.DATE);
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar.get(Calendar.MINUTE);
//
//		// 添加大小月月份并将其转换为list,方便之后的判断
//		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
//		String[] months_little = { "4", "6", "9", "11" };
//
//		final List<String> list_big = Arrays.asList(months_big);
//		final List<String> list_little = Arrays.asList(months_little);
//
//		dialog = new Dialog(this);
//		dialog.setTitle("请选择日期与时间");
//		// 找到dialog的布局文件
//		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//		View view = inflater.inflate(R.layout.time_layout, null);
//
//		// 年
//		final WheelView wv_year = (WheelView) view.findViewById(R.id.year);
//		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
//		wv_year.setCyclic(true);// 可循环滚动
//		wv_year.setLabel("年");// 添加文字
//		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
//
//		// 月
//		final WheelView wv_month = (WheelView) view.findViewById(R.id.month);
//		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
//		wv_month.setCyclic(true);
//		wv_month.setLabel("月");
//		wv_month.setCurrentItem(month);
//
//		// 日
//		final WheelView wv_day = (WheelView) view.findViewById(R.id.day);
//		wv_day.setCyclic(true);
//		// 判断大小月及是否闰年,用来确定"日"的数据
//		if (list_big.contains(String.valueOf(month + 1))) {
//			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//		} else if (list_little.contains(String.valueOf(month + 1))) {
//			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//		} else {
//			// 闰年
//			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
//				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//			else
//				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//		}
//		wv_day.setLabel("日");
//		wv_day.setCurrentItem(day - 1);
//
//		// 时
//		final WheelView wv_hours = (WheelView) view.findViewById(R.id.hour);
//		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
//		wv_hours.setCyclic(true);
//		wv_hours.setCurrentItem(hour);
//
//		// 分
//		final WheelView wv_mins = (WheelView) view.findViewById(R.id.mins);
//		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
//		wv_mins.setCyclic(true);
//		wv_mins.setCurrentItem(minute);
//
//		// 添加"年"监听
//		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
//			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				int year_num = newValue + START_YEAR;
//				// 判断大小月及是否闰年,用来确定"日"的数据
//				if (list_big
//						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//				} else if (list_little.contains(String.valueOf(wv_month
//						.getCurrentItem() + 1))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//				} else {
//					if ((year_num % 4 == 0 && year_num % 100 != 0)
//							|| year_num % 400 == 0)
//						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//					else
//						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//				}
//			}
//		};
//		// 添加"月"监听
//		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
//			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				int month_num = newValue + 1;
//				// 判断大小月及是否闰年,用来确定"日"的数据
//				if (list_big.contains(String.valueOf(month_num))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//				} else if (list_little.contains(String.valueOf(month_num))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//				} else {
//					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
//							.getCurrentItem() + START_YEAR) % 100 != 0)
//							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
//						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//					else
//						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//				}
//			}
//		};
//		wv_year.addChangingListener(wheelListener_year);
//		wv_month.addChangingListener(wheelListener_month);
//
//		// 根据屏幕密度来指定选择器字体的大小
//		int textSize = 0;
//
//		textSize = 15;
//
//		wv_day.TEXT_SIZE = textSize;
//		wv_hours.TEXT_SIZE = textSize;
//		wv_mins.TEXT_SIZE = textSize;
//		wv_month.TEXT_SIZE = textSize;
//		wv_year.TEXT_SIZE = textSize;
//
//		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
//		Button btn_cancel = (Button) view
//				.findViewById(R.id.btn_datetime_cancel);
//		// 确定
//		btn_sure.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// 如果是个数,则显示为"02"的样式
//				String parten = "00";
//				DecimalFormat decimal = new DecimalFormat(parten);
//				((TextView) obj).setText(wv_year.getCurrentItem() + START_YEAR
//						+ "-" + (wv_month.getCurrentItem() + 1) + "-"
//						+ (wv_day.getCurrentItem() + 1) + " "
//						+ wv_hours.getCurrentItem() + ":"
//						+ wv_mins.getCurrentItem() + ":" + "00");
//				// 设置日期的显示
//				// tv_time.setText((wv_year.getCurrentItem() + START_YEAR) + "-"
//				// + decimal.format((wv_month.getCurrentItem() + 1)) + "-"
//				// + decimal.format((wv_day.getCurrentItem() + 1)) + " "
//				// + decimal.format(wv_hours.getCurrentItem()) + ":"
//				// + decimal.format(wv_mins.getCurrentItem()));
//
//				dialog.dismiss();
//			}
//		});
//		// 取消
//		btn_cancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dialog.dismiss();
//			}
//		});
//		// 设置dialog的布局,并显示
//		dialog.setContentView(view);
//		dialog.show();
//	}

	private List<String> cityData() {
		List<String> list = new ArrayList<String>();
		list.add("北京");
		list.add("海南");
		list.add("云南");
		list.add("山东");
		return list;
	}

	private List<String> assetData() {
		List<String> list = new ArrayList<String>();
		list.add("一百万以下");
		list.add("一百万-五百万");
		list.add("五百万-一千万");
		list.add("一千万到一亿");
		list.add("一亿以上");
		return list;

	}

	private List<String> buyHousesData() {
		List<String> list = new ArrayList<String>();
		list.add("为子女买房");
		list.add("为老人买房");
		list.add("首次购房自住");
		list.add("改善性住房");
		list.add("投资");
		list.add("休闲度假养老");
		return list;
	}

	private List<String> canBuyHousesData() {
		List<String> list = new ArrayList<String>();
		list.add("很想买房且有实力");
		list.add("可买可不买且有实力");
		list.add("很想买房且实力一般");
		list.add("可买可不买且实力一般");
		list.add("暂时不想买房");
		return list;
	}

	private List<String> housesIntentionData() {
		List<String> list = new ArrayList<String>();
		list.add("君安世纪城");
		list.add("锦江福田花园");
		return list;
	}

	private List<String> buyKindData() {
		List<String> list = new ArrayList<String>();
		list.add("外地送往本地");
		list.add("本地");
		return list;
	}

}