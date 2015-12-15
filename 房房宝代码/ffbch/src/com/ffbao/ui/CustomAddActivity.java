package com.ffbao.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.entity.CityListBean;
import com.ffbao.entity.ReportDetails;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.ListDialogAdapter;
import com.ffbao.ui.widget.time.JudgeDate;
import com.ffbao.ui.widget.time.ScreenInfo;
import com.ffbao.ui.widget.time.WheelMain;
import com.ffbao.util.AddCustomerUtils;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.ID;
import com.ffbao.util.MyListDialog;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.ViewHelper;

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
public class CustomAddActivity extends BaseActivity implements OnClickListener {

	private static int GO_MODE_COUNT = 0;
	private static int BUY_FLY_COUNT = 0;
	private static int BUY_HOTEL_COUNT = 0;


	@ID(resId = R.id.recommended_input_name)
	private EditText name;// recommended_input_name  姓名

	@ID(resId = R.id.recommended_input_phone)
	private EditText phone;// recommended_input_phone  电话

	@ID(resId = R.id.recommended_city_intention)
	private TextView city_intention;// recommended_city_intention  意向城市

	@ID(resId = R.id.recommended_buy_kind)
	private TextView buy_kind;// recommended_buy_kind  类型

	@ID(resId = R.id.recommended_remark)
	private EditText remark;// recommended_remark  备注

	@ID(resId = R.id.recommended_houses_intention)
	private TextView houses_intention;// recommended_houses_intention  意向楼盘

	@ID(resId = R.id.recommended_city)
	private TextView city;// recommended_city  所在城市

	@ID(resId = R.id.recommended_job)
	private EditText job;// recommended_job  职业

	@ID(resId = R.id.recommended_age)
	private EditText age;// recommended_age   年龄

	@ID(resId = R.id.recommended_sex)
	private TextView sex;// recommended_sex    男女

	@ID(resId = R.id.recommended_address)
	private EditText address;// recommended_address   联系地址

	@ID(resId = R.id.recommended_asset)
	private TextView asset;// recommended_asset   资产状况

	@ID(resId = R.id.recommended_buy_use)
	private TextView buyUse;// recommended_buy_use  购房用途

	@ID(resId = R.id.recommended_buy_possibility)
	private TextView buy_possibility;// recommended_buy_possibility  购房可能性

	@ID(resId = R.id.recommended_country)
	private TextView country;// recommended_country    所在国家：中国

	@ID(resId = R.id.recommended_buy_budget)
	private EditText buy_budget;// recommended_buy_budget  预算

	@ID(resId = R.id.recommended_people_count)
	private EditText people_count;// recommended_people_count   出行人数

	@ID(resId = R.id.recommended_go_day)
	private TextView go_day;// recommended_go_day   出发日期

	@ID(resId = R.id.recommended_arrive_day)
	private TextView arrive_day;// recommended_arrive_day  到达日期

	@ID(resId = R.id.recommended_go_mode)
	private TextView go_mode;// recommended_go_mode   出行方式

	@ID(resId = R.id.recommended_buy_fly)
	private TextView can_buy_fly;// recommended_buy_fly    是否需要订票

	@ID(resId = R.id.recommended_buy_hotel)
	private TextView can_buy_hotel;// recommended_buy_hotel   是否需要预定酒店

	@ID(resId = R.id.houses_submit)
	private Button houses_submit;   //提交按钮

	@ID(resId = R.id.scroll_view)
	private View parentView;       //整个view

	@ID(resId = R.id.timePicker)
	private View timepickerview;

	@ID(resId = R.id.withlook_type)
	private TextView withlook_type;  //新增拖带和自带方式，默认自带，必填项

	@ID(resId = R.id.province)
	private TextView province;   //所在省份


	private  MyListDialog listdialog;


	private RichfitAlertDialog dialog;

	/**
	 * 不包含按钮
	 */
	@ID(resId = R.id.timePicker1)
	private View timepickerview1;

	private String reportID;
	private ReportDetails report;
	//给出变量
	private String Myprovince="";
	private String Mycity="";
	private String Myprovinceid="";
	private String Mycityid="";
	private String wantCity_id = "";
	private String wantCity_level = "";
	private String building_id = "";
	private RichfitAlertDialog dialog1;

