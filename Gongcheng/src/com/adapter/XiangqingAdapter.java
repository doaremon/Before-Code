package com.adapter;

import java.util.List;

import com.example.gongcheng.R;
import com.example.gongcheng.R.id;
import com.gongyong.Config;
import com.yonghuliuyan.Work2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XiangqingAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private List<Work2> list;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;

	public XiangqingAdapter(Context context, List<Work2> list) {
		super();
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if("01".equals(list.get(position).getSims_type())){
			convertView=inflater.inflate(R.layout.twelve, null);
			textView1=	(TextView) convertView.findViewById(R.id.diyigeview);
			textView2=(TextView)convertView.findViewById(R.id.huanzhename);
			textView1.setText(list.get(position).getContent());
			textView2.setText(list.get(position).getSender());
		}
		if("02".equals(list.get(position).getSims_type())){
			convertView=inflater.inflate(R.layout.thirteen, null);
			textView3=(TextView)convertView.findViewById(R.id.yishengname);
			textView4=(TextView)convertView.findViewById(R.id.diergeview1doctor1);
			textView3.setText(list.get(position).getRecipient());
			textView4.setText(list.get(position).getContent());
		}
		
		return convertView;
	}

}
