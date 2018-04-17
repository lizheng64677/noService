package com.suyin.weChat.model.menu;

import java.io.Serializable;

/**
 * 微信自定义菜单基类
 * 
 * @author madara
 *
 */
public class Button implements Serializable {

	private static final long serialVersionUID = -362960079056394723L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
