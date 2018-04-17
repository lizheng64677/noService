package com.suyin.expzhuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.experience.model.ExpPrototype;
import com.suyin.expzhuan.mapper.ExpTaskPrototypeMapper;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;



@Transactional
@Service("ExpZhuanPrototypeService")
public class ExpTaskPrototypeServiceImpl implements ExpTaskPrototypeService{

    private final static Logger log=Logger.getLogger(ExpTaskPrototypeServiceImpl.class);

    @Autowired
    private ExpTaskPrototypeMapper ExpZhuanPrototypeMapper; 

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteExpZhuanPrototype(String id){


        return ExpZhuanPrototypeMapper.deleteExpZhuanPrototype(id);
    }



    /**
     *根据活动id查询是否设置
     */
    @Override
    public ExpTaskPrototype findExpZhuanByExpId(ExpTaskPrototype entity)
    {
        // TODO Auto-generated method stub
        return ExpZhuanPrototypeMapper.findExpZhuanByExpId(entity);
    }


    /**
     * 根据活动id查询动态属性值
     */
    @Override
    public List<Map<String, Object>> findExpDictionaryPrototype(String expId,String moduleType)
    {
        // TODO Auto-generated method stub
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("expId", expId);
        param.put("moduleType", moduleType);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = ExpZhuanPrototypeMapper.findExpZhuanPrototype(param); 
        
        for (Map map : list) {
            param.put("dictionary_id", map.get("dictionary_id"));
            map.put("options", ExpZhuanPrototypeMapper.findExpZhuanPrototypeOption(param));
        }
        return list;
    }



    /**
     * 添加活动动态属性 
     */
    @Override
    public Integer addExpZhuanPrototype(String json)
    {
        try {
            JSONArray array = new JSONArray(json);
            JSONObject iObj;
            ExpTaskPrototype exp;
            for(int i=0;i<array.length();i++){
                iObj= array.getJSONObject(i);
                exp=new ExpTaskPrototype();
                exp.setExpId(Integer.parseInt(iObj.get("expid").toString()));
                exp.setModuleType(Integer.parseInt(iObj.get("moduletype").toString()));
                exp.setIsSelected(1);                  
                exp.setDictionaryId((Integer.parseInt(iObj.get("did").toString())));
                ExpZhuanPrototypeMapper.addExpZhuanPrototype(exp);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 1;
    }




    /**
     * 查询属性集合 
     */
    @Override
    public List<ExpTaskPrototype> findExpZhuanByExpIdList(ExpTaskPrototype entity)
    {
        // TODO Auto-generated method stub
        return ExpZhuanPrototypeMapper.findExpZhuanByExpIdList(entity);
    }



    /**
     * 根据活动id查询是否存在
     */
    @Override
    public List<ExpTaskPrototype> findIsProtoTypeListByExpId(ExpTaskPrototype entity)
    {
        // TODO Auto-generated method stub
        return ExpZhuanPrototypeMapper.findIsProtoTypeListByExpId(entity);
    }
}
