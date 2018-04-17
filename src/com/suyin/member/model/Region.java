package com.suyin.member.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class Region  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Region";
	public static final String ALIAS_REGION_ID = "商圈id";
	public static final String ALIAS_REGION_NAME = "商圈名称";
	public static final String ALIAS_CITY_ID = "城市id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";

	private Page page;//分页插件


	//columns start
		
    /**
     * 商圈id       db_column: region_id 
     */ 	
	private java.lang.Integer regionId;
    /**
     * 商圈名称       db_column: region_name 
     */ 	
	private java.lang.String regionName;
    /**
     * 城市id       db_column: city_id 
     */ 	
	private java.lang.Integer cityId;
    /**
     * 省id       db_column: city_id 
     */     
    private java.lang.Integer provinId;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
	
	//columns end


	public Region(){
	}

	public Region(
		java.lang.Integer regionId
	){
		this.regionId = regionId;
	}

	

	public void setRegionId(java.lang.Integer value) {
		this.regionId = value;
	}
	
	
	public java.lang.Integer getRegionId() {
		return this.regionId;
	}
	
	public java.lang.String getRegionName() {
		return this.regionName;
	}
	
	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}
	
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
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
 
	public java.lang.Integer getProvinId()
    {
        return provinId;
    }

    public void setProvinId(java.lang.Integer provinId)
    {
        this.provinId = provinId;
    }

    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RegionId",getRegionId())
			.append("RegionName",getRegionName())
			.append("CityId",getCityId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRegionId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Region == false) return false;
		if(this == obj) return true;
		Region other = (Region)obj;
		return new EqualsBuilder()
			.append(getRegionId(),other.getRegionId())
			.isEquals();
	}
}

