package com.suyin.decoraterecord.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class DecorateRecord  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "DecorateRecord";
	public static final String ALIAS_RECORD_ID = "recordId";
	public static final String ALIAS_TYPE = "收益类型:0分享，1:购买福券返佣金，2签单奖励";
	public static final String ALIAS_PUBLISH_OPENID = "微信分享发起者openid";
	public static final String ALIAS_PUBLISH_USERID = "发布者userid";
	public static final String ALIAS_ACCEPT_OPENID = "被邀请人微信openid";
	public static final String ALIAS_ACCPT_USERID = "接受人userid";
	public static final String ALIAS_PRICE_STATE = "券状态 0:已收益 1:待收益";
	public static final String ALIAS_STATE = "标识：0发起者，1:接收者";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_COMMISSION_PRICE = "本次变动金额";
	public static final String ALIAS_MESSAGE = "收益说明，说明为何变更";

	private Page page;//分页插件


	//columns start
		
    /**
     * recordId       db_column: record_id 
     */ 	
	private java.lang.Integer recordId;
    /**
     * 收益类型:0分享，1:购买福券返佣金，2签单奖励       db_column: type 
     */ 	
	private java.lang.Integer type;
    /**
     * 微信分享发起者openid       db_column: publish_openid 
     */ 	
	private java.lang.String publishOpenid;
    /**
     * 发布者userid       db_column: publish_userid 
     */ 	
	private java.lang.Integer publishUserid;
    /**
     * 被邀请人微信openid       db_column: accept_openid 
     */ 	
	private java.lang.String acceptOpenid;
    /**
     * 接受人userid       db_column: accpt_userid 
     */ 	
	private java.lang.Integer accptUserid;
    /**
     * 券状态 0:已收益 1:待收益       db_column: price_state 
     */ 	
	private java.lang.Integer priceState;
    /**
     * 标识：0发起者，1:接收者       db_column: state 
     */ 	
	private java.lang.Integer state;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * 本次变动金额       db_column: commission_price 
     */ 	
	private java.lang.String commissionPrice;
    /**
     * 收益说明，说明为何变更       db_column: message 
     */ 	
	private java.lang.String message;
	
	//columns end


	public DecorateRecord(){
	}

	public DecorateRecord(
		java.lang.Integer recordId
	){
		this.recordId = recordId;
	}

	

	public void setRecordId(java.lang.Integer value) {
		this.recordId = value;
	}
	
	
	public java.lang.Integer getRecordId() {
		return this.recordId;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.String getPublishOpenid() {
		return this.publishOpenid;
	}
	
	public void setPublishOpenid(java.lang.String value) {
		this.publishOpenid = value;
	}
	
	public java.lang.Integer getPublishUserid() {
		return this.publishUserid;
	}
	
	public void setPublishUserid(java.lang.Integer value) {
		this.publishUserid = value;
	}
	
	public java.lang.String getAcceptOpenid() {
		return this.acceptOpenid;
	}
	
	public void setAcceptOpenid(java.lang.String value) {
		this.acceptOpenid = value;
	}
	
	public java.lang.Integer getAccptUserid() {
		return this.accptUserid;
	}
	
	public void setAccptUserid(java.lang.Integer value) {
		this.accptUserid = value;
	}
	
	public java.lang.Integer getPriceState() {
		return this.priceState;
	}
	
	public void setPriceState(java.lang.Integer value) {
		this.priceState = value;
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
	
	public java.lang.String getCommissionPrice() {
		return this.commissionPrice;
	}
	
	public void setCommissionPrice(java.lang.String value) {
		this.commissionPrice = value;
	}
	
	public java.lang.String getMessage() {
		return this.message;
	}
	
	public void setMessage(java.lang.String value) {
		this.message = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RecordId",getRecordId())
			.append("Type",getType())
			.append("PublishOpenid",getPublishOpenid())
			.append("PublishUserid",getPublishUserid())
			.append("AcceptOpenid",getAcceptOpenid())
			.append("AccptUserid",getAccptUserid())
			.append("PriceState",getPriceState())
			.append("State",getState())
			.append("CreateTime",getCreateTime())
			.append("CommissionPrice",getCommissionPrice())
			.append("Message",getMessage())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRecordId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DecorateRecord == false) return false;
		if(this == obj) return true;
		DecorateRecord other = (DecorateRecord)obj;
		return new EqualsBuilder()
			.append(getRecordId(),other.getRecordId())
			.isEquals();
	}
}

