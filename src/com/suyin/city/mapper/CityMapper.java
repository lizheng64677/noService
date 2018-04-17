package com.suyin.city.mapper;


import java.util.List;
import java.util.Map;

import com.suyin.city.model.*;




public interface CityMapper {

    /**
     * 新增信息
     */
    public Integer addCity(City entity);

    /**
     * 修改信息
     */
    public Integer updateCity(City entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteCity(String id);
    /**
     * 批量删除
     */
    public Integer deleteCity(String[] id); 

    /**
     * 查询列表
     */
    public List<City> findCity(City entity);

    /**
     * 查询列表分页  
     */
    public List<City> findCityByPage(City entity);
    
    /**
     * 查询树模式列表
     * @param city
     * @return
     */
    public List<City> findCityTreeByPage(City	city);
    
    /**
     *  更具Upid查询菜单信息
     * @param city
     * @return
     */
    public List<City> findCityTreeByUpid(City city);
    /**
     * 通过子节点查询父节点
     * @param id
     * @return
     */
    public Integer findEsayUICityChiledren(String id);    
    
    
    /**
     * 热门城市分页查询
     */
    public List<City> findHotCityByPage(City city);
    /**
     * 通过upid查询所对应的id的name
     * @param city
     * @return
     */
    public Integer updateCityByHot(City city);
    
    
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
