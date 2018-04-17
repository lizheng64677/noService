package com.suyin.weChat.model.db;

import java.io.Serializable;
import java.util.Date;

public class WeChatMenu implements Serializable {

	private static final long serialVersionUID = -230305133110546653L;

	private Integer id;// '菜单',
	private Integer parentId;// '父节点编号',
	private String nodeType;// '节点类型',
	private String nodeKey;// '模块链接',
	private String moduleUrl;// '事件类型',
	private String applicationId;// '所属应用id',
	private String description;// '说明',
	private Date createtime;// '创建时间',
	private Date updatetime;// '更新时间',
	private Integer level;// '节点深度',
	private Integer createUser;
	
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	private String text;
	private String name;
	
	
	
	

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

	private String removeIds;
	
	
	public String getRemoveIds() {
		return removeIds;
	}

	public void setRemoveIds(String removeIds) {
		this.removeIds = removeIds;
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


	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
