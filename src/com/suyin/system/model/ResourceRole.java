package com.suyin.system.model;

import java.io.Serializable;

/**   
 * @Title: ResourceRole.java 
 * @Package com.suyin.system.model 
 * @Description:
 * @author yyy   
 * @date 2015年7月10日 下午1:47:33 
 * @version V1.0   
 */
public class ResourceRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer rescId;
	private Integer roleId;
	public Integer getRescId() {
		return rescId;
	}
	public void setRescId(Integer rescId) {
		this.rescId = rescId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
