package com.suyin.company.service;

import java.util.List;

import java.util.*;
import com.suyin.company.model.*;
import com.suyin.company.service.*;




public interface CompanyService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addCompany(Company entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateCompany(Company entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteCompany(String id);
    
    
    public Integer deleteCompanyByBatch(String[] id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Company> findCompany(Company entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Company> findCompanyByPage(Company entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Company findCompanyById(Company entity);
}
