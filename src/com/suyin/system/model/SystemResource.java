package com.suyin.system.model;

import java.io.Serializable;

public class SystemResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 父节点id
	 */
	private Integer parentId;
	private String icon;
	/**
	 * 节点名称
	 */
	private String nodeName;
	/**
	 * 节点类型
	 */
	private String nodeType;
	/**
	 * 模块链接
	 */
	private String moduleUrl;
	/**
	 * 资源内容
	 */
	private String resourceContent;
	/**
	 * 说明
	 */
	private String resourceExplain;
	/**
	 * 英文名称
	 */
	private String code;
	private String tname;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getResourceContent() {
		return resourceContent;
	}
	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}
	public String getResourceExplain() {
		return resourceExplain;
	}
	public void setResourceExplain(String resourceExplain) {
		this.resourceExplain = resourceExplain;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
