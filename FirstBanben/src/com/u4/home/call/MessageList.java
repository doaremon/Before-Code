package com.u4.home.call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

public class MessageList extends Base {
	private ListView lv_list;
	private JSONArray jsonData = null;
	private MessageAdapter adapter = new MessageAdapter();;
	protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.message_list;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lv_list.setAdapter(adapter);
		serverListMessage();
	}

	public void findId() {
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.message_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
		lv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String messageID = "0", messageURL = "";
				try {
					messageID = jsonData.getJSONObject(position).getString("id");
					messageURL = jsonData.getJSONObject(position).getString("url");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				Intent intent = new Intent(context, MessagePlayer.class);
				intent.putExtra("id", messageID);
				intent.putExtra("url", messageURL);
				startActivity(intent);
			}
		});
	}

	/**
	 * 获取视频留言列表
	 */
	public void serverListMessage() {
		RequestParams params = new RequestParams();
		params.put("c", "listmessage");
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
	 * 删除视频留言
	 * @param position
	 */
	public void serverDelMessage(final int position) {
		String id = "0";
		try {
			id = jsonData.getJSONObject(position).getString("id");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		RequestParams params = new RequestParams();
		params.put("c", "delmessage");
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

	public class MessageAdapter extends BaseAdapter {
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
				convertView = inflater.inflate(R.layout.message_adapter, null);
				TextView tv_room = (TextView) convertView.findViewById(R.id.tv_room);
				TextView tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);
				ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
				ImageView iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
				iv_delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						serverDelMessage(position);
					}
				});
				try {
					JSONObject item = jsonData.getJSONObject(position);
					tv_room.setText(item.getString("room"));
					tv_datetime.setText(item.getString("datetime"));
					if (1 == Integer.parseInt(item.getString("unread"))) {
						iv_icon.setImageResource(R.drawable.ic_video_away);
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			return convertView;
		}
	}
}
