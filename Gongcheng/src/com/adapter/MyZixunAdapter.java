package com.adapter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gongcheng.R;
import com.work.Work;
import com.yonghuliuyan.Work2;

public class MyZixunAdapter extends BaseAdapter{
private TextView textView;
private TextView textView1;
private LayoutInflater inflater;
private Context context;
private List<Work2> list;



	public MyZixunAdapter(Context context, List<Work2> list) {
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
		convertView=inflater.inflate(R.layout.eight, null);
		textView1=(TextView)convertView.findViewById(R.id.wodezixuntextvbiew2);
		textView=(TextView)convertView.findViewById(R.id.wodezixuntextvbiew);
		
		Long time = Long.parseLong(list.get(position).getSend_time());
		Date date = new Date(time);
		Log.i("test", "long---->"+time);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Format format = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String sb = format.format(gc.getTime());
		textView.setText(sb);
		textView1.setText(list.get(position).getContent());
		return convertView;
	}

}
