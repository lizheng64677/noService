package com.suyin.system.model;

import java.io.Serializable;

/**
 * 人员对象
* @Title: SystemUser.java 
* @Package com.suyin.commonConfig.model 
* @Description:
* @author yyy   
* @date 2015年7月14日 上午10:14:04 
* @version V1.0
 */
public class SystemUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String loginName; //登录帐号
	private String loginPwd; //登录密码
	private String state; //状态 0=可用,1=禁用
	private String nickName;//用户名称
	private String iconUrl;//用户图标
	private String createTime;//创建时间
	private String remark;//备注
	
	private Page page;//分页插件
	
	private String role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
