package com.suyin.falsedata.service.impl;

import java.util.List;
import java.util.Map;

import com.suyin.falsedata.mapper.FalseNouserMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.falsedata.model.*;
import com.suyin.falsedata.service.*;



@Transactional
@Service("FalseNouserService")
public class FalseNouserServiceImpl implements FalseNouserService{

    private final static Logger log=Logger.getLogger(FalseNouserServiceImpl.class);

    @Autowired
    private FalseNouserMapper FalseNouserMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addFalseNouser(FalseNouser entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = FalseNouserMapper.addFalseNouser(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }
    /**
     * 从excel批量导入数据
     */
    @Override
    public int addFalseNouserByBatch(List<Map<String, String>> entity) {

        Integer result=0;
        if(null==entity){
            return result;
        }
        else{
            result= FalseNouserMapper.addFalseNouserByBatch(entity);	
        }	
        return result;
    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateFalseNouser(FalseNouser entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = FalseNouserMapper.updateFalseNouser(entity);
            }
        } catch (Exception e) {

            log.error("FalseNouser信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteFalseNouser(String id){


        return FalseNouserMapper.deleteFalseNouser(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<FalseNouser> findFalseNouser(FalseNouser entity){


        return FalseNouserMapper.findFalseNouser(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<FalseNouser> findFalseNouserByPage(FalseNouser entity){


        return FalseNouserMapper.findFalseNouserByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public FalseNouser findFalseNouserById(FalseNouser entity){

        List<FalseNouser> list=FalseNouserMapper.findFalseNouser(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }




}
