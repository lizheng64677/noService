package com.suyin.expdecorateorder.model;

import java.math.BigDecimal;

import com.suyin.system.model.Page;

public class DecorateOrderDTO  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;

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
	private java.lang.String nickName;
	private java.lang.String userPhone;
	private java.lang.String userName;
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
	
	//columns end


	public DecorateOrderDTO(){
	}

	public DecorateOrderDTO(
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

	public java.lang.String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(java.lang.String userPhone) {
		this.userPhone = userPhone;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public BigDecimal getWithdrawPrice() {
		return withdrawPrice;
	}

	public void setWithdrawPrice(BigDecimal withdrawPrice) {
		this.withdrawPrice = withdrawPrice;
	}
}

