/**
 * Project Name:房房宝
 * File Name:UrlUtil.java
 * Package Name:com.ffbao.util
 * Date:2014-9-19上午10:27:52
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
 */

package com.ffbao.util;
/**
 * ClassName:UrlUtil
 *gh6yyg6ffg
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-19 上午10:27:52 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class UrlUtil {
	//	public static final String URL="http://192.168.1.39:8080/Front/FFdBMobile";
	public static final String URL="http://192.168.1.142:8080/Front/FFBMobile";
	// 以下是接口
	//		public static final String URL="http://192.168.1.142:8080/Front/FFBMobile";
	//	public static final String URL="http://192.168.1.76:8080/Front/FFBMobile";
	//	public static final String URL="http://123.57.45.154:5566/FFBMobile";

	//	public static final String URL="http://192.168.1.251:8899/FFbao_Front/FFBMobile";
	//	public static final String URL="http://192.168.1.251:7788/FFBMobile";

	//		public static final String URL="http://123.57.45.154:7878/FFBMobile";

	//		public static final String URL="http://182.92.233.245:7878/FFBMobile";

	//	public static final String URL="http://182.92.233.245:7878/FFBMobile";
	//	public static final String URL="http://192.168.1.250:8888/FFBMobile";

	//	public static final String URL="http://182.92.233.245:5566/FFBMobile";
	//	public static final String URL="http://182.92.233.245:7777/FFBMobile";
	//测试域名
	//public static final String URL="http://182.92.241.153/FFBMobile";
	// TODO 域名
	//		public static final String URL="http://www.fangfangbao.com/FFBMobile";
	//	public static final String URL="http://182.92.233.245:9988/FFBMobile";
	//	public static final String URL="http://123.57.132.183/FFBMobile";

	//	public static final String URL="http://m.fangfangbao.com/FFBMobile";
	//		public static final String URL="http://123.57.45.154:7878/FFBMobile";
	//	public static final String URL="http://123.57.45.154:5566/FFBMobile";
	//	public static final String URL="http://test.workingbook.cn/FFBMobile";
	//	public static final String URL="http://192.168.1.28/ffbao/FFBMobile";
	/**
	 * 用户发送验证码（注册和修改密码使用）
	 */
	public static final String userSendCheckCode="userSendCheckCode";
	/**
	 * 用户注册
	 */
	public static final String userRegister="userRegister";
	/**
	 * 登录服务器，验证用户名密码
	 */
	public static final String userLogin="userLogin";
	/**
	 * 用户注册查询门店(登录以后会从服务器获取令牌UUID)
	 */
	public static final String userSearchCompanyID="userSearchCompanyID";
	/**
	 * 解绑门店
	 */
	public static final String userUnbundlingCompany="userUnbundlingCompany";
	/**
	 * 用户信息修改密码
	 */
	public static final String userUpdateInfo="userUpdateInfo";
	/**
	 * 用户信息修改个人头像
	 */
	public static final String userUpdatePersionImg="userUpdatePersionImg";
	/**
	 * 用户登录后获取详细信息
	 */
	public static final String userGetDetail="userGetDetail";

	/**
	 * 查询银行卡
	 */
	public static final String userGetBankCardInfo="userGetBankCardInfo";

	/**
	 * 添加和删除银行卡
	 */
	public static final String userAddOrDelBankCard="userAddOrDelBankCard";
	/**										  
	 * 用户获取消息接口
	 */
	public static final String userGetMessages="userGetMessages";
	/**
	 * 报备
	 */
	public static final String userAddReportList="userAddReportList";
	/**
	 * 获取报备单列表
	 */
	public static final String userGetReportList="userGetReportList";
	/**
	 * 报备单详细信息
	 */
	public static final String userGetReportDetail="userGetReportDetail";
	/**
	 * 报备单更新接口(带看 申请结佣。。。。)
	 */
	public static final String userUpdateReportListl="userUpdateReportList";
	/**
	 * 行程单接口
	 */
	public static final String userGetTripList="userGetTripList";
	/**
	 *	采购单接口
	 */
	public static final String userGetPurchaseList="userGetPurchaseList";
	/**
	 * 销售业绩接口
	 */
	public static final String userGetPerformanceList="userGetPerformanceList";

	/**
	 * MainActivity 报错误
	 */
	public static final String userUpdateVersion="userUpdateVersion";
	/**
	 * 版本更新(待定)
	 */
	public static final String userLoginUpdateVersion="userLoginUpdateVersion";

	/**
	 * 强制版本更新
	 */
	//	public static final String userLogoutUpdateVersion="userLogoutUpdateVersion";

	/**
	 * 修改密码
	 */
	public static final String userUpdatePassword="userUpdatePassword";
	/**
	 * 登出服务器
	 */
	public static final String userLogout="userLogout";
	/**
	 * 获取消息条数
	 */
	public static final String userGetMessagesCount="userGetMessagesCount";
	/**
	 * 用户信息修改个人头像
	 */
	public static final String userUpdatePersonImg="userUpdatePersonImg";
	/**
	 * 忘记密码
	 */
	public static final String userForgetPassword="userForgetPassword";
	/**
	 * 结佣单接口
	 */
	public static final  String userGetComissionList ="userGetComissionList";
	/**
	 * 获取客户端详细信息
	 */
	public static final  String userPushDetails ="userPushDetails";
	/**
	 * 获取意向城市
	 */
	public static final  String userGetWantCitys ="userGetWantCitys";
	/**
	 * 获取意向楼盘
	 */
	public static final  String userGetWantBuildings ="userGetWantBuildings";
	/**
	 * 用户是否是VIP
	 */
	public static final  String userCheckVIP ="userCheckVIP";
	//积分 、佣金、信用    
	//楼盘信息
	//讨论 一些主题 
	/**
	 * 我的佣金
	 */
	public static final String UserGetMyBrokerage="userGetMyBrokerage";

	/**
	 * 用户添加主题信息
	 */
	public static final  String userAddTopic = "userAddTopic";
	/**
	 * 获取用户主题列表
	 */
	public static final  String userGetTopicList = "userGetTopicList";
	/**
	 * 获取用户主题内容列表
	 */
	public static final  String userGetAnswerTopicContentList = "userGetAnswerTopicContentList";
	/**
	 * 用户主题回复
	 */
	public static final  String userAddTopicContent = "userAddTopicContent";
	/**
	 * 获取其它信息
	 */
	public static final  String userGetOtherMessages = "userGetOtherMessages";
	/**
	 * 修改手机
	 */
	public static final String modify_the_phone = "3";
	/**
	 * 注册
	 */
	public static final String sign_up_mobile = "1";
	/**
	 * 忘记密码
	 */
	public static final String forget_password = "2";
	/**
	 * 获取异常信息
	 */
	public static String userPushErrors = "userPushErrors";
	/**
	 * 获取楼盘信息列表
	 */
	public static Object userGetBuildingInfoList = "userGetBuildingInfoList";
	/**
	 * 获取楼盘参数
	 */
	public static Object userGetBuildParameter = "userGetBuildParameter";
	/**
	 * 获取楼盘列表
	 */
	public static Object userGetBuildingList = "userGetBuildingList";
	/**
	 * 查询图片
	 */
	public static Object userGetBuildPicture = "userGetBuildPicture";
	/**
	 * 团购
	 */
	public static Object userGroupPurchase = "userGroupPurchase";

}
