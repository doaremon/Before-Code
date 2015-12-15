package com.ffbao.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context) {
		super(context);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
//		 int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//			        MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		
		
		int initY = 0;
		boolean result = false;
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			initY = (int) ev.getRawY();
			break;
			
		case MotionEvent.ACTION_MOVE:
			
			int tagerY = (int) ev.getRawY();
			if(tagerY - initY > 0 ){
				result =  getBooleanState();
			}
			break;

		default:
			break;
		}
		
		if(result){
			return super.onInterceptTouchEvent(ev);
		}
		return result;
	}
	
	private boolean getBooleanState(){
		
		if(getFirstVisiblePosition() == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		return super.onTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
}
