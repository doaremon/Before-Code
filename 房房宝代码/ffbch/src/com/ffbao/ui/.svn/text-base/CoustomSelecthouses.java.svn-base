package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ffbao.activity.R;
import com.ffbao.entity.NewBuilding;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.CoustomSelecthousesAdapter;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
/**
 * 重修修改报备单详情的时候，选择一向楼盘
 * @author chenghao
 *
 */
public class CoustomSelecthouses extends BaseActivity implements OnItemClickListener{
	private int requestCode = -1;
	private String id;
	private String level;
	private String target;
	private String agenttype;
	private ListView listView; 
	private List<NewBuilding> buildings = null;
	private List<NewBuilding> targetList = new ArrayList<NewBuilding>();
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_customerselect);
		ExitActivity.addActivity(this);
		Intent intent = getIntent();
		requestCode =intent.getExtras().getInt(Constant.requestCode);
		agenttype =intent.getStringExtra("agenttype");
		showlog("得到的agenttype="+agenttype);
		if (Constant.recommended_houses_intention == requestCode) {
			id = intent.getStringExtra(Constant.id);
			level = intent.getStringExtra(Constant.level);
		}
		if (requestCode != -1) {
			target = intent.getStringExtra(Constant.target);
		}

		listView = (ListView) findViewById(R.id.lv_houses);
		getStringList();
		setAdapter();
		intent = new Intent();
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 根据requestCode 判断是否 targetList
	 */
	private void getStringList() {

		switch (requestCode) {
		case Constant.recommended_houses_intention:
			setActionBarTitle("选择意向楼盘");
			if (StringUtils.isNull(level) && StringUtils.isNull(id)) {
				getIntentBuildingArray(level, id);
			} else {
				ToastUtils.showToast(context, "意向城市未确定");
			}
			break;


		default:
			break;
		}
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param level
	 * @param id
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取意向楼盘
	 */
	private void getIntentBuildingArray(String level, String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetWantBuildings);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.id, id);
		map.put(Constant.level, level);

		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				try {
					buildings = ExecuteJSONUtils.getBuildings(context, msg);
					for (int i = 0; i < buildings.size(); i++) {
						NewBuilding building = buildings.get(i);
						targetList.add(building);
					}
					if (targetList.size() == 0) {

						ToastUtils.showToast(context, "意向楼盘未进行维护");
					}
					setAdapter();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: set Adapter 设置适配器
	 */
	private void setAdapter() {

		CoustomSelecthousesAdapter adapter = new CoustomSelecthousesAdapter(context,
				targetList, target,agenttype);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(this);

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		intent.putExtra(Constant.id, targetList.get(position).getId());
		intent.putExtra("content", targetList.get(position).getValue());
		intent.putExtra("Agenttype", agenttype);
		//当为独立经纪人或者是1的时候都返回独立经纪人的返点
		if("1".equals(agenttype) || "独立经纪人".equals(agenttype)){
			intent.putExtra("persion", targetList.get(position).getPersonrebate());
		}else {
			intent.putExtra("persion", "");
		}
		setResult(-1, intent);
		finish();
	}

}
