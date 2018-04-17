package com.suyin.company.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Company  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Company";
	public static final String ALIAS_COMPANY_ID = "主键";
	public static final String ALIAS_IMAGE = "图片地址";
	public static final String ALIAS_TITLE = "合作商家标题";
	public static final String ALIAS_URL = "合作商家首页（如果有的话）";
	public static final String ALIAS_MODULE = "module";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * 主键       db_column: company_id 
     */ 	
	private java.lang.Integer companyId;
    /**
     * 图片地址       db_column: image 
     */ 	
	private java.lang.String image;
    /**
     * 合作商家标题       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * 合作商家首页（如果有的话）       db_column: url 
     */ 	
	private java.lang.String url;
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

	public Company(){
	}

	public Company(
		java.lang.Integer companyId
	){
		this.companyId = companyId;
	}

	

	public void setCompanyId(java.lang.Integer value) {
		this.companyId = value;
	}
	
	
	public java.lang.Integer getCompanyId() {
		return this.companyId;
	}
	
	public java.lang.String getImage() {
		return this.image;
	}
	
	public void setImage(java.lang.String value) {
		this.image = value;
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
			.append("CompanyId",getCompanyId())
			.append("Image",getImage())
			.append("Title",getTitle())
			.append("Url",getUrl())
			.append("Module",getModule())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompanyId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Company == false) return false;
		if(this == obj) return true;
		Company other = (Company)obj;
		return new EqualsBuilder()
			.append(getCompanyId(),other.getCompanyId())
			.isEquals();
	}
}


/**/

