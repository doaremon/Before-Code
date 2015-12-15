package com.ffbao.exception;
/**
 * 
 * @FileName:HttpException.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HttpException.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: http framework throws exception
 */
public class HttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpException() {
		super();
	}

	public HttpException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public HttpException(String detailMessage) {
		super(detailMessage);
	}

	public HttpException(Throwable throwable) {
		super(throwable);
	}

}
