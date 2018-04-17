package com.suyin.weChat.mapper;

import java.util.List;

import com.suyin.weChat.model.db.WeChatMenu;

public interface WeChatMenuMapper {
	  public List<WeChatMenu> findWeChatMenu(WeChatMenu weChatMenu);
	  
	  public Integer addWeChatMenu(WeChatMenu weChatMenu);
	  
	  public Integer deleteWeChatMenu(WeChatMenu weChatMenu);
	  
	  public Integer updateWeChatMenu(WeChatMenu weChatMenu);
	  
	  public WeChatMenu getWeChatMenu(WeChatMenu weChatMenu);
}
