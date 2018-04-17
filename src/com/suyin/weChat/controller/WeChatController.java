package com.suyin.weChat.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suyin.weChat.service.WeChatService;

import com.suyin.weChat.util.SignUtil;
import com.suyin.weChat.util.InputStreamUtils;

@Controller
@RequestMapping("/weChat")
public class WeChatController {

    @Autowired
    WeChatService weChatService;

    @RequestMapping(value="/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(request.getMethod().toUpperCase().equals("POST")){

            this.handleMethod(request, response);
        }else if(request.getMethod().toUpperCase().equals("GET")){ 
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");

            PrintWriter out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
            out = null;

        }
    }

    /**
     * 处理微信服务器发来的消息
     * @throws Exception 
     */
    @RequestMapping("/handleMethod")
    public void handleMethod(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String resultxml = InputStreamUtils.InputStreamTOString(request.getInputStream());

        // 调用核心业务类接收消息、处理消息
        Object respMessage=weChatService.agentWebChatService(resultxml);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
