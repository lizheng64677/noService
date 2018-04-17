package com.suyin.product.service;

import java.util.List;

import java.util.*;
import com.suyin.product.model.*;
import com.suyin.product.service.*;
import com.suyin.system.model.Attachment;




public interface ProductService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addProduct(Product entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateProduct(Product entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteProduct(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Product> findProduct(Product entity);
    
    /**
     * 根据商家id查询 
     * @param entity
     * @return
     */
    public List<Product> findProductByMemberId(Product entity);
    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Product> findProductByPage(Product entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Product findProductById(Product entity);
    
    /**
     * 查询附件 
     * @param entity
     * @return
     */
    public List<Attachment> findProAttachmentByEntityId(Attachment entity);
}
