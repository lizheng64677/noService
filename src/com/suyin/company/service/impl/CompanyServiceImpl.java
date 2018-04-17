package com.suyin.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.company.mapper.CompanyMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.company.model.*;
import com.suyin.company.service.*;



@Transactional
@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService{

    private final static Logger log=Logger.getLogger(CompanyServiceImpl.class);
    
    @Autowired
    private CompanyMapper CompanyMapper; 
    

	@Autowired
	private AttachmentMapper attachmentMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addCompany(Company entity){
        Integer result=0;
        try {
            if(entity==null){
                return result;
            }else{
                result = CompanyMapper.addCompany(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getCompanyId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
            }
        } catch (Exception e) {
           log.error("Company信息修改异常"+e.getMessage());
           e.printStackTrace();
           throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateCompany(Company entity){

        Integer result=0;
        try {
            if(entity==null){
                return result;
            }else{
                result = CompanyMapper.updateCompany(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getCompanyId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
            }
        } catch (Exception e) {
            
            log.error("Company信息修改异常"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
           
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteCompany(String id){
        return CompanyMapper.deleteCompany(id);
    }
    
    /**
     * 批量删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteCompanyByBatch(String[] ids){
    	return CompanyMapper.deleteCompanyByBatch(ids);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Company> findCompany(Company entity){
        
        
        return CompanyMapper.findCompanyList(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Company> findCompanyByPage(Company entity){
        
        
        return CompanyMapper.findCompanyByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Company findCompanyById(Company entity){
        return CompanyMapper.findCompanyById(entity);
    }
}
