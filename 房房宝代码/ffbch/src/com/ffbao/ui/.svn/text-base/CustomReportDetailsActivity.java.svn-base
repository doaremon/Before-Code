package com.ffbao.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.entity.Comission;
import com.ffbao.entity.Purchase;
import com.ffbao.entity.ReportDetails;
import com.ffbao.entity.Travel;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.ID;
import com.ffbao.util.ReprotState;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.ViewHelper;

/**
 * 
 * @FileName:CustomReportDetailsActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomReportDetailsActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 报备单详情展示
 */
public class CustomReportDetailsActivity extends BaseActivity implements
OnClickListener {
	private String reportID;

	@ID(resId = R.id.report_detail_title)
	private TextView reportTitle;// report_detail_title

	// @ID(resId = R.id.travel_detail_title)
	private TextView travelTitle;// travel_detail_title

	@ID(resId = R.id.purchase_detail_title)
	private TextView purchaseTitle;// purchase_detail_title

	@ID(resId = R.id.recommended_input_name)
	private TextView reportName;// recommended_input_name

	@ID(resId = R.id.recommended_input_phone)
	private TextView reportPhone;// recommended_input_phone

	@ID(resId = R.id.recommended_city_intention)
	private TextView reportIntentRummery;// recommended_city_intention

	@ID(resId = R.id.recommended_houses_intention)
	private TextView reportIntentSite;// recommended_houses_intention


	@ID(resId = R.id.recommended_job)
	private TextView reportJob;// recommended_job

	@ID(resId = R.id.recommended_age)
	private TextView reportAge;// recommended_age

	@ID(resId = R.id.recommended_radioGroup)
	private TextView reportGener;// recommended_radioGroup

	@ID(resId = R.id.recommended_address)
	private TextView reportAddress;// recommended_address

	@ID(resId = R.id.recommended_asset)
	private TextView reportAsset;// recommended_asset

	@ID(resId = R.id.recommended_buy_use)
	private TextView reportBuyUse;// recommended_buy_use

	@ID(resId = R.id.recommended_buy_possibility)
	private TextView reportPossibility;// recommended_buy_possibility


	@ID(resId = R.id.recommended_buy_budget)
	private TextView reportBudget;// recommended_buy_budget

	@ID(resId = R.id.recommended_buy_kind)
	private TextView reportKind;// recommended_buy_kind

	@ID(resId = R.id.recommended_people_count)
	private TextView reportPepleCount;// recommended_people_count

	@ID(resId = R.id.recommended_go_day)
	private TextView reportGoDay;// recommended_go_day

	@ID(resId = R.id.recommended_arrive_day)
	private TextView reportArrivaDay;// recommended_arrive_day

	@ID(resId = R.id.recommended_go_mode)
	private TextView reportTripMode;// recommended_go_mode

	@ID(resId = R.id.recommended_journey)
	private TextView reportJourney;// recommended_journey

	@ID(resId = R.id.recommended_buy_fly)
	private TextView reportBuyFly;// recommended_buy_fly

	@ID(resId = R.id.recommended_buy_hotel)
	private TextView reportBuyHotel;// recommended_buy_hotel

	@ID(resId = R.id.recommended_reportid)
	private TextView recommendedReportid;// recommended_reportid

	@ID(resId = R.id.recommended_reportstate)
	private TextView recommendedReportstate;// recommended_reportstate

	@ID(resId = R.id.recommended_remark)
	private TextView recommendedRemark;// recommended_remark

	@ID(resId = R.id.recommended_companyName)
	private TextView recommendedCompanyName;// recommended_companyName


	private TextView travelSerial;// travel_serial
	private TextView travelname;// travel_name
	private TextView travelTripMode;// travel_tripMode
	private TextView travelReturnMode;// travel_returnMode
	private TextView travelReturnDate;// travel_returnMode
	private TextView travelReturnType;// travel_returnMode
	private TextView travelReturnTicket;// travel_returnMode
	private TextView travelTicket;// travel_ticket
	private TextView travelRummery;// travel_rummery
	private TextView travelDepartrue;// travel_departrue
	private TextView travelArrival;// travel_arrivel
	private TextView travelState;// travel_state

	@ID(resId = R.id.report_detail_travelList)
	private LinearLayout llTravelList;// report_detail_travelList

	@ID(resId = R.id.purchase_serial)
	private TextView purchaseSerial;// purchase_serial

	@ID(resId = R.id.purchase_premiseName)
	private TextView purchasePremiseName;// purchase_premiseName

	@ID(resId = R.id.purchase_roomNum)
	private TextView purchaseRommNum;// purchase_roomNum

	@ID(resId = R.id.purchase_RoomConurbation)
	private TextView purchaseRoomConurbation;// purchase_RoomConurbation

	@ID(resId = R.id.purchase_roomSite)
	private TextView purchaseRommSite;// purchase_roomSite

	@ID(resId = R.id.purchase_RoomEare)
	private TextView purchaseRommEar;// purchase_roomSite

	@ID(resId = R.id.purchase_unitcost)
	private TextView purchaseUnitCost;// purchase_unitcost

	@ID(resId = R.id.purchase_count)
	private TextView purchaseCount;// purchase_count

	@ID(resId = R.id.purchase_amountAdvanced)
	private TextView purchaseAmountAdvanced;// purchase_amountAdvanced

	@ID(resId = R.id.purchase_total)
	private TextView purchaseTotal;// purchase_total

	@ID(resId = R.id.purchase_actualBalance)
	private TextView purchaseActualBalance;// purchase_actualBalance

	@ID(resId = R.id.purchase_state)
	private TextView purchaseState;// purchase_state

	@ID(resId = R.id.purchase_createTime)
	private TextView purchaseCreateTime;// purchase_createTime


	@ID(resId = R.id.purchase_layout)
	private LinearLayout purchaseLinealayout;// purchase_operation

	@ID(resId = R.id.comission_layout)
	private LinearLayout comissionLayout;// comission_layout

	@ID(resId = R.id.comission_serial)
	private TextView comissionSerial;// comission_serial

	@ID(resId = R.id.comission_person)
	private TextView comissionPerson;// comission_person

	@ID(resId = R.id.comission_compand)
	private TextView comissionCompand;// comission_compand

	@ID(resId = R.id.comission_reportid)
	private TextView comissionReportid;// comission_reportid

	@ID(resId = R.id.commission_points)
	private TextView commissionPoints;// commission_points

	@ID(resId = R.id.commission_amount)
	private TextView commissionAmount;// commission_amount

	@ID(resId = R.id.comission_state)
	private TextView comissionState;// comission_state

	@ID(resId = R.id.comission_opertation)
	private TextView comissionOpertation;// comission_opertation

	@ID(resId = R.id.comission_createtime)
	private TextView comissionCreatetime;// comission_createtime

	@ID(resId = R.id.comission_remark)
	private TextView comissionRemark;// comission_remark

	@ID(resId = R.id.report_recommended_province)
	private TextView report_recommended_province;// report_recommended_province新增省份

	@ID(resId = R.id.report_recommended_city)
	private TextView report_recommended_city;// report_recommended_city  新增城市

	@ID(resId = R.id.country)
	private TextView country;// report_recommended_city  zhongguo

	@ID(resId = R.id.detailswithlooktype)
	private TextView detailswithlooktype;// 新增带看方式

	private ReportDetails report ;
	private static String defualtValue = "未填写";
	//佣金点view
	@ID(resId = R.id.Details_commission_points)
	private LinearLayout Details_commission_points;
	//佣金额view
	@ID(resId = R.id.Details_commission_amount)
	private LinearLayout Details_commission_amount;
	
	@ID(resId = R.id.lineview2)
	private ImageView lineview2;
	@ID(resId = R.id.lineview1)
	private ImageView lineview1;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Intent intent=getIntent();
		reportID=intent.getStringExtra(Constant.companyID);

		setContentView(R.layout.ffb_activity_report_detail);

		setActionBarTitle("报备单详情");
		ExitActivity.addActivity(this);
		ViewHelper.init(this);

	}

	@Override
	protected void onResume() {
		if (StringUtils.isNull(reportID)) {
			getReportDetail();
		}
		super.onResume();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.header_tag: // 获取报备 详细信息
			ToastUtils.showToast(this, "tag");
			break;

		default:
			break;
		}
	}
	/**
	 * @Deprecatred:
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:行程单详情
	 */
	private void getTripDetail() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetTripList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportId, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				try {
					List<Travel> travelList = ExecuteJSONUtils.getTravelList(
							CustomReportDetailsActivity.this, msg);
					if (travelList != null) {

						if (travelList.size() > 0) {

							for (int i = 0; i < travelList.size(); i++) {

								Travel travel = travelList.get(i);
								if (travel != null) {
									View travelReport = LayoutInflater
											.from(CustomReportDetailsActivity.this)
											.inflate(
													R.layout.report_detail_travel,
													null);
									travelTitle = (TextView) travelReport
											.findViewById(R.id.travel_detail_title);
									travelSerial = (TextView) travelReport
											.findViewById(R.id.travel_serial);
									travelname = (TextView) travelReport
											.findViewById(R.id.travel_name);
									travelReturnMode = (TextView) travelReport
											.findViewById(R.id.travel_returnMode); // 是否往返
									travelReturnDate = (TextView) travelReport
											.findViewById(R.id.travel_returnDate); // 返程日期
									travelReturnType = (TextView) travelReport
											.findViewById(R.id.travel_returnType); // 返程方式
									travelReturnTicket = (TextView) travelReport
											.findViewById(R.id.travel_returnTicket); // 返程票务
									travelTripMode = (TextView) travelReport
											.findViewById(R.id.travel_tripMode);
									travelTicket = (TextView) travelReport
											.findViewById(R.id.travel_ticket);
									travelRummery = (TextView) travelReport
											.findViewById(R.id.travel_rummery);
									travelDepartrue = (TextView) travelReport
											.findViewById(R.id.travel_departrue);
									travelArrival = (TextView) travelReport
											.findViewById(R.id.travel_arrivel);
									travelState = (TextView) travelReport
											.findViewById(R.id.travel_state);

									setViewValue(travelSerial, travel.getTripinfoid());
									setViewValue(travelname, travel.getCustomername());
									setViewValue(travelTripMode, travel.getTrans_typevalue());
									setViewValue(travelTicket, travel.getTrans_number());
									setViewValue(travelRummery, travel.getHotel());
									setViewValue(travelDepartrue, travel.getDeparture());
									setViewValue(travelArrival, travel.getArrival());
									setViewValue(travelState, travel.getStateValue());

									setViewValue(travelReturnMode, travel.getTripinfo_typevalue());// 是否往返
									setViewValue(travelReturnDate, travel.getBack_departure());// 返程日期
									setViewValue(travelReturnType, travel.getBack_trans_typevalue());// 返程方式
									setViewValue(travelReturnTicket, travel.getBack_trans_number());// 返程票务
									llTravelList.addView(travelReport);
								}
							}
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}


	/**
	 * @Deprecatred:
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:报备单详情
	 */
	private void getReportDetail() {

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
						CustomReportDetailsActivity.this, msg);
				if (StringUtils.isNull(reportID)) {
					getTripDetail();
					getPurchaseDetail();
					getReportComission();
				}

				if (report != null) {
					setViewValue(reportName, report.getCustomername());
					setViewValue(country, report.getCountry());
					setViewValue(reportPhone, report.getCustomerphone());
					setViewValue(reportIntentRummery, report.getIntention_cityvalue());
					setViewValue(reportIntentSite, report.getIntention_buildingvalue());
					//新增省份report.getProvince()
					String province=selectprovince(report.getProvince());
					setViewValue(report_recommended_province,province );
					//改变城市
					String city=selectcity(report.getCity());
					setViewValue(report_recommended_city, city);

					setViewValue(reportAge, report.getAge());
					setViewValue(reportAddress, report.getAddress());
					setViewValue(reportPossibility, report.getPossibilitvalue());
					setViewValue(reportBudget, report.getBudget());
					setViewValue(reportGoDay, report.getDeparture_date());
					setViewValue(reportArrivaDay,report.getArrival_date());
					setViewValue(reportBuyFly, report.getNeed_ticketvalue());
					setViewValue(reportBuyHotel, report.getNeed_hotelvalue());
					setViewValue(reportPepleCount, report.getHeadcount());
					setViewValue(reportBuyUse, report.getPurposevalue());
					setViewValue(reportTripMode,report.getTrip_typevalue());
					//					setViewValue(reportCountry, report.getCountry());
					setViewValue(reportJob, report.getVocation());
					setViewValue(reportJourney, report.getTripplanstatevalue());
					setViewValue(reportAsset,report.getPropertyvalue());
					setViewValue(reportKind, report.getTypevalue());
					setViewValue(reportGener,report.getSexvalue());
					setViewValue(recommendedReportid, reportID);

					setViewValue(detailswithlooktype, "1".equals(report.getWithlooktype())?"托带":"自带");

					String company=report.getCompanyname();
					if(APP.independentname.equals(company)){
						setViewValue(recommendedCompanyName, "暂无");
					}else {
						setViewValue(recommendedCompanyName, report.getCompanyname());
					}

					setViewValue(recommendedRemark, report.getRemark());
					String state = report.getState();
					if (state != null && state.length()!=0) {
						int statevalue = Integer.valueOf(state);
						//						recommendedReportstate.setText(ReprotState
						//								.getMap(statevalue));
						setViewValue(recommendedReportstate, ReprotState.getMap(statevalue));
					}

					String statevalue = report.getState();
					// System.out.println(state);
					String companyid = SharedPrefConstance.getStringValue(
							context, SharedPrefConstance.companyid);
					//&& companyid.equals(report.getCompanyid())
					if (("0".equals(statevalue)||"1".equals(statevalue))) {
						Log.i("chenghao", "if");
						setActionBarOther("编辑报备").setOnClickListener(
								new OnClickListener() {
									@Override
									public void onClick(View v) {
										Intent intent = new Intent(
												CustomReportDetailsActivity.this,
												CustomAddActivity.class);
										intent.putExtra("reportID", reportID);
										intent.putExtra("state","update");
										startActivity(intent);
									}
								});
					} else {
						Log.i("chenghao", "else");
						setActionBarOther("编辑").setVisibility(View.INVISIBLE);
					}

				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}
	/**
	 * @Deprecatred:
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:结算单
	 */
	private void getReportComission() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetComissionList);
		// map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
		// SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportId, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				Comission comission = ExecuteJSONUtils.getReportComission(
						CustomReportDetailsActivity.this, msg);

				if (comission != null && comission.getComissionid() != null
						&& comission.getComissionid().length()!=0) {

					setViewValue(comissionSerial, comission.getComissionid());
					setViewValue(comissionPerson, comission.getAgentname());
					String compay=comission.getAgentcompany();
					if(APP.independentname.equals(compay)){
						setViewValue(comissionCompand,"暂无");
					}else {
						setViewValue(comissionCompand,compay);
					}

					double agent=Double.parseDouble(comission.getBrokeragerate());
					//					int temp = (int)(agent * 1000);
					//					agent = (double)temp / 10;

					setViewValue(comissionReportid, comission.getReportid());
					//原来的是：setViewValue(commissionPoints, comission.getBrokeragerate());
                    
					if("1".equals(report.getAgentType())){
						setViewValue(commissionPoints, mul(agent, 100)+"%");
						setViewValue(commissionAmount, "¥"+StringUtils.isVerfyJE(comission.getBrokeragefee()));
					}else {
						Details_commission_points.setVisibility(View.GONE);
						Details_commission_amount.setVisibility(View.GONE);
						lineview1.setVisibility(View.GONE);
						lineview2.setVisibility(View.GONE);
					}


					setViewValue(comissionOpertation, comission.getOperatorid());
					setViewValue(comissionCreatetime, comission.getCreate_time());
					setViewValue(comissionState, comission.getStatevalue());
					setViewValue(comissionRemark, comission.getRemark());


				} else {
					comissionLayout.setVisibility(View.GONE);
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}
	public static double mul(double d1,double d2){
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	} 
	/**
	 * @Deprecatred:
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:申购单
	 */
	private void getPurchaseDetail() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetPurchaseList);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportId, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				try {
					Purchase purchase = ExecuteJSONUtils.getPurchase(
							CustomReportDetailsActivity.this, msg);

					if (purchase != null) {

						setViewValue(purchaseSerial, purchase.getPurchaseid());
						setViewValue(purchasePremiseName, purchase.getBuildingname());
						setViewValue(purchaseRommNum, purchase.getHouse_number());
						setViewValue(purchaseRoomConurbation, purchase.getPurchase_city());
						setViewValue(purchaseRommEar, purchase.getPurchase_district());
						setViewValue(purchaseRommSite, purchase.getAddress());
						setViewValue(purchaseUnitCost, "¥"+StringUtils.isVerfyJE(purchase.getPrice()));
						setViewValue(purchaseCount, purchase.getAmout());
						setViewValue(purchaseAmountAdvanced, purchase.getPrepayment());
						setViewValue(purchaseTotal, "¥"+StringUtils.isVerfyJE(purchase.getTotalprice()));
						setViewValue(purchaseActualBalance, "¥"+StringUtils.isVerfyJE(purchase.getActiualTotal_price()));
						setViewValue(purchaseState, purchase.getStatevalue());
						setViewValue(purchaseCreateTime, purchase.getCreate_time());

						//						purchaseSerial.setText(purchase.getPurchaseid());
						//						purchasePremiseName.setText(purchase.getBuildingname());
						//						purchaseRommNum.setText(purchase.getHouse_number());
						//						purchaseRoomConurbation.setText(purchase
						//								.getPurchase_city());
						//						purchaseRommEar.setText(purchase.getPurchase_district());
						//						purchaseRommSite.setText(purchase.getAddress());
						//						purchaseUnitCost.setText(purchase.getPrice());
						//						purchaseCount.setText(purchase.getAmout());
						//						purchaseAmountAdvanced.setText(purchase.getPrepayment());
						//						purchaseTotal.setText(purchase.getTotalprice());
						//						purchaseActualBalance.setText(purchase.getActiualTotal_price());
						//						purchaseState.setText(purchase.getStatevalue());
						//						purchaseCreateTime.setText(purchase.getCreate_time());

						// purchaseOperation.setText(purchase.getOperation());
						purchaseLinealayout.setVisibility(View.VISIBLE);
					} else {
						purchaseLinealayout.setVisibility(View.GONE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	/**
	 * @Deprecatred:
	 * @param view
	 * @param value
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:设置控件值
	 */
	private void setViewValue(TextView view, String value) {

		if (StringUtils.isNull(value)) {

			view.setText(value);

		} else {
			view.setText(defualtValue);
		}
	}
}
