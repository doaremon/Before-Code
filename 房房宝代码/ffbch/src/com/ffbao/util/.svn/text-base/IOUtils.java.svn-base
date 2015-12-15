package com.ffbao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Logger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * 
 * @FileName:IOUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:IOUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 传输 数据流工具类
 */
public class IOUtils {
	public static boolean isWifiAvailable(Context context) {
		boolean isWifiAvailable = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			isWifiAvailable = true;
		}
		return isWifiAvailable;
	}

	public static String stream2String(final InputStream instream) throws IOException {
		final StringBuilder sb = new StringBuilder();
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} finally {
			closeStream(instream);
		}
		return sb.toString();
	}

	/**
	 * ���潫�ַ��浽ָ���ļ��� ���ļ���Ϊ��ǰ�ĺ���ֵ����ʽ����
	 */
	public static void saveFile(String content, File file) {
		try {
			Date date = new Date();
			if (file.exists()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file, date + ".txt")));
				writer.write(content);
				// �ر�������
				IOUtils.closeStream(writer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���潫�ַ��浽ָ���ļ���
	 * 
	 * @param content
	 *            �ļ�����
	 * @param file
	 *            �ļ���·���������ļ���
	 */
	public static void saveFile(String content, File file, String filename) {
		try {
			if (file.exists()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file, filename)));
				writer.write(content);
				IOUtils.closeStream(writer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ���ļ�
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * �ر�IO������
	 * 
	 * @param stream
	 */
	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				Log.e("IOUtils", e.toString());
			}
		}
	}

	/**
	 * ��ȡ�ļ������ݣ����ַ����ʽ���أ�
	 * ע����û�п��Ǳ�������
	 * @return �ļ����ݣ����û���ļ������ؿ�
	 */
	public static String getFileContent(File file) {
		if (file.exists()) {
			StringBuilder builder = new StringBuilder();
			BufferedReader bufferedReader = null;
			try {
				FileReader reader = new FileReader(file);
				bufferedReader = new BufferedReader(reader);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					builder.append(lineTxt);
				}
				bufferedReader.close();
			} catch (Exception e) {
				Log.e("IOUtils", e.toString());
			} finally {
				if (bufferedReader != null) {
					bufferedReader = null;
				}
			}
			return builder.toString();
		}
		return null;
	}
}
