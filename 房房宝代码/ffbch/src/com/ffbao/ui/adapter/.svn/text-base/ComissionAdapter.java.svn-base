package com.ffbao.ui.adapter;

import com.ffbao.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * @FileName:ComissionAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ComissionAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 排行榜列表 adapter
 */
public class ComissionAdapter extends BaseAdapter {

	
	private Context context ;
	public  ComissionAdapter (Context context){
		
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView== null){
			convertView = LayoutInflater.from(context).inflate(R.layout.ffb_customer_item1, null);
		}
		return convertView;
	}

}
