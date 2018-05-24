package com.suyin.decorateuser.mapper;


import java.util.List;

import java.util.*;
import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;




public interface ExpDecorateUserMapper {


    /**
     * 修改信息
     */
    public Integer updateExpDecorateUser(ExpDecorateUser entity); 

    /**
     * 查询列表
     */
    public List<ExpDecorateUser> findExpDecorateUser(ExpDecorateUser entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDecorateUser> findExpDecorateUserByPage(ExpDecorateUser entity);

}
