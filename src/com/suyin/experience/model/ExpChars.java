/*
 * 文件名：ExpChars.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-17
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.model;

import com.suyin.system.model.Page;

/**
 * 免费活动数据实体
 * @author lz
 * @version 2015-12-17
 * @see ExpChars
 * @since
 */

public class ExpChars implements java.io.Serializable
{

    private int expId;
    private int pv;
    private int uv;
    private String clicentType;
    private int expType;
    private String validity;
    private int addDay;
    private int proNum;
    private int expNum;
    private String price;
    private String proName;
    private String title;
    private int probability;
    private String bengTime;
    private String endTime;
    private String rnum;
    private int xqs;
    private Page page;//分页插件
    private String popNum; //总人气

    private String userGold;
    private String tjNum;
    private String shNum;
    private String orderNum;
    private String zs;
    private String qj;

    public int getExpId()
    {
        return expId;
    }
    public void setExpId(int expId)
    {
        this.expId = expId;
    }
    public int getPv()
    {
        return pv;
    }
    public void setPv(int pv)
    {
        this.pv = pv;
    }
    public int getUv()
    {
        return uv;
    }
    public void setUv(int uv)
    {
        this.uv = uv;
    }
    public String getClicentType()
    {
        return clicentType;
    }
    public void setClicentType(String clicentType)
    {
        this.clicentType = clicentType;
    }
    public int getExpType()
    {
        return expType;
    }
    public void setExpType(int expType)
    {
        this.expType = expType;
    }
    public String getValidity()
    {
        return validity;
    }
    public void setValidity(String validity)
    {
        this.validity = validity;
    }
    public int getAddDay()
    {
        return addDay;
    }
    public void setAddDay(int addDay)
    {
        this.addDay = addDay;
    }
    public int getProNum()
    {
        return proNum;
    }
    public void setProNum(int proNum)
    {
        this.proNum = proNum;
    }
    public int getExpNum()
    {
        return expNum;
    }
    public void setExpNum(int expNum)
    {
        this.expNum = expNum;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }
    public String getProName()
    {
        return proName;
    }
    public void setProName(String proName)
    {
        this.proName = proName;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getProbability()
    {
        return probability;
    }
    public void setProbability(int probability)
    {
        this.probability = probability;
    }
    public String getBengTime()
    {
        return bengTime;
    }
    public void setBengTime(String bengTime)
    {
        this.bengTime = bengTime;
    }
    public String getEndTime()
    {
        return endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    public String getRnum()
    {
        return rnum;
    }
    public void setRnum(String rnum)
    {
        this.rnum = rnum;
    }
    public int getXqs()
    {
        return xqs;
    }
    public void setXqs(int xqs)
    {
        this.xqs = xqs;
    }
    public Page getPage()
    {
        return page;
    }
    public void setPage(Page page)
    {
        this.page = page;
    }
    public String getPopNum()
    {
        return popNum;
    }
    public void setPopNum(String popNum)
    {
        this.popNum = popNum;
    }
    
    public String getUserGold()
    {
        return userGold;
    }
    public void setUserGold(String userGold)
    {
        this.userGold = userGold;
    }
    public String getTjNum()
    {
        return tjNum;
    }
    public void setTjNum(String tjNum)
    {
        this.tjNum = tjNum;
    }
    public String getShNum()
    {
        return shNum;
    }
    public void setShNum(String shNum)
    {
        this.shNum = shNum;
    }
    public String getZs()
    {
        return zs;
    }
    public void setZs(String zs)
    {
        this.zs = zs;
    }
    
    public String getOrderNum()
    {
        return orderNum;
    }
    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public String getQj()
    {
        return qj;
    }
    public void setQj(String qj)
    {
        this.qj = qj;
    }
    @Override
    public String toString()
    {
        return "ExpChars [expId=" + expId + ", pv=" + pv + ", uv=" + uv + ", clicentType="
            + clicentType + ", expType=" + expType + ", validity=" + validity + ", addDay="
            + addDay + ", proNum=" + proNum + ", expNum=" + expNum + ", price=" + price
            + ", proName=" + proName + ", title=" + title + ", probability=" + probability
            + ", bengTime=" + bengTime + ", endTime=" + endTime + ", rnum=" + rnum + ", xqs="
            + xqs + ", page=" + page + ", popNum=" + popNum + "]";
    }


}
