package com.adapter;

import com.example.gongcheng.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MyxingqiAdapter extends BaseAdapter{
	String []str={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	Context context;
	LayoutInflater inflater;
	LinearLayout layout;
	TextView textView;

	public MyxingqiAdapter(Context context) {
		super();
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return str.length;
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
		convertView=inflater.inflate(R.layout.twenty_five_persion, null);
		textView=(TextView)convertView.findViewById(R.id.xingqix_persion);
		layout=(LinearLayout)convertView.findViewById(R.id.layout_persion);
		textView.setText(str[position]);
		if(position==0){
			layout.setBackgroundResource(R.drawable.detail_top_bg);

		}
		else if(position==6){
			layout.setBackgroundResource(R.drawable.detail_bottom_bg);

		}
		else{		
			layout.setBackgroundResource(R.drawable.detail_center_bg);
		}

		return convertView;
	}

}
