package com.ffbao.entity;
/**
 * login completed  caching user information
 * cache android SharedPreferences
 * @author lee
 *
 */
/**
 * 
 * @FileName:User.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:User.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 */
public class User {

	/**
	 * servicephone: "13910688234", servicename: "吴大力", tdbarcode_path:
	 * "http://f1.ffbao.net/img/13910688234/tbdarcode.png", ffb_tdbarcode_path:
	 * "http://f1.ffbao.net/img/1111/11.png", shareGuideUrl:
	 * "http://www.ffb.net/comminissionguide.html", myselfimg:
	 * "http://file2.ffb.net/100005/20140921101010.jpg", companyname: "北京链接地产"
	 */

	private String servicephone; /*customer service telephone*/
	private String servicename;  /*customer service name*/
	private String tdbarcode_path;/*current QRcode image RUL*/
	private String ffb_tdbarcode_path;/*The latest version of the current app,download the QRCode image URL*/
	private String shareGuideUrl;  /*share guide url*/
	private String myselfimg;     /*current user head image*/
	private String companyname;   /*current user company name*/
	public String myselfname;		/*current user real name*/
	public String myselfphone;		/*current user telephone*/
	public String sex;				/*current user gener*/
	public String companyid;		/*current user company serial*/
	public String version;			/*current app version*/
	public String rolecount;		/*current user role */

	public String isBank;   //这个是新增，判断是否有银行卡

	//这个是在userGetDetail 返回之中多加这个三个字段
	//	public String openaccountname;
	//	public String getOpenaccountname() {
	//		return openaccountname;
	//	}
	//
	//	public void setOpenaccountname(String openaccountname) {
	//		this.openaccountname = openaccountname;
	//	}
	//
	//	public String getBankname() {
	//		return bankname;
	//	}
	//
	//	public void setBankname(String bankname) {
	//		this.bankname = bankname;
	//	}
	//
	//	public String getBanknumber() {
	//		return banknumber;
	//	}
	//
	//	public void setBanknumber(String banknumber) {
	//		this.banknumber = banknumber;
	//	}
	//
	//	public String bankname;
	//	public String banknumber;


	

	public String getRolecount() {
		return rolecount;
	}

	public String getIsBank() {
		return isBank;
	}

	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}

	public void setRolecount(String rolecount) {
		this.rolecount = rolecount;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMyselfname() {
		return myselfname;
	}

	public void setMyselfname(String myselfname) {
		this.myselfname = myselfname;
	}

	public String getMyselfphone() {
		return myselfphone;
	}

	public void setMyselfphone(String myselfphone) {
		this.myselfphone = myselfphone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getServicephone() {
		return servicephone;
	}

	public void setServicephone(String servicephone) {
		this.servicephone = servicephone;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getTdbarcode_path() {
		return tdbarcode_path;
	}

	public void setTdbarcode_path(String tdbarcode_path) {
		this.tdbarcode_path = tdbarcode_path;
	}

	public String getFfb_tdbarcode_path() {
		return ffb_tdbarcode_path;
	}

	public void setFfb_tdbarcode_path(String ffb_tdbarcode_path) {
		this.ffb_tdbarcode_path = ffb_tdbarcode_path;
	}

	public String getShareGuideUrl() {
		return shareGuideUrl;
	}

	public void setShareGuideUrl(String shareGuideUrl) {
		this.shareGuideUrl = shareGuideUrl;
	}

	public String getMyselfimg() {
		return myselfimg;
	}

	public void setMyselfimg(String myselfimg) {
		this.myselfimg = myselfimg;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
}
