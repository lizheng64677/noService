package com.suyin.about.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class About  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "About";
	public static final String ALIAS_ABOUT_ID = "aboutId";
	public static final String ALIAS_CONTENT = "内容@";
	public static final String ALIAS_TYPE = "0表示平台简介；1表示用户协议";
	public static final String ALIAS_MODULE = "module";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * aboutId       db_column: about_id 
     */ 	
	private java.lang.Integer aboutId;
    /**
     * 内容@       db_column: content 
     */ 	
	private java.lang.String content;
    /**
     * 0表示平台简介；1表示用户协议       db_column: type 
     */ 	
	private java.lang.Integer type;
    /**
     * module       db_column: module 
     */ 	
	private java.lang.String module;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * updateTime       db_column: update_time 
     */ 	
	private java.util.Date updateTime;
	
	//columns end

	private List<Attachment> attachments;
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public About(){
	}

	public About(
		java.lang.Integer aboutId
	){
		this.aboutId = aboutId;
	}

	

	public void setAboutId(java.lang.Integer value) {
		this.aboutId = value;
	}
	
	
	public java.lang.Integer getAboutId() {
		return this.aboutId;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.String getModule() {
		return this.module;
	}
	
	public void setModule(java.lang.String value) {
		this.module = value;
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
	
	public String getUpdateTimeString() {
		try {
			return  new SimpleDateFormat(FORMAT_UPDATE_TIME).format(getUpdateTime());
		}catch(Exception e) {
			return null;
		}
	}
	public void setUpdateTimeString(String value) {
		try {
			setUpdateTime(new SimpleDateFormat(FORMAT_UPDATE_TIME).parse(value));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	/*
*/
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("AboutId",getAboutId())
			.append("Content",getContent())
			.append("Type",getType())
			.append("Module",getModule())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAboutId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof About == false) return false;
		if(this == obj) return true;
		About other = (About)obj;
		return new EqualsBuilder()
			.append(getAboutId(),other.getAboutId())
			.isEquals();
	}
}


/**/

