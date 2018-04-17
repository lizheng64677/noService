package com.suyin.experience.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpDetail  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpDetail";
	public static final String ALIAS_EXP_DETAIL_ID = "活动详情id";
	public static final String ALIAS_MEMBER_ID = "产品所属商家id";
	public static final String ALIAS_EXP_ID = "活动id";
	public static final String ALIAS_PRO_ID = "产品id";
	public static final String ALIAS_EXP_NUM = "活动人气数";
	public static final String ALIAS_PRO_NUM = "活动详情数量";
	public static final String ALIAS_INTEGRAL = "所需积分数";
	public static final String ALIAS_VALIDITY = "券的到期时间";
	public static final String ALIAS_ADD_DAY = "几天后过期";
	public static final String ALIAS_SEQ_NO = "排序";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";

	private Page page;//分页插件


	//columns start
		
    /**
     * 活动详情id       db_column: exp_detail_id 
     */ 	
	private java.lang.Integer expDetailId;
    /**
     * 产品所属商家id       db_column: member_id 
     */ 	
	private java.lang.Integer memberId;
    /**
     * 活动id       db_column: exp_id 
     */ 	
	private java.lang.Integer expId;
    /**
     * 产品id       db_column: pro_id 
     */ 	
	private java.lang.Integer proId;
    /**
     * 活动人气数       db_column: exp_num 
     */ 	
	private java.lang.Integer expNum;
    /**
     * 活动详情数量       db_column: pro_num 
     */ 	
	private java.lang.Integer proNum;
    /**
     * 所需积分数       db_column: integral 
     */ 	
	private java.lang.Integer integral;
    /**
     * 券的到期时间       db_column: validity 
     */ 	
	private java.lang.String validity;
    /**
     * 几天后过期       db_column: add_day 
     */ 	
	private java.lang.Integer addDay;
    /**
     * 排序       db_column: seq_no 
     */ 	
	private java.lang.Integer seqNo;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
	
	//columns end

	//临时
	public java.lang.String busname;
	public java.lang.String proname;
	

	public ExpDetail(){
	}

	public ExpDetail(
		java.lang.Integer expDetailId
	){
		this.expDetailId = expDetailId;
	}

	

	public void setExpDetailId(java.lang.Integer value) {
		this.expDetailId = value;
	}
	
	
	public java.lang.Integer getExpDetailId() {
		return this.expDetailId;
	}
	
	public java.lang.Integer getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(java.lang.Integer value) {
		this.memberId = value;
	}
	
	public java.lang.Integer getExpId() {
		return this.expId;
	}
	
	public void setExpId(java.lang.Integer value) {
		this.expId = value;
	}
	
	public java.lang.Integer getProId() {
		return this.proId;
	}
	
	public void setProId(java.lang.Integer value) {
		this.proId = value;
	}
	
	public java.lang.Integer getExpNum() {
		return this.expNum;
	}
	
	public void setExpNum(java.lang.Integer value) {
		this.expNum = value;
	}
	
	public java.lang.Integer getProNum() {
		return this.proNum;
	}
	
	public void setProNum(java.lang.Integer value) {
		this.proNum = value;
	}
	
	public java.lang.Integer getIntegral() {
		return this.integral;
	}
	
	public void setIntegral(java.lang.Integer value) {
		this.integral = value;
	}
	
	public java.lang.String getValidity() {
		return this.validity;
	}
	
	public void setValidity(java.lang.String value) {
		this.validity = value;
	}
	
	public java.lang.Integer getAddDay() {
		return this.addDay;
	}
	
	public void setAddDay(java.lang.Integer value) {
		this.addDay = value;
	}
	
	public java.lang.Integer getSeqNo() {
		return this.seqNo;
	}
	
	public void setSeqNo(java.lang.Integer value) {
		this.seqNo = value;
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
    
	public java.lang.String getBusname()
    {
        return busname;
    }

    public void setBusname(java.lang.String busname)
    {
        this.busname = busname;
    }

  
    public java.lang.String getProname()
    {
        return proname;
    }

    public void setProname(java.lang.String proname)
    {
        this.proname = proname;
    }

    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ExpDetailId",getExpDetailId())
			.append("MemberId",getMemberId())
			.append("ExpId",getExpId())
			.append("ProId",getProId())
			.append("ExpNum",getExpNum())
			.append("ProNum",getProNum())
			.append("Integral",getIntegral())
			.append("Validity",getValidity())
			.append("AddDay",getAddDay())
			.append("SeqNo",getSeqNo())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getExpDetailId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpDetail == false) return false;
		if(this == obj) return true;
		ExpDetail other = (ExpDetail)obj;
		return new EqualsBuilder()
			.append(getExpDetailId(),other.getExpDetailId())
			.isEquals();
	}
}

