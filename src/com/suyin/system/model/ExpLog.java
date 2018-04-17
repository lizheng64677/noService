/*
 * 文件名：ExpLog.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-16
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.model;

/**
 * 辅助转换活动日志
 * @author lz
 * @version 2015-12-16
 * @see ExpLog
 * @since
 */

public class ExpLog
{

    private String expId;
    private String clicentType;
    private String userId;
    private String expType;
    public String getExpId()
    {
        return expId;
    }
    public void setExpId(String expId)
    {
        this.expId = expId;
    }
    public String getClicentType()
    {
        return clicentType;
    }
    public void setClicentType(String clicentType)
    {
        this.clicentType = clicentType;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getExpType()
    {
        return expType;
    }
    public void setExpType(String expType)
    {
        this.expType = expType;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expId == null) ? 0 : expId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((clicentType == null) ? 0 : clicentType.hashCode());
        result = prime * result + ((expType == null) ? 0 : expType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        ExpLog other = (ExpLog)obj;
        if (expId == null){

            if (other.expId != null){
                return false;
            }
        }else if(!expId.equals(other.expId)){

            return false;
        }

        if (userId == null){

            if (other.userId != null){
                return false;
            }
        }else if(!userId.equals(other.userId)){
            return false;
        }        
        if (clicentType == null){

            if (other.clicentType != null){
                return false;
            }
        }else if(!clicentType.equals(other.clicentType)){
            return false;
        }
        if (expType == null){

            if (other.expType != null){
                return false;
            }
        }else if(!expType.equals(other.expType)){
            return false;
        }
        return true;
    }

}
