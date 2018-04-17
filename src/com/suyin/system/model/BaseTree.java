package com.suyin.system.model;

import java.io.Serializable;

public class BaseTree implements Serializable{

	private static final long serialVersionUID = 6079336016060263580L;
	private Integer id;
	private Integer parentId;
	private String name;
	private String text;
	private String state;
	private String iconCls;
	private Integer sonTrees;//子节点数量
	public Integer getSonTrees() {
		return sonTrees;
	}
	public void setSonTrees(Integer sonTrees) {
		this.sonTrees = sonTrees;
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
