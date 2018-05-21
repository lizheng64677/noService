package com.suyin.uservoucher.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDecorateUserVoucher  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDecorateUserVoucher";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_VOUCHER_ID = "券主键ID";
	public static final String ALIAS_VOURCHE_CODE = "券号";
	public static final String ALIAS_STATE = "券状态 0:未使用，1:已使用，2已过期作废";
	public static final String ALIAS_OPENID = "openid";
	public static final String ALIAS_USE_OPENID = "推荐人openid";
	public static final String ALIAS_CREATE_TIME = "券购买时间";
	public static final String ALIAS_USE_TIME = "使用时间";

	private Page page;//分页插件


	//columns start
		
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 券主键ID       db_column: voucher_id 
     */ 	
	private java.lang.Integer voucherId;
    /**
     * 券号       db_column: vourche_code 
     */ 	
	private java.lang.String vourcheCode;
    /**
     * 券状态 0:未使用，1:已使用，2已过期作废       db_column: state 
     */ 	
	private java.lang.Integer state;
    /**
     * openid       db_column: openid 
     */ 	
	private java.lang.String openid;
    /**
     * 推荐人openid       db_column: use_openid 
     */ 	
	private java.lang.String useOpenid;
    /**
     * 券购买时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 使用时间       db_column: use_time 
     */ 	
	private java.lang.String useTime;
	/**
	 * 用户头像
	 */
	private java.lang.String headImg;
	/**
	 * 用户昵称
	 */
	private java.lang.String nickName;
	/**
	 * 推荐人头像
	 */
	private java.lang.String uheadImg;
	/**
	 * 推荐人昵称
	 */
	private java.lang.String unickName;
	/**
	 * 优惠券金额
	 */
	private java.lang.String price;
	/**
	 * 返现金额
	 */
	private java.lang.String usePrice;
	/**
	 * 优惠券金额
	 */
	private java.lang.String name;
	
	/**
	 * 0 福利券， 1体验券，2优惠券
	 */
	private java.lang.String  type;

	//columns end


	public ExpDecorateUserVoucher(){
	}

	public ExpDecorateUserVoucher(
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
	
	public java.lang.Integer getVoucherId() {
		return this.voucherId;
	}
	
	public void setVoucherId(java.lang.Integer value) {
		this.voucherId = value;
	}
	
	public java.lang.String getVourcheCode() {
		return this.vourcheCode;
	}
	
	public void setVourcheCode(java.lang.String value) {
		this.vourcheCode = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	
	public java.lang.String getUseOpenid() {
		return this.useOpenid;
	}
	
	public void setUseOpenid(java.lang.String value) {
		this.useOpenid = value;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	public java.lang.String getUseTime() {
		return this.useTime;
	}
	
	public void setUseTime(java.lang.String value) {
		this.useTime = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    
    
	public java.lang.String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(java.lang.String headImg) {
		this.headImg = headImg;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getUheadImg() {
		return uheadImg;
	}

	public void setUheadImg(java.lang.String uheadImg) {
		this.uheadImg = uheadImg;
	}

	public java.lang.String getUnickName() {
		return unickName;
	}

	public void setUnickName(java.lang.String unickName) {
		this.unickName = unickName;
	}

	public java.lang.String getPrice() {
		return price;
	}

	public void setPrice(java.lang.String price) {
		this.price = price;
	}

	public java.lang.String getUsePrice() {
		return usePrice;
	}

	public void setUsePrice(java.lang.String usePrice) {
		this.usePrice = usePrice;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("VoucherId",getVoucherId())
			.append("VourcheCode",getVourcheCode())
			.append("State",getState())
			.append("Openid",getOpenid())
			.append("UseOpenid",getUseOpenid())
			.append("CreateTime",getCreateTime())
			.append("UseTime",getUseTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDecorateUserVoucher == false) return false;
		if(this == obj) return true;
		ExpDecorateUserVoucher other = (ExpDecorateUserVoucher)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

