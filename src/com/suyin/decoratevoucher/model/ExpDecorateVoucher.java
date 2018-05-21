package com.suyin.decoratevoucher.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDecorateVoucher  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDecorateVoucher";
	public static final String ALIAS_ID = "主键id";
	public static final String ALIAS_NAME = "券的名称";
	public static final String ALIAS_TITLE = "券标题,简述";
	public static final String ALIAS_PRICE = "优惠券金额";
	public static final String ALIAS_USE_PRICE = "券试用后，返现给推荐人的佣金";
	public static final String ALIAS_TYPE = "券的类型:0：福利券，1：体验券，2:优惠券";
	public static final String ALIAS_STATE = "状态：0长期，1有效期";
	public static final String ALIAS_VALIDITY_DAY = "有效期";
	public static final String ALIAS_CREATE_USER = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "使用时间";
	public static final String ALIAS_CONTENT = "券使用说明";

	private Page page;//分页插件


	//columns start
		
    /**
     * 主键id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 券的名称       db_column: name 
     */ 	
	private java.lang.String name;
    /**
     * 券标题,简述       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * 优惠券金额       db_column: price 
     */ 	
	private Long price;
    /**
     * 券试用后，返现给推荐人的佣金       db_column: use_price 
     */ 	
	private Long usePrice;
    /**
     * 券的类型:0：福利券，1：体验券，2:优惠券       db_column: type 
     */ 	
	private java.lang.Integer type;
    /**
     * 状态：0长期，1有效期       db_column: state 
     */ 	
	private java.lang.Integer state;
    /**
     * 有效期       db_column: validity_day 
     */ 	
	private java.lang.Integer validityDay;
    /**
     * 创建人       db_column: create_user 
     */ 	
	private java.lang.String createUser;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 使用时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
    /**
     * 券使用说明       db_column: content 
     */ 	
	private java.lang.String content;
	
	/**
	 * 券图形地址
	 */
	private java.lang.String voucheUrl;
	/**
	 * 券总数
	 */
	private java.lang.Integer num;
	/**
	 * 券剩余
	 */
	private java.lang.Integer remNum;
	
	
	//columns end


	public ExpDecorateVoucher(){
	}

	public ExpDecorateVoucher(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public Long getPrice() {
		return this.price;
	}
	
	public void setPrice(Long value) {
		this.price = value;
	}
	
	public Long getUsePrice() {
		return this.usePrice;
	}
	
	public void setUsePrice(Long value) {
		this.usePrice = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getValidityDay() {
		return this.validityDay;
	}
	
	public void setValidityDay(java.lang.Integer value) {
		this.validityDay = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    
	public java.lang.String getVoucheUrl() {
		return voucheUrl;
	}

	public void setVoucheUrl(java.lang.String voucheUrl) {
		this.voucheUrl = voucheUrl;
	}
	

	public java.lang.Integer getNum() {
		return num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public java.lang.Integer getRemNum() {
		return remNum;
	}

	public void setRemNum(java.lang.Integer remNum) {
		this.remNum = remNum;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Title",getTitle())
			.append("Price",getPrice())
			.append("UsePrice",getUsePrice())
			.append("Type",getType())
			.append("State",getState())
			.append("ValidityDay",getValidityDay())
			.append("CreateUser",getCreateUser())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDecorateVoucher == false) return false;
		if(this == obj) return true;
		ExpDecorateVoucher other = (ExpDecorateVoucher)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

