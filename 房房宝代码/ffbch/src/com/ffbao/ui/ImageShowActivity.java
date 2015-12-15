package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.ffbao.activity.R;
import com.ffbao.entity.HouseImage;
import com.ffbao.entity.HouseUnits;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.widget.DragImageView;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.StringUtils;
import com.ffbao.util.UrlUtil;
import com.lidroid.xutils.BitmapUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * 
 * @FileName:ImageShowActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ImageShowActivity.java
 * @author lee
 * @create Date2014-11-13
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 楼盘展示界面
 */
public class ImageShowActivity extends BaseActivity implements
		OnPageChangeListener, OnClickListener {
	private ViewPager mViewPager;

	public String flag;
	public String buildingid;

	private Context context;

	private HouseImage buildingImages;

	private int assortSize = 0;
	private int houseSize = 0;
	private int planSize = 0;
	private int realisticSize = 0;
	private int templetSize = 0;
	private int effectPictureSize = 0;

	private TextView bottomTextView;
	private TextView tveffect;
	private TextView tvplan;
	private TextView tvrealistic;
	private TextView tvassort;
	private TextView tvTemplet;
	private TextView tvhouse;

	private HorizontalScrollView scroll;

	private String type;
	private int position = 0;

	private int indexText;
	private int screen_w;
	private List<HouseUnits> houseUnits;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.ffb_activity_image);
		ExitActivity.addActivity(this);
		initView();
		// bottomTextView = new TextView(this);
		setActionBarTitle("楼盘图片");
		//update by shenwenshi
		//date:2014-12-08
		//for change transer value 
		//Bundle to Intent
