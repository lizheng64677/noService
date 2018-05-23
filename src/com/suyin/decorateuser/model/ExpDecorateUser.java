package com.suyin.decorateuser.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDecorateUser  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDecorateUser";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_OPENID = "微信openid";
	public static final String ALIAS_USE_OPENID = "推荐人openid";
	public static final String ALIAS_USE_NUM = "已推荐用户；已邀请用户  默认0";
	public static final String ALIAS_USER_PHONE = "手机号";
	public static final String ALIAS_IS_EXP_USER = "是否体验用户:0 否：1是";
	public static final String ALIAS_IS_EXP_ORG = "是否外销编制:0否1是";
	public static final String ALIAS_NICK_NAME = "微信昵称";
	public static final String ALIAS_USER_NAME = "实名";
	public static final String ALIAS_USER_STATE = "用户状态(0正常,1锁定,2删除)";
	public static final String ALIAS_COUNT_PRICE = "总金额";
	public static final String ALIAS_BALANCE_PRICE = "余额";
	public static final String ALIAS_ALIPAY_NUMBER = "支付宝账号";
	public static final String ALIAS_IPHONE = "手机号";
	public static final String ALIAS_PASSWORD = "提现密码";
	public static final String ALIAS_HEAD_IMG = "headImg";

	private Page page;//分页插件


	//columns start
		
    /**
     * userId       db_column: user_id 
     */ 	
	private java.lang.Integer userId;
    /**
     * 微信openid       db_column: openid 
     */ 	
	private java.lang.String openid;
    /**
     * 推荐人openid       db_column: use_openid 
     */ 	
	private java.lang.String useOpenid;
    /**
     * 已推荐用户；已邀请用户  默认0       db_column: use_num 
     */ 	
	private java.lang.Integer useNum;
    /**
     * 手机号       db_column: user_phone 
     */ 	
	private java.lang.String userPhone;
    /**
     * 是否体验用户:0 否：1是       db_column: is_exp_user 
     */ 	
	private java.lang.Integer isExpUser;
    /**
     * 是否外销编制:0否1是       db_column: is_exp_org 
     */ 	
	private java.lang.Integer isExpOrg;
    /**
     * 微信昵称       db_column: nick_name 
     */ 	
	private java.lang.String nickName;
    /**
     * 实名       db_column: user_name 
     */ 	
	private java.lang.String userName;
    /**
     * 用户状态(0正常,1锁定,2删除)       db_column: user_state 
     */ 	
	private java.lang.Integer userState;
    /**
     * 总金额       db_column: count_price 
     */ 	
	private Long countPrice;
    /**
     * 余额       db_column: balance_price 
     */ 	
	private Long balancePrice;
    /**
     * 支付宝账号       db_column: alipay_number 
     */ 	
	private java.lang.String alipayNumber;
    /**
     * 手机号       db_column: iphone 
     */ 	
	private java.lang.String iphone;
    /**
     * 提现密码       db_column: password 
     */ 	
	private java.lang.String password;
    /**
     * headImg       db_column: head_img 
     */ 	
	private java.lang.String headImg;
	
	private java.lang.Integer saerchType;
	
	
	private java.lang.String text;
	//columns end


	public ExpDecorateUser(){
	}

	public ExpDecorateUser(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	
	public java.lang.Integer getUserId() {
		return this.userId;
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
	
	public java.lang.Integer getUseNum() {
		return this.useNum;
	}
	
	public void setUseNum(java.lang.Integer value) {
		this.useNum = value;
	}
	
	public java.lang.String getUserPhone() {
		return this.userPhone;
	}
	
	public void setUserPhone(java.lang.String value) {
		this.userPhone = value;
	}
	
	public java.lang.Integer getIsExpUser() {
		return this.isExpUser;
	}
	
	public void setIsExpUser(java.lang.Integer value) {
		this.isExpUser = value;
	}
	
	public java.lang.Integer getIsExpOrg() {
		return this.isExpOrg;
	}
	
	public void setIsExpOrg(java.lang.Integer value) {
		this.isExpOrg = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.Integer getUserState() {
		return this.userState;
	}
	
	public void setUserState(java.lang.Integer value) {
		this.userState = value;
	}
	
	public Long getCountPrice() {
		return this.countPrice;
	}
	
	public void setCountPrice(Long value) {
		this.countPrice = value;
	}
	
	public Long getBalancePrice() {
		return this.balancePrice;
	}
	
	public void setBalancePrice(Long value) {
		this.balancePrice = value;
	}
	
	public java.lang.String getAlipayNumber() {
		return this.alipayNumber;
	}
	
	public void setAlipayNumber(java.lang.String value) {
		this.alipayNumber = value;
	}
	
	public java.lang.String getIphone() {
		return this.iphone;
	}
	
	public void setIphone(java.lang.String value) {
		this.iphone = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getHeadImg() {
		return this.headImg;
	}
	
	public void setHeadImg(java.lang.String value) {
		this.headImg = value;
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
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("Openid",getOpenid())
			.append("UseOpenid",getUseOpenid())
			.append("UseNum",getUseNum())
			.append("UserPhone",getUserPhone())
			.append("IsExpUser",getIsExpUser())
			.append("IsExpOrg",getIsExpOrg())
			.append("NickName",getNickName())
			.append("UserName",getUserName())
			.append("UserState",getUserState())
			.append("CountPrice",getCountPrice())
			.append("BalancePrice",getBalancePrice())
			.append("AlipayNumber",getAlipayNumber())
			.append("Iphone",getIphone())
			.append("Password",getPassword())
			.append("HeadImg",getHeadImg())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDecorateUser == false) return false;
		if(this == obj) return true;
		ExpDecorateUser other = (ExpDecorateUser)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

