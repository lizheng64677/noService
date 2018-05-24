package com.suyin.decoratemessage.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class DecorateMessage  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "DecorateMessage";
	public static final String ALIAS_MESSAGE_ID = "messageId";
	public static final String ALIAS_USER_ID = "表示是那个用户的消息，具体关联哪个表待定";
	public static final String ALIAS_CONTENT = "消息内容";
	public static final String ALIAS_SEND_MODULE_NAME = "发送消息的模块名称，如果以后要由消息点击后跳到模块，可以和send_entity联合找到";
	public static final String ALIAS_SEND_ENTITY = "sendEntity";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_IS_READ = "是否已读(默认是N未读状态)（现在用不到，也许以后会用到）";

	private Page page;//分页插件


	//columns start
		
    /**
     * messageId       db_column: message_id 
     */ 	
	private java.lang.Integer messageId;
    /**
     * 表示是那个用户的消息，具体关联哪个表待定       db_column: user_id 
     */ 	
	private java.lang.Integer userId;
	/**
	 * 微信openid
	 */
	private java.lang.String openid;
    /**
     * 消息内容       db_column: content 
     */ 	
	private java.lang.String content;
    /**
     * 发送消息的模块名称，如果以后要由消息点击后跳到模块，可以和send_entity联合找到       db_column: send_module_name 
     */ 	
	private java.lang.String sendModuleName;
    /**
     * sendEntity       db_column: send_entity 
     */ 	
	private java.lang.Integer sendEntity;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * 是否已读(默认是N未读状态)（现在用不到，也许以后会用到）       db_column: is_read 
     */ 	
	private java.lang.String isRead;
	
	//columns end


	public DecorateMessage(){
	}

	public DecorateMessage(
		java.lang.Integer messageId
	){
		this.messageId = messageId;
	}

	

	public void setMessageId(java.lang.Integer value) {
		this.messageId = value;
	}
	
	
	public java.lang.Integer getMessageId() {
		return this.messageId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getSendModuleName() {
		return this.sendModuleName;
	}
	
	public void setSendModuleName(java.lang.String value) {
		this.sendModuleName = value;
	}
	
	public java.lang.Integer getSendEntity() {
		return this.sendEntity;
	}
	
	public void setSendEntity(java.lang.Integer value) {
		this.sendEntity = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.lang.String getIsRead() {
		return this.isRead;
	}
	
	public void setIsRead(java.lang.String value) {
		this.isRead = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    
	public java.lang.String getOpenid() {
		return openid;
	}

	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("MessageId",getMessageId())
			.append("UserId",getUserId())
			.append("Content",getContent())
			.append("SendModuleName",getSendModuleName())
			.append("SendEntity",getSendEntity())
			.append("CreateTime",getCreateTime())
			.append("IsRead",getIsRead())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMessageId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DecorateMessage == false) return false;
		if(this == obj) return true;
		DecorateMessage other = (DecorateMessage)obj;
		return new EqualsBuilder()
			.append(getMessageId(),other.getMessageId())
			.isEquals();
	}
}

