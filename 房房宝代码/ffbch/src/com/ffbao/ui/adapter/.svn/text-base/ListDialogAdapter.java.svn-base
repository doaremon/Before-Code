package com.ffbao.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.CityListBean;
/**
 * 适配listdialog的adapter
 * @author cc
 *
 */
public class ListDialogAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private List<CityListBean> list;


	public ListDialogAdapter(Context context, List<CityListBean> list) {
		super();
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.listdialogadaptertext, null);
		TextView listadapter_text=(TextView) convertView.findViewById(R.id.listadapter_text);
		listadapter_text.setText(list.get(position).getCity().toString());
		return convertView;
	}

}
