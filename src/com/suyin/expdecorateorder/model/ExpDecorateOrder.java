package com.suyin.expdecorateorder.model;

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
	private java.lang.Long withdrawPrice;
    /**
     * 提现状态0,审核中,1审核通过，2审核失败       db_column: state 
     */ 	
	private java.lang.Integer state;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * 审核时间       db_column: review_time 
     */ 	
	private java.util.Date reviewTime;
    /**
     * 审核人编号       db_column: review_user 
     */ 	
	private java.lang.String reviewUser;
	
	//columns end


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
	
	public java.lang.Long getWithdrawPrice() {
		return this.withdrawPrice;
	}
	
	public void setWithdrawPrice(java.lang.Long value) {
		this.withdrawPrice = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getReviewTime() {
		return this.reviewTime;
	}
	
	public void setReviewTime(java.util.Date value) {
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
}

