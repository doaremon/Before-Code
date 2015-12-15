package com.u4.home.call;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 重写了viewpage
 * 设置的数模没有反应
 * @author Administrator
 *
 */
public class MyViewpages extends ViewPager{

	private boolean enabled;


	public MyViewpages(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.enabled = false;
	}

	//触摸没有反应就可以了
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onTouchEvent(event);
		}

		return false;
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onInterceptTouchEvent(event);
		}

		return false;
	}

	public void setPagingEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
