package com.u4.home.call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

/**
 * 社区通知
 * 
 * @author Administrator
 * 
 */
public class NoticeList extends Base {

	private ListView lv_list;
	private JSONArray jsonData = null;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	private NoticeAdapter adapter = new NoticeAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.notice_list;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lv_list.setAdapter(adapter);
		serverListNotice();
	}

	private void findId() {
		inflater = LayoutInflater.from(context);
		
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.notice_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	/**
	 * 获取社区通知列表
	 */
	public void serverListNotice() {
		RequestParams params = new RequestParams();
		params.put("c", "listnotice");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				try {
					if (1 == response.getInt("status")) {
						jsonData = response.getJSONArray("data");
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// lv_list.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				showLog("adapter.notifyDataSetChanged");
			}
		});
	}

	public class NoticeAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return jsonData == null || jsonData.length() == 0 ? 1 : jsonData.length();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (jsonData == null || jsonData.length() == 0) {
				convertView = inflater.inflate(R.layout.list_row_empty, null);
				TextView tv_empty = (TextView) convertView.findViewById(R.id.tv_empty);
				tv_empty.setText(jsonData == null ? R.string.data_loading : R.string.data_empty);
			} else {
				convertView = inflater.inflate(R.layout.notice_adapter, null);
				convertView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						try {
							Intent intent = new Intent(context, NoticeDetail.class);
							intent.putExtra("id", jsonData.getJSONObject(position).getString("id"));
							startActivity(intent);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});

				try {
					JSONObject item = jsonData.getJSONObject(position);
					TextView tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
					TextView tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);
					tv_subject.setText(item.getString("subject"));
					tv_datetime.setText(item.getString("datetime"));
					if (1 == Integer.parseInt(item.getString("unread"))) {
						ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
						iv_icon.setBackgroundResource(R.drawable.icon_email);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}
}
