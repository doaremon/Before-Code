package com.u4.home.safe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Base;
import com.u4.home.db.Warning_DB;

public class WarningList extends Base {
	private ListView lv_list;
	private JSONArray jsonData = null;
	private Warning_DB db;
	private WarningAdapter adapter = new WarningAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.warning_list;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// get data
		db = new Warning_DB(context);
		db.open();
		jsonData = db.warningList();
		db.close();

		// set list
		lv_list.setAdapter(adapter);
	}

	public void findId() {
		inflater = LayoutInflater.from(context);

		// set header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// set main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.warning_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	public class WarningAdapter extends BaseAdapter {

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
				convertView = inflater.inflate(R.layout.warning_adapter, null);
				TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
				TextView tv_subtitle = (TextView) convertView.findViewById(R.id.tv_subtitle);
				TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
				try {
					JSONObject item = jsonData.getJSONObject(position);
					tv_title.setText(item.getString("name"));
					tv_subtitle.setText(item.getString("type"));
					tv_content.setText(item.getString("datetime").replace(" ", "\n"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}

}
