package com.suyin.member.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class Store  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "Store";
	public static final String ALIAS_STORE_ID = "storeId";
	public static final String ALIAS_STORE_NAME = "门店名称";
	public static final String ALIAS_STORE_TEL = "门店电话";
	public static final String ALIAS_STORE_ADDRESS = "门店地址";
	public static final String ALIAS_STORE_ADDRESS_URL = "门店地址链接";
	public static final String ALIAS_STORE_PIC_URL = "门店图片";
	public static final String ALIAS_CITY_ID = "城市id";
	public static final String ALIAS_CG_ID = "门店所属品类id";
	public static final String ALIAS_REGION_ID = "regionId";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_COLLECT_NUM = "门店收藏数";

	private Page page;//分页插件


	//columns start
		
    /**
     * storeId       db_column: store_id 
     */ 	
	private java.lang.Integer storeId;
    /**
     * 门店名称       db_column: store_name 
     */ 	
	private java.lang.String storeName;
    /**
     * 门店电话       db_column: store_tel 
     */ 	
	private java.lang.String storeTel;
    /**
     * 门店地址       db_column: store_address 
     */ 	
	private java.lang.String storeAddress;
    /**
     * 门店地址链接       db_column: store_address_url 
     */ 	
	private java.lang.String storeAddressUrl;
    /**
     * 门店图片       db_column: store_pic_url 
     */ 	
	private java.lang.String storePicUrl;
    /**
     * 城市id       db_column: city_id 
     */ 	
	private java.lang.Integer cityId;
    /**
     * 城市id       db_column: city_id 
     */     
    private java.lang.Integer provinId;
    /**
     * 门店所属品类id       db_column: cg_id 
     */ 	
	private java.lang.Integer cgId;
    /**
     * regionId       db_column: region_id 
     */ 	
	private java.lang.Integer regionId;
    /**
     * createTime       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * updateTime       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
    /**
     * 门店收藏数       db_column: collect_num 
     */ 	
	private java.lang.Integer collectNum;
	
	//columns end


	public Store(){
	}

	public Store(
		java.lang.Integer storeId
	){
		this.storeId = storeId;
	}

	

	public void setStoreId(java.lang.Integer value) {
		this.storeId = value;
	}
	
	
	public java.lang.Integer getStoreId() {
		return this.storeId;
	}
	
	public java.lang.String getStoreName() {
		return this.storeName;
	}
	
	public void setStoreName(java.lang.String value) {
		this.storeName = value;
	}
	
	public java.lang.String getStoreTel() {
		return this.storeTel;
	}
	
	public void setStoreTel(java.lang.String value) {
		this.storeTel = value;
	}
	
	public java.lang.String getStoreAddress() {
		return this.storeAddress;
	}
	
	public void setStoreAddress(java.lang.String value) {
		this.storeAddress = value;
	}
	
	public java.lang.String getStoreAddressUrl() {
		return this.storeAddressUrl;
	}
	
	public void setStoreAddressUrl(java.lang.String value) {
		this.storeAddressUrl = value;
	}
	
	public java.lang.String getStorePicUrl() {
		return this.storePicUrl;
	}
	
	public void setStorePicUrl(java.lang.String value) {
		this.storePicUrl = value;
	}
	
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	public java.lang.Integer getCgId() {
		return this.cgId;
	}
	
	public void setCgId(java.lang.Integer value) {
		this.cgId = value;
	}
	
	public java.lang.Integer getRegionId() {
		return this.regionId;
	}
	
	public void setRegionId(java.lang.Integer value) {
		this.regionId = value;
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
	
	public java.lang.Integer getCollectNum() {
		return this.collectNum;
	}
	
	public void setCollectNum(java.lang.Integer value) {
		this.collectNum = value;
	}
	
    public java.lang.Integer getProvinId()
    {
        return provinId;
    }

    public void setProvinId(java.lang.Integer provinId)
    {
        this.provinId = provinId;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("StoreId",getStoreId())
			.append("StoreName",getStoreName())
			.append("StoreTel",getStoreTel())
			.append("StoreAddress",getStoreAddress())
			.append("StoreAddressUrl",getStoreAddressUrl())
			.append("StorePicUrl",getStorePicUrl())
			.append("CityId",getCityId())
			.append("CgId",getCgId())
			.append("RegionId",getRegionId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("CollectNum",getCollectNum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getStoreId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Store == false) return false;
		if(this == obj) return true;
		Store other = (Store)obj;
		return new EqualsBuilder()
			.append(getStoreId(),other.getStoreId())
			.isEquals();
	}
}

