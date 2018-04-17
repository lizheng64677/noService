package com.suyin.city.service;

import java.util.*;

import com.suyin.city.model.*;




public interface CityService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addCity(City entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateCity(City entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteCity(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<City> findCity(City entity);


    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<City> findCityByPage(City entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public City findCityById(City entity);



    /**
     * 查询树模式列表
     * @param city
     * @return
     */
    public List<City> findCityTreeByPage(City city);

    /**
     *  更具Upid查询菜单信息
     * @param city
     * @return
     */
    public List<City> findCityTreeByUpid(City city);


    /**
     * 城市分页查询
     */
    public List<City> findHotCityByPage(City city);

    /**
     * 设为热门城市
     * @param city
     * @return
     */
    public Integer hotCity(City city);


    /**
     * 
     * 城市拼音制作更新
     * @param mapInfo
     * @return 
     * @see
     */
    public Integer updateCityInfoName(Map<String,Object> mapInfo);
    /**
     * JSON格式数据查询制作
     * @param mapInfo
     * @return 
     * @see
     */
    public List<Map<String,Object>>findUpdateCityInfo(Map<String,Object> mapInfo);
    /**
     * 数据处理
     * @return 
     * @see
     */
    public List<Map<String,Object>>findForCityListInfo();
    
    public List<Map<String,Object>>findForCityListInfoa(Map<String,Object> map);
}
