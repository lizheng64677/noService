package com.suyin.system.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信通道类
 * @version 2015-8-25
 * @see SendMessage
 * @since
 */
public class SendMessage
{   

   /**
    * 短信调用通道
    * @param phone
    * @param message 
    * @see
    */
    public static void sendMessage(String phone,String message){
        //发送短信
        Map<String, String> map = new HashMap<String, String>();

        //美联软通
        String sendMessageUrl ="http://m.5c.com.cn/api/send/index.php";
        map.put("username", "dongfang1");//此处填写用户账号
        map.put("password", "asdf123");//此处填写用户密码
        map.put("mobile",phone);//此处填写发送号码
        //map.put("tempid", "JSM40140-0003");//模板使用
        map.put("content", message+"为您注册账户的验证码，为保证您的账户安全，请勿外泄。如有疑问请致电400-8540505【NO团网】");//此处填写模板短信内容
        map.put("apikey", "54c06118b0de262273492bf05e1997b5");
        //map.put("code","gbk");
        //map.put("msgtype","2");
        HttpPost.doPost(sendMessageUrl, map,"GBK");
    }
    
    /**
     * 注册所需的短信发送通道 
     * @param phone
     * @param message 
     * @see
     */
    public static void nuserRegMessage(String phone,String message){
        
        //发送短信
        Map<String, String> map = new HashMap<String, String>();
        //美联软通
        String sendMessageUrl ="http://m.5c.com.cn/api/send/index.php";
        map.put("username", "dongfang1");//此处填写用户账号
        map.put("password", "asdf123");//此处填写用户密码
        map.put("mobile",phone);//此处填写发送号码
        map.put("content", message+"为您注册账户的验证码，为保证您的账户安全，请勿外泄。如有疑问请致电400-8540505【NO团网】");
        map.put("apikey", "54c06118b0de262273492bf05e1997b5");
        HttpPost.doPost(sendMessageUrl, map,"GBK");
    }
    
    /**
     * 系统生成的密码发送通道
     * @see
     */
    public static void backCodeMessage(String phone,String message){
        
        //发送短信
        Map<String, String> map = new HashMap<String, String>();
        //美联软通
        String sendMessageUrl ="http://m.5c.com.cn/api/send/index.php";
        map.put("username", "dongfang1");//此处填写用户账号
        map.put("password", "asdf123");//此处填写用户密码
        map.put("mobile",phone);//此处填写发送号码
        map.put("content", message+"为系统为您随机生成的密码，为保证您的账户安全，请勿外泄。如有疑问请致电400-8540505【NO团网】");
        map.put("apikey", "54c06118b0de262273492bf05e1997b5");
        HttpPost.doPost(sendMessageUrl, map,"GBK");
    }
    
    /**
     * 找回密码生成的验证码通道 
     * @see
     */
    public static void backPwdMessage(String phone,String message){
        
        //发送短信
        Map<String, String> map = new HashMap<String, String>();
        //美联软通
        String sendMessageUrl ="http://m.5c.com.cn/api/send/index.php";
        map.put("username", "dongfang1");//此处填写用户账号
        map.put("password", "asdf123");//此处填写用户密码
        map.put("mobile",phone);//此处填写发送号码
        map.put("content", message+"为您找回密码的验证码，为保证您的账户安全，请勿外泄。如有疑问请致电400-8540505【NO团网】");
        map.put("apikey", "54c06118b0de262273492bf05e1997b5");
        HttpPost.doPost(sendMessageUrl, map,"GBK");
    }
    
    
    /**
     * 注册所需的短信发送通道 
     * @param phone
     * @param message 
     * @see
     */
    public static void orderVoucherMessage(String phone,String vouCode,String message){
        
        //发送短信
        Map<String, String> map = new HashMap<String, String>();
        //美联软通
        String sendMessageUrl ="http://m.5c.com.cn/api/send/index.php";
        map.put("username", "dongfang1");//此处填写用户账号
        map.put("password", "asdf123");//此处填写用户密码
        map.put("mobile",phone);//此处填写发送号码
        map.put("content", "恭喜你获得"+message+"，请在“我的-我的券”中查询消费券号！如有疑问请致电400-8540505【NO团网】");
        map.put("apikey", "54c06118b0de262273492bf05e1997b5");
        HttpPost.doPost(sendMessageUrl, map,"GBK");
    }
    public static void main(String[] args)
    {

//        sendMessage("18705164891", "李政");
        
        System.out.println(VoucherUtil.getRandomString(6));    
    }
}
