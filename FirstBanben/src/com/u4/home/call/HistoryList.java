package com.u4.home.call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * 呼叫记录
 * 
 * @author Administrator
 * 
 */
public class HistoryList extends Base {

	private ListView lv_list;
	private JSONArray jsonData = null;
	private HistoryAdapter adapter = new HistoryAdapter();
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.history_list;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lv_list.setAdapter(adapter);
		serverListHistory();
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.history_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	/**
	 * 获取呼叫记录
	 */
	public void serverListHistory() {
		RequestParams params = new RequestParams();
		params.put("c", "listhistory");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 == response.getInt("status")) {
						jsonData = response.getJSONArray("data");
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				adapter.notifyDataSetChanged();
				showLog("adapter.notifyDataSetChanged");
			}
		});
	}

	/**
	 * 删除呼叫记录
	 * 
	 * @param position
	 */
	public void serverDelHistory(final int position) {
		String id = "0";
		try {
			id = jsonData.getJSONObject(position).getString("id");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		RequestParams params = new RequestParams();
		params.put("c", "delhistory");
		params.put("id", id);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 == response.getInt("status")) {
						jsonData = removeJSONArray(jsonData, position);
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				adapter.notifyDataSetChanged();
				showLog("adapter.notifyDataSetChanged");
			}
		});
	}

	public class HistoryAdapter extends BaseAdapter {
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
				convertView = inflater.inflate(R.layout.history_adapter, null);
				TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
				ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
				ImageView iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
				iv_delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						serverDelHistory(position);
					}
				});
				try {
					JSONObject item = jsonData.getJSONObject(position);
					tv_name.setText(item.getString("room"));
					tv_time.setText(item.getString("datetime"));

					int type = Integer.parseInt(item.getString("type"));
					iv_icon.setImageResource(type == 3 ? R.drawable.icon_arrow_missed : (type == 2 ? R.drawable.icon_arrow_received : R.drawable.icon_arrow_dialed));
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			return convertView;
		}
	}
}
