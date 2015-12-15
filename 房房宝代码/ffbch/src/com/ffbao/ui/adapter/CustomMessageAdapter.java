package com.ffbao.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.ReportMessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * @FileName:CustomMessageAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomMessageAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 报备单列表
 */
public class CustomMessageAdapter extends BaseAdapter {

	private Context context;
	private List<ReportMessage> reportMessageList;

	public CustomMessageAdapter(Context context,
			List<ReportMessage> reportMessageList) {

		this.context = context;
		this.reportMessageList = reportMessageList;
	}

	@Override
	public int getCount() {
		return reportMessageList.size();
	}

	@Override
	public ReportMessage getItem(int position) {
		return reportMessageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_custom_message, null);

			ViewHolder holder = new ViewHolder();
			holder.createTime = (TextView) convertView
					.findViewById(R.id.createtime);
			holder.messageContent = (TextView) convertView
					.findViewById(R.id.messagecontent);
			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
			ReportMessage reportMessage = reportMessageList.get(position);
			if(!"1".equals(reportMessage.getConfirmed())){
//				int color = context.getResources().getColor(R.color.black);
				holder.createTime.setTextColor(context.getResources().getColor(R.color.black));
				holder.messageContent.setTextColor(context.getResources().getColor(R.color.black));
			}else{
//				context.getResources().getColor(R.color.trend_list_bg);
				holder.createTime.setTextColor(	context.getResources().getColor(R.color.back_title_color));
				holder.messageContent.setTextColor(	context.getResources().getColor(R.color.back_title_color));
			}
			holder.createTime.setText(reportMessage.getCreate_time());
			holder.messageContent.setText(reportMessage.getMessagecontent());
		return convertView;
	}

	public static class ViewHolder {

		TextView messageContent;
		TextView createTime;
	}
}
