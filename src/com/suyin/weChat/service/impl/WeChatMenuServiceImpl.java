package com.suyin.weChat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.suyin.weChat.mapper.WeChatMenuMapper;
import com.suyin.weChat.model.db.WeChatMenu;
import com.suyin.weChat.service.WeChatMenuService;


@Transactional
@Service("weChatMenuService")
public class WeChatMenuServiceImpl implements WeChatMenuService {
    private   Logger log = Logger.getLogger(this.getClass());

    @Autowired
    WeChatMenuMapper weChatMenuMapper;

    @Override
    public List<WeChatMenu> findWeChatMenu(WeChatMenu weChatMenu) {
        return weChatMenuMapper.findWeChatMenu(weChatMenu);
    }
    @Override
    public Integer addWeChatMenu(WeChatMenu weChatMenu) {
        weChatMenu.setCreatetime(new Date());
        return weChatMenuMapper.addWeChatMenu(weChatMenu);
    }

    @Override
    public Integer deleteWeChatMenu(WeChatMenu weChatMenu) {
        return weChatMenuMapper.deleteWeChatMenu(weChatMenu);
    }

    @Override
    public Integer updateWeChatMenu(WeChatMenu weChatMenu) {
        weChatMenu.setUpdatetime(new Date());
        return weChatMenuMapper.updateWeChatMenu(weChatMenu);
    }

    @Override
    public WeChatMenu getWeChatMenu(WeChatMenu weChatMenu) {
        return weChatMenuMapper.getWeChatMenu(weChatMenu);
    }

    @Override
    public JSONObject assembleMenu(String applicationId) {
        /**
         * 获取一级菜单
         */
        WeChatMenu weChatMenu=new WeChatMenu();
        weChatMenu.setLevel(1);

        /**
         * 微信接口参数
         */
        Map<String,Object> button=new LinkedHashMap<String,Object>();
        List<Map<String,Object>> mapButton = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> towButton = null;
        Map<String,Object> firstButton=null;
        Map<String,Object> tButton;
        List<WeChatMenu> firstMenu=weChatMenuMapper.findWeChatMenu(weChatMenu);
        try {
            for(WeChatMenu menu:firstMenu){
                firstButton=new LinkedHashMap<String,Object>();
                firstButton.put("name", menu.getName());
                WeChatMenu param=new WeChatMenu();
                param.setParentId(menu.getId());
                menu.setLevel(null);
                List<WeChatMenu> towMenu=weChatMenuMapper.findWeChatMenu(param);
                if(null!=towMenu && !towMenu.isEmpty()){
                    towButton=new ArrayList<Map<String,Object>>();
                    for(WeChatMenu t:towMenu){
                        tButton=new LinkedHashMap<String,Object>();
                        tButton.put("name", t.getName());
                        tButton.put("type", t.getNodeType());
                        if("view".equals(t.getNodeType())){//地址链接

                            tButton.put("url", t.getModuleUrl());
                        }else if("click".equals(t.getNodeType())){  //点击事件

                            tButton.put("key",t.getModuleUrl());
                        }else if("scancode_waitmsg".equals(t.getNodeType())){//扫码带提示

                            tButton.put("key","rselfmenu_0_0");
                        }else if("scancode_push".equals(t.getNodeType())){//扫码推事件

                            tButton.put("key","rselfmenu_0_1");
                        }else if("pic_sysphoto".equals(t.getNodeType())){//系统拍照发图

                            tButton.put("key","rselfmenu_1_0");
                        }else if("pic_photo_or_album".equals(t.getNodeType())){//拍照或者相册发图

                            tButton.put("key","rselfmenu_1_1");
                        }else if("pic_weixin".equals(t.getNodeType())){//微信相册发图

                            tButton.put("key","rselfmenu_1_2");
                        }else if("location_select".equals(t.getNodeType())){//发送位置

                            tButton.put("key","rselfmenu_2_0");
                        }
                        towButton.add(tButton);
                    }
                    firstButton.put("sub_button", towButton);
                }else{

                    firstButton.put("type",menu.getNodeType());

                    if("view".equals(menu.getNodeType())){          //地址链接

                        firstButton.put("url", menu.getModuleUrl());
                    }else if("click".equals(menu.getNodeType())){   //点击事件 

                        firstButton.put("key",menu.getModuleUrl());
                    }else if("scancode_waitmsg".equals(menu.getNodeType())){//扫码带提示

                        firstButton.put("key","rselfmenu_0_0");

                    }else if("scancode_push".equals(menu.getNodeType())){   //扫码推事件

                        firstButton.put("key","rselfmenu_0_1");
                    }else if("pic_sysphoto".equals(menu.getNodeType())){    //系统拍照发图

                        firstButton.put("key","rselfmenu_1_0");
                    }else if("pic_photo_or_album".equals(menu.getNodeType())){//拍照或者相册发图

                        firstButton.put("key","rselfmenu_1_1");
                    }else if("pic_weixin".equals(menu.getNodeType())){//微信相册发图

                        firstButton.put("key","rselfmenu_1_2");
                    }else if("location_select".equals(menu.getNodeType())){//发送位置

                        firstButton.put("key","rselfmenu_2_0");
                    }

                }
                mapButton.add(firstButton);
            }
        } catch (Exception e) {
            log.error("Service Error WeChatMenuServiceImpl -> assembleMenu"
                + e.getMessage());
        }
        button.put("button", mapButton);
        return JSONObject.fromObject(button);
    }  

}
