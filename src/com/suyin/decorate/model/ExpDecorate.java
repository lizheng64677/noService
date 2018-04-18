package com.suyin.decorate.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDecorate  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDecorate";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "活动名称";
	public static final String ALIAS_TITLE = "活动标题";
	public static final String ALIAS_DESCRIPTION = "活动描述";
	public static final String ALIAS_BEGIN_TIME = "活动开始时间";
	public static final String ALIAS_END_TIME = "活动结束时间";
	public static final String ALIAS_CREATE_TIME = "活动创建时间";
	public static final String ALIAS_UPDATE_TIME = "活动修改时间";
	public static final String ALIAS_UPATE_USER = "活动修改人";
	public static final String ALIAS_CREATE_USER = "活动创建人";
	public static final String ALIAS_SHARE_TITLE = "页面分享标题";
	public static final String ALIAS_SHARE_IMG = "页面分享图片";
	public static final String ALIAS_BEGIN_MONEY = "随机金额开始数目";
	public static final String ALIAS_END_MONEY = "随机金额结束数目";

	private Page page;//分页插件


	//columns start
		
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 活动名称       db_column: name 
     */ 	
	private java.lang.String name;
    /**
     * 活动标题       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * 活动描述       db_column: description 
     */ 	
	private java.lang.String description;
	/**
	 * 活动状态 0 未开始,1开始 ，2结束
	 */
	private java.lang.Integer status;
    /**
     * 活动开始时间       db_column: begin_time 
     */ 	
	private java.lang.String beginTime;
    /**
     * 活动结束时间       db_column: end_time 
     */ 	
	private java.lang.String endTime;
    /**
     * 活动创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 活动修改时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
    /**
     * 活动修改人       db_column: upate_user 
     */ 	
	private java.lang.Integer upateUser;
    /**
     * 活动创建人       db_column: create_user 
     */ 	
	private java.lang.Integer createUser;
    /**
     * 页面分享标题       db_column: share_title 
     */ 	
	private java.lang.String shareTitle;
    /**
     * 页面分享图片       db_column: share_img 
     */ 	
	private java.lang.String shareImg;
    /**
     * 随机金额开始数目       db_column: begin_money 
     */ 	
	private java.lang.String beginMoney;
    /**
     * 随机金额结束数目       db_column: end_money 
     */ 	
	private java.lang.String endMoney;
	
	//columns end


	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public ExpDecorate(){
	}

	public ExpDecorate(
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
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	
	
	public java.lang.String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(java.lang.String beginTime) {
		this.beginTime = beginTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.lang.String updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.Integer getUpateUser() {
		return this.upateUser;
	}
	
	public void setUpateUser(java.lang.Integer value) {
		this.upateUser = value;
	}
	
	public java.lang.Integer getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(java.lang.Integer value) {
		this.createUser = value;
	}
	
	public java.lang.String getShareTitle() {
		return this.shareTitle;
	}
	
	public void setShareTitle(java.lang.String value) {
		this.shareTitle = value;
	}
	
	public java.lang.String getShareImg() {
		return this.shareImg;
	}
	
	public void setShareImg(java.lang.String value) {
		this.shareImg = value;
	}
	

	
    public java.lang.String getBeginMoney() {
		return beginMoney;
	}

	public void setBeginMoney(java.lang.String beginMoney) {
		this.beginMoney = beginMoney;
	}

	public java.lang.String getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(java.lang.String endMoney) {
		this.endMoney = endMoney;
	}

	public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Title",getTitle())
			.append("Description",getDescription())
			.append("BeginTime",getBeginTime())
			.append("EndTime",getEndTime())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("UpateUser",getUpateUser())
			.append("CreateUser",getCreateUser())
			.append("ShareTitle",getShareTitle())
			.append("ShareImg",getShareImg())
			.append("BeginMoney",getBeginMoney())
			.append("EndMoney",getEndMoney())
			.append("status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDecorate == false) return false;
		if(this == obj) return true;
		ExpDecorate other = (ExpDecorate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

