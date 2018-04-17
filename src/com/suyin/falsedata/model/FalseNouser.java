package com.suyin.falsedata.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class FalseNouser  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "FalseNouser";
	public static final String ALIAS_USER_ID = "主键";
	public static final String ALIAS_USER_PHONE = "手机号";
	public static final String ALIAS_USER_STATE = "用户状态";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_HEAD_URL = "头像@头像url";
	public static final String ALIAS_TEXP_SHARE_NUM = "用户所分享的数量";
	public static final String ALIAS_TTASK_EASY_NUM = "轻松赚预留字段(暂不实现 )";
	public static final String ALIAS_TTASK_HELP_NUM = "帮我赚预留字段(暂不实现)";

	private Page page;//分页插件


	//columns start
		
    /**
     * 主键       db_column: user_id 
     */ 	
	private java.lang.Integer userId;
    /**
     * 手机号       db_column: user_phone 
     */ 	
	private java.lang.String userPhone;
    /**
     * 假数据       db_column: user_state 
     */ 	
	private java.lang.Integer userState;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 修改时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
    /**
     * 头像@头像url       db_column: head_url 
     */ 	
	private java.lang.String headUrl;
    /**
     * 用户所分享的数量       db_column: t_exp_share_num 
     */ 	
	private java.lang.Integer texpShareNum;
    /**
     * 轻松赚预留字段(暂不实现 )       db_column: t_task_easy_num 
     */ 	
	private java.lang.Integer ttaskEasyNum;
    /**
     * 帮我赚预留字段(暂不实现)       db_column: t_task_help_num 
     */ 	
	private java.lang.Integer ttaskHelpNum;
	
	//columns end


	public FalseNouser(){
	}

	public FalseNouser(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public java.lang.String getUserPhone() {
		return this.userPhone;
	}
	
	public void setUserPhone(java.lang.String value) {
		this.userPhone = value;
	}
	
	public java.lang.Integer getUserState() {
		return this.userState;
	}
	
	public void setUserState(java.lang.Integer value) {
		this.userState = value;
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
	
	public java.lang.String getHeadUrl() {
		return this.headUrl;
	}
	
	public void setHeadUrl(java.lang.String value) {
		this.headUrl = value;
	}
	
	public java.lang.Integer getTexpShareNum() {
		return this.texpShareNum;
	}
	
	public void setTexpShareNum(java.lang.Integer value) {
		this.texpShareNum = value;
	}
	
	public java.lang.Integer getTtaskEasyNum() {
		return this.ttaskEasyNum;
	}
	
	public void setTtaskEasyNum(java.lang.Integer value) {
		this.ttaskEasyNum = value;
	}
	
	public java.lang.Integer getTtaskHelpNum() {
		return this.ttaskHelpNum;
	}
	
	public void setTtaskHelpNum(java.lang.Integer value) {
		this.ttaskHelpNum = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("UserPhone",getUserPhone())
			.append("UserState",getUserState())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("HeadUrl",getHeadUrl())
			.append("TexpShareNum",getTexpShareNum())
			.append("TtaskEasyNum",getTtaskEasyNum())
			.append("TtaskHelpNum",getTtaskHelpNum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FalseNouser == false) return false;
		if(this == obj) return true;
		FalseNouser other = (FalseNouser)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

