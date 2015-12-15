package com.ffbao.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;
/**
 * 
 * @FileName:PagerFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:PagerFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取pagerFragment
 */
public abstract class PagerFragment extends Fragment {

	public abstract String getFragmentName();
	public static Boolean ifshow=true;

	public String getTitle(){

		return "";
	}

	public static void showlog(String msg){
		if(ifshow){
			Log.i("chenghao", msg);
		}
	}
	public static void showToast(Context context,String msg){
		if(ifshow){
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}

}
