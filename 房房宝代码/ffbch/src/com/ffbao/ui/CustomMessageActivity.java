package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.entity.Report;
import com.ffbao.entity.ReportMessage;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.CustomMessageAdapter;
import com.ffbao.ui.widget.XListView;
import com.ffbao.ui.widget.XListView.IXListViewListener;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @FileName:CustomMessageActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomMessageActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 与消息提示相关 消息列表
 */
public class CustomMessageActivity extends BaseActivity implements
		IXListViewListener {

	private XListView message;
	private int page = 0;
	private int pageCount = 20;
	private int position = 0;

	private List<ReportMessage> reportMessageList = new ArrayList<ReportMessage>();
	private View noData;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_custom_message);
		ExitActivity.addActivity(this);
		setActionBarTitle("提醒");
		initData();
		message = (XListView) findViewById(R.id.lv_customer_message);
		noData = findViewById(R.id.noData);
		setAdapter();
		message.setXListViewListener(this);
		message.setPullLoadEnable(false);
	}

	private void initData() {
		// commandText=userGetMessagesCount&userid=100005&UUID=d0d4c193-cac5-4e6b-85b7-620921d01e82
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetMessages);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.pageid, page + "");
		map.put(Constant.pagecount, pageCount + "");
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (page == 0) {
					reportMessageList = new ArrayList<ReportMessage>();
					// message.stopRefresh();
				}
				
				position = reportMessageList.size();
				
				List<ReportMessage> reportMessageListNEW = ExecuteJSONUtils
						.getReportMessageList(CustomMessageActivity.this, msg);
				if (reportMessageListNEW != null) {
					if (reportMessageListNEW.size() == 0
							&& reportMessageList.size() != 0) {
						ToastUtils.showToast(context, "没有历史消息了");
					}
					reportMessageList.addAll(reportMessageListNEW);
					if (reportMessageList.size() != 0) {

						// reportMessageList.addAll(reportMessageListNEW);
						setAdapter();
					} else {
						message.setVisibility(View.GONE);
						noData.setVisibility(View.VISIBLE);
					}

					if (page == 0) {

						message.stopRefresh();
						if (reportMessageList.size() > 10) {
							message.setPullLoadEnable(true);
						}
					} else {
						message.stopLoadMore();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {
				message.stopRefresh();
				message.stopLoadMore();
			}
		});

	}

	private void setAdapter() {
		CustomMessageAdapter adapter = new CustomMessageAdapter(this,
				reportMessageList);
		message.setAdapter(adapter);
		if (reportMessageList.size() >= position) {
			message.setSelection(position);
		}
		message.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ReportMessage message = (ReportMessage) parent.getAdapter()
						.getItem(position);
				if (message != null && !"0".equals(message.getReportid())) {
					Intent intent = new Intent();
					intent.setClass(CustomMessageActivity.this,
							CustomDetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString(Constant.companyID, message.getReportid());
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	@Override
	public void onRefresh() {

		page = 0;
		initData();
		// message.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		page += 1;
		initData();
		// message.stopLoadMore();
	}
}
