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
 * 通话设置
 * 
 * @author Administrator
 * 
 */
public class BlockList extends Base implements OnClickListener {
	private TextView tv_group_name;
	private ImageView iv_switch_block, iv_switch_allow, iv_add;
	private ListView lv_list;

	private int type = 0;
	private JSONArray listAllow = null, listBlock = null, listCurrent = null;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	private CallSettingAdapter adapter = new CallSettingAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.call_setting;
		findId();
	}

	private void findId() {
		inflater = LayoutInflater.from(context);
		
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.block_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		iv_add = (ImageView) findViewById(R.id.iv_add);
		iv_switch_block = (ImageView) findViewById(R.id.iv_switch_block);
		iv_switch_allow = (ImageView) findViewById(R.id.iv_switch_allow);
		tv_group_name = (TextView) findViewById(R.id.tv_group_name);
		lv_list = (ListView) findViewById(R.id.lv_list);

		iv_add.setOnClickListener(this);
		iv_switch_block.setOnClickListener(this);
		iv_switch_allow.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		lv_list.setAdapter(adapter);
		serverListBlock();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_add:
			Intent intent = new Intent(context, BlockAppender.class);
			intent.putExtra("type", type);
			startActivity(intent);
			break;
		case R.id.iv_switch_block:
		case R.id.iv_switch_allow:
			serverSetBlock(type == 1 ? "2" : "1");
			break;
		default:
			break;
		}
	}

	/**
	 * 刷新列表
	 */
	private void refreshList() {
		listCurrent = type == 1 ? listBlock : (type == 2 ? listAllow : new JSONArray());
		iv_switch_block.setImageResource(type == 1 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
		iv_switch_allow.setImageResource(type == 2 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
		tv_group_name.setText(type == 1 ? R.string.block_list : R.string.allow_list);
		adapter.notifyDataSetChanged();
		showLog("refreshList : adapter.notifyDataSetChanged = " + adapter.toString());
	}

	/**
	 * 获取屏蔽名单记录
	 */
	public void serverListBlock() {
		showLog("serverListBlock");
		RequestParams params = new RequestParams();
		params.put("c", "listblock");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 == response.getInt("status")) {
						JSONObject data = response.getJSONObject("data");
						type = Integer.parseInt(data.getString("type"));
						listBlock = data.getJSONArray("black");
						listAllow = data.getJSONArray("white");
						refreshList();
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 设置屏蔽模式
	 * 
	 * @param type
	 */
	public void serverSetBlock(final String mode) {
		RequestParams params = new RequestParams();
		params.put("c", "setblock");
		params.put("type", mode);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 == response.getInt("status")) {
						type = Integer.parseInt(mode);
						refreshList();
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 删除屏蔽名单
	 */
	private void serverDelBlock(final int position) {
		String id = "0";
		try {
			id = listCurrent.getJSONObject(position).getString("id");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		RequestParams params = new RequestParams();
		params.put("c", "delblock");
		params.put("id", id);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 == response.getInt("status")) {
						if (type == 1) {
							listBlock = removeJSONArray(listBlock, position);
						}
						if (type == 2) {
							listAllow = removeJSONArray(listAllow, position);
						}
						refreshList();
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private class CallSettingAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listCurrent == null || listCurrent.length() == 0 ? 1 : listCurrent.length();
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
			if (listCurrent == null || listCurrent.length() == 0) {
				convertView = inflater.inflate(R.layout.list_row_empty, null);
				TextView tv_empty = (TextView) convertView.findViewById(R.id.tv_empty);
				tv_empty.setText(listCurrent == null ? R.string.data_loading : R.string.data_empty);
			} else {
				convertView = inflater.inflate(R.layout.block_adapter, null);
				TextView tv_room = (TextView) convertView.findViewById(R.id.tv_room);
				ImageView iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
				iv_delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						serverDelBlock(position);
					}
				});
				try {
					JSONObject item = listCurrent.getJSONObject(position);
					tv_room.setText(item.getString("room"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}

	}

}
