package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gongcheng.R;

public class GrAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private TextView textView;
	private ImageView imageView;
	private String[] aaa = { "我的咨询", "我的订单", "个人中心" };
	private int[] bbb = { R.drawable.home_menu_wdzx_normal,
			R.drawable.home_menu_yysz_normal, R.drawable.home_menu_grzx_normal };

	public GrAdapter(Context context) {
		super();

		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return aaa.length;
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
		convertView = inflater.inflate(R.layout.three, null);
		imageView = (ImageView) convertView.findViewById(R.id.imageViewItemOperate);
		textView = (TextView) convertView.findViewById(R.id.textViewItemOperate);
		imageView.setBackgroundResource(bbb[position]);
		textView.setText(aaa[position]);
		return convertView;
	}

}
