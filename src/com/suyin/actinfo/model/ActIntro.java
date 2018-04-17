package com.suyin.actinfo.model;


import java.text.SimpleDateFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;


public class ActIntro implements java.io.Serializable
{

    private static final long serialVersionUID = 5454155825314635342L;

    public static final String TABLE_ALIAS = "ActIntro";

    public static final String ALIAS_ACT_INTRO_ID = "主键";

    public static final String ALIAS_CONTENT = "活动说明";

    public static final String ALIAS_TYPE = "0:轻松赚，1：帮我赚，2：抽奖式，3：人气式，4：0元式，5：兑换式";

    public static final String ALIAS_CREATE_TIME = "createTime";

    public static final String ALIAS_UPDATE_TIME = "updateTime";

    private Page page;//分页插件

    public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";

    //columns start

    /**
     * 主键       db_column: act_intro_id 
     */
    private java.lang.Integer actIntroId;

    /**
     * 活动说明       db_column: content 
     */
    private java.lang.String content;

    /**
     * 0:轻松赚，1：帮我赚，2：抽奖式，3：人气式，4：0元式，5：兑换式       db_column: type 
     */
    private java.lang.Integer type;

    /**
     * createTime       db_column: create_time 
     */
    private java.util.Date createTime;

    /**
     * updateTime       db_column: update_time 
     */
    private java.util.Date updateTime;

    //columns end

    public ActIntro()
    {}

    public ActIntro(java.lang.Integer actIntroId)
    {
        this.actIntroId = actIntroId;
    }

    public void setActIntroId(java.lang.Integer value)
    {
        this.actIntroId = value;
    }

    public java.lang.Integer getActIntroId()
    {
        return this.actIntroId;
    }

    public java.lang.String getContent()
    {
        return this.content;
    }

    public void setContent(java.lang.String value)
    {
        this.content = value;
    }

    public java.lang.Integer getType()
    {
        return this.type;
    }

    public void setType(java.lang.Integer value)
    {
        this.type = value;
    }

    public java.util.Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value)
    {
        this.createTime = value;
    }

    public java.util.Date getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date value)
    {
        this.updateTime = value;
    }

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }

    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("ActIntroId",
            getActIntroId()).append("Content", getContent()).append("Type", getType()).append(
            "CreateTime", getCreateTime()).append("UpdateTime", getUpdateTime()).toString();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(getActIntroId()).toHashCode();
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof ActIntro == false) return false;
        if (this == obj) return true;
        ActIntro other = (ActIntro)obj;
        return new EqualsBuilder().append(getActIntroId(), other.getActIntroId()).isEquals();
    }

    public String getCreateTimeString()
    {
        try
        {
            return new SimpleDateFormat(FORMAT_CREATE_TIME).format(getCreateTime());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void setCreateTimeString(String value)
    {
        try
        {
            setCreateTime(new SimpleDateFormat(FORMAT_CREATE_TIME).parse(value));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getUpdateTimeString()
    {
        try
        {
            return new SimpleDateFormat(FORMAT_UPDATE_TIME).format(getUpdateTime());
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void setUpdateTimeString(String value)
    {
        try
        {
            setUpdateTime(new SimpleDateFormat(FORMAT_UPDATE_TIME).parse(value));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
