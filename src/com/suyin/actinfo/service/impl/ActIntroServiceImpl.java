package com.suyin.actinfo.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.actinfo.mapper.ActIntroMapper;
import com.suyin.actinfo.model.ActIntro;
import com.suyin.actinfo.service.ActIntroService;


@Transactional
@Service("ActIntroService")
public class ActIntroServiceImpl implements ActIntroService
{

    private final static Logger log = Logger.getLogger(ActIntroServiceImpl.class);

    @Autowired
    private ActIntroMapper ActIntroMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addActIntro(ActIntro entity)
    {
        Integer result = 0;
        try
        {

            if (entity == null)
            {
                return result;
            }
            else
            {
                result = ActIntroMapper.addActIntro(entity);
            }

        }
        catch (Exception e)
        {

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
    public Integer updateActIntro(ActIntro entity)
    {

        Integer result = 0;
        try
        {
            if (entity == null)
            {

                return result;
            }
            else
            {

                result = ActIntroMapper.updateActIntro(entity);
            }
        }
        catch (Exception e)
        {

            log.error("ActIntro信息修改异常" + e.getMessage());
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
    public Integer deleteActIntro(String id)
    {

        return ActIntroMapper.deleteActIntro(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ActIntro> findActIntro(ActIntro entity)
    {

        return ActIntroMapper.findActIntro(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ActIntro> findActIntroByPage(ActIntro entity)
    {

        return ActIntroMapper.findActIntroByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ActIntro findActIntroById(ActIntro entity)
    {

        List<ActIntro> list = ActIntroMapper.findActIntroById(entity);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
