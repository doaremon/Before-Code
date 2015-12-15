package com.ffbao.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class DisplayUtils {

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @return
	 * @date:2014-11-21
	 * @author:lee
	 * @Funtion:手机屏幕宽度
	 */
	public static int getDisplay(Activity context){
		DisplayMetrics dm = new DisplayMetrics();
		Display display =  context.getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		return  dm.widthPixels;
	}
	
	private  static boolean  FragmentRefresh = true;
	private  static boolean  UserRefresh = false;
	
	public static boolean getFragmentState(){
		
		return FragmentRefresh;
	}
	
	public static void setUnRefresh(){
		
		FragmentRefresh = true;
	}
	
	public static void setRefreshComplete(){
		
		FragmentRefresh = false;
	}
	
	
	
}
