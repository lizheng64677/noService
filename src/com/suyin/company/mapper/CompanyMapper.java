package com.suyin.company.mapper;


import java.util.List;

import java.util.*;
import com.suyin.company.model.*;
import com.suyin.company.service.*;




public interface CompanyMapper {

    /**
     * 新增信息
     */
    public Integer addCompany(Company entity);

    /**
     * 修改信息
     */
    public Integer updateCompany(Company entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteCompany(String id);
    /**
     * 批量删除
     */
    public Integer deleteCompanyByBatch(String[] id); 

    /**
     * 查询列表
     */
    public List<Company> findCompanyList(Company entity);

    /**
     * 查询列表分页  
     */
    public List<Company> findCompanyByPage(Company entity);
    
    /**
     * 根据主键查找
     */
    public Company findCompanyById(Company entity);

}
