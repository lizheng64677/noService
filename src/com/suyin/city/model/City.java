package com.suyin.city.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class City  implements java.io.Serializable{
    
	private static final long serialVersionUID = 5454155825314635342L;
	public static final String TABLE_ALIAS = "City";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "城市名称";
	public static final String ALIAS_LEVEL = "级别";
	public static final String ALIAS_UPID = "从属于";
	public static final String ALIAS_LIST = "排序";
	
	private Page page;//分页插件
	
	private String removeIds;
	
	//是否展开
	private String state = "cloesed";
	//树型菜单图标
	private String iconCls="icon-nav";
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemoveIds() {
		return removeIds;
	}
	public void setRemoveIds(String removeIds) {
		this.removeIds = removeIds;
	}

	private Integer parentId;
	
	private Integer hotCity;//0-代表是热门城市  1-代表没有设置成热门城市
	
	public Integer getHotCity() {
		return hotCity;
	}

	public void setHotCity(Integer hotCity) {
		this.hotCity = hotCity;
	}

	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * 城市名称       db_column: name 
     */ 	
	private java.lang.String name;
    /**
     * 级别       db_column: level 
     */ 	
	private Integer level;
    /**
     * 从属于       db_column: upid 
     */ 	
	private java.lang.Integer upid;
    /**
     * 排序       db_column: list 
     */ 	
	private Integer list;
	
	//columns end
	private String ctname;

	public City(){
	}

	public City(
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
	
	public Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	public java.lang.Integer getUpid() {
		return this.upid;
	}
	
	public void setUpid(java.lang.Integer value) {
		this.upid = value;
	}
	
	public Integer getList() {
		return this.list;
	}
	
	public void setList(Integer value) {
		this.list = value;
	}
	
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    
	public String getCtname()
    {
        return ctname;
    }
    public void setCtname(String ctname)
    {
        this.ctname = ctname;
    }
    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Level",getLevel())
			.append("Upid",getUpid())
			.append("List",getList())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof City == false) return false;
		if(this == obj) return true;
		City other = (City)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

