package com.suyin.activity.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class Activity  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Activity";
	public static final String ALIAS_ID = "活动主键";
	public static final String ALIAS_TITLE = "活动标题";
	public static final String ALIAS_BEGIN_TIME = "活动开始时间";
	public static final String ALIAS_END_TIME = "活动结束时间";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_VOTING_RULES = "活动规则";
	public static final String ALIAS_CONTENT = "活动简介";
	public static final String ALIAS_PARTICIPANT_NUMBER = "参数人数";
	public static final String ALIAS_VISIT_TIMES = "访问次数";

	private Page page;//分页插件


	//columns start
		
    /**
     * 活动主键       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 活动标题       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * 活动开始时间       db_column: begin_time 
     */ 	
	private java.lang.String beginTime;
    /**
     * 活动结束时间       db_column: end_time 
     */ 	
	private java.lang.String endTime;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
    /**
     * 活动规则       db_column: voting_rules 
     */ 	
	private java.lang.String votingRules;
    /**
     * 活动简介       db_column: content 
     */ 	
	private java.lang.String content;
    /**
     * 参数人数       db_column: participant_number 
     */ 	
	private java.lang.Long participantNumber = 0L;
    /**
     * 访问次数       db_column: visit_times 
     */ 	
	private java.lang.Long visitTimes =0L ;
	
	//columns end


	public Activity(){
	}

	public Activity(
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
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(java.lang.String value) {
		this.beginTime = value;
	}
	
	public java.lang.String getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.lang.String value) {
		this.endTime = value;
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
	
	public java.lang.String getVotingRules() {
		return this.votingRules;
	}
	
	public void setVotingRules(java.lang.String value) {
		this.votingRules = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.Long getParticipantNumber() {
		return this.participantNumber;
	}
	
	public void setParticipantNumber(java.lang.Long value) {
		this.participantNumber = value;
	}
	
	public java.lang.Long getVisitTimes() {
		return this.visitTimes;
	}
	
	public void setVisitTimes(java.lang.Long value) {
		this.visitTimes = value;
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
			.append("Title",getTitle())
			.append("BeginTime",getBeginTime())
			.append("EndTime",getEndTime())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("VotingRules",getVotingRules())
			.append("Content",getContent())
			.append("ParticipantNumber",getParticipantNumber())
			.append("VisitTimes",getVisitTimes())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Activity == false) return false;
		if(this == obj) return true;
		Activity other = (Activity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

