package com.u4.home.safe;

import java.util.ArrayList;
import java.util.List;

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
import com.u4.home.common.Base;
import com.u4.home.db.Zone_DB;
import com.u4.home.db.Zone_Shitilei;

public class DefenceZone extends Base {
	private ListView lv_list;
	private Zone_DB db;
	private List<Zone_Shitilei> list = new ArrayList<Zone_Shitilei>();
	private DefendAdapter adapter = new DefendAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.defence_setting;
		
		db = new Zone_DB(context);
		
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		db.open();
		Zone_Shitilei[] zone_Shitilei = db.queryAllData();
		for (int c = 0; c < zone_Shitilei.length; c++) {
			list.add(zone_Shitilei[c]);
		}
		db.close();
		
		lv_list.setAdapter(adapter);
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.defence_zone, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	private class DefendAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
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
			convertView = inflater.inflate(R.layout.defence_zone_adapter, null);
			TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			TextView tv_subtitle = (TextView) convertView.findViewById(R.id.tv_subtitle);
			ImageView iv_switch = (ImageView) convertView.findViewById(R.id.iv_switch);
			tv_title.setText(list.get(position).id + "# " + list.get(position).name);
			tv_subtitle.setText(list.get(position).type);
			iv_switch.setImageResource(list.get(position).defended == 1 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
			iv_switch.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					list.get(position).defended = list.get(position).defended == 1 ? 0 : 1;
					db.open();
					db.updateDefended(list.get(position).id, list.get(position).defended);
					db.close();
					adapter.notifyDataSetChanged();
				}
			});
			return convertView;
		}

	}

}
