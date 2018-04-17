package com.suyin.participator.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Participator  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Participator";
	public static final String ALIAS_ID = "活动主键";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_NUMBER = "编号";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_ACTIVITY_DECLARATION = "活动宣言";
	public static final String ALIAS_INTRODUCE = "个人简介";
	public static final String ALIAS_TYPE = "类型：0 强警标兵,1最美警嫂";
	public static final String ALIAS_VOTES_NUMBER = "得票数";
	public static final String ALIAS_HEAD_IMG_URL = "头像地址";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";

	private Page page;//分页插件


	//columns start
		
    /**
     * 活动主键       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 姓名       db_column: name 
     */ 	
	private java.lang.String name;
    /**
     * 编号       db_column: number 
     */ 	
	private java.lang.String number;
    /**
     * 年龄       db_column: age 
     */ 	
	private java.lang.Long age;
    /**
     * 活动宣言       db_column: activity_declaration 
     */ 	
	private java.lang.String activityDeclaration;
    /**
     * 个人简介       db_column: introduce 
     */ 	
	private java.lang.String introduce;
    /**
     * 类型：0 强警标兵,1最美警嫂       db_column: type 
     */ 	
	private java.lang.Long type;
    /**
     * 得票数       db_column: votes_number 
     */ 	
	private java.lang.Long votesNumber = 0L;
    /**
     * 头像地址       db_column: head_img_url 
     */ 	
	private java.lang.String headImgUrl;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
	
    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;
	
	//columns end


	public Participator(){
	}

	public Participator(
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
	
	public java.lang.String getNumber() {
		return this.number;
	}
	
	public void setNumber(java.lang.String value) {
		this.number = value;
	}
	
	public java.lang.Long getAge() {
		return this.age;
	}
	
	public void setAge(java.lang.Long value) {
		this.age = value;
	}
	
	public java.lang.String getActivityDeclaration() {
		return this.activityDeclaration;
	}
	
	public void setActivityDeclaration(java.lang.String value) {
		this.activityDeclaration = value;
	}
	
	public java.lang.String getIntroduce() {
		return this.introduce;
	}
	
	public void setIntroduce(java.lang.String value) {
		this.introduce = value;
	}
	
	public java.lang.Long getType() {
		return this.type;
	}
	
	public void setType(java.lang.Long value) {
		this.type = value;
	}
	
	public java.lang.Long getVotesNumber() {
		return this.votesNumber;
	}
	
	public void setVotesNumber(java.lang.Long value) {
		this.votesNumber = value;
	}
	
	public java.lang.String getHeadImgUrl() {
		return this.headImgUrl;
	}
	
	public void setHeadImgUrl(java.lang.String value) {
		this.headImgUrl = value;
	}
	
	public java.lang.String getCreateTime() {
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
			.append("Id",getId())
			.append("Name",getName())
			.append("Number",getNumber())
			.append("Age",getAge())
			.append("ActivityDeclaration",getActivityDeclaration())
			.append("Introduce",getIntroduce())
			.append("Type",getType())
			.append("VotesNumber",getVotesNumber())
			.append("HeadImgUrl",getHeadImgUrl())
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
		if(obj instanceof Participator == false) return false;
		if(this == obj) return true;
		Participator other = (Participator)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}

