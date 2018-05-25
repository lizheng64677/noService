package com.suyin.uservoucher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.uservoucher.mapper.ExpDecorateUserVoucherMapper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.decoratemessage.model.DecorateMessage;
import com.suyin.decoratemessage.service.DecorateMessageService;
import com.suyin.decoraterecord.model.DecorateRecord;
import com.suyin.decoraterecord.service.DecorateRecordService;
import com.suyin.decorateuser.model.ExpDecorateUser;
import com.suyin.decorateuser.service.ExpDecorateUserService;
import com.suyin.system.util.Md5Util;

import java.util.*;

import com.suyin.uservoucher.model.*;
import com.suyin.uservoucher.service.*;



@Transactional
@Service("ExpDecorateUserVoucherService")
public class ExpDecorateUserVoucherServiceImpl implements ExpDecorateUserVoucherService{

    private final static Logger log=Logger.getLogger(ExpDecorateUserVoucherServiceImpl.class);
    
    @Autowired
    private ExpDecorateUserVoucherMapper ExpDecorateUserVoucherMapper; 
    @Autowired
    private DecorateMessageService decorateMessageService;
    @Autowired
    private DecorateRecordService decorateRecordService;
    @Autowired
    private ExpDecorateUserService decorateUserService;
    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDecorateUserVoucher(ExpDecorateUserVoucher entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDecorateUserVoucherMapper.addExpDecorateUserVoucher(entity);
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
    public Integer updateExpDecorateUserVoucher(ExpDecorateUserVoucher entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateUserVoucherMapper.updateExpDecorateUserVoucher(entity);
                if(result>0){
                	//获取推荐返现佣金,推荐人openid信息
                	ExpDecorateUserVoucher voucher=this.findExpDecorateUserVoucherById(entity);
                	if(StringUtils.isNotEmpty(voucher.getUseOpenid())){
                		String useOpenid=voucher.getUseOpenid();
                		String usePrice=voucher.getUsePrice();
                		
                		//1添加消息给推荐人，在推荐人的我的消息查看
                		DecorateMessage message=new DecorateMessage();
                		message.setContent("您推荐的用户"+voucher.getName()+"已消券,您获得佣金"+usePrice+"(元)");
                		message.setOpenid(useOpenid);
                		message.setSendModuleName("用户消券");
                		message.setSendEntity(0);
                		decorateMessageService.addDecorateMessage(message);
                		//2为推荐人的金额变动做记录
                		DecorateRecord record=new DecorateRecord();
                		record.setAcceptOpenid(voucher.getOpenid());//受邀人 （自己）
                		record.setCommissionPrice(usePrice);
                		record.setMessage("受邀人"+voucher.getNickName()+"消券,返佣金");         
                		record.setPublishOpenid(voucher.getUseOpenid());//分享着 (我的推荐人)
                		//TODO 这里的状态可能需要重构下
                		record.setState(0);//0 发起人，1受邀人
                		record.setType(1);//收益类型:0分享，1:购买福券返佣金，2签单奖励
                		record.setPriceState(0);//券状态 0:已收益 1:待收益
                		decorateRecordService.addDecorateRecord(record);
                		//3变动推荐人金额
                		ExpDecorateUser user=new ExpDecorateUser();
                		user.setOpenid(useOpenid);
                		user.setBackPrice(usePrice);
                		decorateUserService.updateUserPriceAndOtherInfo(user);
                	}else{
                		log.debug("没有推荐人..不进行返现");
                	}
                	
                }
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateUserVoucher信息修改异常"+e.getMessage());
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
    public Integer deleteExpDecorateUserVoucher(String id){
        
        
        return ExpDecorateUserVoucherMapper.deleteExpDecorateUserVoucher(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucher(ExpDecorateUserVoucher entity){
        
        
        return ExpDecorateUserVoucherMapper.findExpDecorateUserVoucher(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucherByPage(ExpDecorateUserVoucher entity){
        
        
        return ExpDecorateUserVoucherMapper.findExpDecorateUserVoucherByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateUserVoucher findExpDecorateUserVoucherById(ExpDecorateUserVoucher entity){
        
        List<ExpDecorateUserVoucher> list=ExpDecorateUserVoucherMapper.findExpDecorateUserVoucher(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
