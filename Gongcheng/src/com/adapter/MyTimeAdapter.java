package com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.gongcheng.R;
import com.work.Work5;

public class MyTimeAdapter extends BaseAdapter{
	Context context;
	LayoutInflater inflater;
	TextView textView1;
	TextView textView2;
	TextView textView3;
	List<Work5> list;
	Button button;


	public MyTimeAdapter(Context context, List<Work5> list) {
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
	public Object getItem(int position) {
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
		convertView=inflater.inflate(R.layout.twenty_six_persion, null);
		textView1=(TextView)convertView.findViewById(R.id.shangwu_pe);
		textView2=(TextView)convertView.findViewById(R.id.texxia);
		textView3=(TextView)convertView.findViewById(R.id.xinqixuanze_pe);
		button=(Button)convertView.findViewById(R.id.kaiguanbutton_pe);
		//给button添加图片
		button.setBackgroundResource(list.get(position).getOnoff()?R.drawable.on:R.drawable.off);
		final int is=position;

		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Onoff是个boo值
				list.get(is).setOnoff(list.get(is).getOnoff()?false:true);
				notifyDataSetChanged();

			}
		});	
		textView1.setText(list.get(position).getMorningst()+"~"+list.get(position).getMorningsp());
		textView2.setText(list.get(position).getAfternoonst()+"~"+list.get(position).getAfternoonsp());
		textView3.setText(list.get(position).getDate());
		return convertView;
	}

}