	private String state;//判断是否是从修改页面过来的

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_recommend_guest);
		ExitActivity.addActivity(this);
		initView();
		initData();
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
			//当不为null的时候，则是修改报备单
			if (reportID != null) {
				state=intent.getStringExtra("state");
				getReportDetail(reportID);
				setActionBarTitle("编辑报备");
				phone.setEnabled(false);
				province.setEnabled(false);
				city.setEnabled(false);
			}
			//当为null的时候则是添加一个新的报备单
			else {
				setActionBarTitle("添加报备");
				Intent intent2=getIntent();
				wantCity_id = intent2.getStringExtra("wantCity_id");
				wantCity_level = intent2.getStringExtra("wantCity_level");
				building_id = intent2.getStringExtra("building_id");
				String wantCity_content = intent2.getStringExtra("wantCity_content");
				String building_content = intent2.getStringExtra("building_content");
				String agent = intent2.getStringExtra("Agent");
				String personrebate = intent2.getStringExtra("Personrebate");
				if(null==agent && null==personrebate && null==building_content){
					houses_intention.setHint("请选择意向楼盘");
				}else if(null==agent || null==personrebate){
					houses_intention.setText(Html.fromHtml("<font color='#000000'>"+building_content+"</font>"));
				}else {
					if("经纪人".equals(RoleUtils.getRoleName())){
						houses_intention.setText(Html.fromHtml("<font color='#000000'>"+building_content+"</font>"));
					}else {
						houses_intention.setText(Html.fromHtml("<font color='#000000'>"+building_content+"</font>"+"<br/>"+"<font color='#adadad'>"+"独立经纪人:  "+personrebate+"%  "+"</font>"));	
					}

				}
				city_intention.setText(null==wantCity_content?"请选择意向城市":wantCity_content);
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
						CustomAddActivity.this, msg);

				if (report != null) {

					withlook_type.setText("1".equals(report.getWithlooktype())?"托带":"自带");
					name.setText(report.getCustomername());
					phone.setText(report.getCustomerphone());

					city_intention.setText(report.getIntention_cityvalue());
					buy_kind.setText(report.getTypevalue());
					if (StringUtils.isNull(report.getIntention_buildingvalue())) {
						houses_intention.setText(report
								.getIntention_buildingvalue());
					} else {
						houses_intention.setHint("请选择意向楼盘");
					}
					String citys= selectcity(report.getCity());
					city.setText(citys);
					//这里应该有个report.getProvince()以后得修改
					String pro=selectprovince(report.getProvince());
					province.setText(pro);
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
						asset.setText(report.getPropertyvalue());
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
		province.setOnClickListener(this);
		city.setOnClickListener(this);
		withlook_type.setOnClickListener(this);

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
		switch (v.getId()) {
		case R.id.province:
			listdialog=new MyListDialog(CustomAddActivity.this);
			ListDialogAdapter adapter=new ListDialogAdapter(CustomAddActivity.this, APP.citylist);
			listdialog.dialog_listview.setAdapter(adapter);
			listdialog.show();
			listdialog.dialog_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					province.setText(APP.citylist.get(position).getCity().toString());
					Myprovince=APP.citylist.get(position).getCity().toString();
					city.setText("");
					listdialog.dismiss();
					for(int m=0;m<APP.citylist.size();m++){
						if(Myprovince.equals(APP.citylist.get(m).getCity())){
							Myprovinceid=APP.citylist.get(m).getId();
						}
					}
				}
			});
			break;
		case R.id.b_ok:
			timepickerview.setVisibility(View.GONE);
			break;
		case R.id.b_cancel:
			timepickerview.setVisibility(View.GONE);
			break;
		case R.id.custom_addAndEdit:
			parentView.setFocusable(true);
			parentView.setFocusableInTouchMode(true);
			parentView.requestFocus();
			break;
		case R.id.withlook_type:
			intent.putExtra(Constant.requestCode, Constant.recommended_withlooktype);
			intent.putExtra(Constant.target, withlook_type.getText().toString());
			startActivityForResult(intent, Constant.recommended_withlooktype);
			break;

		case R.id.recommended_sex:
			intent.putExtra(Constant.requestCode, Constant.recommended_sex);
			intent.putExtra(Constant.target, sex.getText().toString());
			startActivityForResult(intent, Constant.recommended_sex);
			break;
		case R.id.recommended_buy_hotel:
			intent.putExtra(Constant.requestCode, Constant.recommended_buy_hotel);
			intent.putExtra(Constant.target, can_buy_hotel.getText().toString());
			startActivityForResult(intent, Constant.recommended_buy_hotel);
			break;
		case R.id.recommended_buy_fly:

			intent.putExtra(Constant.requestCode, Constant.recommended_buy_fly);
			intent.putExtra(Constant.target, can_buy_fly.getText().toString());
			startActivityForResult(intent, Constant.recommended_buy_fly);
			break;
		case R.id.recommended_go_mode:
			intent.putExtra(Constant.requestCode, Constant.recommended_go_mode);
			intent.putExtra(Constant.target, go_mode.getText().toString());
			startActivityForResult(intent, Constant.recommended_go_mode);
			break;
		case R.id.recommended_city_intention:
			intent.putExtra(Constant.requestCode, Constant.recommended_city_intention);
			intent.putExtra(Constant.target, city_intention.getText().toString());
			startActivityForResult(intent, Constant.recommended_city_intention);
			break;
		case R.id.recommended_asset:
			intent.putExtra(Constant.requestCode, Constant.recommended_asset);
			intent.putExtra(Constant.target, asset.getText().toString());
			startActivityForResult(intent, Constant.recommended_asset);
			break;
		case R.id.recommended_buy_use:
			intent.putExtra(Constant.requestCode, Constant.recommended_buy_use);
			intent.putExtra(Constant.target, buyUse.getText().toString());
			startActivityForResult(intent, Constant.recommended_buy_use);
			break;
		case R.id.recommended_buy_possibility:
			intent.putExtra(Constant.requestCode,Constant.recommended_buy_possibility);
			intent.putExtra(Constant.target, buy_possibility.getText().toString());
			startActivityForResult(intent, Constant.recommended_buy_possibility);
			break;
		case R.id.recommended_houses_intention:
			if (StringUtils.isNull(wantCity_id)&& StringUtils.isNull(wantCity_level)) {
				Intent intent2=new Intent(this, CoustomSelecthouses.class);
				intent2.putExtra(Constant.requestCode,Constant.recommended_houses_intention);
				intent2.putExtra(Constant.target, houses_intention.getText().toString());
				intent2.putExtra(Constant.id, wantCity_id);
				intent2.putExtra(Constant.level, wantCity_level);
				if(reportID != null){
					showlog("现在是修改报备单");
					showlog("agenttype="+report.getAgentType());
					intent2.putExtra("agenttype", report.getAgentType());
				}else {
					showlog("现在是添加报备单");
					showlog("agenttype="+RoleUtils.getRoleName());
					intent2.putExtra("agenttype", RoleUtils.getRoleName());
				}
				startActivityForResult(intent2,Constant.recommended_houses_intention);
			} else {
				ToastUtils.showToast(context, "请选择意向城市");
			}
			break;
		case R.id.recommended_buy_kind:
			intent.putExtra(Constant.requestCode, Constant.recommended_buy_kind);
			intent.putExtra(Constant.target, buy_kind.getText().toString());
			startActivityForResult(intent, Constant.recommended_buy_kind);
			break;
		case R.id.recommended_go_day:
			showDateTimePicker(go_day);
			break;
		case R.id.recommended_arrive_day:
			showDateTimePicker(arrive_day);
			break;
		case R.id.houses_submit:
			if("update".equals(state)){
				update();
			}else {
				OperaterCustomer();
			}
			break;
		case R.id.recommended_city:
			if(Myprovince!=null && Myprovince.length()!=0){
				String[] spcity=APP.city.split(";");
				final List<CityListBean> list=new ArrayList<CityListBean>();
				for(int c=0;c<spcity.length;c++){
					if(Myprovince.equals(spcity[c].split("=")[0])){
						String mycity=spcity[c].split("=")[1];
						String [] spmycity=mycity.split(",");
						for(int k=0;k<spmycity.length;k++){
							CityListBean object=new CityListBean();
							String[] index=spmycity[k].split("-");
							object.setCity(index[1]);
							object.setId(index[0]);
							list.add(object);
						}
					}
				}
				listdialog=new MyListDialog(CustomAddActivity.this);
				ListDialogAdapter adapter1=new ListDialogAdapter(CustomAddActivity.this, list);
				listdialog.dialog_listview.setAdapter(adapter1);
				listdialog.show();
				listdialog.dialog_listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						city.setText(list.get(position).getCity().toString());
						Mycity=list.get(position).getCity().toString();
						for(int q=0;q<list.size();q++){
							if(Mycity.equals(list.get(q).getCity())){
								Mycityid=list.get(q).getId();
							}
						}
						listdialog.dismiss();
					}
				});

			}else{
				Toast.makeText(CustomAddActivity.this, "请先选择省份", Toast.LENGTH_LONG).show();
			}
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
			showlog("content="+content);
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
		case Constant.recommended_withlooktype:
			withlook_type.setText(content);
			break;
		case Constant.recommended_city_intention:
			city_intention.setText(content);
			String wantCityid = intent.getStringExtra(Constant.id);
			String wantCityLevel = intent.getStringExtra(Constant.level);
			if(wantCity_id!=null && wantCity_level!=null){
				if (wantCity_id.equals(wantCityid)
						&& wantCity_level.equals(wantCityLevel)) {
				} else {
					building_id = "";
					houses_intention.setText("");
					houses_intention.setHint("请选择意向楼盘");
				}
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
			String agenttype=intent.getStringExtra("Agenttype");
			String persion=intent.getStringExtra("persion");
			showlog("返回agenttype="+agenttype);
			showlog("返回persion="+persion);
			if("0".equals(agenttype) || "经纪人".equals(agenttype)){
				showlog("执行到返回,0,经纪人");
				houses_intention.setText(Html.fromHtml("<font color='#000000'>"+content+"</font>"));
			}else if("1".equals(agenttype) || "独立经纪人".equals(agenttype)){
				showlog("执行到返回，1，独立经纪人");
				houses_intention.setText(Html.fromHtml("<font color='#000000'>"+content+"</font>"+"<br/>"+"<font color='#adadad'>"+"独立经纪人:  "+persion+"%  "+"</font>"));
			}
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
	private void update(){

		String soName = name.getText().toString().trim();
		String soPhone = phone.getText().toString().replace(" ", "").trim();
		String soCity = city_intention.getText().toString().trim();
		if (!StringUtils.isNull(soName)) {
			Toast.makeText(CustomAddActivity.this, "用户名不能为空", 0).show();
			return;
		} else if (!StringUtils.isNull(soPhone)
				&& !StringUtils.isNumber(soPhone)) {
			Toast.makeText(CustomAddActivity.this, "电话不能为空或者电话格式不正确", 0).show();
			return;
		} else if ((soPhone.length() < 5 || soPhone.length() > 19)
				&& !StringUtils.isNumber(soPhone)) {
			Toast.makeText(CustomAddActivity.this, "电话格式不正确", 0).show();
			return;
		} 
		else if ("".equals(soCity)) {
			Toast.makeText(CustomAddActivity.this, "请选择意向城市", 0).show();
			return;
		} else if ("请选择类型".equals(buy_kind.getText().toString())) {
			Toast.makeText(CustomAddActivity.this, "请选择类型", 0).show();
			return;
		} else if ("城市地产".equals(buy_kind.getText().toString())
				&& "".equals(houses_intention.getText().toString())) {
			Toast.makeText(CustomAddActivity.this, "城市地产需要选择意向楼盘", 0).show();
			return;
		} 
		else {
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
			if (!"".equals(houses_intention.getText().toString())) {
				if (StringUtils.isNull(building_id)) {
					map.put("intention_buildingid", building_id);
				} else {
					ToastUtils.showToast(context, "意向楼盘需要重新选择");
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
			if (reportID != null) {
				editCustomer(map);
			} else {
				if (!StringUtils.isPhone(soPhone)) {
					notifyCoumterPhone(map);
					return;
				}

				if (dialog == null)
					dialog = new RichfitAlertDialog(context);
				if (!dialog.isShow()) {
					dialog.setTitle("提示");
					dialog.show();
					dialog.setContent("是否确认提交？");
					dialog.setNegativeButton("确定",
							new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
							addCustomer(map);
						}
					});
					dialog.setPositiveButton("取消",
							new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
				}

			}
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
		if (!StringUtils.isNull(soName)) {
			Toast.makeText(CustomAddActivity.this, "用户名不能为空", 0).show();
			return;
		} else if (!StringUtils.isNull(soPhone)
				&& !StringUtils.isNumber(soPhone)) {
			Toast.makeText(CustomAddActivity.this, "电话不能为空或者电话格式不正确", 0).show();
			return;
		} else if ((soPhone.length() < 5 || soPhone.length() > 19)
				&& !StringUtils.isNumber(soPhone)) {
			Toast.makeText(CustomAddActivity.this, "电话格式不正确", 0).show();
			return;
		} 
		else if(province.getText().toString()== null || province.getText().toString().length()==0){
			Toast.makeText(CustomAddActivity.this, "请填写居住省份", 0).show();
			return;
		}
		else if(city.getText().toString()==null || city.getText().toString().length()==0){
			Toast.makeText(CustomAddActivity.this, "请填写居住城市", 0).show();
			return;
		}
		else if ("".equals(soCity)) {
			Toast.makeText(CustomAddActivity.this, "请选择意向城市", 0).show();
			return;
		} else if ("请选择类型".equals(buy_kind.getText().toString())) {
			Toast.makeText(CustomAddActivity.this, "请选择类型", 0).show();
			return;
		} else if ("城市地产".equals(buy_kind.getText().toString())
				&& "".equals(houses_intention.getText().toString())) {
			Toast.makeText(CustomAddActivity.this, "城市地产需要选择意向楼盘", 0).show();
			return;
		} 

		else {

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
			if (!"".equals(houses_intention.getText().toString())) {
				Log.i("chenghao", houses_intention.getText().toString());
				Log.i("chenghao", "building_id="+building_id);
				if (StringUtils.isNull(building_id)) {
					map.put("intention_buildingid", building_id);
				} else {
					ToastUtils.showToast(context, "意向楼盘需要重新选择");
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
			if (reportID != null) {
				editCustomer(map);
			} else {
				if (!StringUtils.isPhone(soPhone)) {
					notifyCoumterPhone(map);
					return;
				}

				if (dialog == null)
					dialog = new RichfitAlertDialog(context);
				if (!dialog.isShow()) {
					dialog.setTitle("提示");
					dialog.show();
					dialog.setContent("是否确认提交？");
					dialog.setNegativeButton("确定",
							new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
							addCustomer(map);
						}
					});
					dialog.setPositiveButton("取消",
							new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
				}

			}
		}
	}

	/**
	 * @Deprecatred:
	 * @param map
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 非必需验证数据 （选择）
	 */
	private void selectReportState(final Map<String, Object> map) {
		String mysex= sex.getText().toString();
		if("男".equals(mysex)){
			map.put("sex", 0);
		}else {
			map.put("sex", 1);
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
		map.put("city", Mycityid);
		map.put("province", Myprovinceid);
		map.put("vocation", job.getText().toString());

		//这个新添加String 带看方式，自带：0-托带：1
		map.put("withlooktype", "托带".equals(withlook_type.getText().toString())?"1":"0");

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
					if(dialog!=null && dialog.isShow()){
						dialog.close();
					}
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
				boolean boo = ExecuteJSONUtils.getAddReportResult(
						CustomAddActivity.this, msg);
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
		map.put("from_type", "0");
		houses_submit.setEnabled(false);
		Log.i("chenghao", "发送的map为="+map);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				boolean boo = ExecuteJSONUtils.getAddReportResult(
						CustomAddActivity.this, msg);
				houses_submit.setEnabled(true);
				if (boo) {
					if(dialog!=null && dialog.isShow()){
						dialog.close();
					}
					finish();
					Toast.makeText(CustomAddActivity.this, "报备单添加成功", 0).show();
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
	 * @param tagerView
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:弹出日期时间选择器
	 */
	private void showDateTimePicker(final TextView tagerView) {

		timepickerview.setVisibility(View.VISIBLE);
		Button timerOk = (Button) timepickerview.findViewById(R.id.b_ok);
		Button timerCancle = (Button) timepickerview
				.findViewById(R.id.b_cancel);
		// 显示选择时间的轮盘
		ScreenInfo screenInfo = new ScreenInfo(CustomAddActivity.this);
		final WheelMain wheelMain = new WheelMain(timepickerview1);
		wheelMain.screenheight = screenInfo.getHeight();

		Calendar calendar = Calendar.getInstance();

		String time = tagerView.getText().toString();
		wheelMain.setHasSelectTime(true);// true显示时分，false不显示时分
		String timeSB = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(timeSB, Locale.US);
		if (JudgeDate.isDate(time, timeSB)) {
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		wheelMain.initDateTimePicker(year, month, day, hour, minute, week);
		timepickerview.setVisibility(View.VISIBLE);
		// 点击事件
		timerCancle.setOnClickListener(this);
		timerOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				timepickerview.setVisibility(View.GONE);
				tagerView.setText(wheelMain.getTime());

			}
		});
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
		if (timepickerview != null) {
			timepickerview.setVisibility(View.GONE);
		}
	}

}
