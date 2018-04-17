/*
 * 文件名：ReflectHelper.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：WS
 * 修改时间：2015年3月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.util;

import java.lang.reflect.Field;

public class ReflectHelper
{
    /**
     * 获取obj对象fieldName的Field
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getFieldByFieldName(Object obj, String fieldName)
    {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(fieldName);
            }
            catch (NoSuchFieldException e)
            {}
        }
        return null;
    }

    /**
     * 获取obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
        throws SecurityException, NoSuchFieldException, IllegalArgumentException,
        IllegalAccessException
    {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null)
        {
            if (field.isAccessible())
            {
                value = field.get(obj);
            }
            else
            {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 设置obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value)
        throws SecurityException, NoSuchFieldException, IllegalArgumentException,
        IllegalAccessException
    {
        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible())
        {
            field.set(obj, value);
        }
        else
        {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }
}
