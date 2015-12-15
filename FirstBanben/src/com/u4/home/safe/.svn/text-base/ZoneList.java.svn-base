package com.u4.home.safe;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Zone_DB;
import com.u4.home.db.Zone_Shitilei;

public class ZoneList extends Base {
	private Zone_DB db;
	private ListView lv_list;
	private List<Zone_Shitilei> list = new ArrayList<Zone_Shitilei>();
	private XitongDialog dialog;
	private String[] zoneTypes = { "红外", "烟感", "一氧化碳" };
	private ArrayAdapter<String> zoneAdapter;
	private ZoneAdapter adapter = new ZoneAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.defence_zone_setting;
		
		db = new Zone_DB(context);
		dialog = new XitongDialog(context);
		
		findId();
		
		zoneAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, zoneTypes);
		zoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		lv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				dialog.show("zone");
				dialog.et_zonename.setText(list.get(arg2).name);
				dialog.so_zonetype.setAdapter(zoneAdapter);
				dialog.so_zonetype.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						dialog.so_zonetype.setTag(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				dialog.btn_ok.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();

						list.get(arg2).name = dialog.et_zonename.getText().toString().trim();
						list.get(arg2).type = zoneTypes[Integer.parseInt(dialog.so_zonetype.getTag().toString())];

						db.open();
						db.updateInfo(list.get(arg2).id, list.get(arg2).name, list.get(arg2).type);
						db.close();
						adapter.notifyDataSetChanged();
					}
				});
			}
		});
	}

	public void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.zone_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	public class ZoneAdapter extends BaseAdapter {
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
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.zone_adapter, null);
			TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			tv_title.setText(list.get(position).id + "# " + list.get(position).name);
			tv_content.setText(list.get(position).type);
			return convertView;
		}

	}

}
