package com.suyin.expdecorateorder.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDecorateOrder  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDecorateOrder";
	public static final String ALIAS_ORDER_ID = "orderId";
	public static final String ALIAS_OPENID = "微信id";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_WITHDRAW_PRICE = "提现金额";
	public static final String ALIAS_STATE = "提现状态0,审核中,1审核通过，2审核失败";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_REVIEW_TIME = "审核时间";
	public static final String ALIAS_REVIEW_USER = "审核人编号";

	private Page page;//分页插件


	//columns start
		
    /**
     * orderId       db_column: order_id 
     */ 	
	private java.lang.Integer orderId;
    /**
     * 微信id       db_column: openid 
     */ 	
	private java.lang.String openid;
    /**
     * 用户id       db_column: user_id 
     */ 	
	private java.lang.Integer userId;
    /**
     * 提现金额       db_column: withdraw_price 
     */ 	
	private BigDecimal withdrawPrice;
    /**
     * 提现状态0,审核中,1审核通过，2审核失败       db_column: state 
     */ 	
	private java.lang.Integer state;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 审核时间       db_column: review_time 
     */ 	
	private java.lang.String reviewTime;
    /**
     * 审核人编号       db_column: review_user 
     */ 	
	private java.lang.String reviewUser;
	/**
	 * 用户微信昵称
	 */
	private java.lang.String  nickName;
	/**
	 * 用户实名
	 */
	private java.lang.String  userName;
	/**
	 * 用户手机号
	 */
	private java.lang.String  userPhone;
	/*
	 * 支付宝
	 */
	private java.lang.String alipayNumber;
	
	/**
	 *  0姓名，微信昵称 ，手机号
	 *  查询类型
	 */
	private java.lang.Integer saerchType;
	
	/**
	 * 查询内容
	 */
	private java.lang.String text;
	
	
	//columns end


	public java.lang.String getAlipayNumber() {
		return alipayNumber;
	}

	public void setAlipayNumber(java.lang.String alipayNumber) {
		this.alipayNumber = alipayNumber;
	}

	public ExpDecorateOrder(){
	}

	public ExpDecorateOrder(
		java.lang.Integer orderId
	){
		this.orderId = orderId;
	}

	

	public void setOrderId(java.lang.Integer value) {
		this.orderId = value;
	}
	
	
	public java.lang.Integer getOrderId() {
		return this.orderId;
	}
	
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	

	
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	public java.lang.String getReviewTime() {
		return this.reviewTime;
	}
	
	public void setReviewTime(java.lang.String value) {
		this.reviewTime = value;
	}
	
	public java.lang.String getReviewUser() {
		return this.reviewUser;
	}
	
	public void setReviewUser(java.lang.String value) {
		this.reviewUser = value;
	}
	
    public java.lang.Integer getSaerchType() {
		return saerchType;
	}

	public void setSaerchType(java.lang.Integer saerchType) {
		this.saerchType = saerchType;
	}

	public java.lang.String getText() {
		return text;
	}

	public void setText(java.lang.String text) {
		this.text = text;
	}

	public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    
	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(java.lang.String userPhone) {
		this.userPhone = userPhone;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OrderId",getOrderId())
			.append("Openid",getOpenid())
			.append("UserId",getUserId())
			.append("WithdrawPrice",getWithdrawPrice())
			.append("State",getState())
			.append("CreateTime",getCreateTime())
			.append("ReviewTime",getReviewTime())
			.append("ReviewUser",getReviewUser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrderId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDecorateOrder == false) return false;
		if(this == obj) return true;
		ExpDecorateOrder other = (ExpDecorateOrder)obj;
		return new EqualsBuilder()
			.append(getOrderId(),other.getOrderId())
			.isEquals();
	}

	public BigDecimal getWithdrawPrice() {
		return withdrawPrice;
	}

	public void setWithdrawPrice(BigDecimal withdrawPrice) {
		this.withdrawPrice = withdrawPrice;
	}
}

