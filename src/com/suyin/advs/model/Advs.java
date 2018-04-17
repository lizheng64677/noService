package com.suyin.advs.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Advs  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Advs";
	public static final String ALIAS_ADV_ID = "主键id";
	public static final String ALIAS_ADV_NAME = "名称@广告位名称";
	public static final String ALIAS_PIC_URL = "图片@图片url";
	public static final String ALIAS_LINK_URL = "链接地址@is_grap=1，链接url";
	public static final String ALIAS_PIC_INDEX = "顺序";
	public static final String ALIAS_DESCRIPTION = "广告描述";
	public static final String ALIAS_TYPE = "类型@0：首页广告；1：发现广告";
	public static final String ALIAS_MODEL = "model";
	public static final String ALIAS_ENTITY = "entity";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private Page page;//分页插件


	//columns start
		
    /**
     * 主键id       db_column: adv_id 
     */ 	
	private int advId;
    /**
     * 名称@广告位名称       db_column: adv_name 
     */ 	
	private java.lang.String advName;
    /**
     * 图片@图片url       db_column: pic_url 
     */ 	
	private java.lang.String picUrl;
    /**
     * 链接地址@is_grap=1，链接url       db_column: link_url 
     */ 	
	private java.lang.String linkUrl;
    /**
     * 顺序       db_column: pic_index 
     */ 	
	private int picIndex;
    /**
     * 广告描述       db_column: description 
     */ 	
	private java.lang.String description;
    /**
     * 类型@0：首页广告；1：发现广告       db_column: type 
     */ 	
	private java.lang.String type;
    /**
     * model       db_column: model 
     */ 	
	private java.lang.String module;
    /**
     * entity       db_column: entity 
     */ 	
	private int entity;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.util.Date createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
	private java.util.Date updateTime;
	
	//新增活动id
	private Integer expId;
	//客户端类型
	private String clientType;
	
	
	private List<Attachment> attachments;
	//columns end


	public Advs(){
	}

	public Advs(
		int advId
	){
		this.advId = advId;
	}


	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setAdvId(int value) {
		this.advId = value;
	}
	
	
	public int getAdvId() {
		return this.advId;
	}
	
	public java.lang.String getAdvName() {
		return this.advName;
	}
	
	public void setAdvName(java.lang.String value) {
		this.advName = value;
	}
	
	public java.lang.String getPicUrl() {
		return this.picUrl;
	}
	
	public void setPicUrl(java.lang.String value) {
		this.picUrl = value;
	}
	
	public java.lang.String getLinkUrl() {
		return this.linkUrl;
	}
	
	public void setLinkUrl(java.lang.String value) {
		this.linkUrl = value;
	}
	
	public int getPicIndex() {
		return this.picIndex;
	}
	
	public void setPicIndex(int value) {
		this.picIndex = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	
	public java.lang.String getModule() {
		return module;
	}

	public void setModule(java.lang.String module) {
		this.module = module;
	}

	public int getEntity() {
		return this.entity;
	}
	
	public void setEntity(int value) {
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
		
	
	
	public Integer getExpId() {
		return expId;
	}

	public void setExpId(Integer expId) {
		this.expId = expId;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("AdvId",getAdvId())
			.append("AdvName",getAdvName())
			.append("PicUrl",getPicUrl())
			.append("LinkUrl",getLinkUrl())
			.append("PicIndex",getPicIndex())
			.append("Description",getDescription())
			.append("Type",getType())
			.append("Module",this.getModule())
			.append("Entity",getEntity())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("ExpId",getExpId())
			.append("ClientType",getClientType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAdvId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Advs == false) return false;
		if(this == obj) return true;
		Advs other = (Advs)obj;
		return new EqualsBuilder()
			.append(getAdvId(),other.getAdvId())
			.isEquals();
	}
}


