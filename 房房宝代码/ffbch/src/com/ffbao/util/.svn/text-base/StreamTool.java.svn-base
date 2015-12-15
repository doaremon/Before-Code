package com.ffbao.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

public class StreamTool {

	
	/**
	 * 
	 * @Deprecatred:
	 * @param inStream
	 * @return
	 * @throws Exception
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:从输入流中读取数据
	 */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }
    /**
     * 
     * @Deprecatred:
     * @param inStream
     * @return
     * @throws Exception
     * @date:2014-11-4
     * @author:lee
     * @Funtion:从输入流中读取数据
     */
    public static String readInputStreamString(InputStream inStream) throws Exception{
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    	byte[] buffer = new byte[1024];
    	int len = 0;
    	while( (len = inStream.read(buffer)) !=-1 ){
    		outStream.write(buffer, 0, len);
    	}
    	byte[] data = outStream.toByteArray();//网页的二进制数据
    	outStream.close();
    	inStream.close();
        String res = EncodingUtils.getString(data, "UTF-8");
    	return res;
    }
}
