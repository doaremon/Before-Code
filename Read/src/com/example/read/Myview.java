package com.example.read;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Myview extends View{
	private String name,newname;
	private String [] mystr;
	private WindowManager manager;
	private int width,height;
	private float dox,doy,upx,upy;
	public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public Myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public Myview(Context context,String name,int width,int height) {
		super(context);
		// TODO Auto-generated constructor stub
		this.name=name;
		this.width=width;
		this.height=height;
		Log.i("chenghao", "构造");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//		setMeasuredDimension(width-50, height-50);
	}




	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("chenghao", "onDraw");
		TextPaint textPaint = new TextPaint();
		textPaint.setARGB(0xFF, 0, 0, 0);
		textPaint.setTextSize(50);
		textPaint.setAntiAlias(true);

		StaticLayout layout = new StaticLayout(Html.fromHtml(name), textPaint, width,
				Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
		canvas.save();
		canvas.translate(0,0);//从20，20开始画
		layout.draw(canvas);
		canvas.restore();//别忘了restore




		// TODO Auto-generated method stub"abc\r\n123"

	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			dox=event.getX();
			doy=event.getY();
			//			Log.i("chenghao", "dox="+dox);
			//			Log.i("chenghao", "doy="+doy);

			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:
			upx=event.getX();
			upy=event.getY();
			//			Log.i("chenghao", "upx="+upx);
			//			Log.i("chenghao", "upy="+upy);

			float indexX=upx-dox;
			float indexY=upy-doy;

			if(indexX>0){
				if(indexY>0){
					Log.i("chenghao", "右滑下滑 ");
				}else {
					Log.i("chenghao", "右滑上滑");
				}

			}else{
				if(indexY>0){
					Log.i("chenghao", "左滑下滑");
				}else {
					Log.i("chenghao", "左滑上滑");
				}

			}
			Log.i("chenghao", "起点坐标= "+dox+"."+doy);
			Log.i("chenghao", "中点坐标= "+upx+"."+upy);


			break;

		default:
			break;
		}
		return true;   
	}



}
