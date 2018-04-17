package com.suyin.theme.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Theme  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Theme";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_IMAGE = "图片地址";
	public static final String ALIAS_TYPE = "1.美女探店；2.游戏；3.折扣;4.主题";
	public static final String ALIAS_BEGIN = "结束时间";
	public static final String ALIAS_END = "开始时间";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_URL = "点击图片的链接地址";
	public static final String ALIAS_MODULE = "module";
	public static final String ALIAS_ENTITY = "entity";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String FORMAT_BEGIN = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_END = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 图片地址       db_column: image 
     */ 	
	private java.lang.String image;
    /**
     * 1.美女探店；2.游戏；3.折扣;4.主题       db_column: type 
     */ 	
	private java.lang.Integer type;
    /**
     * 结束时间       db_column: begin 
     */ 	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private java.util.Date begin;
    /**
     * 开始时间       db_column: end 
     */ 	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private java.util.Date end;
    /**
     * 标题       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * 点击图片的链接地址       db_column: url 
     */ 	
	private java.lang.String url;
    /**
     * module       db_column: module 
     */ 	
	private java.lang.String module;
    /**
     * entity       db_column: entity 
     */ 	
	private java.lang.Integer entity;
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

	public Theme(){
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Theme(
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
	
	public java.lang.String getImage() {
		return this.image;
	}
	
	public void setImage(java.lang.String value) {
		this.image = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public String getBeginString() {
		try {
			return  new SimpleDateFormat(FORMAT_BEGIN).format(getBegin());
		}catch(Exception e) {
			return null;
		}
	}
	
	
	public java.util.Date getBegin() {
		return this.begin;
	}
	
	public void setBegin(java.util.Date value) {
		this.begin = value;
	}
	
	public String getEndString() {
		try {
			return  new SimpleDateFormat(FORMAT_END).format(getEnd());
		}catch(Exception e) {
			return null;
		}
	}
	public void setEndString(String value) {
		try {
			setEnd(new SimpleDateFormat(FORMAT_END).parse(value));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public java.util.Date getEnd() {
		return this.end;
	}
	
	public void setEnd(java.util.Date value) {
		this.end = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getModule() {
		return this.module;
	}
	
	public void setModule(java.lang.String value) {
		this.module = value;
	}
	
	public java.lang.Integer getEntity() {
		return this.entity;
	}
	
	public void setEntity(java.lang.Integer value) {
		this.entity = value;
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
			.append("Id",getId())
			.append("Image",getImage())
			.append("Type",getType())
			.append("Begin",getBegin())
			.append("End",getEnd())
			.append("Title",getTitle())
			.append("Url",getUrl())
			.append("Module",getModule())
			.append("Entity",getEntity())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Theme == false) return false;
		if(this == obj) return true;
		Theme other = (Theme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


/**/

