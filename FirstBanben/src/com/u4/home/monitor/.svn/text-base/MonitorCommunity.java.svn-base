package com.u4.home.monitor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

public class MonitorCommunity extends Base {
	private ListView lvList;
	private JSONArray jsonData = null;
	private MonitorCommunityAdapter adapter = new MonitorCommunityAdapter();
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.monitor_community;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lvList.setAdapter(adapter);
		getListCamera();
	}

	public void findId() {
		// set header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// set main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.list_simple, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		TextView tvGroupName = (TextView) findViewById(R.id.tv_group_name);
		tvGroupName.setText(res_top_finish);

		lvList = (ListView) findViewById(R.id.lv_list);
		lvList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try {
					Intent intent = new Intent(context, MyVideo.class);
					intent.putExtra("url", jsonData.getJSONObject(arg2).getString("url"));
					startActivity(intent);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class MonitorCommunityAdapter extends BaseAdapter {
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
		public View getView(int position, View convertView, ViewGroup parent) {
			if (jsonData == null || jsonData.length() == 0) {
				convertView = inflater.inflate(R.layout.list_row_empty, null);
				TextView tv_empty = (TextView) convertView.findViewById(R.id.tv_empty);
				tv_empty.setText(jsonData == null ? R.string.data_loading : R.string.data_empty);
			} else {
				convertView = inflater.inflate(R.layout.monitor_list_adapter, null);
				TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
				try {
					tvTitle.setText(jsonData.getJSONObject(position).getString("name"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}

	public void getListCamera() {
		RequestParams params = new RequestParams();
		params.put("c", "listcamera");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				try {
					if (1 == response.getInt("status")) {
						jsonData = response.getJSONArray("data");
					} else {
						jsonData = new JSONArray();
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

}
