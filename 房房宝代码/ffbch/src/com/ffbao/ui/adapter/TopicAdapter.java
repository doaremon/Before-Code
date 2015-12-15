package com.ffbao.ui.adapter;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.Topic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * @FileName:TopicAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:TopicAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取主题列表
 */
public class TopicAdapter extends BaseAdapter {

	private Context context;
	private List<Topic> topics;
	public TopicAdapter(Context context, List<Topic> topics ,boolean flag) {

		this.context = context;
		this.topics = topics;
	}

	@Override
	public int getCount() {
		return topics.size();
	}

	@Override
	public Object getItem(int position) {
		return topics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_custom_topic, null);
			ViewHolder holder = new ViewHolder();
			holder.content = (TextView) convertView
					.findViewById(R.id.add_topic_context);
			holder.creattime = (TextView) convertView
					.findViewById(R.id.add_topic_creattime);
			holder.username = (TextView) convertView
					.findViewById(R.id.add_topic_username);
			holder.rolename = (TextView) convertView
					.findViewById(R.id.add_topic_rolename);
			holder.resultcount = (TextView) convertView
					.findViewById(R.id.add_topic_result_count);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Topic report = topics.get(position);
		holder.content.setText(report.getTopiccontent());
		holder.creattime.setText(report.getCreate_time());
		holder.username.setText(report.getUsername());
		Log.i("chenghao", "返回的getRolename="+report.getRolename());
		if("暂无".equals(report.getCompanyname()) && "独立经纪人".equals(report.getRolename())){
			holder.rolename.setText("独立经纪人");
		}else {
			holder.rolename.setText(report.getCompanyname()+"-"+report.getRolename());
		}

		if(report.getAnswercount() !=null){

			holder.resultcount.setText(report.getAnswercount()+"回复");
		}else{
			holder.resultcount.setText("0 回复");
		}
		return convertView;
	}

	public static class ViewHolder {

		TextView content;
		TextView creattime;
		TextView username;
		TextView rolename;
		TextView resultcount;
	}

}