/*		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			buildingid = extras.getString(Constant.buildingid, "");
			type = extras.getString(Constant.type, "");
			position = extras.getInt(Constant.position, 0);
			indexText= extras.getInt("tempIndex", 0);
		    System.out.println(indexText);
//			houseUnits = (List<HouseUnits>) extras.getSparseParcelableArray("array");
			houseUnits = extras.getParcelableArrayList("array");
		}
*/		//now change to intent
		Intent intent=getIntent();
		buildingid=intent.getStringExtra(Constant.buildingid);
		type=intent.getStringExtra(Constant.type);
		position=intent.getIntExtra(Constant.position,0);
		indexText=intent.getIntExtra("tempIndex",0);
		System.out.println(indexText);
		houseUnits=intent.getParcelableArrayListExtra("array");
		
		if(!"".equals(type)){
			setActionBarTitle("户型介绍");
		}
		DisplayMetrics dm = new DisplayMetrics();
		android.view.Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		screen_w = dm.widthPixels;
		mViewPager.setOnPageChangeListener(this);
		initData();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.welcome_viewPager);
		scroll = (HorizontalScrollView) findViewById(R.id.scroll);
		bottomTextView = (TextView) findViewById(R.id.noData_text);
		tveffect = (TextView) findViewById(R.id.tveffect);
		tvplan = (TextView) findViewById(R.id.tvplan);
		tvrealistic = (TextView) findViewById(R.id.tvrealistic);
		tvassort = (TextView) findViewById(R.id.tvassort);
		tvTemplet = (TextView) findViewById(R.id.tvTemplet);
		tvhouse = (TextView) findViewById(R.id.tvhouse);

		tveffect.setOnClickListener(this);
		tvplan.setOnClickListener(this);
		tvrealistic.setOnClickListener(this);
		tvassort.setOnClickListener(this);
		tvTemplet.setOnClickListener(this);
		tvhouse.setOnClickListener(this);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:发送获取图片请求
	 */
	private void initData() {

		Map<String, Object> map = new HashMap<String, Object>();
		// userGetBuildPicture
		map.put(Constant.commandText, UrlUtil.userGetBuildPicture);
		map.put(Constant.buildingid, buildingid);
		HttpClientRequest.getHttpPost(context, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				try {
					buildingImages = ExecuteJSONUtils.getBuildingImages(
							context, msg);
					if ("0".equals(type)) {
						buildingImages
								.setAssortPicture(new ArrayList<String>());
						buildingImages.setPlanPicture(new ArrayList<String>());
						buildingImages
								.setRealisticPicture(new ArrayList<String>());
						buildingImages
								.setTempletPicture(new ArrayList<String>());
						buildingImages
								.setEffectPicture(new ArrayList<String>());
						houseSize = buildingImages.getHousePicture().size();
						buildingImages.setSize(buildingImages.getHousePicture()
								.size());
						houseSize = houseUnits.size();
						buildingImages.setHousePicture(getBuildingHousePicture());
					} else {
						assortSize = buildingImages.getAssortPicture().size();
						houseSize = buildingImages.getHousePicture().size();
						planSize = buildingImages.getPlanPicture().size();
						realisticSize = buildingImages.getRealisticPicture()
								.size();
						templetSize = buildingImages.getTempletPicture().size();
						effectPictureSize = buildingImages.getEffectPicture()
								.size();
					}
					mViewPager.setAdapter(pager);
					if("0".equals(type)){
						setPositionScroll(position);
					}else{
						setPositionScroll(0);
						ViewPagerInit();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}
	
	private List<String> getBuildingHousePicture() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < houseUnits.size(); i++) {

			HouseUnits houseUnit = houseUnits.get(i);
			list.add(houseUnit.getHousephoto());
		}
		return list;
	}
	/**
	 * @Deprecatred:
	 * @param i
	 * @date:2014-11-25
	 * @author:lee
	 * @Funtion:初始化位置
	 */
	private void ViewPagerInit() {

		if (effectPictureSize > 0) { // 效果图
		} else {
			tveffect.setVisibility(View.GONE);
		}

		if (planSize > 0) { // 规划图

		} else {
			tvplan.setVisibility(View.GONE);

		}
		if (realisticSize > 0) { // 实景图

		} else {
			tvrealistic.setVisibility(View.GONE);

		}

		if (assortSize > 0) { // 配套图

		} else {

			tvassort.setVisibility(View.GONE);
		}
		if (templetSize > 0) { // 样板间图
		} else {
			tvTemplet.setVisibility(View.GONE);

		}
		if (houseSize > 0) { // 户型图

		} else {

			tvhouse.setVisibility(View.GONE);
		}

	}
	/**
	 * PageAdapter
	 */
	PagerAdapter pager = new PagerAdapter() {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			
			if ( indexText==1)
			{
				  return buildingImages.getSize();
             
			}else
			{
				 return getBuildingHousePicture().size();  
          
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BitmapUtils bitmapUtils = new BitmapUtils(context);
			DragImageView imageView = new DragImageView(context);

			String imageURL = null;
			if(position == 0){
				imageURL = getImageURLCurrentPosition(ImageShowActivity.this.position);
			}else if(position == ImageShowActivity.this.position){
				imageURL = getImageURLCurrentPosition(0);
			}else{
				imageURL = getImageURLCurrentPosition(position);
			}
			
			if (StringUtils.isNull(imageURL)) {
				bitmapUtils.display(imageView, imageURL);
			}
			container.addView(imageView);
			return imageView;
		}

		/**
		 * @Deprecatred:
		 * @param position
		 * @return
		 * @date:2014-11-13
		 * @author:lee
		 * @Funtion:获取图片URL
		 */
		private String getImageURLCurrentPosition(int position) {
			String URL = null;
			int size = position;
			if (effectPictureSize > 0) // 效果图

				if (effectPictureSize > position) {

					return buildingImages.getEffectPicture().get(size);
				} else {
					position -= effectPictureSize;
				}

			if (planSize > 0) // 规划图

				if (planSize > position) {
					// 第几张图片
					return buildingImages.getPlanPicture().get(position);
				} else {

					position -= planSize;
				}
			if (realisticSize > 0) // 实景图

				if (realisticSize > position) {

					return buildingImages.getRealisticPicture().get(position);
				} else {
					position -= realisticSize;
				}

			if (assortSize > 0) { // 配套图
				//

				if (assortSize > position) {
					return buildingImages.getAssortPicture().get(position);
				} else {
					position -= assortSize;
				}
			} else {
				// TODO 图片没有这个图片时候
			}
			if (templetSize > 0) // 样板间图
				// 第几张图片
				if (templetSize > position) {

					return buildingImages.getTempletPicture().get(position);
				} else {
					position -= templetSize;
				}
			if (houseSize > 0) // 户型图

				if (houseSize >= position) {

					return buildingImages.getHousePicture().get(position);
				} else {
					position -= houseSize;
				}

			return URL;
		}

	};

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {

		setPositionScroll(position);

	}

	private void setPositionScroll(int position) {
		tveffect.setBackgroundColor(0);
		tvplan.setBackgroundColor(0);
		tvassort.setBackgroundColor(0);
		tvrealistic.setBackgroundColor(0);
		tvTemplet.setBackgroundColor(0);
		tvhouse.setBackgroundColor(0);

		int w = scroll.getWidth();
		if (effectPictureSize > 0) { // 效果图
			if (effectPictureSize > position) {
				bottomTextView.setText("效果图");
				tveffect.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));
				int left = tveffect.getLeft();
				scroll.smoothScrollTo(Math.abs(left), 0);
				return;
			} else {
				position -= effectPictureSize;
				tveffect.setBackgroundColor(0);
			}
		} else {
			tveffect.setVisibility(View.GONE);

		}

		if (planSize > 0) { // 规划图

			if (planSize > position) {
				// 第几张图片
				bottomTextView.setText("规划图 ");
				tvplan.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));

				int left = tvplan.getLeft();
				scroll.smoothScrollTo(Math.abs(left), 0);
				return;
			} else {

				position -= planSize;
				tvplan.setBackgroundColor(0);
			}
		} else {
			tvplan.setVisibility(View.GONE);

		}
		if (realisticSize > 0) { // 实景图

			if (realisticSize > position) {

				bottomTextView.setText("实景图");
				tvrealistic.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));
				int left = tvrealistic.getLeft();
				scroll.smoothScrollTo(Math.abs(left), 0);
				return;
			} else {
				position -= realisticSize;
				tvrealistic.setBackgroundColor(0);
			}
		} else {
			tvrealistic.setVisibility(View.GONE);

		}

		if (assortSize > 0) { // 配套图
			//

			if (assortSize > position) {
				// 第几张图片
				bottomTextView.setText("配套图");
				tvassort.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));
				scroll.smoothScrollBy(Math.abs(w - (screen_w - 120)), 0);
				return;
			} else {
				position -= assortSize;
				tvassort.setBackgroundColor(0);
			}
		} else {

			tvassort.setVisibility(View.GONE);
		}
		if (templetSize > 0) { // 样板间图
			// 第几张图片
			if (templetSize > position) {

				bottomTextView.setText("样板间图");
				tvTemplet.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));
				int left = tvTemplet.getLeft();
				scroll.smoothScrollBy(Math.abs(left), 0);
				return;
			} else {
				position -= templetSize;
				tvTemplet.setBackgroundColor(0);
			}
		} else {
			tvTemplet.setVisibility(View.GONE);

		}
		if (houseSize > 0) { // 户型图

			if (houseSize >= position) {
				// 第几张图片
				bottomTextView.setText("户型图");
				tvhouse.setBackgroundColor(getResources().getColor(
						R.color.bg_nav));
				int left = tvhouse.getLeft();
				scroll.smoothScrollTo(Math.abs(left), 0);
				return;
			} else {
				position -= houseSize;
				tvhouse.setBackgroundColor(0);
			}
		} else {

			tvhouse.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tveffect:

			setPositionScroll(0);
			mViewPager.setCurrentItem(0, true);
			break;
		case R.id.tvplan:

			setPositionScroll(effectPictureSize);
			mViewPager.setCurrentItem(effectPictureSize, true);
			break;
		case R.id.tvrealistic:

			setPositionScroll(effectPictureSize + planSize);
			mViewPager.setCurrentItem(effectPictureSize + planSize, true);
			break;
		case R.id.tvassort:

			setPositionScroll(effectPictureSize + planSize + realisticSize);
			mViewPager.setCurrentItem(effectPictureSize + planSize
					+ realisticSize, true);
			break;
		case R.id.tvTemplet:

			setPositionScroll(effectPictureSize + planSize + realisticSize
					+ assortSize);
			mViewPager.setCurrentItem(effectPictureSize + planSize
					+ realisticSize + assortSize, true);
			break;
		case R.id.tvhouse:

			setPositionScroll(effectPictureSize + planSize + realisticSize
					+ assortSize + templetSize);
			mViewPager.setCurrentItem(effectPictureSize + planSize
					+ realisticSize + templetSize + assortSize, true);
			break;

		default:
			break;
		}
	}
}
