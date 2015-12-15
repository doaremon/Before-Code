package com.u4.home.monitor;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Base;

public class MonitorFamily extends Base {
	private String list[] = { "客厅", "书房", "厨房" };
	private ListView lvList;
	private MonitorFamilyAdapter adapter = new MonitorFamilyAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.monitor_family;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lvList.setAdapter(adapter);
	}

	private void findId() {
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
	}

	public class MonitorFamilyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.length;
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
			convertView = inflater.inflate(R.layout.monitor_list_adapter, null);
			TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			tvTitle.setText(list[position]);
			return convertView;
		}

	}

}
