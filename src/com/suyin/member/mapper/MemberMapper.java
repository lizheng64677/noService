package com.suyin.member.mapper;


import java.util.List;
import java.util.Map;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface MemberMapper {

    /**
     * 新增信息
     */
    public Integer addMember(Member entity);

    /**
     * 修改信息
     */
    public Integer updateMember(Member entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteMember(String id);
    /**
     * 批量删除
     */
    public Integer deleteMember(String[] id); 

    /**
     * 查询列表
     */
    public List<Member> findMember(Member entity);

    /**
     * 查询列表分页  
     */
    public List<Member> findMemberByPage(Member entity);
    /**
     * map形式返回数据
     * @param entity
     * @return 
     * @see
     */
    public List<Map<String, Object>>findMemberByMapPage(Member entity);
    /**
     * 判断当前用户名是否存在
     * @param map
     * @return 
     * @see
     */
    public Map<String,Object>isUserName(Map<String,Object> map);

}
