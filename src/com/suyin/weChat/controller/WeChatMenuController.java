package com.suyin.weChat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.suyin.common.SystemPropertiesHolder;
import com.suyin.system.model.LoginUser;
import com.suyin.weChat.model.db.WeChatMenu;
import com.suyin.weChat.service.WeChatMenuService;
import com.suyin.weChat.util.HttpClientUtils;


@Controller
@RequestMapping(value = "/weChatMenu")
public class WeChatMenuController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    WeChatMenuService weChatMenuService;


    @RequestMapping(value = "")
    public ModelAndView init(Model model, HttpServletResponse response,
                             HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("weChat/menu/index");

        return mv;
    }


    @RequestMapping(value = "/synTreeList")
    public @ResponseBody List<WeChatMenu> synList(
        HttpServletRequest request) {
        WeChatMenu weChatMenu=new WeChatMenu();
        return weChatMenuService.findWeChatMenu(weChatMenu);
    }

    @RequestMapping(value = "/doAdd")
    public  ModelAndView doAdd(HttpServletRequest request,WeChatMenu weChatMenu) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", request.getParameter("id"));
        mv.setViewName("weChat/menu/add");
        return mv;
    }

    @RequestMapping(value = "/addWeChatMenu")
    public @ResponseBody Map<String,Object> addWebChatMenu(HttpServletRequest request,WeChatMenu weChatMenu)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            LoginUser loginUser=(LoginUser)request.getSession().getAttribute("loginUser");
            weChatMenu.setApplicationId("MSMF");
            weChatMenu.setCreateUser(loginUser.getUserRoleId());
            Integer i=weChatMenuService.addWeChatMenu(weChatMenu);
            map.put("message",i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatMenuController-> addWeChatMenu  " + e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/deteteWeChatMenu")
    public @ResponseBody Map<String,Object> deteteWebChatMenu(HttpServletRequest request,WeChatMenu weChatMenu)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            Integer i=weChatMenuService.deleteWeChatMenu(weChatMenu);
            map.put("message",i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatMenuController-> deteteWeChatMenu  " + e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "/doUpdateWeChatMenu")
    public  ModelAndView doUpdateWeChatMenu(HttpServletRequest request,WeChatMenu weChatMenu) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("weChatMenu", weChatMenuService.getWeChatMenu(weChatMenu));
            mv.setViewName("weChat/menu/update");
        } catch (Exception e) {
            log.error("Controller Error WeChatMenuController-> doUpdateWeChatMenu  " + e.getMessage());
        }
        return mv;
    }

    @RequestMapping(value = "/updateWeChatMenu")
    public @ResponseBody Map<String,Object> updateWeChatMenu(HttpServletRequest request,WeChatMenu weChatMenu)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            Integer i=weChatMenuService.updateWeChatMenu(weChatMenu);
            map.put("message",i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatMenuController-> updateWeChatMenu  " + e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/assembleMenu")
    public @ResponseBody String assembleMenu(HttpServletRequest request) {
        JSONObject message = null;
        try {

            // 获取新的token
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + SystemPropertiesHolder.get("appid")
                + "&secret="
                + SystemPropertiesHolder.get("secret");
            JSONObject jsonObject=HttpClientUtils.get(url);
            System.out.println(jsonObject);
            String token=jsonObject.get("access_token").toString();
            String parm=weChatMenuService.assembleMenu("MSMF").toString();	
            String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ token;
            message=HttpClientUtils.postWx(menuUrl, parm);
            System.out.println(message);
        } catch (Exception e) {
            log.error("Controller Error WebChatMenuController-> assembleMenu  " + e.getMessage());
        }
        return message.toString();
    }
}
