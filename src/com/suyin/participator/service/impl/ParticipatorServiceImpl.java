package com.suyin.participator.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.participator.mapper.ParticipatorMapper;
import com.suyin.participator.model.Participator;
import com.suyin.participator.service.ParticipatorService;

@Transactional
@Service("ParticipatorService")
public class ParticipatorServiceImpl implements ParticipatorService{

    private final static Logger log=Logger.getLogger(ParticipatorServiceImpl.class);
    
    @Autowired
    private ParticipatorMapper ParticipatorMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addParticipator(Participator entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	entity.setCreateTime(sdf.format(new Date()));
            	entity.setUpdateTime(sdf.format(new Date()));
                result = ParticipatorMapper.addParticipator(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateParticipator(Participator entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	entity.setCreateTime(sdf.format(new Date()));
            	entity.setUpdateTime(sdf.format(new Date()));
                result = ParticipatorMapper.updateParticipator(entity);
            }
        } catch (Exception e) {
            
            log.error("Participator信息修改异常"+e.getMessage());
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
    public Integer deleteParticipator(String id){
        
        
        return ParticipatorMapper.deleteParticipator(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Participator> findParticipator(Participator entity){
        
        
        return ParticipatorMapper.findParticipator(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Participator> findParticipatorByPage(Participator entity){
        
        
        return ParticipatorMapper.findParticipatorByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Participator findParticipatorById(Participator entity){
        
        Participator participator=ParticipatorMapper.findParticipatorById(entity);
        return participator;
    }
}
