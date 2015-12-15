package com.ffbao.net;
/**
 * 
 * @FileName:CallBackListener.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CallBackListener.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: network framework  callbackListener 
 */
public interface CallBackListener {
	
	/**
	 * @Deprecatred:
	 * @param error
	 * @param msg
	 * @date:2014-11-10
	 * @author:lee
	 * @Funtion:处理接口失败
	 */
	public void onFailure(Exception error, String msg);
	/**
	 * @Deprecatred:
	 * @param error
	 * @param msg
	 * @date:2014-11-10
	 * @author:lee
	 * @Funtion:处理接口成功
	 */
	public void onSuccess(String msg);
	
}
