package com.u4.home.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Equipment_DB;

@SuppressLint("HandlerLeak")
public class Curtain extends Base {
	private Equipment_DB db;
	private EquipmentAdapter adapter = new EquipmentAdapter();
	private XitongDialog dialog;
	private LayoutInflater inflater;
	private JSONArray jsonData;

	private GridView gv_list;
	private int type_id = 3; // 设备类型：窗帘
	private String array[] = { "1", "2", "3", "4" };
	private String unit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.control_curtain;
		db = new Equipment_DB(context);
		dialog = new XitongDialog(context);
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// set usb handler
		Appcontext.handlerUSB = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				showLog("返回的数据为=" + msg.obj.toString());
			}
		};

		db.open();
		jsonData = db.equipmentList(type_id);
		db.close();

		gv_list.setAdapter(adapter);
		gv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				try {
					if (position == jsonData.length()) {
						dialog.show("equipment");
						dialog.et_equipment_code.setText("");
						dialog.et_equipment_name.setText("");
						ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(context, R.array.equipment_unit, android.R.layout.simple_spinner_item);
						adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						dialog.so_equipment_unit.setAdapter(adapters);
						dialog.so_equipment_unit.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
								unit = array[position];
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								unit = array[0];
							}
						});

						dialog.btn_ok.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
								String code = dialog.et_equipment_code.getText().toString().trim();
								String name = dialog.et_equipment_name.getText().toString().trim();
								if (!"".equals(name)) {
									db.open();
									db.equipmentAdd(code, unit, name, "", type_id);
									jsonData = db.equipmentList(type_id);
									adapter.notifyDataSetChanged();
									db.close();
								}
							}
						});
					} else {
						JSONObject item = (JSONObject) jsonData.get(position);

						int status = item.getInt("status") == 1 ? 0 : 1;

						String cmd = Appcontext.serviceUSB.createData(type_id, item.getString("code"), item.getString("unit"), status);
						if (Appcontext.serviceUSB != null && cmd.length() == 22)
							Appcontext.serviceUSB.sendData(cmd);

						db.open();
						db.equipmentSet(item.getInt("id"), status);
						jsonData.put(position, db.equipmentGet(item.getInt("id")));
						db.close();
						adapter.notifyDataSetChanged();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		gv_list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v, final int position, long arg3) {
				if (jsonData.length() > position) {
					dialog.show("delete");
					dialog.btn_ok.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							try {
								dialog.dismiss();
								db.open();
								db.equipmentDel(jsonData.getJSONObject(position).getInt("id"));
								jsonData = db.equipmentList(type_id);
								db.close();
								adapter.notifyDataSetChanged();
							} catch (SQLiteException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
				}
				return true;
			}
		});
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.equipment_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		gv_list = (GridView) findViewById(R.id.gv_control);
	}

	public class EquipmentAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return jsonData.length() + 1;
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
			if (position == jsonData.length()) {
				convertView = inflater.inflate(R.layout.equipment_append, null);
			} else {
				convertView = inflater.inflate(R.layout.equipment_adapter, null);
				ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
				TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				try {
					JSONObject item = jsonData.getJSONObject(position);
					tv_name.setText(item.getString("name"));
					iv_icon.setImageResource(item.getInt("status") == 1 ? R.drawable.icon_control_curtain_on : R.drawable.icon_control_curtain_off);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}

}
