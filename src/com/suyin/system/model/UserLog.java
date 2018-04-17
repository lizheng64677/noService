/*
 * 文件名：UserLog.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：XYX
 * 修改时间：2014-7-9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.model;

public class UserLog
{

    private String userId;

    private String logType;

    //平台类型
    private String clicentType;


    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLogType()
    {
        return logType;
    }

    public void setLogType(String logType)
    {
        this.logType = logType;
    }

    public String getClicentType()
    {
        return clicentType;
    }

    public void setClicentType(String clicentType)
    {
        this.clicentType = clicentType;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logType == null) ? 0 : logType.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((clicentType == null) ? 0 : clicentType.hashCode());
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
        UserLog other = (UserLog)obj;
        if (logType == null){

            if (other.logType != null){
                return false;
            }
        }else if(!logType.equals(other.logType)){

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
        return true;
    }


}
