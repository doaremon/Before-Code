package com.ffbao.entity;

/**
 * 
 * @FileName:ReportDetails.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ReportDetails.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: get report detail Message， call userGetReportDetail
 */
public class ReportDetails {

	/**
	 recorid:100001,
	 customerphone:"134433222123"，
	 customername:"沈东等"，
	 country:"中国",
	 city:"海南",
	 age:40,
	 vocation:"经理",
	 property:"100万以下",
	 address:""朝阳区慧忠北路慧忠里123号楼(近北辰东路)",
	 type:"外往内地发送",
	 intention_city:"海南",
	 intention_buildingname:"绿地国宝",
	 budget:100,
	 purpose:"为子女购房",
	 possibility:"很想买而且很有实力",
	 "trip_type":"房房宝安排",
	 trip_plan_state："待定",
	 need_ticket:"待定",
	 need_hotel:"待定",
	 headcount:3,
	 departure_date:“”,
	 arrival_date:"",
	 createdate:"2014-09-21 10:00:00"，state:"报备成功"
	 */
	/**
	{"status":1,"message":"成功","result":{
	
	"reporid":100002,
	"customername":"张三本人",
	"customerphone":"13211451123",
	"country":"中国",
	"city":"北京",
	"age":38,
	"vocation":"教育行业",
	"property":"2",
	"address":"gugukkhu",
	"type":0,
	"typevalue":"本地送往外地",
	"intention_city":"海南",
	"sex":1,
	"sexvalue":"女",
	"intention_building":1,
	"intention_buildingvalue":"君安世纪城",
	"budget":1,
	"budgetvalue":"1百万-5百万",
	"purpose":"0",
	"purposevalue":"为子女买房",
	"possibilit":"1",
	"possibilitvalue":"可买可不买且有实力",
	"trip_type":1,
	"trip_typevalue":"自定义行程",
	"need_ticket":1,
	"need_ticketvalue":"需要",
	"need_hotel":2,
	"need_hotelvalue":"不需要",
	"headcount":3,
	"tripplanstate":0,
	"tripplanstatevalue":"待定",
	"departure_date":"2014-10-11 11:33:32",
	"arrival_date":"2014-10-12 11:33:35"
	"agentType": 1
	
	  NSNumber* companyid;
    NSString* companyname;
    remark
	}}

	 */
	private String reporid;
	private String customername;
	private String customerphone;
	private String country;
	private String city;
	private String age;
	private String vocation;
	private String property;
	private String propertyvalue;
	private String address;
	private String type;
	private String typevalue;
	private String intention_city;
	private String intention_city_level;
	private String intention_cityvalue;
	private String sex;
	private String sexvalue;
	private String intention_building;
	private String intention_buildingvalue;
	private String budget;
	private String budgetvalue;
	private String purpose;
	private String purposevalue;
	private String possibilit;
	private String possibilitvalue;
	private String trip_type;
	private String trip_typevalue;
	private String need_ticket;
	private String need_ticketvalue;
	private String need_hotel;
	private String need_hotelvalue;
	private String headcount;
	private String tripplanstate;
	private String tripplanstatevalue;
	private String departure_date;
	private String arrival_date;
	private String state;
	private String statevalue;
	private String customerid;
	private String companyid;
	private String companyname;
	private String remark;
	private String withlooktype;
	private String agentType;
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getWithlooktype() {
		return withlooktype;
	}
	public void setWithlooktype(String withlooktype) {
		this.withlooktype = withlooktype;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	private String province;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIntention_city_level() {
		return intention_city_level;
	}
	public void setIntention_city_level(String intention_city_level) {
		this.intention_city_level = intention_city_level;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getReporid() {
		return reporid;
	}
	public void setReporid(String reporid) {
		this.reporid = reporid;
	}
	public String getIntention_cityvalue() {
		return intention_cityvalue;
	}
	public void setIntention_cityvalue(String intention_cityvalue) {
		this.intention_cityvalue = intention_cityvalue;
	}
	public String getPropertyvalue() {
		return propertyvalue;
	}
	public void setPropertyvalue(String propertyvalue) {
		this.propertyvalue = propertyvalue;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getBudgetvalue() {
		return budgetvalue;
	}
	public void setBudgetvalue(String budgetvalue) {
		this.budgetvalue = budgetvalue;
	}
	public String getTypevalue() {
		return typevalue;
	}
	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSexvalue() {
		return sexvalue;
	}
	public void setSexvalue(String sexvalue) {
		this.sexvalue = sexvalue;
	}
	public String getIntention_buildingvalue() {
		return intention_buildingvalue;
	}
	public void setIntention_buildingvalue(String intention_buildingvalue) {
		this.intention_buildingvalue = intention_buildingvalue;
	}
	public String getPurposevalue() {
		return purposevalue;
	}
	public void setPurposevalue(String purposevalue) {
		this.purposevalue = purposevalue;
	}
	public String getPossibilitvalue() {
		return possibilitvalue;
	}
	public void setPossibilitvalue(String possibilitvalue) {
		this.possibilitvalue = possibilitvalue;
	}
	public String getTrip_typevalue() {
		return trip_typevalue;
	}
	public void setTrip_typevalue(String trip_typevalue) {
		this.trip_typevalue = trip_typevalue;
	}
	public String getNeed_ticketvalue() {
		return need_ticketvalue;
	}
	public void setNeed_ticketvalue(String need_ticketvalue) {
		this.need_ticketvalue = need_ticketvalue;
	}
	public String getNeed_hotelvalue() {
		return need_hotelvalue;
	}
	public void setNeed_hotelvalue(String need_hotelvalue) {
		this.need_hotelvalue = need_hotelvalue;
	}
	public String getTripplanstate() {
		return tripplanstate;
	}
	public void setTripplanstate(String tripplanstate) {
		this.tripplanstate = tripplanstate;
	}
	public String getTripplanstatevalue() {
		return tripplanstatevalue;
	}
	public void setTripplanstatevalue(String tripplanstatevalue) {
		this.tripplanstatevalue = tripplanstatevalue;
	}
	public String getStatevalue() {
		return statevalue;
	}
	public void setStatevalue(String statevalue) {
		this.statevalue = statevalue;
	}
	public String getIntention_building() {
		return intention_building;
	}
	public void setIntention_building(String intention_building) {
		this.intention_building = intention_building;
	}
	public String getCustomerphone() {
		return customerphone;
	}
	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getVocation() {
		return vocation;
	}
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}
	public String getPossibilit() {
		return possibilit;
	}
	public void setPossibilit(String possibilit) {
		this.possibilit = possibilit;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntention_city() {
		return intention_city;
	}
	public void setIntention_city(String intention_city) {
		this.intention_city = intention_city;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTrip_type() {
		return trip_type;
	}
	public void setTrip_type(String trip_type) {
		this.trip_type = trip_type;
	}
	
	public String getNeed_ticket() {
		return need_ticket;
	}
	public void setNeed_ticket(String need_ticket) {
		this.need_ticket = need_ticket;
	}
	public String getNeed_hotel() {
		return need_hotel;
	}
	public void setNeed_hotel(String need_hotel) {
		this.need_hotel = need_hotel;
	}
	public String getHeadcount() {
		return headcount;
	}
	public void setHeadcount(String headcount) {
		this.headcount = headcount;
	}
	public String getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
