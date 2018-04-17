package com.suyin.simple.model;

import java.io.Serializable;

import com.suyin.system.model.Page;

public class EasyUITree implements Serializable {
	private static final long serialVersionUID = -3666171831438565L;
	private Integer id;
	private Integer parentId;
	private String name;
	private String text;
	private String state = "cloesed";
	private String iconCls="icon-nav";
	private Page page;
	
	
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
