/*
 * 文件名：BaseLogModel.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-7
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.model;

import java.util.Date;

import com.suyin.system.util.Tools;

/**
 * 操作记录实体对应类
 * @author lz
 * @version 2015-12-7
 * @see BaseLogModel
 * @since
 */

public class BaseLogModel
{


    //主键id
    private String id;
    //用户userId 不同于 微信openId andriod ios 通用 userId
    private String userId;
    //模块名称
    private String modelName;
    //模块定义编号
    private String modelNumber;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //记录日志操作类型
    private String logType; 
    //平台类型
    private String clicentType;
    //uv
    private int uv;
    //pv
    private int pv;
    private Page page;//分页插件
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logType == null) ? 0 : logType.hashCode());
        result = prime * result + ((clicentType == null) ? 0 : clicentType.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseLogModel other = (BaseLogModel)obj;
        if (logType == null){

            if (other.logType != null){
                return false;
            }

        }else if (!logType.equals(other.logType)){
            return false;
        }
        if (clicentType == null){

            if (other.clicentType != null){
                return false;
            }

        }else if (!clicentType.equals(other.clicentType)){
            return false;
        }
        return true;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getModelName()
    {
        return modelName;
    }
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }
    public String getModelNumber()
    {
        return modelNumber;
    }
    public void setModelNumber(String modelNumber)
    {
        this.modelNumber = modelNumber;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {

        this.createTime = createTime;
    }    

    public String getLogType()
    {
        return logType;
    }
    public void setLogType(String logType)
    {
        this.logType = logType;
    }

    public int getUv()
    {
        return uv;
    }
    public void setUv(int uv)
    {
        this.uv = uv;
    }
    public int getPv()
    {
        return pv;
    }
    public void setPv(int pv)
    {
        this.pv = pv;
    }

    public String getClicentType()
    {
        return clicentType;
    }
    public void setClicentType(String clicentType)
    {
        this.clicentType = clicentType;
    }

    public Page getPage()
    {
        return page;
    }



    public void setPage(Page page)
    {
        this.page = page;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    private String begTime;
    private String endTime;

    public String getBegTime()
    {
        return begTime;
    }
    public void setBegTime(String begTime)
    {
        this.begTime = begTime;
    }
    public String getEndTime()
    {
        return endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }



    @Override
    public String toString()
    {

        return  userId + "," + modelName + "," + modelNumber + ","+logType+ ","+clicentType;
        //        +"," + Tools.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss")
    }



}
