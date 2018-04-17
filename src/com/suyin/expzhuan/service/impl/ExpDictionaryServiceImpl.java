package com.suyin.expzhuan.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.expzhuan.mapper.ExpDictionaryMapper;
import com.suyin.expzhuan.model.ExpDictionary;
import com.suyin.expzhuan.service.ExpDictionaryService;



@Transactional
@Service("ExpDictionaryService")
public class ExpDictionaryServiceImpl implements ExpDictionaryService{

    private final static Logger log=Logger.getLogger(ExpDictionaryServiceImpl.class);

    @Autowired
    private ExpDictionaryMapper ExpDictionaryMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDictionary(ExpDictionary entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDictionaryMapper.addExpDictionary(entity);
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
    public Integer updateExpDictionary(ExpDictionary entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDictionaryMapper.updateExpDictionary(entity);
            }
        } catch (Exception e) {

            log.error("ExpDictionary信息修改异常"+e.getMessage());
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
    public Integer deleteExpDictionary(String id){


        return ExpDictionaryMapper.deleteExpDictionary(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDictionary> findExpDictionary(ExpDictionary entity){


        return this.checkTree(ExpDictionaryMapper.findExpDictionary(entity));
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDictionary> findExpDictionaryByPage(ExpDictionary entity){


        return this.checkTree(ExpDictionaryMapper.findExpDictionaryByPage(entity));
    }
    private List<ExpDictionary> checkTree(List<ExpDictionary> list) {
        for (ExpDictionary dic : list) {
            if (dic.getSonTrees() > 0) {
                dic.setState("closed");
            } else {
                dic.setState("open");
            }
            if(1==dic.getDictionaryType()){
                dic.setIconCls("icon-more");
            }if(2==dic.getDictionaryType()){
                dic.setIconCls("icon-lock");
            }if(3==dic.getDictionaryType()){
                dic.setIconCls("icon-sum");
            }if(4==dic.getDictionaryType()){
                dic.setIconCls("icon-man");
            }if(5==dic.getDictionaryType()){
                dic.setIconCls("icon-undo");
            }
        }
        return list;
    }
    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDictionary findExpDictionaryById(ExpDictionary entity){

        List<ExpDictionary> list=ExpDictionaryMapper.findExpDictionary(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 根据活动id删除
     */
    @Override
    public Integer deleteExpDictionaryWhereByExpId(String id)
    {
        // TODO Auto-generated method stub
        return ExpDictionaryMapper.deleteExpDictionaryWhereByExpId(id);
    }

    /**
     * 根据活动id及类型 联合删除
     */
    @Override
    public Integer deleteExpUnionByExpId(ExpDictionary entity)
    {
        // TODO Auto-generated method stub
        return ExpDictionaryMapper.deleteExpUnionByExpId(entity);
    }

}
