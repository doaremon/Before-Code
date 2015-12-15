package com.ffbao.ui.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.HouseMessage;
import com.ffbao.entity.HouseSimple;
import com.ffbao.ui.CustomAddActivity;
import com.ffbao.ui.HousesDetailsActivity;
import com.ffbao.ui.adapter.CoustomAdapter.ViewHolder;
import com.ffbao.util.SharedPrefConstance;
import com.lidroid.xutils.BitmapUtils;

/**
 * 
 * @FileName:HouseAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HouseAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 展现楼盘相关消息
 */
public class HouseMessageAdapter extends BaseAdapter {

	private List<HouseMessage> house;
	private Context context;

	public HouseMessageAdapter(List<HouseMessage> house, Context context) {
		this.house = house;
		this.context = context;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Object getItem(int position) {
		return house.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_house_message, null);

			ViewHolder holder = new ViewHolder();
			holder.share = (ImageView) convertView
					.findViewById(R.id.item_house_share);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_houst_title);
			holder.date = (TextView) convertView
					.findViewById(R.id.item_house_date);
			holder.llImages = (LinearLayout) convertView
					.findViewById(R.id.item_house_images);
//			holder.content = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		}

		// handlerSimpleDisplay(position, convertView);
		return convertView;
	}

	private void handlerSimpleDisplay(int position, View convertView) {

		HouseMessage houseMessage = house.get(position);
		if (houseMessage != null) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.title.setText(houseMessage.getTitle());
			holder.date.setText(houseMessage.getDate());
			List<String> imageUrls = houseMessage.getImageUrls();
			if (imageUrls != null && imageUrls.size() > 0) {
				BitmapUtils bitmaputlis = new BitmapUtils(context);
				for (String imageURL : imageUrls) {

					ImageView child = new ImageView(context);
					bitmaputlis.display(child, imageURL);
					holder.llImages.addView(child, 0);
				}
			}
			holder.content.setText(houseMessage.getContent());
		}
	}

	public static class ViewHolder {

		ImageView share;
		TextView title;
		TextView date;
		LinearLayout llImages;
		TextView content;
	}

}
