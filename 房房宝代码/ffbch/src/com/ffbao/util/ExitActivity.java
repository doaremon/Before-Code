/**
 * Project Name:房房宝
 * File Name:ExitActivity.java
 * Package Name:com.ffbao.util
 * Date:2014-10-17下午7:27:24
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * ClassName:ExitActivity
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-10-17 下午7:27:24 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ExitActivity extends Application{
	/**
     * Activity列表
     */
    private static List<Activity> activityList = new LinkedList<Activity>();
    
    /**
     * 全局唯一实例
     */
    private static ExitActivity instance;
    

    /**
     * 该类采用单例模式，不能实例化
     */
    private ExitActivity()
    {
    }

    /**
     * 获取类实例对象
     * @return    MyActivityManager
     */
    public static ExitActivity getInstance() {
        if (null == instance) {
            instance = new ExitActivity();            
        }
        return instance;
    }


    /**
     * 保存Activity到现有列表中
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }


    /**
     * 关闭保存的Activity
     */
    public static void exit() {
        if(activityList!=null)
        {
            Activity activity;
            
            for (int i=0; i<activityList.size(); i++) {            
                activity = activityList.get(i);
                
                if(activity!=null)
                {
                    if(!activity.isFinishing())
                    {
                        activity.finish();
                    }
                    
                    activity = null;
                }
                
                activityList.remove(i);                
                i--;
            }
        }
    }
}
