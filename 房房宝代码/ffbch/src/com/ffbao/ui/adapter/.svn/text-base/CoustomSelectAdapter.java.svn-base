package com.ffbao.ui.adapter;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.Building;
import com.ffbao.entity.Report;
import com.ffbao.entity.WantCity;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
 * @Funtion: 修改和添加报备时候  进行选择项展示
 */
public class CoustomSelectAdapter extends BaseAdapter {

	private Context context;
	private List<?> reports;
	private String target;

	public CoustomSelectAdapter(Context context, List<?> reports, String target) {

		this.context = context;
		this.reports = reports;
		this.target = target;
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
					R.layout.item_customselect, null);
			ViewHolder holder = new ViewHolder();
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_content);
			holder.tag = (ImageView) convertView
					.findViewById(R.id.iv_content_tag);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Object object = reports.get(position);
		Log.i("chenghao", "这个object是个啥？="+object.toString());
		if (object instanceof String) {
			holder.content.setText(object.toString());
		} else if (object instanceof WantCity) {
			WantCity wantCitry = (WantCity) object;
			holder.content.setText(wantCitry.getValue());
		} else if (object instanceof Building) {
			Building building = (Building) object;
			holder.content.setText(building.getValue());
		}
//		if (StringUtils.isNull(target)) {
//			if (target.equals(holder.content.getText().toString())) {
//				holder.tag.setVisibility(View.VISIBLE);
//			}
//		}
		return convertView;
	}

	public static class ViewHolder {

		TextView content;
		ImageView tag;
	}

}
