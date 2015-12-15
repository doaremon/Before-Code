package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.entity.HouseMessage;
import com.ffbao.entity.Report;
import com.ffbao.entity.ReportMessage;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.CustomMessageAdapter;
import com.ffbao.ui.adapter.HouseMessageAdapter;
import com.ffbao.ui.widget.XListView;
import com.ffbao.ui.widget.XListView.IXListViewListener;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
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
public class HouseMessageActivity extends BaseActivity implements
		IXListViewListener {

	private XListView message;
	private List<HouseMessage> houseMessages = new ArrayList<HouseMessage>();
	private int position = 0;

	private int page = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_custom_message);
		ExitActivity.addActivity(this);
		setActionBarTitle("活动详情");
		message = (XListView) findViewById(R.id.lv_customer_message);
		message.setXListViewListener(this);

		initData();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-6
	 * @author:lee
	 * @Funtion:获取楼盘消息界面
	 */
	private void initData() {
		Map<String, Object> map = new HashMap<String, Object>();

		HttpClientRequest.getHttpPost(context, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void setAdapter() {
		HouseMessageAdapter adapter = new HouseMessageAdapter(houseMessages,
				this);
		message.setAdapter(adapter);

		if (houseMessages.size() >= position) {
			message.setSelection(position);
		}
	}

	@Override
	public void onRefresh() {

		houseMessages = new ArrayList<HouseMessage>();
		page = 0;
		initData();
		message.stopRefresh();

	}

	@Override
	public void onLoadMore() {
		page += 1;
		initData();
		message.stopLoadMore();
	}
}
