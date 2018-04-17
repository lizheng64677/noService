package com.suyin.expzhuan.model;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpTaskOrder  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "ExpTaskOrder";
	public static final String ALIAS_ORDER_ID = "orderId";
	public static final String ALIAS_USER_ID = "用户表t_nouser表的主键";
	public static final String ALIAS_EXP_ID = "t_exp_zhuan表的主键，表示参加了那个活动";
	public static final String ALIAS_REGTYPE = "0：微信；1：ios；2：android 参加的";
	public static final String ALIAS_MODULE_TYPE = "0表示齐心赚，1表示全民赚";
	public static final String ALIAS_SHOW_TYPE = "全民赚的话 0 表示app下载；1 表示问卷调查";
	public static final String ALIAS_STATUS = "全民赚有4个状态(0:刚参加；1：提交了问题或图片；2：通过；3：拒绝),齐心赚没有什么状态";
	public static final String ALIAS_TOTAL_GOLD = "用户这一次参加活动，获得的总的金币数量";
	public static final String ALIAS_BROWSE_COUNT = "只有齐心赚才用的字段，表示有多少人点击了转发";
	public static final String ALIAS_IMAGE_URL = "如果是全民赚的话，要上传一个图片";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * orderId       db_column: order_id 
     */ 	
	private java.lang.Integer orderId;
	
	/*全民赚订单中上传的电话号码*/
	private String userPhone1;
	
    public final String getUserPhone1() {
		return userPhone1;
	}

	public final void setUserPhone1(String userPhone1) {
		this.userPhone1 = userPhone1;
	}

	/**
     * 用户表t_nouser表的主键       db_column: user_id 
     */ 	
	
	private java.lang.Integer userId;
    /**
     * t_exp_zhuan表的主键，表示参加了那个活动       db_column: exp_id 
     */ 	
	private java.lang.Integer expId;
    /**
     * 0：微信；1：ios；2：android 参加的       db_column: regtype 
     */ 	
	private java.lang.Integer regtype;
    /**
     * 0表示齐心赚，1表示全民赚       db_column: module_type 
     */ 	
	private java.lang.Integer moduleType;
    /**
     * 全民赚的话 0 表示app下载；1 表示问卷调查       db_column: show_type 
     */ 	
	private java.lang.Integer showType;
    /**
     * 全民赚有4个状态(0:刚参加；1：提交了问题或图片；2：通过；3：拒绝),齐心赚没有什么状态       db_column: status 
     */ 	
	private java.lang.Integer status;
    /**
     * 用户这一次参加活动，获得的总的金币数量       db_column: total_gold 
     */ 	
	private java.lang.Integer totalGold;
    /**
     * 只有齐心赚才用的字段，表示有多少人点击了转发       db_column: browse_count 
     */ 	
	private java.lang.Integer browseCount;
    /**
     * 如果是全民赚的话，要上传一个图片       db_column: image_url 
     */ 	
	private java.lang.String imageUrl;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * updateTime       db_column: update_time 
     */ 	
	private java.util.Date updateTime;
	
	
	private String userPhone;
	private ExpTask task;
	
	//columns end
	

	public final String getUserPhone() {
		return userPhone;
	}

	public final void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public final ExpTask getTask() {
		return task;
	}

	public final void setTask(ExpTask task) {
		this.task = task;
	}

	public ExpTaskOrder(){
	}

	public ExpTaskOrder(
		java.lang.Integer orderId
	){
		this.orderId = orderId;
	}

	

	public void setOrderId(java.lang.Integer value) {
		this.orderId = value;
	}
	
	
	public java.lang.Integer getOrderId() {
		return this.orderId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getExpId() {
		return this.expId;
	}
	
	public void setExpId(java.lang.Integer value) {
		this.expId = value;
	}
	
	public java.lang.Integer getRegtype() {
		return this.regtype;
	}
	
	public void setRegtype(java.lang.Integer value) {
		this.regtype = value;
	}
	
	public java.lang.Integer getModuleType() {
		return this.moduleType;
	}
	
	public void setModuleType(java.lang.Integer value) {
		this.moduleType = value;
	}
	
	public java.lang.Integer getShowType() {
		return this.showType;
	}
	
	public void setShowType(java.lang.Integer value) {
		this.showType = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getTotalGold() {
		return this.totalGold;
	}
	
	public void setTotalGold(java.lang.Integer value) {
		this.totalGold = value;
	}
	
	public java.lang.Integer getBrowseCount() {
		return this.browseCount;
	}
	
	public void setBrowseCount(java.lang.Integer value) {
		this.browseCount = value;
	}
	
	public java.lang.String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(java.lang.String value) {
		this.imageUrl = value;
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
			.append("OrderId",getOrderId())
			.append("UserId",getUserId())
			.append("ExpId",getExpId())
			.append("Regtype",getRegtype())
			.append("ModuleType",getModuleType())
			.append("ShowType",getShowType())
			.append("Status",getStatus())
			.append("TotalGold",getTotalGold())
			.append("BrowseCount",getBrowseCount())
			.append("ImageUrl",getImageUrl())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrderId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpTaskOrder == false) return false;
		if(this == obj) return true;
		ExpTaskOrder other = (ExpTaskOrder)obj;
		return new EqualsBuilder()
			.append(getOrderId(),other.getOrderId())
			.isEquals();
	}
}


/**/

