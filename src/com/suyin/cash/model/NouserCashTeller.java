package com.suyin.cash.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class NouserCashTeller  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "NouserCashLog";
	public static final String ALIAS_LOG_ID = "logId";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_MONEY = "money";
	public static final String ALIAS_DIRECTION = "增加了是1，减少了是2";
	public static final String ALIAS_STATUS = "0新建，1同意，2，拒绝";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * logId       db_column: log_id 
     */ 	
	private java.lang.Integer logId;
    /**
     * userId       db_column: user_id 
     */ 	
	private java.lang.Integer userId;
    /**
     * money       db_column: money 
     */ 	
	private BigDecimal money;
    /**
     * 0新建，1同意，2，拒绝       db_column: status 
     */ 	
	private java.lang.String status;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.util.Date createTime;
	
	private String updateTime;
	
	
	//columns end
	private String userPhone;
	
	public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public final String getUpdateTime() {
		return updateTime;
	}

	public final void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	private List<Attachment> attachments;
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public NouserCashTeller(){
	}

	public NouserCashTeller(
		java.lang.Integer logId
	){
		this.logId = logId;
	}

	

	public void setLogId(java.lang.Integer value) {
		this.logId = value;
	}
	
	
	public java.lang.Integer getLogId() {
		return this.logId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public BigDecimal getMoney() {
		return this.money;
	}
	
	public void setMoney(  BigDecimal value) {
		this.money = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public String getCreateTimeString() {
		try {
			return  new SimpleDateFormat(FORMAT_CREATE_TIME).format(getCreateTime());
		}catch(Exception e) {
			return null;
		}
	}
	public void setCreateTimeString(String value) {
		try {
			setCreateTime(new SimpleDateFormat(FORMAT_CREATE_TIME).parse(value));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	/*
*/
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLogId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof NouserCashTeller == false) return false;
		if(this == obj) return true;
		NouserCashTeller other = (NouserCashTeller)obj;
		return new EqualsBuilder()
			.append(getLogId(),other.getLogId())
			.isEquals();
	}
}


/**/

