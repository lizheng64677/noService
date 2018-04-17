package com.suyin.experience.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.experience.mapper.ExpPrototypeMapper;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.util.Md5Util;

import java.util.*;

import com.suyin.experience.model.*;
import com.suyin.experience.service.*;



@Transactional
@Service("ExpPrototypeService")
public class ExpPrototypeServiceImpl implements ExpPrototypeService{

    private final static Logger log=Logger.getLogger(ExpPrototypeServiceImpl.class);

    @Autowired
    private ExpPrototypeMapper ExpPrototypeMapper; 


    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteExpPrototype(String id){


        return ExpPrototypeMapper.deleteExpPrototype(id);
    }



    /**
     *根据活动id查询是否设置
     */
    @Override
    public ExpPrototype findExpByExpId(ExpPrototype entity)
    {
        // TODO Auto-generated method stub
        return ExpPrototypeMapper.findExpByExpId(entity);
    }


    /**
     * 根据活动id查询动态属性值
     */
    @Override
    public List<Map<String, Object>> findUserPrototype(String expId)
    {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = ExpPrototypeMapper.findExpPrototype();
        Map<String,Object> param=new HashMap<String,Object>();
        if(null!=expId){
            param.put("expId", expId);
        }
        for (Map map : list) {
            param.put("dictionary_id", map.get("dictionary_id"));
            map.put("options", ExpPrototypeMapper.findExpPrototypeOption(param));
        }
        return list;
    }



    /**
     * 添加活动动态属性 
     */
    @Override
    public Integer addExpPrototype(String json)
    {
        try {
            JSONArray array = new JSONArray(json);
            JSONObject iObj;
            ExpPrototype exp;
            for(int i=0;i<array.length();i++){
                iObj= array.getJSONObject(i);
                exp=new ExpPrototype();
                exp.setExpId((Integer.parseInt(iObj.get("expid").toString())));
                exp.setModuleType((Integer.parseInt(iObj.get("type").toString())));
                exp.setIsSelected(1);                  
                exp.setDictionaryId((Integer.parseInt(iObj.get("did").toString())));
                ExpPrototypeMapper.addExpPrototype(exp);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 1;
    }
    @Override
    public Integer addExpTaskPrototype(String json)
    {
    	try {
    		JSONArray array = new JSONArray(json);
    		JSONObject iObj;
    		ExpPrototype exp;
    		for(int i=0;i<array.length();i++){
    			iObj= array.getJSONObject(i);
    			exp=new ExpPrototype();
    			exp.setExpId((Integer.parseInt(iObj.get("expid").toString())));
    			exp.setIsSelected(1);                  
    			exp.setDictionaryId((Integer.parseInt(iObj.get("did").toString())));
    			ExpPrototypeMapper.addExpTaskPrototype(exp);
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
    public List<ExpPrototype> findExpByExpIdList(ExpPrototype entity)
    {
        // TODO Auto-generated method stub
        return ExpPrototypeMapper.findExpByExpIdList(entity);
    }



	@Override
	public void deleteExpTaskPrototype(String expId) {
		this.ExpPrototypeMapper.deleteExpTaskPrototype(expId);
		
	}



	@Override
	public List<Map<String, Object>> findExpTaskPrototype(String expId) {
		// TODO Auto-generated method stub
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = ExpPrototypeMapper.findExpPrototype();
        Map<String,Object> param=new HashMap<String,Object>();
        if(null!=expId){
            param.put("expId", expId);
        }
        for (Map map : list) {
            param.put("dictionary_id", map.get("dictionary_id"));
            map.put("options", ExpPrototypeMapper.findExpTaskPrototypeOption(param));
        }
        return list;
	}
}
