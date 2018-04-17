package com.suyin.city.service.impl;

import java.util.List;
import java.util.Map;

import com.suyin.city.mapper.CityMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.city.model.*;
import com.suyin.city.service.*;



@Transactional
@Service("CityService")
public class CityServiceImpl implements CityService{

    private final static Logger log=Logger.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper CityMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addCity(City entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = CityMapper.addCity(entity);
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
    public Integer updateCity(City entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = CityMapper.updateCity(entity);
            }
        } catch (Exception e) {

            log.error("City信息修改异常"+e.getMessage());
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
    public Integer deleteCity(String id){


        return CityMapper.deleteCity(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<City> findCity(City entity){


        return CityMapper.findCity(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<City> findCityByPage(City entity){


        return CityMapper.findCityByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @returns
     */
    @Override 
    public City findCityById(City entity){

        List<City> list=CityMapper.findCity(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /* (non-Javadoc)
     * @see com.suyin.city.service.CityService#updateCityInfoName(java.util.Map)
     */
    @Override
    public Integer updateCityInfoName(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return CityMapper.updateCityInfoName(mapInfo);
    }

    /* (non-Javadoc)
     * @see com.suyin.city.service.CityService#findUpdateCityInfo(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findUpdateCityInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return CityMapper.findUpdateCityInfo(mapInfo);
    }

    /**
     * 查询树模式列表
     * @param city
     * @return
     */
    @Override
    public List<City> findCityTreeByPage(City city) {
        List<City> list=CityMapper.findCityTreeByPage(city);
        int i=0;
        for(City tree:list){
            i=CityMapper.findEsayUICityChiledren(tree.getId().toString());
            if(i>0){
                tree.setState("closed");
                tree.setIconCls("icon-nav");
            }else{
                tree.setState("open");
                tree.setIconCls("icon-nav");
                //给null 默认是树形菜单tub
            }
        }
        return list;
    }

    /**
     *  更具Upid查询菜单信息
     * @param city
     * @return
     */
    @Override
    public List<City> findCityTreeByUpid(City city) {
        // TODO Auto-generated method stub
        List<City> list=CityMapper.findCityTreeByUpid(city);
        int i=0;
        for(City tree:list){
            i=CityMapper.findEsayUICityChiledren(tree.getId().toString());
            if(i>0){
                tree.setState("closed");
                tree.setIconCls("icon-save");
            }else{
                tree.setState("open");
                tree.setIconCls("icon-save");
            }
        }
        return list;
    }


    @Override
    public List<City> findHotCityByPage(City city) {
        // TODO Auto-generated method stub
        try {
            return CityMapper.findHotCityByPage(city);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("Service Error CityServiceImpl -> findHotCityByPage"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    /**
     * 通过upid查询id对应的name
     */
    //	public List<City> findHotCityName(City city){
    //		List<City> list=CityMapper.findHotCityName(city);
    //		int i=0;
    //		for(City upid:list){
    //			i=CityMapper.findHotCityUpidName(upid.getId().toString());
    //			if(i>0&&i==0){
    //				upid.getName();
    //			}
    //		}
    //		return list;
    //	}

    @Override
    public Integer hotCity(City city) {
        // TODO Auto-generated method stub
        City c;
        try {
            for(String id:city.getRemoveIds().split(",")){
                c=new City();
                c.setId(Integer.parseInt(id));
                c.setHotCity(city.getHotCity());
                CityMapper.updateCityByHot(c);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch blockout 
            log.error("Service Error CityServiceImpl -> hotCity"
                + e.getMessage());
            throw new RuntimeException();
        }
        return 1;
    }

    /* (non-Javadoc)
     * @see com.suyin.city.service.CityService#findForCityListInfo()
     */
    @Override
    public List<Map<String, Object>> findForCityListInfo()
    {
        // TODO Auto-generated method stub
        return CityMapper.findForCityListInfo();
    }

    /* (non-Javadoc)
     * @see com.suyin.city.service.CityService#findForCityListInfoa(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findForCityListInfoa(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return CityMapper.findForCityListInfoa(map);
    }
}
