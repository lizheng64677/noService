/*
 * 文件名：ExpTime.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-10-11
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.model;

import com.suyin.system.model.Page;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lz
 * @version 2015-10-11
 * @see ExpTime
 * @since
 */

public class ExpTime implements java.io.Serializable
{

    private String memberId;
    private String userId;
    private String detailId;
    private String expId;
    private String shareNum;
    private String shareId;
    private String iphone;
    private String userName;
    private String expType;
    private String timeId;
    private String createTime;
    private String orderId;
    private String popStatus;
    private String prizeStatus;
    private String voucherStatus;
    private String title;
    private String beginTime;
    private String endTime;
    private String userPhone;
    
    
    public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPopStatus() {
		return popStatus;
	}
	public void setPopStatus(String popStatus) {
		this.popStatus = popStatus;
	}
	public String getPrizeStatus() {
		return prizeStatus;
	}
	public void setPrizeStatus(String prizeStatus) {
		this.prizeStatus = prizeStatus;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public ExpTime(){
        
    } 
    
    private Page page;//分页插件
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    public String getMemberId()
    {
        return memberId;
    }
    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getDetailId()
    {
        return detailId;
    }
    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }
    public String getExpId()
    {
        return expId;
    }
    public void setExpId(String expId)
    {
        this.expId = expId;
    }
    public String getShareNum()
    {
        return shareNum;
    }
    public void setShareNum(String shareNum)
    {
        this.shareNum = shareNum;
    }
    public String getShareId()
    {
        return shareId;
    }
    public void setShareId(String shareId)
    {
        this.shareId = shareId;
    }
    public String getIphone()
    {
        return iphone;
    }
    public void setIphone(String iphone)
    {
        this.iphone = iphone;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getTimeId()
    {
        return timeId;
    }
    public void setTimeId(String timeId)
    {
        this.timeId = timeId;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public String getVoucherStatus()
    {
        return voucherStatus;
    }
    public void setVoucherStatus(String voucherStatus)
    {
        this.voucherStatus = voucherStatus;
    }
    @Override
    public String toString()
    {
        return "ExpTime [memberId=" + memberId + ", userId=" + userId + ", detailId=" + detailId
               + ", expId=" + expId + ", shareNum=" + shareNum + ", shareId=" + shareId
               + ", iphone=" + iphone + ", userName=" + userName + ", timeId=" + timeId
               + ", createTime=" + createTime + ", voucherStatus=" + voucherStatus + ", page="
               + page + "]";
    }



}
