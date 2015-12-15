package com.u4.home.call;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

public class NoticeDetail extends Base {
	private String id = "0";
	private TextView tv_subject, tv_datetime, tv_content;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.notice_list;
		id = getIntent().getStringExtra("id");
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		serverGetNotice(id);
	}

	public void findId() {
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.notice_detail, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		tv_subject = (TextView) findViewById(R.id.tv_subject);
		tv_datetime = (TextView) findViewById(R.id.tv_datetime);
		tv_content = (TextView) findViewById(R.id.tv_content);
	}

	private void serverGetNotice(String id) {
		RequestParams params = new RequestParams();
		params.put("c", "getnotice");
		params.put("id", id);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				try {
					if (1 == response.getInt("status")) {
						JSONObject data = response.getJSONObject("data");
						tv_subject.setText(data.getString("subject"));
						tv_datetime.setText(data.getString("datetime"));
						tv_content.setText(data.getString("content"));
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
