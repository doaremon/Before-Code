package com.ffbao.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.engine.impl.GetUserDetailEngine;
import com.ffbao.entity.BankBean;
import com.ffbao.entity.Comission;
import com.ffbao.entity.CommissionNoteInfo;
import com.ffbao.entity.ReportDetails;
import com.ffbao.entity.Topic;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.TopicAdapter;
import com.ffbao.ui.widget.MyScrollView;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

/**
 * 
 * @FileName:CustomDetailsActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomDetailsActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 客户详情
 */
public class CustomDetailsActivity extends BaseActivity implements
OnClickListener {

	private String reportID;
	private String number;

	private TextView name;
	private TextView phone;
	private TextView houseName;

	private Button btnlook;
	private Button btndele;
	/**
	 * 已报备
	 */
	private ImageView report;
	/**
	 * 等待验证
	 */
	private ImageView verifying;
	/**
	 * 已验证
	 */
	private ImageView verify;
	/**
	 * 本地分单
	 */
	private ImageView localderive;
	/**
	 * 签约看房
	 */
	private ImageView lookSigning;
	/**
	 * 预订飞机票
	 */
	private ImageView bookTicket;
	/**
	 * 预订酒店
	 */
	private ImageView bookHotel;
	/**
	 * 接待地已分单
	 */
	private ImageView receivederive;
	/**
	 * 已接待
	 */
	private ImageView receiver;
	/**
	 * 已看房
	 */
	private ImageView look;
	/**
	 * 已申购
	 */
	private ImageView purchase;
	/**
	 * 已申购确认
	 */
	private ImageView purchaseconmfire;
	/**
	 * 合同已签
	 */
	private ImageView contractSighed;
	/**
	 * payment 已付款
	 */
	private ImageView payment;
	/**
	 * 已成交
	 */
	private ImageView strike;
	/**
	 * 申请结拥
	 */
	private ImageView commission;
	/**
	 * commissioned 结佣审批通过
	 */
	private ImageView commissioned;
	/**
	 * 已开发票
	 */
	private ImageView receipt;
	/**
	 * 已结拥
	 */
	private ImageView accounted;
	/**
	 * 已报备
	 */
	private TextView reportTag;
	/**
	 * 等待验证
	 */
	//	private TextView verifyingTag;
	/**
	 * 已验证
	 */
	//	private TextView verifyTag;
	/**
	 * 本地分单
	 */
	//	private TextView localderiveTag;
	/**
	 * 签约看房
	 */
	private TextView lookSigningTag;
	/**
	 * 预订飞机票
	 */
	//	private TextView bookTicketTag;
	/**
	 * 预订酒店
	 */
	//	private TextView bookHotelTag;
	/**
	 * 接待地已分单
	 */
	//	private TextView receivederiveTag;
	/**
	 * 已接待
	 */
	private TextView receiverTag;
	/**
	 * 已看房
	 */
	//	private TextView lookTag;
	/**
	 * 已申购
	 */
	private TextView purchaseTag;
	/**
	 * 已申购确认
	 */
	//	private TextView purchaseconmfireTag;
	/**
	 * 合同已签
	 */
	//	private TextView contractSighedTag;
	/**
	 * payment 已付款
	 */
	//	private TextView paymentTag;
	/**
	 * 已成交
	 */
	private TextView strikeTag;
	/**
	 * 申请结拥
	 */
	//	private TextView commissionTag;
	/**
	 * commissioned 结佣审批通过
	 */
	//	private TextView commissionedTag;
	/**
	 * 已开发票
	 */
	//	private TextView receiptTag;
	/**
	 * 已结拥
	 */
	private TextView accountedTag;

	private MyScrollView myScrollView;

	private HorizontalScrollView scroll;

	private Button call_phone, send_msg;
	private LinearLayout call_service;

	//	private String state;
	private List<Topic> topiclist = new ArrayList<Topic>();
	private ListView lv_topic;

	private ReportDetails reportDtails;
	private TopicAdapter adpater;
	private CommissionNoteInfo commissionNoteInfo;

	private int page = 0;
	private int pagecount = 20;
	private int position = 0;
	private RichfitAlertDialog dialog;
	private View headtag;
	private View reprotdel;
	private View reportDetail;

	private Integer state;

	private View icon_strike;
	private View icon_commission;
	private View icon_receipt;
	private View icon_accounted;

	private String billid;//这张单子的经纪公司id


	private TextView tvServicesCommissioner;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ExitActivity.addActivity(this);
		//新修改的
		Intent intent=getIntent();
		reportID=intent.getStringExtra(Constant.companyID);
		//原来的写法
		//		Bundle extras = getIntent().getExtras();
		//		reportID = extras.getString(Constant.companyID, "");

		setContentView(R.layout.ffb_activity_custom_details);
		setActionBarTitle("报备详情");
		initView();
		setAdapter();

	}

	@Override
	protected void onResume() {

		initData();
		getTopicList();
		super.onResume();
	}

	protected void onNewIntent(Intent intent){
		//新写的
		Intent intent1=getIntent();
		reportID=intent1.getStringExtra(Constant.companyID);
		//原来的
		//		Bundle extras = getIntent().getExtras();
		//		reportID = extras.getString(Constant.companyID, "");
		//		setContentView(R.layout.activity_custom_details);
		initData();
		getTopicList();
	}

	private void initView() {

		lv_topic = (ListView) findViewById(R.id.lv_topic);
		findViewById(R.id.llServicesCommissioner).setOnClickListener(this);
		call_service = (LinearLayout) findViewById(R.id.llServicesCommissioner);
		call_service.setOnClickListener(this);
		View header = LayoutInflater.from(this).inflate(
				R.layout.ffb_activity_custom_details_head, null);
		lv_topic.addHeaderView(header);

		name = (TextView) header.findViewById(R.id.customer_name);
		phone = (TextView) header.findViewById(R.id.customer_phone);
		btnlook = (Button) header.findViewById(R.id.btnLook);
		btndele = (Button) header.findViewById(R.id.btnDele);
		houseName = (TextView) header.findViewById(R.id.tvHouseName);

		report = (ImageView) header.findViewById(R.id.btn_icon_report);
		reportTag = (TextView) header.findViewById(R.id.tv_tag_report);
		verifying = (ImageView) header.findViewById(R.id.btn_icon_verifying);
		//		verifyingTag = (TextView) header.findViewById(R.id.tv_tag_verifying);
		verify = (ImageView) header.findViewById(R.id.btn_icon_verify);
		//		verifyTag = (TextView) header.findViewById(R.id.tv_tag_verify);
		localderive = (ImageView) header
				.findViewById(R.id.btn_icon_localderive);
		//		localderiveTag = (TextView) header
		//				.findViewById(R.id.tv_tag_localderive);
		lookSigning = (ImageView) header
				.findViewById(R.id.btn_icon_lookSigning);
		lookSigningTag = (TextView) header
				.findViewById(R.id.tv_tag_lookSigning);
		bookTicket = (ImageView) header.findViewById(R.id.btn_icon_bookTicket);
		//		bookTicketTag = (TextView) header.findViewById(R.id.tv_tag_bookTicket);
		bookHotel = (ImageView) header.findViewById(R.id.btn_icon_bookHotel);
		//		bookHotelTag = (TextView) header.findViewById(R.id.tv_tag_bookHotel);
		receivederive = (ImageView) header
				.findViewById(R.id.btn_icon_receivederive);
		//		receivederiveTag = (TextView) header
		//				.findViewById(R.id.tv_tag_receivederive);
		receiver = (ImageView) header.findViewById(R.id.btn_icon_receiver);
		receiverTag = (TextView) header.findViewById(R.id.tv_tag_receiver);
		look = (ImageView) header.findViewById(R.id.btn_icon_look);
		//		lookTag = (TextView) header.findViewById(R.id.tv_tag_look);
		purchase = (ImageView) header.findViewById(R.id.btn_icon_purchase);
		purchaseTag = (TextView) header.findViewById(R.id.tv_tag_purchase);
		purchaseconmfire = (ImageView) header
				.findViewById(R.id.btn_icon_purchaseconmfire);
		//		purchaseconmfireTag = (TextView) header
		//				.findViewById(R.id.tv_tag_purchaseconmfire);
		contractSighed = (ImageView) header
				.findViewById(R.id.btn_icon_contractSighed);
		//		contractSighedTag = (TextView) header
		//				.findViewById(R.id.tv_tag_contractSighed);
		payment = (ImageView) header.findViewById(R.id.btn_icon_payment);
		scroll = (HorizontalScrollView) header.findViewById(R.id.scroll);
		//		paymentTag = (TextView) header.findViewById(R.id.tv_tag_payment);
		strike = (ImageView) header.findViewById(R.id.btn_icon_strike);
		strikeTag = (TextView) header.findViewById(R.id.tv_tag_strike);
		commission = (ImageView) header.findViewById(R.id.btn_icon_commission);
		//		commissionTag = (TextView) header.findViewById(R.id.tv_tag_commission);
		//		commissioned = (ImageView) header
		//				.findViewById(R.id.btn_icon_commissioned);
		//		commissionedTag = (TextView) header
		//				.findViewById(R.id.tv_tag_commissioned);
		receipt = (ImageView) header.findViewById(R.id.btn_icon_receipt);
		//		receiptTag = (TextView) header.findViewById(R.id.tv_tag_receipt);
		accounted = (ImageView) header.findViewById(R.id.btn_icon_accounted);
		accountedTag = (TextView) header.findViewById(R.id.tv_tag_accounted);

		icon_strike = header.findViewById(R.id.icon_strike);
		icon_commission = header.findViewById(R.id.icon_commission);
		icon_receipt = header.findViewById(R.id.icon_receipt);
		icon_accounted =  header.findViewById(R.id.icon_accounted);
		tvServicesCommissioner=(TextView) findViewById(R.id.tvServicesCommissioner);

		btnlook.setOnClickListener(this);

		headtag = header.findViewById(R.id.header_tag);
		reprotdel = header.findViewById(R.id.reprotid_del);
		reportDetail = header.findViewById(R.id.report_detail);
		headtag.setOnClickListener(this);
		header.findViewById(R.id.btnDele).setOnClickListener(this);
		header.findViewById(R.id.report_detail).setOnClickListener(this);
		header.findViewById(R.id.tvHouseName).setOnClickListener(this);

		send_msg = (Button) header.findViewById(R.id.customer_message);
		call_phone = (Button) header.findViewById(R.id.customer_call);
		send_msg.setOnClickListener(this);
		call_phone.setOnClickListener(this);
		String call=SharedPrefConstance.getStringValue(CustomDetailsActivity.this,
				SharedPrefConstance.servicephone);
		if(APP.nationwidecall.equals(call)){
			tvServicesCommissioner.setText("全国客服电话");
		}else {
		}

	}

	// private void addOpertion() {
	// send_msg = (Button) header.findViewById(R.id.customer_message);
	// call_phone = (Button) header.findViewById(R.id.customer_call);
	// send_msg.setOnClickListener(this);
	// call_phone.setOnClickListener(this);
	//
	// }

	private void addOpertion() {
		if(state == Constant.state_99){
		}else {
			setActionBarOther("添加话题").setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					if (dialog == null)
						dialog = new RichfitAlertDialog(CustomDetailsActivity.this);
					if (!dialog.isShow()) {
						dialog.setTitle("发起新话题");
						dialog.show();

						final EditText et_servierUrl = new EditText(
								CustomDetailsActivity.this);
						et_servierUrl.setMaxLines(5);
						dialog.setContent(et_servierUrl);
						dialog.setNegativeButton("确认", new OnClickListener() {

							@Override
							public void onClick(View v) {
								String etservierUrl = et_servierUrl.getText()
										.toString().trim();
								if (etservierUrl != null && etservierUrl.length()!=0) {
									addTopic(etservierUrl);
									dialog.close();
								} else {
									ToastUtils.showToast(context, "发起新话题内容不能为空");
								}
							}
						});
						dialog.setPositiveButton("取消", new OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.close();
							}
						});
					}
				}
			});
		}

	}

	private void addTopic(final String topic) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userAddTopic);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportid, reportID);
		map.put(Constant.username, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfname));
		map.put(Constant.rolename, RoleUtils.getRoleName());
		map.put(Constant.companyname, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.companyname));
		map.put(Constant.topic, topic);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.simpleUpdateInfo(
						CustomDetailsActivity.this, msg)) {
					try {
						List<Topic> topicList2 = ExecuteJSONUtils.getTopicList(
								CustomDetailsActivity.this, msg, false);
						if (topicList2 != null) {
							topiclist.addAll(0, topicList2);
							setAdapter();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}

	private void getTopicList() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetTopicList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportid, reportID);
		map.put(Constant.pageid, page);
		map.put(Constant.pagecount, pagecount);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.simpleUpdateInfo(
						CustomDetailsActivity.this, msg)) {
					try {
						topiclist = ExecuteJSONUtils.getTopicList(
								CustomDetailsActivity.this, msg, false);
						if (topiclist != null && topiclist.size() > 0) {
							setAdapter();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void setAdapter() {
		adpater = new TopicAdapter(this, topiclist, false);
		lv_topic.setAdapter(adpater);
		lv_topic.setSelection(position);
		addListener();
		adpater.notifyDataSetChanged();
	}

	private void addListener() {
		lv_topic.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(state == Constant.state_99){
					Toast.makeText(context, "报备单已经作废，此功能无效", Toast.LENGTH_LONG).show();
				}else {
					CustomDetailsActivity.this.position = position;
					Topic topic = (Topic) parent.getItemAtPosition(position);

					Intent intent = new Intent(CustomDetailsActivity.this, CustomTopicContentActivity.class);
					intent.putExtra(Constant.topicid, String.valueOf(topic.getTopicid()));
					intent.putExtra(Constant.reportid, String.valueOf(topic.getReportid()));
					intent.putExtra("Username", topic.getUsername());
					intent.putExtra("Companyname", topic.getCompanyname());
					intent.putExtra("Rolename", topic.getRolename());
					intent.putExtra("Topiccontent", topic.getTopiccontent());
					intent.putExtra("Create_time", topic.getCreate_time());
					intent.putExtra("billid", billid);
					startActivity(intent);	

				}

			}
		});

	}

	private void initData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetReportDetail);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.reportid, reportID);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				reportDtails = ExecuteJSONUtils.getReportDtails(
						CustomDetailsActivity.this, msg);
				billid=reportDtails.getCompanyid();
				if (reportDtails != null) {
					display(reportDtails);
					String company = SharedPrefConstance.getStringValue(
							CustomDetailsActivity.this,
							SharedPrefConstance.companyid);
					if (company != null && company.length()!=0) {
						if (company.equals(reportDtails.getCompanyid())) {
							addOpertion();
						}
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	private void display(ReportDetails reportDtails) {

		name.setText(reportDtails.getCustomername());
		phone.setText(reportDtails.getCustomerphone());
		String intentionBuilding = reportDtails.getIntention_buildingvalue();

		houseName
		.setText(reportDtails.getIntention_cityvalue() +"  "+ (StringUtils.isNull(intentionBuilding) ? intentionBuilding
				: "未填写楼盘"));
		number = reportDtails.getCustomerphone();
		if (reportDtails.getState() != null
				&& reportDtails.getState().length()!=0) {
			state = Integer.valueOf(reportDtails.getState());
			setState(state);
		}

	}

	private void setState(int state) {

		btndele.setVisibility(View.GONE);
		btnlook.setVisibility(View.GONE);
		headtag.setVisibility(View.VISIBLE);
		if (state == Constant.state_99) {
			reportDetail.setVisibility(View.GONE);
			reprotdel.setVisibility(View.VISIBLE);

			return;
		}
		if (state == Constant.state_98) {
			state = 3;
		}

		if (state >= Constant.state_0) {
			groundTextColor(reportTag);
			groundImage(report);
		}
		if (state == Constant.state_0) {
			groundTextColor(reportTag);
			groundImageCurrent(report);
		}
		if (state == Constant.state_0 || state == Constant.state_1) {
			btndele.setVisibility(View.VISIBLE);
			btnlook.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_1) {
			//			groundTextColor(verifyingTag);
			groundImagedefualt(verifying);
			verifying.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_2) {
			//			groundTextColor(verifyTag);
			groundImagedefualt(verify);
			verify.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_3) {
			//			groundTextColor(localderiveTag);
			groundImagedefualt(localderive);
			localderive.setVisibility(View.VISIBLE);
		}
		if (state >= Constant.state_4) {
			groundTextColor(lookSigningTag);
			groundImage(lookSigning);
		}
		if (state == Constant.state_4) {
			groundTextColor(lookSigningTag);
			groundImageCurrent(lookSigning);
		}
		if (state == Constant.state_5) {
			//			groundTextColor(bookTicketTag);
			groundImagedefualt(bookTicket);
			bookTicket.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_6) {
			//			groundTextColor(bookHotelTag);
			groundImagedefualt(bookHotel);
			bookHotel.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_7) {
			//			groundTextColor(receivederiveTag);
			groundImagedefualt(receivederive);
			receivederive.setVisibility(View.VISIBLE);
		}
		if (state >= Constant.state_8) {
			groundTextColor(receiverTag);
			groundImage(receiver);
		}
		if (state == Constant.state_8) {
			groundTextColor(receiverTag);
			groundImageCurrent(receiver);
		}
		if (state == Constant.state_9) {
			//			groundTextColor(lookTag);
			groundImagedefualt(look);
			look.setVisibility(View.VISIBLE);
		}
		if (state >= Constant.state_10) {
			groundTextColor(purchaseTag);
			groundImage(purchase);
		}
		if (state == Constant.state_10) {
			groundTextColor(purchaseTag);
			groundImageCurrent(purchase);
		}
		if (state == Constant.state_11) {
			//			groundTextColor(purchaseconmfireTag);
			groundImagedefualt(purchaseconmfire);
			purchaseconmfire.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_12) {
			//			groundTextColor(contractSighedTag);
			groundImagedefualt(contractSighed);
			contractSighed.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_13) {
			//			groundTextColor(paymentTag);
			groundImagedefualt(payment);
			payment.setVisibility(View.VISIBLE);
		}
		if (state >= Constant.state_14) {
			groundTextColor(strikeTag);
			groundImage(strike);
			//			scroll.setScrollX(scroll.getWidth()*2);
			scroll.smoothScrollTo(scroll.getWidth(), 0);
		}
		if (state == Constant.state_14) {
			if("1".equals(reportDtails.getAgentType())){
				btnlook.setText("申请结佣");
				btnlook.setVisibility(View.VISIBLE);
			}
			groundImageCurrent(strike);
		}
		if (state == Constant.state_15) {
			//			groundTextColor(commissionTag);
			groundImagedefualt(commission);
			commission.setVisibility(View.VISIBLE);
		}
		if (state == Constant.state_15) {
			getReportComission();
		}

		if (state == Constant.state_17) {
			//			groundTextColor(receiptTag);
			groundImagedefualt(receipt);
			receipt.setVisibility(View.VISIBLE);
		}
		if (state >=Constant.state_18) {
			groundTextColor(accountedTag);
			groundImage(accounted);
		}
		if (state == Constant.state_18) {
			groundTextColor(accountedTag);
			groundImageCurrent(accounted);
			//			accounted.setBackgroundResource(R.drawable.ffb_circle_current);
		}
	}

	@SuppressWarnings("deprecation")
	private void groundImage(ImageView view) {

		view.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ffb_circle_checked));
	}

	@SuppressWarnings("deprecation")
	private void groundImageCurrent(ImageView view) {

		view.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ffb_circle_current));
	}

	@SuppressWarnings("deprecation")
	private void groundImagedefualt(ImageView view) {

		view.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ffb_circle_current_defualt));
	}

	private void groundTextColor(TextView view) {

		// view.setBackgroundDrawable(getResources().getDrawable(
		// R.drawable.circle_checked));
		view.setTextColor(getResources().getColor(R.color.bg_nav));
	}

	@Override
	public void onClick(View v) {

		if(reportDtails == null){

			//			ToastUtils.showToast(context, "数据还在请求");
			return;
		}
		Intent intent = new Intent();

		switch (v.getId()) {
		case R.id.header_tag: // 获取报备 详细信息
			intent.setClass(CustomDetailsActivity.this,
					CustomReportDetailsActivity.class);
			intent.putExtra("state",reportDtails.getState());
			intent.putExtra(Constant.companyID, reportID);
			startActivity(intent);
			break;
		case R.id.btnLook: // 带看

			seeReportOperat();
			break;
		case R.id.btnDele: // 作废按钮
			delReprotOperat();
			break;
		case R.id.report_detail: // 楼盘详细记录
			break;
		case R.id.tvHouseName: // 楼盘信息

			break;

		case R.id.tv_operater_others_btn:
			startActivity(new Intent(this, EditActivity.class));
			break;
		case R.id.customer_message:

			if (!StringUtils.isNull(number)) {
				ToastUtils.showToast(context, "客户手机号为空");
				return;
			}
			Uri uri = Uri.parse("smsto:" + number);
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "");
			startActivity(intent);
			break;
		case R.id.customer_call:

			concatCustomer();
			break;
		case R.id.llServicesCommissioner:
			String message;
			if(APP.nationwidecall.equals(SharedPrefConstance.getStringValue(
					CustomDetailsActivity.this,
					SharedPrefConstance.servicephone))){
				message = "全国客服电话:"
						+ SharedPrefConstance.getStringValue(
								CustomDetailsActivity.this,
								SharedPrefConstance.servicephone);
			}else {
				message = "联系经纪服务专员拨:"
						+ SharedPrefConstance.getStringValue(
								CustomDetailsActivity.this,
								SharedPrefConstance.servicephone);
			}

			dialogShow(message);

			break;
		default:
			break;
		}
	}

	private void concatCustomer() {
		if (!StringUtils.isNull(number)) {
			ToastUtils.showToast(context, "客户手机号为空");
			return;
		}
		new AlertDialog.Builder(this).setTitle("").setMessage("拨打电话:" + number)
		.setNegativeButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(Intent.ACTION_CALL, Uri
						.parse("tel:" + number)));
			}
		}).setPositiveButton("取消", null).create().show();
	}

	private void delReprotOperat() {
		if (reportDtails != null && reportDtails.getState() != null
				&& reportDtails.getState().length()!=0) {
			Integer state = Integer.valueOf(reportDtails.getState());
			if (state == Constant.state_0 || state == Constant.state_1) {
				if (dialog == null)
					dialog = new RichfitAlertDialog(this);
				if (!dialog.isShow()) {
					dialog.setTitle("提示");
					dialog.show();
					dialog.setContent("确认作废?");
					dialog.setNegativeButton("确认", new OnClickListener() {

						@Override
						public void onClick(View v) {
							operationReport("99");
							dialog.close();
						}
					});
					dialog.setPositiveButton("取消", new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
				}
			}
		}
	}

	private void seeReportOperat() {
		if (reportDtails != null && reportDtails.getState() != null
				&& reportDtails.getState().length()!=0) {
			Integer state = Integer.valueOf(reportDtails.getState());
			if (state == Constant.state_0 || state == Constant.state_1) {
				operationReport("1");
			}
			if (state == Constant.state_14 || state == Constant.state_15) {
				//这里添加一个查询接口
				GetUserDetailEngine userDetail = new GetUserDetailEngine(CustomDetailsActivity.this);
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put(Constant.commandText, UrlUtil.userGetBankCardInfo);
				map1.put(Constant.UUID, SharedPrefConstance.getStringValue(
						CustomDetailsActivity.this, SharedPrefConstance.UUID, ""));
				map1.put(Constant.userid, SharedPrefConstance.getStringValue(
						CustomDetailsActivity.this, SharedPrefConstance.userid, ""));
				showlog("查询银行卡：发送map="+map1);
				userDetail.execute(map1, new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						showlog("查询银行卡返回值msg="+msg);
						try {
							JSONObject jsonObject=new JSONObject(msg);
							int status=jsonObject.getInt("status");
							if(status==49){
								//现在是没有银行卡，显示信息，跳转到添加银行卡
								if (dialog == null)
									dialog = new RichfitAlertDialog(CustomDetailsActivity.this);
								if (!dialog.isShow()) {
									dialog.setTitle("提示");
									dialog.show();
									dialog.setContent("您现在没有银行卡信息，是否添加银行卡?");
									dialog.setNegativeButton("确认", new OnClickListener() {

										@Override
										public void onClick(View v) {
											dialog.close();
											Intent intent=new Intent(CustomDetailsActivity.this, AddBnakActivity.class);
											startActivity(intent);
										}
									});
									dialog.setPositiveButton("取消", new OnClickListener() {

										@Override
										public void onClick(View v) {
											dialog.close();
										}
									});
								}
							} else if(status==1){
								//有银行卡信息，显示弹出框。确定结佣，取消关闭框
								String result=jsonObject.getString("result");
								JSONObject jsonObject2=new JSONObject(result);
								JSONArray array=jsonObject2.getJSONArray("backCardInfo");
								List<BankBean>	banklist=new ArrayList<BankBean>();
								for(int c=0;c<array.length();c++){
									JSONObject jsonObject3=new JSONObject(array.get(c).toString());
									BankBean bankBean=new BankBean();
									bankBean.setBankname(jsonObject3.getString("bankname"));
									bankBean.setBanknumber(jsonObject3.getString("banknumber"));
									bankBean.setOpenaccountname(jsonObject3.getString("openaccountname"));
									banklist.add(bankBean);
								}
								if (dialog == null)
									dialog = new RichfitAlertDialog(CustomDetailsActivity.this);
								if (!dialog.isShow()) {
									dialog.setTitle("银行卡信息");
									dialog.show();
									dialog.setContent("您现在的银行卡信息为: \n 开户名："+banklist.get(0).getOpenaccountname()+
											"\n 开户行："+banklist.get(0).getBankname()+"\n 卡号："+
											banklist.get(0).getBanknumber());
									dialog.setNegativeButton("确认", new OnClickListener() {

										@Override
										public void onClick(View v) {
											dialog.close();
											//开始结佣
											Map<String, Object> map = new HashMap<String, Object>();
											map.put(Constant.commandText, UrlUtil.userGetPurchaseList);
											map.put(Constant.reportid, reportID);
											map.put(Constant.UUID, SharedPrefConstance.getStringValue(CustomDetailsActivity.this,
													SharedPrefConstance.UUID));
											HttpClientRequest.getHttpPost(CustomDetailsActivity.this, map,
													new CallBackListener() {

												@Override
												public void onSuccess(String msg) {
													commissionNoteInfo = ExecuteJSONUtils
															.getCommissionNoteInfoResult(
																	CustomDetailsActivity.this, msg);
													if (commissionNoteInfo != null) {
														display(reportDtails);
														View view = setComission();

														dialogShow(view);
													}
												}

												private View setComission() {
													View view = LayoutInflater.from(
															CustomDetailsActivity.this).inflate(
																	R.layout.ffb_commission_note_info, null);
													TextView agent_info = (TextView) view
															.findViewById(R.id.agent_info);
													TextView report_id = (TextView) view
															.findViewById(R.id.report_id);
													TextView commission_name = (TextView) view
															.findViewById(R.id.commission_name);
													TextView commission_number = (TextView) view
															.findViewById(R.id.commission_number);
													TextView commission_company = (TextView) view
															.findViewById(R.id.commission_company);
													TextView commission_subscribe_id = (TextView) view
															.findViewById(R.id.commission_subscribe_id);
													TextView commission_houses_money = (TextView) view
															.findViewById(R.id.commission_houses_money);
													TextView commission_money = (TextView) view
															.findViewById(R.id.commission_money);
													agent_info.setText(SharedPrefConstance
															.getStringValue(
																	getApplicationContext(),
																	SharedPrefConstance.myselfname));
													report_id.setText(commissionNoteInfo
															.getReportid());
													commission_name.setText(commissionNoteInfo
															.getCustomername());
													double agent = Double.parseDouble((commissionNoteInfo
															.getAgentbrokeragerate()));
													commission_number.setText(mul(agent, 100)+"%");
													String name=commissionNoteInfo.getAgentcompany();
													if(APP.independentname.equals(name)){
														commission_company.setText("暂无");
													}else {
														if(name.length()>6){
															StringBuffer buffer=new StringBuffer();
															buffer.append(name.substring(0, 6)+"...");
															commission_company.setText(buffer.toString());
														}else {
															commission_company.setText(name);
														}

													}

													commission_subscribe_id
													.setText(commissionNoteInfo
															.getPurchaseid());
													commission_houses_money
													.setText("¥"+StringUtils.isVerfyJE(commissionNoteInfo
															.getActiualTotal_price()));
													commission_money.setText("¥"+StringUtils.isVerfyJE(commissionNoteInfo
															.getAgentbrokeragefee()));
													return view;
												}

												@Override
												public void onFailure(Exception error, String msg) {

												}
											});

										}
									});
									dialog.setPositiveButton("取消", new OnClickListener() {

										@Override
										public void onClick(View v) {
											dialog.close();
										}
									});
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
					@Override
					public void onFailure(Exception error, String msg) {

					}
				});

			}
		}
	}
	public static double mul(double d1, double d2){        

		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	private void operationReport(final String state) {
		//首先判断传入的state的值为多少，如果是99，那么是作废。1则是带看。15则是开始借用 ，如果为1 的时候需要判断，其余都不需要判断
		if("1".equals(state)){
			showlog("当值为1的时候，走这里 ，state="+state);
			if(StringUtils.isPhone(phone.getText().toString().trim())){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(Constant.commandText, UrlUtil.userUpdateReportListl);
				map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
						SharedPrefConstance.userid));
				map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
						SharedPrefConstance.UUID));
				map.put(Constant.reportid, reportID);
				map.put(Constant.customerid, reportDtails.getCustomerid());
				map.put(Constant.state, state);
				HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						String stateValue = state;
						if (ExecuteJSONUtils.simpleUpdateInfo(
								CustomDetailsActivity.this, msg)) {

							int stateInteger = Integer.valueOf(stateValue);


							if (stateInteger == 0 || stateInteger == 1) {
								btnlook.setVisibility(View.INVISIBLE);
//								if(StringUtils.isPhone(phone.getText().toString().trim())){
									ToastUtils.showToast(context, "带看成功");
//								}
								// 需要刷新
								DisplayUtils.setUnRefresh();
							} else if (stateInteger == 15) {

								btnlook.setVisibility(View.INVISIBLE);
								ToastUtils.showToast(context, "申请结佣成功");
								// 需要刷新
								DisplayUtils.setUnRefresh();
							} else if (Constant.state_Dele.equals(state)) {
								finish();
								ToastUtils.showToast(context, "作废成功");
								// 需要刷新
								DisplayUtils.setUnRefresh();
							}

						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

					}
				});
				
			}else{
				
				final RichfitAlertDialog dialog=new RichfitAlertDialog(CustomDetailsActivity.this);
				dialog.show();
				dialog.setTitle("提示");
				dialog.setContent("该客户联系方式不是手机号，所以无法发送验证码！");
				dialog.setNegativeButton("确定", new OnClickListener() {

					@Override
					public void onClick(View v) {
						
						Map<String, Object> map = new HashMap<String, Object>();
						map.put(Constant.commandText, UrlUtil.userUpdateReportListl);
						map.put(Constant.userid, SharedPrefConstance.getStringValue(CustomDetailsActivity.this,
								SharedPrefConstance.userid));
						map.put(Constant.UUID, SharedPrefConstance.getStringValue(CustomDetailsActivity.this,
								SharedPrefConstance.UUID));
						map.put(Constant.reportid, reportID);
						map.put(Constant.customerid, reportDtails.getCustomerid());
						map.put(Constant.state, state);
						HttpClientRequest.getHttpPost(CustomDetailsActivity.this, map, new CallBackListener() {

							@Override
							public void onSuccess(String msg) {
								dialog.close();
								String stateValue = state;
								if (ExecuteJSONUtils.simpleUpdateInfo(
										CustomDetailsActivity.this, msg)) {

									int stateInteger = Integer.valueOf(stateValue);


									if (stateInteger == 0 || stateInteger == 1) {
										btnlook.setVisibility(View.INVISIBLE);
//										if(StringUtils.isPhone(phone.getText().toString().trim())){
											ToastUtils.showToast(context, "带看成功");
//										}
										// 需要刷新
										DisplayUtils.setUnRefresh();
									} else if (stateInteger == 15) {

										btnlook.setVisibility(View.INVISIBLE);
										ToastUtils.showToast(context, "申请结佣成功");
										// 需要刷新
										DisplayUtils.setUnRefresh();
									} else if (Constant.state_Dele.equals(state)) {
										finish();
										ToastUtils.showToast(context, "作废成功");
										// 需要刷新
										DisplayUtils.setUnRefresh();
									}

								}
							}

							@Override
							public void onFailure(Exception error, String msg) {

							}
						});
					}
				});
				dialog.setPositiveButton("取消", new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.close();
					}
				});
			}
			
			
		}else{
			//其余的值都走这里
			showlog("当值不为1的时候，走这里 ，state="+state);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.commandText, UrlUtil.userUpdateReportListl);
			map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
					SharedPrefConstance.userid));
			map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
					SharedPrefConstance.UUID));
			map.put(Constant.reportid, reportID);
			map.put(Constant.customerid, reportDtails.getCustomerid());
			map.put(Constant.state, state);
			HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

				@Override
				public void onSuccess(String msg) {
					String stateValue = state;
					if (ExecuteJSONUtils.simpleUpdateInfo(
							CustomDetailsActivity.this, msg)) {

						int stateInteger = Integer.valueOf(stateValue);


						if (stateInteger == 0 || stateInteger == 1) {
							btnlook.setVisibility(View.INVISIBLE);
//							if(StringUtils.isPhone(phone.getText().toString().trim())){
								ToastUtils.showToast(context, "带看成功");
//							}
							// 需要刷新
							DisplayUtils.setUnRefresh();
						} else if (stateInteger == 15) {

							btnlook.setVisibility(View.INVISIBLE);
							ToastUtils.showToast(context, "申请结佣成功");
							// 需要刷新
							DisplayUtils.setUnRefresh();
						} else if (Constant.state_Dele.equals(state)) {
							finish();
							ToastUtils.showToast(context, "作废成功");
							// 需要刷新
							DisplayUtils.setUnRefresh();
						}

					}
				}

				@Override
				public void onFailure(Exception error, String msg) {

				}
			});
			
			
		}
	
	}

	private void dialogShow(String dialogContext) {
		if (dialog == null) {
			dialog = new RichfitAlertDialog(this);
		}
		if (!dialog.isShow()) {
			dialog = new RichfitAlertDialog(this);
			dialog.show();
			dialog.setTitle("提示");
			dialog.setContent(dialogContext);
			dialog.setNegativeButton("拨打", new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.close();
					String number = SharedPrefConstance.getStringValue(
							CustomDetailsActivity.this,
							SharedPrefConstance.servicephone);
					startActivity(new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + number)));

				}
			});
			dialog.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.close();
				}
			});
		}
	}

	private void dialogShow(View view) {
		if (dialog == null) {
			dialog = new RichfitAlertDialog(this);
		}
		if (!dialog.isShow()) {

			dialog.show();
			dialog.setTitle("提示");
			dialog.setContent(view);
			dialog.setNegativeButton("确认", new OnClickListener() {

				@Override
				public void onClick(View v) {

					operationReport("15");
					dialog.close();
					dialog = null;
				}
			});
			dialog.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.close();
					dialog = null;
				}
			});
		}
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:报备申购单
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
						context, msg);

				if (comission != null && comission.getComissionid() != null
						&& comission.getComissionid().length()!=0) {
					// 审批不同
					if ("3".equals(comission.getState())) {
						btnlook.setText("申请结佣");
						btnlook.setVisibility(View.VISIBLE);
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}
}
