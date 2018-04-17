package com.suyin.weChat.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.suyin.weChat.model.db.WeChatMenu;

public interface WeChatMenuService {
	public List<WeChatMenu> findWeChatMenu(WeChatMenu weChatMenu);

	public Integer addWeChatMenu(WeChatMenu weChatMenu);

	public Integer deleteWeChatMenu(WeChatMenu weChatMenu);

	public Integer updateWeChatMenu(WeChatMenu weChatMenu);
	
	public WeChatMenu getWeChatMenu(WeChatMenu weChatMenu);
	
	public JSONObject assembleMenu(String applicationId);
}
