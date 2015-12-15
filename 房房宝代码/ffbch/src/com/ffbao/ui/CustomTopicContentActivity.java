package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.Topic;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.TopicContentAdapter;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
/**
 * 
 * @FileName:CustomTopicContentActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomTopicContentActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 话题详情
 */
public class CustomTopicContentActivity extends BaseActivity implements
OnClickListener {

	private String topicID;
	private String reportID;
	private Topic topic;

	private int page = 0;
	private int pagecount = 20;

	private int position = 0;

	private ListView topics;
	private EditText EtResult;
	private ImageButton sendBtn;
	private String billid;
	private TopicContentAdapter adapter;
	private List<Topic> topicList = new ArrayList<Topic>();
	private String Username,Companyname,Rolename,Topiccontent,Create_time;

	private LinearLayout bottonall;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		Intent intent=getIntent();
		topicID=intent.getStringExtra(Constant.topicid);
		reportID=intent.getStringExtra(Constant.reportid);
		Username=intent.getStringExtra("Username");
		Companyname=intent.getStringExtra("Companyname");
		Rolename=intent.getStringExtra("Rolename");
		Topiccontent=intent.getStringExtra("Topiccontent");
		Create_time=intent.getStringExtra("Create_time");
		billid=intent.getStringExtra("billid");

		//		topic = (Topic) extras.get(Constant.topic);
		setContentView(R.layout.ffb_activity_customer_topic_result);
		setActionBarTitle("话题详情");
		ExitActivity.addActivity(this);
		initView();

	}

	@Override
	protected void onResume() {
		initData();
		super.onResume();
	}

	private void initView() {
		bottonall=(LinearLayout) findViewById(R.id.bottonall);
		EtResult = (EditText) findViewById(R.id.chat_content_et);
		sendBtn = (ImageButton) findViewById(R.id.chat_msg_send_btn);
		sendBtn.setOnClickListener(this);
		topics = (ListView) findViewById(R.id.topiclist);

		View header = LayoutInflater.from(this).inflate(
				R.layout.item_custom_topic_result_head, null);
		TextView  username = (TextView) header.findViewById(R.id.add_topic_username);
		TextView  rolename = (TextView) header.findViewById(R.id.add_topic_rolename);
		TextView  content = (TextView) header.findViewById(R.id.add_topic_context);
		TextView  createtime = (TextView) header.findViewById(R.id.add_topic_creattime);

		username.setText(Username);
		if("暂无".equals(Companyname) && "独立经纪人".equals(Rolename)){
			rolename.setText("独立经纪人");
		}else {
			rolename.setText(Companyname+"-"+Rolename);
		}
		String company = SharedPrefConstance.getStringValue(
				CustomTopicContentActivity.this,
				SharedPrefConstance.companyid);
		Log.i("chenghao", "单子的id="+billid);
		Log.i("chenghao", "company="+company);
		if(company.equals(billid)){
		}else{
			bottonall.setVisibility(View.GONE);
		}
		content.setText(Topiccontent);
		createtime.setText(Create_time);

//		topics.addHeaderView(header);
	}

	private void reslutTopic(final String result) {


		if(result.trim().length()==0){
			ToastUtils
			.showToast(CustomTopicContentActivity.this, "请检查回复内容");
			return ;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userAddTopicContent);
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
		map.put(Constant.topicid, topicID);
		map.put(Constant.content, result);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.simpleUpdateInfo(
						CustomTopicContentActivity.this, msg)) {
					try {
						List<Topic> topicList2 = ExecuteJSONUtils.getTopicList(
								CustomTopicContentActivity.this, msg, true);
						if (topicList2 != null) {
							topicList.addAll(topicList.size(), topicList2);
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

		// if (adapter == null) {
		adapter = new TopicContentAdapter(this, topicList);
		// }
		topics.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		topics.setSelection(position);
	}

	private void initData() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetAnswerTopicContentList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.topicid, topicID);
		map.put(Constant.pageid, page);
		map.put(Constant.pagecount, pagecount);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				showlog("返回的信息为="+msg);

				if (ExecuteJSONUtils.simpleUpdateInfo(
						CustomTopicContentActivity.this, msg)) {
					try {
						topicList = ExecuteJSONUtils.getTopicList(
								CustomTopicContentActivity.this, msg, true);
						if (topicList != null) {
							position = topicList.size();
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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.chat_msg_send_btn: // 发送回复
			String content = EtResult.getText().toString().trim();
			if (content != null && content.length()!=0) {

				// sendBtn.setEnabled(false);
				reslutTopic(content);
				EtResult.setText("");
				// sendBtn.setEnabled(true);
			} else {
				ToastUtils
				.showToast(CustomTopicContentActivity.this, "请检查回复内容");
			}
			break;
		default:
			break;
		}
	}

}
