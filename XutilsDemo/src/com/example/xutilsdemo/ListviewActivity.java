package com.example.xutilsdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ListviewActivity extends Activity{
	@ViewInject(R.id.showlist)
	private ListView showlist;
	List<String> list=new ArrayList<String>();
	BitmapUtils bitmapUtils = new BitmapUtils(ListviewActivity.this);
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.mylistview);
    	
    	ViewUtils.inject(ListviewActivity.this);
    	
    	list.add("http://www.timebooks.com.cn/static/image/201507/bacf72f0-258e-4648-ac5e-7b92d6470a91_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201507/bacf72f0-258e-4648-ac5e-7b92d6470a91_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201507/bacf72f0-258e-4648-ac5e-7b92d6470a91_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201507/bacf72f0-258e-4648-ac5e-7b92d6470a91_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201507/bacf72f0-258e-4648-ac5e-7b92d6470a91_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/3e6a5348-e672-4436-a044-25378777b31e_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/f77c0372-974b-4799-b3fc-ddfbd6b97e7f_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/a6ad7b5e-ed5d-445e-a5b3-3e094d580c78_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/fbdd5a1f-a3d3-4d9b-a5e1-aef85d3b6a03_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/24cefe9a-d255-4ea2-89ac-861e279574d3_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/36ac5d23-3682-4452-9aee-d5bf2ed21383_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/7137fdeb-5e9c-4fc0-bfa9-69cc686e9bd2_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/acdce89b-37a7-4aff-8899-92719f830e1b_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/84a27220-646d-4440-acca-fef0e2e9ecb6_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/81ea1e69-dbe3-4a3d-86ae-50ce93f18cce_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/00f3d898-7ced-41f8-be7d-da04f36b8af7_400.jpg");
    	list.add("http://www.timebooks.com.cn/static/image/201506/13e7cefa-b80d-4c0c-89c1-3851f8eb5b01_400.jpg");
    	
    	
    	showlist.setAdapter(new Myadapter());
    	
    	showlist.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
    }
    
    
    
    
    public class Myadapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView=LayoutInflater.from(ListviewActivity.this).inflate(R.layout.item, null);
			ImageView view=(ImageView) convertView.findViewById(R.id.img);
			bitmapUtils.display(view, list.get(position));
			return convertView;
		}
    	
    }
}
