package com.suyin.decorateuser.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.common.Utils;
import com.suyin.decoratemessage.model.DecorateMessage;
import com.suyin.decoratemessage.service.DecorateMessageService;
import com.suyin.decoraterecord.model.DecorateRecord;
import com.suyin.decoraterecord.service.DecorateRecordService;
import com.suyin.decorateuser.mapper.ExpDecorateUserMapper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.util.Md5Util;
import com.suyin.uservoucher.model.ExpDecorateUserVoucher;
import com.suyin.uservoucher.service.ExpDecorateUserVoucherService;

import java.util.*;

import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;



@Transactional
@Service("ExpDecorateUserService")
public class ExpDecorateUserServiceImpl implements ExpDecorateUserService{

    private final static Logger log=Logger.getLogger(ExpDecorateUserServiceImpl.class);
    
    @Autowired
    private ExpDecorateUserMapper ExpDecorateUserMapper; 
    @Autowired
    private DecorateMessageService decorateMessageService;
    @Autowired
    private DecorateRecordService decorateRecordService;
    @Autowired
    private ExpDecorateUserVoucherService decorateUserVoucherService;
   
	@Override
	public Integer updateUserPriceAndOtherInfo(ExpDecorateUser entity)throws Exception {
		// TODO Auto-generated method stub
		//待收益金额，减去本次的返现金额，等于剩余待收益金额
		ExpDecorateUser useEntity=this.findExpDecorateUserById(entity);
		//金额格式
		DecimalFormat df= new DecimalFormat("######0.00");	
		//本次返现金额
		double backPrice=Double.parseDouble(entity.getBackPrice());	
		//待收益金额 (此待收益金额，是受邀用户购买券时增加的待收益佣金金额，因此这里需要将这笔金额从待收益里去掉)
//		double sleepPrice=Double.parseDouble(useEntity.getSleepPrice());
//		double lastSleepPrice=Utils.subUserPrice(backPrice, sleepPrice);
//		String saveSleepPirce=df.format(lastSleepPrice);//最终入库
//		useEntity.setSleepPrice(saveSleepPirce);				
		//增加可提现余额
		double balancePrice=Double.parseDouble(useEntity.getBalancePrice());
		double lastBalancePrice=Utils.addUserPrice(backPrice, balancePrice);
		String saveBalancePrice=df.format(lastBalancePrice);//最终入库
		useEntity.setBalancePrice(saveBalancePrice);
		//增加总收益余额
		double countPrice=Double.parseDouble(useEntity.getCountPrice());
		double lastCountPrice=Utils.addUserPrice(backPrice, countPrice);
		String saveCountPrice=df.format(lastCountPrice);//最终入库
		useEntity.setCountPrice(saveCountPrice);
		return ExpDecorateUserMapper.updateUserPriceAndOtherInfo(useEntity);
	}
	
    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateExpDecorateUser(ExpDecorateUser entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateUserMapper.updateExpDecorateUser(entity);
                if(result>0){                	
                	//获取推荐返现佣金,推荐人openid信息
                	ExpDecorateUser userEnv=this.findExpDecorateUserById(entity);
                	if(StringUtils.isNotEmpty(userEnv.getUseOpenid())){
                		String useOpenid=userEnv.getUseOpenid();
                		String usePrice=entity.getBackPrice();           		
                		//1添加消息给推荐人，在推荐人的我的消息查看
                		DecorateMessage message=new DecorateMessage();
                		message.setContent("您推荐的用户"+userEnv.getUserName()+"已成功签单,您获得佣金"+usePrice+"(元)");
                		message.setOpenid(useOpenid);
                		message.setSendModuleName("用户消券");
                		message.setSendEntity(0);
                		decorateMessageService.addDecorateMessage(message);
                		//2为推荐人的金额变动做记录
                		DecorateRecord record=new DecorateRecord();
                		record.setAcceptOpenid(userEnv.getOpenid());//受邀人 （自己）
                		record.setCommissionPrice(usePrice);
                		record.setMessage("受邀人"+userEnv.getNickName()+"已成功签单,返佣金");         
                		record.setPublishOpenid(userEnv.getUseOpenid());//分享着 (我的推荐人)
                		//TODO 这里的状态可能需要重构下
                		record.setState(0);//0 发起人，1受邀人
                		record.setType(2);//收益类型:0分享，1:购买福券返佣金，2签单奖励
                		record.setPriceState(0);//券状态 0:已收益 1:待收益
                		decorateRecordService.addDecorateRecord(record);
                		//3变动推荐人金额
                		ExpDecorateUser user=new ExpDecorateUser();
                		user.setOpenid(useOpenid);
                		user.setBackPrice(usePrice);
                		this.updateUserPriceAndOtherInfo(user);
                	}
                }else{
                	log.debug("签单失败...");
                }
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateUser信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

  
    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUser> findExpDecorateUser(ExpDecorateUser entity){
        
        
        return ExpDecorateUserMapper.findExpDecorateUser(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUser> findExpDecorateUserByPage(ExpDecorateUser entity){
        
        
        return ExpDecorateUserMapper.findExpDecorateUserByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateUser findExpDecorateUserById(ExpDecorateUser entity){
        
        List<ExpDecorateUser> list=ExpDecorateUserMapper.findExpDecorateUser(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }

}
