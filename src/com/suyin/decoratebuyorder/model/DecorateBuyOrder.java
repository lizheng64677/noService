package com.suyin.decoratebuyorder.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class DecorateBuyOrder  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "DecorateBuyOrder";
	public static final String ALIAS_ORDER_ID = "orderId";
	public static final String ALIAS_OPENID = "微信openid";
	public static final String ALIAS_ORDER_CODE = "订单编号";
	public static final String ALIAS_ORDER_PRICE = "订单金额";
	public static final String ALIAS_ORDER_TYPE = "订单类型:0:福利券，1 体验券，2优惠券";
	public static final String ALIAS_ORDER_STATE = "订单状态:0待支付，1已支付";
	public static final String ALIAS_ORDER_NAME = "产品名称";
	public static final String ALIAS_VOUCHER_CODE = "买到的券号";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";

	private Page page;//分页插件


	//columns start
		
    /**
     * orderId       db_column: order_id 
     */ 	
	private java.lang.Integer orderId;
    /**
     * 微信openid       db_column: openid 
     */ 	
	private java.lang.String openid;
    /**
     * 订单编号       db_column: order_code 
     */ 	
	private java.lang.String orderCode;
    /**
     * 订单金额       db_column: order_price 
     */ 	
	private java.lang.String orderPrice;
    /**
     * 订单类型:0:福利券，1 体验券，2优惠券       db_column: order_type 
     */ 	
	private java.lang.Integer orderType;
    /**
     * 订单状态:0待支付，1已支付       db_column: order_state 
     */ 	
	private java.lang.Integer orderState;
    /**
     * 产品名称       db_column: order_name 
     */ 	
	private java.lang.String orderName;
    /**
     * 买到的券号       db_column: voucher_code 
     */ 	
	private java.lang.String voucherCode;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 修改时间       db_column: update_time 
     */ 	
	private java.lang.String  updateTime;
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
	//columns end

	
	/**
	 *  0姓名，微信昵称 ，手机号
	 *  查询类型
	 */
	private java.lang.Integer saerchType;
	
	/**
	 * 查询内容
	 */
	private java.lang.String text;
	public DecorateBuyOrder(){
	}

	public DecorateBuyOrder(
		java.lang.Integer orderId
	){
		this.orderId = orderId;
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
	
	public java.lang.String getOrderCode() {
		return this.orderCode;
	}
	
	public void setOrderCode(java.lang.String value) {
		this.orderCode = value;
	}
	
	public java.lang.String getOrderPrice() {
		return this.orderPrice;
	}
	
	public void setOrderPrice(java.lang.String value) {
		this.orderPrice = value;
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

	public java.lang.Integer getOrderType() {
		return this.orderType;
	}
	
	public void setOrderType(java.lang.Integer value) {
		this.orderType = value;
	}
	
	public java.lang.Integer getOrderState() {
		return this.orderState;
	}
	
	public void setOrderState(java.lang.Integer value) {
		this.orderState = value;
	}
	
	public java.lang.String getOrderName() {
		return this.orderName;
	}
	
	public void setOrderName(java.lang.String value) {
		this.orderName = value;
	}
	
	public java.lang.String getVoucherCode() {
		return this.voucherCode;
	}
	
	public void setVoucherCode(java.lang.String value) {
		this.voucherCode = value;
	}
	
	public java.lang.String  getCreateTime() {
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
			.append("OrderCode",getOrderCode())
			.append("OrderPrice",getOrderPrice())
			.append("OrderType",getOrderType())
			.append("OrderState",getOrderState())
			.append("OrderName",getOrderName())
			.append("VoucherCode",getVoucherCode())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrderId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DecorateBuyOrder == false) return false;
		if(this == obj) return true;
		DecorateBuyOrder other = (DecorateBuyOrder)obj;
		return new EqualsBuilder()
			.append(getOrderId(),other.getOrderId())
			.isEquals();
	}
}

