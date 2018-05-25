package com.suyin.decorateuser.service;

import java.util.*;

import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;




public interface ExpDecorateUserService{

	  /**
     * 修改金额信息
     * 及相关信息
     * @param entity
     * @return
	 * @throws Exception 
     */
    public Integer updateUserPriceAndOtherInfo(ExpDecorateUser entity) throws Exception;
    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorateUser(ExpDecorateUser entity);

  

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorateUser> findExpDecorateUser(ExpDecorateUser entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDecorateUser> findExpDecorateUserByPage(ExpDecorateUser entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorateUser findExpDecorateUserById(ExpDecorateUser entity);
}
