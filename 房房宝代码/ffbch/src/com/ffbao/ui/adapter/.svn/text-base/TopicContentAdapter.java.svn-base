package com.ffbao.ui.adapter;

import java.util.List;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.entity.Topic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * @FileName:TopicContentAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:TopicContentAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取主题内容列表
 */
public class TopicContentAdapter extends BaseAdapter {

	private Context context;
	private List<Topic> topics;
	public TopicContentAdapter(Context context, List<Topic> topics ) {

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

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_custom_topic_result_head, null);
			ViewHolder holder = new ViewHolder();
			holder.content = (TextView) convertView
					.findViewById(R.id.add_topic_context);
			holder.creattime = (TextView) convertView
					.findViewById(R.id.add_topic_creattime);
			holder.username = (TextView) convertView
					.findViewById(R.id.add_topic_username);
			holder.rolename = (TextView) convertView
					.findViewById(R.id.add_topic_rolename);
			//			holder.resultcount = (TextView) convertView
			//					.findViewById(R.id.add_topic_result_count);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Topic topic = topics.get(position);
		//		if(position == 0){
		//			setViewHolder(holder,true);
		//			holder.content.setText(topic.getTopiccontent());
		//			holder.creattime.setText(topic.getCreate_time());
		//			holder.username.setText(topic.getUsername());
		//			holder.rolename.setText(topic.getCompanyname()+"-"+topic.getRolename());
		//		}else{
		holder.content.setText(topic.getAnswercontent());
		holder.creattime.setText(topic.getCreate_time());
		holder.username.setText(topic.getUsername());

		if(APP.independentname.equals(topic.getCompanyname())){
			holder.rolename.setText(topic.getRolename());
			holder.rolename.setTextColor(context.getResources().getColor(R.color.item_custom_name));
		}else {
			holder.rolename.setText(topic.getCompanyname()+"-"+topic.getRolename());
			holder.rolename.setTextColor(context.getResources().getColor(R.color.item_custom_name));
		}

		//		setViewHolder(holder,false);
		//		}

		return convertView;
	}

	private void setViewHolder(ViewHolder holder,boolean flag){

		if(flag){
			setTextViewColor(holder.content);
			setTextViewColor(holder.creattime);
			setTextViewColor(holder.username);
			setTextViewColor(holder.rolename);
		}else{

			setTextViewColorNormal(holder.content);
			setTextViewColorNormal(holder.creattime);
			setTextViewColorNormal(holder.username);
			setTextViewColorNormal(holder.rolename);
		}
	}
	private void setTextViewColor(TextView tager){
		tager.setTextColor(context.getResources().getColor(R.color.black));
		//		tager.setTextSize(20);
	}

	private void setTextViewColorNormal(TextView tager){
		tager.setTextColor(context.getResources().getColor(R.color.back_title_color));
		tager.setTextSize(18);
	}
	public static class ViewHolder {

		TextView content;
		TextView creattime;
		TextView username;
		TextView rolename;
		//		TextView resultcount;

	}

}
