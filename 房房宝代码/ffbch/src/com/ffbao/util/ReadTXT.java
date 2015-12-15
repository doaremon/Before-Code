package com.ffbao.util;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

/**
 * 
 * @FileName:ReadTXT.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ReadTXT.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:  读取txt的类
 */
public class ReadTXT
{
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param fileName
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:从asset中获取文件并读取数据（资源文件只能读不能写）
	 */
	public static String readFromAsset(Context context,String fileName)
	{
		String res = "";
		try
		{
			InputStream in = context.getResources().getAssets().open(fileName);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			if (buffer[0] == -17 && buffer[1] == -69 && buffer[2] == -65)
			{
				buffer[0] = 32;
				buffer[1] = 32;
				buffer[2] = 32;
			}
			res = EncodingUtils.getString(buffer, "UTF-8");
			//res = res.replace("\r\n", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
}
