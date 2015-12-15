package com.ffbao.util;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

import com.ffbao.activity.R;

/**
 * 
 * @FileName:MyProgress.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:MyProgress.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: Progressbar Utils 
 */
public class MyProgress {

	// private Context context;

	// private MyProgress(Context context) {
	// this.context = context;
	// }

	private MyProgress() {
	}

	private static MyProgress instance = null;
	private AlertDialog alertDialog;
	private String  histroyActivity = "";
	public synchronized static MyProgress getInstance() {
		if (instance == null) {
			instance = new MyProgress();
		}
		return instance;
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:progress Dialog showing
	 */
	public void getProgressDialogShow(Context context) {
		if (context != null) {
//			if (alertDialog != null) {
//
//				if (((ContextWrapper) alertDialog.getContext())
//						.getBaseContext().getClass().getSimpleName()
//						.equals(context.getClass().getSimpleName())) {
//					alertDialog.show();
//				} else {
//					alertDialog.dismiss();
//					createNewAlertDialog(context);
//				}
//			} else {
//
//				createNewAlertDialog(context);
//			}
			createNewAlertDialog(context);
			alertDialog.show();
		}
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:create new AlertDialog 
	 */
	private  boolean  createNewAlertDialog(Context context) {
		try {
			ActivityManager ActivityMagr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			String currentActivity = ActivityMagr.getRunningTasks(1).get(0).baseActivity.getClassName();
			if(!histroyActivity.equals(currentActivity)){
				
				if(alertDialog != null){
					alertDialog.dismiss();
				}
				alertDialog = new AlertDialog.Builder(context)
//				alertDialog = new AlertDialog.Builder(context, R.style.dialog1)
						.create();
				alertDialog.show();
				alertDialog.setCancelable(true);
				alertDialog.setContentView(R.layout.ffb_progress_layout);
				
				return true;
			}
			histroyActivity = currentActivity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createNewAlertDialog(context);
		
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:Dialog dismiss
	 */
	public void dismissDialog() {
		if (alertDialog != null) {
			alertDialog.dismiss();
		}
	}

}
