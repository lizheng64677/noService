package com.suyin.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.product.mapper.ProductMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.product.model.*;
import com.suyin.product.service.*;



@Transactional
@Service("ProductService")
public class ProductServiceImpl implements ProductService{

    private final static Logger log=Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper ProductMapper; 
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addProduct(Product entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{

                result = ProductMapper.addProduct(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getProId());
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
    public Integer updateProduct(Product entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ProductMapper.updateProduct(entity);
                Attachment attachment=new Attachment();
                attachment.setEntity(entity.getProId());
                if(entity.getAttachments().size()>0){
                    this.attachmentMapper.deleteAttachment(attachment);
                    if(entity.getAttachments().size()>0) {
                        for(Attachment a:entity.getAttachments())
                            a.setEntity(entity.getProId());
                            this.attachmentMapper.addAttachments(entity.getAttachments());
                    }
                }
            }
        } catch (Exception e) {

            log.error("Product信息修改异常"+e.getMessage());
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
    public Integer deleteProduct(String id){


        return ProductMapper.deleteProduct(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Product> findProduct(Product entity){


        return ProductMapper.findProduct(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Product> findProductByPage(Product entity){


        return ProductMapper.findProductByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Product findProductById(Product entity){

        List<Product> list=ProductMapper.findProduct(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 根据商家id查询所含产品
     */
    @Override
    public List<Product> findProductByMemberId(Product entity)
    {
        // TODO Auto-generated method stub
        return ProductMapper.findProductByMemberId(entity);
    }


    /**
     * 根据产品id查询附件
     */
    @Override
    public List<Attachment> findProAttachmentByEntityId(Attachment entity)
    {
        // TODO Auto-generated method stub
        return ProductMapper.findProAttachmentByEntityId(entity);
    }
}
