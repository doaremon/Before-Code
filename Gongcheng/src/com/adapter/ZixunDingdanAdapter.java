package com.adapter;

import java.util.List;

import com.example.gongcheng.R;
import com.work.Work4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//×ÉÑ¯¶©µ¥
public class ZixunDingdanAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	List<Work4> list;
	TextView textView1;
	TextView textView2;
	TextView textView3;
	public ZixunDingdanAdapter(Context context, List<Work4> list) {
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=inflater.inflate(R.layout.seventeen, null);
		textView1=(TextView)convertView.findViewById(R.id.zixundingdan_id);
		textView2=(TextView)convertView.findViewById(R.id.zixundingdan_time);
		textView3=(TextView)convertView.findViewById(R.id.zixundingdan_zhuangtai);
		textView1.setText(list.get(position).getOrdernum());
		textView2.setText(list.get(position).getYytime());
		textView3.setText(list.get(position).getState());
		return convertView;
	}

}
