/*
 * 文件名：Tools.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：WS
 * 修改时间：2015年3月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;

import com.suyin.experience.model.Experience;
import com.suyin.system.model.Attachment;

public class Tools
{
    /**
     * 检测字符串是否不为空(null,"","null")
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s)
    {
        return s != null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s)
    {
        return s == null || "".equals(s) || "null".equals(s);
    }

    /**
     * 字符串转换为字符串数组
     * @param str 字符串
     * @param splitRegex 分隔符
     * @return
     */
    public static String[] str2StrArray(String str, String splitRegex)
    {
        if (isEmpty(str))
        {
            return null;
        }
        return str.split(splitRegex);
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     * @param str   字符串
     * @return
     */
    public static String[] str2StrArray(String str)
    {
        return str2StrArray(str, ",\\s*");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date)
    {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     * @param date
     * @return
     */
    public static Date str2Date(String date)
    {
        if (notEmpty(date))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try
            {
                return sdf.parse(date);
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new Date();
        }
        else
        {
            return null;
        }
    }

    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format)
    {
        if (date != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        else
        {
            return "";
        }
    }

    /**
     * 字符串为null的返回""
     * @param date
     * @param format
     * @return
     */
    public static String nullToEmpty(String str)
    {
        if(isEmpty(str)){
            return "";
        }
        return str;
    }
    public static String getTime(String dateTime){
        String returnTime=dateTime.substring(0,10);
        String nowDate=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
        long leftTime=(Tools.str2Date(dateTime).getTime())-Tools.str2Date(nowDate).getTime();
        if(Math.floor(leftTime/1000/60/60)<=24){
            int h=Integer.parseInt(dateTime.substring(11,13));
            if(h==0){
                returnTime="今天23:59"; 
            }
            else{
                returnTime="今天"+dateTime.substring(11,16);
            }
        }
        return returnTime;
    }

    /*时间比大小  -1为过期*/
    public static int timeCompare(String t1){
        int result = 0 ;
        if(null != t1){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1=Calendar.getInstance();
            Calendar c2=Calendar.getInstance();
            try {
                c1.setTime(formatter.parse(t1));
                c2.setTime(formatter.parse(getCurrentTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result=c1.compareTo(c2);
        }
        return result;
    }

    public static String getCurrentTime(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 处理从前台传回的所有附件的信息，生成attachment的list；
     * <dl>
     * 	<dt>前台附件格式即参数allImages的格式：</dt>
     * <dd> url;savepath;fileName;fileSize@url;savepath;fileName;fileSize@.......</dd>
     * <dd> 每两个附件中间用@符号分隔;每个附件的各种信息用分号分隔。这里只提供 url（该附件的访问路径），savepath（存储地址），fileName（文件名），fileSize（文件大小，以B为单位）</dd>
     * </dl>
     * @param allImages
     * @param moduleName 模块名称
     * @return
     */
    public static List<Attachment> getAttachements(String allImages,String moduleName){
        List<Attachment> attach=new ArrayList<Attachment>();
        Attachment a=null;
        if(StringUtils.isNotBlank(allImages)) {
            String[] as=allImages.split("@");
            for(int i=0;i<as.length;i++) {
                a=new Attachment();
                String[] aas=as[i].split(";");
                a.setDisplay_path(aas[0]);
                a.setModule(moduleName);
                a.setFile_path(aas[1]);
                a.setFile_name(aas[2]);	        	
                //这里把文件大小转换为以KB为单位
                a.setFile_size(Double.valueOf(Long.parseLong(aas[3])/1024).intValue());
                //用到实体属性
                if(aas.length>4){
                    a.setEntity_attribute(aas[4]);
                }
                attach.add(a);
            }
        }
        return attach;
    } 

    public static List<Attachment> getAttachements_arry(String[] str,String moduleName){
        List<Attachment> attach=new ArrayList<Attachment>();
        Attachment a=null;
        String allImages="";
        for (int j = 0; j < str.length; j++ )
        {
            allImages=str[j];
            if(StringUtils.isNotBlank(allImages)) {
                String[] as=allImages.split("@");
                for(int i=0;i<as.length;i++) {
                    a=new Attachment();
                    String[] aas=as[i].split(";");
                    a.setDisplay_path(aas[0]);
                    a.setModule(moduleName);
                    a.setFile_path(aas[1]);
                    a.setFile_name(aas[2]);             
                    //这里把文件大小转换为以KB为单位
                    a.setFile_size(Double.valueOf(Long.parseLong(aas[3])/1024).intValue());
                    //用到实体属性
                    if(aas.length>4){
                        a.setEntity_attribute(aas[4]);
                    }
                    attach.add(a);
                }
            }

        }
        return attach;
    } 

    /**
     * 免费活动中使用到
     * 单位天数 余整转换
     * 区分期别  如：1期 2期
     * @param entity
     * @return 
     * @see
     */
    public static List<Map<String,Object>> getExpTime(Experience entity){
        List<Map<String,Object>>  times = new ArrayList<Map<String,Object>>();
        Date beginDate = DateUtil.stringToDate(entity.getBeginTime(), "yyyy-MM-dd");
        Date endDate = DateUtil.stringToDate(entity.getEndTime(), "yyyy-MM-dd");
        int diffDay = DateUtil.daysBetween(beginDate, endDate)+1;
        int unitDay = entity.getUnitDay();
        for (int i = 0; i < diffDay/unitDay; i++ )
        {
            Map<String,Object>  time = new HashMap<String,Object>();
            String beginTime = DateUtil.getDate(DateUtil.stringToDate(entity.getBeginTime(), "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm", i*unitDay);
            if(i==0){
                time.put("beginTime", beginTime);
            }else{
                time.put("beginTime", DateUtil.getDate(DateUtil.stringToDate(beginTime, "yyyy-MM-dd"), "yyyy-MM-dd"));
            }
            if((i+1)==diffDay/unitDay){
                time.put("endTime",entity.getEndTime());
            }else{
                time.put("endTime", DateUtil.getDate(DateUtil.stringToDate(beginTime, "yyyy-MM-dd"), "yyyy-MM-dd", unitDay));
            }
            time.put("expId", entity.getExpId());
            times.add(time);
        }
        return times;
    }
    /**
     * 
     * 几天后失效 
     * @param addDay
     * @return 
     * @see
     */
    public static String getValidTime(int addDay){

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date); 
        calendar.add(calendar.DATE, addDay);
        date=calendar.getTime();

        String str=Tools.date2Str(date, "yyyy-MM-dd");
        return str;
    }
    
    public static String getDate(Date date, String format, Integer d)
    {
 
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, d);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines){       
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    /**
     * 汉字转换位汉语拼音，英文字符不变
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToSpell(String chines){        
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

}
