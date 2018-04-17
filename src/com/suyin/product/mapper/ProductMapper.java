package com.suyin.product.mapper;


import java.util.List;

import java.util.*;
import com.suyin.product.model.*;
import com.suyin.product.service.*;
import com.suyin.system.model.Attachment;




public interface ProductMapper {

    /**
     * 新增信息
     */
    public Integer addProduct(Product entity);

    /**
     * 修改信息
     */
    public Integer updateProduct(Product entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteProduct(String id);
    /**
     * 批量删除
     */
    public Integer deleteProduct(String[] id); 

    /**
     * 查询列表
     */
    public List<Product> findProduct(Product entity);

    /**
     * 
     *根据商家id查询所含产品 
     * @param entity
     * @return 
     * @see
     */
    public List<Product>findProductByMemberId(Product entity);

    /**
     * 查询列表分页  
     */
    public List<Product> findProductByPage(Product entity);
    
    /**
     * 查询附件 
     * @param entity
     * @return
     */
    public List<Attachment> findProAttachmentByEntityId(Attachment entity);

}
