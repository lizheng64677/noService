/*
 * 文件名：BaseExpLogModel.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-16
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.model;

import java.util.Date;

import com.suyin.system.util.Tools;

/**
 * 活动日志记录实体
 * @author lz
 * @version 2015-12-16
 * @see BaseExpLogModel
 * @since
 */

public class BaseExpLogModel
{
    private String expId;
    private String userId;
    private String detaiId;
    private String clicentType;
    private String expType;
    private String createTime;
    private String updateTime;
    private int pv;
    private int uv;
    private Page page;//分页插件
    public String getExpId()
    {
        return expId;
    }
    public void setExpId(String expId)
    {
        this.expId = expId;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getDetaiId()
    {
        return detaiId;
    }
    public void setDetaiId(String detaiId)
    {
        this.detaiId = detaiId;
    }
    public String getClicentType()
    {
        return clicentType;
    }
    public void setClicentType(String clicentType)
    {
        this.clicentType = clicentType;
    }

    public String getExpType()
    {
        return expType;
    }
    public void setExpType(String expType)
    {
        this.expType = expType;
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

    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public String getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }    
    public Page getPage()
    {
        return page;
    }
    public void setPage(Page page)
    {
        this.page = page;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expId == null) ? 0 : expId.hashCode());
        result = prime * result + ((clicentType == null) ? 0 : clicentType.hashCode());
        result = prime * result + ((expType == null) ? 0 : expType.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseExpLogModel other = (BaseExpLogModel)obj;
        if (expId == null){

            if (other.expId != null){
                return false;
            }

        }else if (!expId.equals(other.expId)){
            return false;
        }
        if (clicentType == null){

            if (other.clicentType != null){
                return false;
            }

        }else if (!clicentType.equals(other.clicentType)){
            return false;
        }
        if (expType == null){

            if (other.expType != null){
                return false;
            }

        }else if (!expType.equals(other.expType)){
            return false;
        }
        return true;
    }
    @Override
    public String toString()
    {

        return userId+ "," +expId + "," + detaiId + ","+clicentType+","+expType+","+ Tools.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
    }


}
