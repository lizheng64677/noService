package com.suyin.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.member.mapper.MemberMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;



@Transactional
@Service("MemberService")
public class MemberServiceImpl implements MemberService{

    private final static Logger log=Logger.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper MemberMapper; 
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addMember(Member entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = MemberMapper.addMember(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getMemberId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
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
    public Integer updateMember(Member entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = MemberMapper.updateMember(entity);
            }
        } catch (Exception e) {

            log.error("Member信息修改异常"+e.getMessage());
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
    public Integer deleteMember(String id){


        return MemberMapper.deleteMember(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Member> findMember(Member entity){


        return MemberMapper.findMember(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Member> findMemberByPage(Member entity){


        return MemberMapper.findMemberByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Member findMemberById(Member entity){

        List<Member> list=MemberMapper.findMember(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 判断当前用户名是否已经存在
     */
    @Override
    public Map<String, Object> isUserName(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return this.MemberMapper.isUserName(map);
    }

    /**
     * map形式返回数据
     */
    @Override
    public List<Map<String, Object>> findMemberByMapPage(Member entity)
    {
        // TODO Auto-generated method stub
        return this.MemberMapper.findMemberByMapPage(entity);
    }
}
