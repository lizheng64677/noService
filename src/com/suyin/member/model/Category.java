package com.suyin.member.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class Category  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "品类管理";
	public static final String ALIAS_CG_ID = "品类主键";
	public static final String ALIAS_CG_NAME = "品类名称";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";

    private Page page;//分页插件


	//columns start
		
    /**
     * 品类主键       db_column: cg_id 
     */ 	
	private java.lang.Integer cgId;
    /**
     * 品类名称       db_column: cg_name 
     */ 	
	private java.lang.String cgName;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 修改时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
	
	//columns end


	public Category(){
	}

	public Category(
		java.lang.Integer cgId
	){
		this.cgId = cgId;
	}

	

	public void setCgId(java.lang.Integer value) {
		this.cgId = value;
	}
	
	
	public java.lang.Integer getCgId() {
		return this.cgId;
	}
	
	public java.lang.String getCgName() {
		return this.cgName;
	}
	
	public void setCgName(java.lang.String value) {
		this.cgName = value;
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
			.append("CgId",getCgId())
			.append("CgName",getCgName())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCgId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Category == false) return false;
		if(this == obj) return true;
		Category other = (Category)obj;
		return new EqualsBuilder()
			.append(getCgId(),other.getCgId())
			.isEquals();
	}
}

