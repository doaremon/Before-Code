package com.ffbao.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.entity.HouseParamter;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @FileName:BuildingDetailsActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:BuildingDetailsActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取参数界面
 */
public class BuildingDetailsActivity extends BaseActivity implements
OnClickListener {

	private String buildingid;

	private TextView startTime;
	private TextView developer;
	private TextView wuye;
	// private TextView wuyeType;
	private TextView buildType;
	private TextView buildArea;
	private TextView decorate;
	private TextView households;
	private TextView parkingDigits;
	private TextView plotRatio;
	private TextView greeningRate;
	private TextView llServicesCommissioner;
	// private TextView propertyFee;
	private String servicePhone;

	private RichfitAlertDialog dialog1;
	private TextView tvServiceName;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		//update by shenwenshi
		//date:2014-12-08
		//for change transer value 
		//Bundle to Intent
		//buildingid = getIntent().getExtras().getString(Constant.buildingid, "");
		Intent intent = getIntent();
		buildingid = intent.getStringExtra(Constant.buildingid);
		setContentView(R.layout.ffb_activity_houses_details_building);
		setActionBarTitle("楼盘参数");
		ExitActivity.addActivity(this);
		startTime = (TextView) findViewById(R.id.tv_start_time);
		developer = (TextView) findViewById(R.id.tv_developer);
		wuye = (TextView) findViewById(R.id.tv_wuye);
		// wuyeType = (TextView) findViewById(R.id.tv_wuye_type);
		buildType = (TextView) findViewById(R.id.tv_build_type);
		buildArea = (TextView) findViewById(R.id.tv_build_area);
		decorate = (TextView) findViewById(R.id.tv_decorate);
		households = (TextView) findViewById(R.id.tvHouseholds);
		parkingDigits = (TextView) findViewById(R.id.tvParkingDigits);
		plotRatio = (TextView) findViewById(R.id.tvPlotRatio);
		greeningRate = (TextView) findViewById(R.id.tvGreeningRate);
		// propertyFee = (TextView) findViewById(R.id.tvPropertyFee);
		tvServiceName=(TextView) findViewById(R.id.tvServiceName);
		servicePhone = SharedPrefConstance.getStringValue(
				context, SharedPrefConstance.servicephone, "");

		String iflonging = SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.companyid);
		if("".equals(iflonging)){
			tvServiceName.setText("全国客服电话");
		}else {
			if (APP.nationwidecall.equals(servicePhone)) {
				tvServiceName.setText("全国客服电话");
			}else {
				tvServiceName.setText("联系经纪服务专员");
			}
		}

		tvServiceName.setOnClickListener(this);
		// startTime.setText("2014年11月6日18:56:59");
		// developer.setText("万科");
		// wuye.setText("万科");
		// buildType.setText("住宅");
		// buildArea.setText("12万平方");
		// decorate.setText("装修");
		// households.setText("1209");
		// parkingDigits.setText("102");
		// plotRatio.setText("30%");
		// greeningRate.setText("35%");
		// propertyFee.setText("2.6");
		initData();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();


	}

	private void initData() {

		Map<String, Object> map = new HashMap<String, Object>();
		// TODO 定义接口名称
		map.put(Constant.commandText, UrlUtil.userGetBuildParameter);
		// map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
		// SharedPrefConstance.userid));
		// map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
		// SharedPrefConstance.UUID));
		map.put(Constant.buildingid, buildingid);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				HouseParamter buildingPatamer = ExecuteJSONUtils
						.getBuildingPatamer(context, msg);
				if (buildingPatamer != null) {

					startTime.setText(updateTimeFomat(buildingPatamer
							.getOpen_time()));
					developer.setText(buildingPatamer.getDeveloperName());
					wuye.setText(buildingPatamer.getProperty_company());
					// wuyeType.setText(buildingPatamer.getProperty_company());
					buildType.setText(buildingPatamer.getBuildTypeName());
					buildArea.setText(buildingPatamer.getBuildArea());
					decorate.setText(buildingPatamer.getBuild_decorateName());
					households.setText(buildingPatamer.getPlanning_resident());
					parkingDigits.setText(buildingPatamer.getParking());
					plotRatio.setText(buildingPatamer.getCubage_rate() + "%");
					greeningRate.setText(buildingPatamer.getGreen_rate() + "%");
					// propertyFee.setText(buildingPatamer.getPropertyFee());
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	private String updateTimeFomat(String openTime) {
		try {
			String format = "yyyy-MM-dd HH:mm";
			DateFormat df = new SimpleDateFormat(format, Locale.US);
			Date date = df.parse(openTime);
			DateFormat dftarget = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			return dftarget.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvServiceName:

			//			final String servicePhone = SharedPrefConstance.getStringValue(
			//					context, SharedPrefConstance.servicephone, "");
			//			if (servicePhone != null && servicePhone.length()!=0) {
			if (dialog1 == null) {
				dialog1 = new RichfitAlertDialog(context);
			}
			if (!dialog1.isShow()) {
				dialog1.show();
				if(servicePhone.length()==0){
					servicePhone="4006328989";
					dialog1.setContent("是否拨打电话:" + servicePhone);
				}else {
					dialog1.setContent("是否拨打电话:" + servicePhone);
				}

				dialog1.setPositiveButton("取消", new OnClickListener() {

					@Override
					public void onClick(View v) {

						dialog1.close();
					}
				});
				dialog1.setNegativeButton("拨打", new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri
								.parse("tel:" + servicePhone));
						startActivity(intent);
						dialog1.close();

					}
				});
			}
			//			}
			break;

		default:
			break;
		}
	}

}
