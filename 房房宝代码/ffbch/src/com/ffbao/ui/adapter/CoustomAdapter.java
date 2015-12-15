package com.ffbao.ui.adapter;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.Report;
import com.ffbao.util.ToastUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * @FileName:CoustomAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CoustomAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取report列表
 */
public class CoustomAdapter extends BaseAdapter {

	private Context context;
	private List<Report> reports;

	public CoustomAdapter(Context context, List<Report> reports) {

		this.context = context;
		this.reports = reports;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ffb_customer_item1, null);
			ViewHolder holder = new ViewHolder();
			holder.customerName = (TextView) convertView
					.findViewById(R.id.customerName);
			holder.customerPhone = (TextView) convertView
					.findViewById(R.id.customerPhone);
			holder.customerTime = (TextView) convertView
					.findViewById(R.id.customerTime);
			holder.customerDes = (TextView) convertView
					.findViewById(R.id.customerDes);
			holder.reportCity = (TextView) convertView
					.findViewById(R.id.reportCity);
			holder.reportBuilding = (TextView) convertView
					.findViewById(R.id.reportBuilding);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Report report = reports.get(position);
		holder.customerName.setText(report.getName());
		holder.customerPhone.setText(report.getPhone());
		holder.customerDes.setText(report.getState());
		holder.customerTime.setText(report.getCreatedate());
		holder.reportCity.setText(report.getIntention_city());
		holder.reportBuilding.setText(report.getIntention_building());
		
		return convertView;
	}

	public static class ViewHolder {

		TextView customerName;
		TextView customerPhone;
		TextView customerTime;
		TextView customerDes;
		TextView reportCity;
		TextView reportBuilding;
	}

}
