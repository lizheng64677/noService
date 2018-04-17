package com.suyin.member.service;

import java.util.List;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface MemberService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addMember(Member entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateMember(Member entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteMember(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Member> findMember(Member entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
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
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Member findMemberById(Member entity);
    /**
     * 判断当前用户名是否存在
     * @param map
     * @return 
     * @see
     */
    public Map<String,Object>isUserName(Map<String,Object> map);
}
