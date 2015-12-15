package com.u4.home.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
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
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Equipment_DB;

public class Infrared extends Base {
	private Equipment_DB db;
	private EquipmentAdapter adapter = new EquipmentAdapter();
	private XitongDialog dialog;
	private LayoutInflater inflater;
	private JSONArray jsonData;

	private GridView gv_list;
	private int type_id = 4; // 设备类型：红外
	private int minCmd = 64, lenCmd = 8; // 最小指令64，最大指令171，指令数量8
	private String unit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.control_infrared;
		db = new Equipment_DB(context);
		dialog = new XitongDialog(context);
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		db.open();
		jsonData = db.equipmentList(type_id); // 根据类型获取设备列表
		showLog("jsonData : " + jsonData);
		db.close();

		gv_list.setAdapter(adapter);
		gv_list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				// TODO Auto-generated method stub
				dialog.show("delete");
				dialog.btn_ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						try {
							dialog.dismiss();
							db.open();
							db.equipmentDel(jsonData.getJSONObject(arg2).getInt("id"));
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
				return true;
			}
		});



		gv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				if (position == jsonData.length()) {
					dialog.show("infrared");
					dialog.et_equipment_code.setText("");
					dialog.et_equipment_name.setText("");
					ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(context, R.array.infrared_unit, android.R.layout.simple_spinner_item);
					adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					dialog.so_infrared_unit.setAdapter(adapters);
					dialog.so_infrared_unit.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							unit = (position + 1) + "";
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							unit = 1 + "";
						}
					});
					dialog.btn_ok.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							String code = dialog.et_infrared_code.getText().toString().trim();
							String name = dialog.et_infrared_name.getText().toString().trim();
							if (!"".equals(name)) {
								db.open();

								int startCmd = minCmd;
								String cmds = db.equipmentCmds(code);
								System.out.println("cmds= :" + cmds);
								System.out.println(cmds != null);
								if (cmds != null) {
									String[] curCmds = cmds.split(",");
									for (int i = 0; i < curCmds.length; i++) {
										if (startCmd <= Integer.parseInt(curCmds[i])) {
											startCmd = Integer.parseInt(curCmds[i]) + 1;
										}
									}
								}

								String newCmds = "";
								for (int i = 0; i < lenCmd; i++) {
									newCmds += (startCmd + i) + (i == lenCmd - 1 ? "" : ",");
								}

								db.equipmentAdd(code, unit, name, newCmds, type_id);
								jsonData = db.equipmentList(type_id);
								db.close();
								adapter.notifyDataSetChanged();
							}
						}
					});
				} else {
					try {
						Intent intent;
						JSONObject jsonObjects = (JSONObject) jsonData.get(position);
						showLog("jsonObjects : " + jsonObjects);

						intent = new Intent(context, InfraredRemote.class);
						intent.putExtra("name", jsonObjects.getString("name"));
						intent.putExtra("unit", jsonObjects.getString("unit"));
						intent.putExtra("cmds", jsonObjects.getString("cmds"));
						intent.putExtra("code", jsonObjects.getString("code"));
						startActivity(intent);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
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
					iv_icon.setImageResource(item.getString("unit").equals("1") ? R.drawable.bnt_tv : R.drawable.bnt_kt);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}

}
