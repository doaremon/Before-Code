package com.ffbao.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.NewBuilding;

/**
 * @FileName:CoustomSelectAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CoustomSelectAdapter.java
 * @author lee
 * @create Date2014-11-13
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 修改和添加报备时候  进行楼盘选择项展示以及显示佣金点
 */
public class CoustomSelecthousesAdapter extends BaseAdapter {

	private Context context;
	private List<NewBuilding> reports;
	private String target;
	private String agenttype;

	public CoustomSelecthousesAdapter(Context context, List<NewBuilding> reports, String target,String agenttype) {

		this.context = context;
		this.reports = reports;
		this.target = target;
		this.agenttype=agenttype;
	}

	@Override
	public int getCount() {
		return reports.size();
	}

	@Override
	public Object getItem(int position) {
		return reports.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_customselecthouse, null);
			ViewHolder holder = new ViewHolder();
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_content);
			holder.tag = (TextView) convertView
					.findViewById(R.id.iv_content_tag);
			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.content.setText(reports.get(position).getValue());
		//当传过的来agenttype值为1，和独立经纪人的时候，则显示佣金点。
		//当传过的来agenttype值为0，和经纪人的时候。不显示佣金点。
		if("1".equals(agenttype)){
			holder.tag.setText("独立经纪人："+reports.get(position).getPersonrebate()+"%/套");
		}else if("独立经纪人".equals(agenttype)){
			holder.tag.setText("独立经纪人："+reports.get(position).getPersonrebate()+"%/套");
		}


		return convertView;
	}
	public static class ViewHolder {

		TextView content;
		TextView tag;
	}

}
