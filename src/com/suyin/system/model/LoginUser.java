package com.suyin.system.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class LoginUser implements Serializable{

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	private static final long serialVersionUID = -986484290441092481L;

	private String loginName;
	
	private Integer userId;

	private Integer userRoleId;
	
	private String applicationId;
	
	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	private List<Map<String,Object>> map;
	
	
	public List<Map<String, Object>> getMap() {
		return map;
	}

	public void setMap(List<Map<String, Object>> map) {
		this.map = map;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
