package com.suyin.expzhuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.expzhuan.mapper.ExpTaskOrderMapper;
import com.suyin.expzhuan.model.ExpTaskOrder;
import com.suyin.expzhuan.service.ExpTaskOrderService;



@Transactional
@Service("ExpTaskOrderService")
public class ExpTaskOrderServiceImpl implements ExpTaskOrderService{

    @SuppressWarnings("unused")
    private final static Logger log=Logger.getLogger(ExpTaskOrderServiceImpl.class);

    @Autowired
    private ExpTaskOrderMapper expTaskOrderMapper; 


    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpTaskOrder> findExpTaskOrderByPage(ExpTaskOrder entity){
        return expTaskOrderMapper.findExpTaskOrderByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpTaskOrder findExpTaskOrderById(ExpTaskOrder entity){
        return expTaskOrderMapper.findExpTaskOrderById(entity);
    }

    /**
     * 批量同意或者拒绝全民赚的订单,同意的话有有四个步骤
     * 1.修改订单状态
     * 2.给用户加钱
     * 3.修改订单中的“已获得的金币数”
     * 4.给用户加上金币获得的log
     * 
     * 拒绝的话只要修改订单状态就好了
     */
    @Override
    @Transactional
    public void updateExpTaskOrder(String ids, String status) {
        Map<String,String> condition=new HashMap<String, String>();
        condition.put("ids", ids);
        condition.put("status", status);
        //给用户发消息
        this.expTaskOrderMapper.addUserMessage(condition);
        //修改订单状态
        this.expTaskOrderMapper.updateExpTaskOrderStatus(condition);
        if("3".equals(status)) return;  //拒绝就改一下状态就返回

        /**以下部分先用for循环替代后期优化考虑**/
        String[] orderId=ids.split(",");
        for (int i = 0; i < orderId.length; i++ )
        {
            condition.put("orderId", orderId[i]);
            this.expTaskOrderMapper.updateUserCoins(condition);
        }
        /**以上部分先用for循环替代后期优化考虑**/

        //给用户价钱
        //给订单加钱
        this.expTaskOrderMapper.updateOrderCoin(condition);
        //给用户加log
        this.expTaskOrderMapper.addUserCoinLog(condition);

    }

    @Override
    public List<Map<String,String>> findAllExpTaskA(){
        List<Map<String,String>> list=this.expTaskOrderMapper.findAllExpTaskA();
        Map<String,String> map=new HashMap<String,String>();
        map.put("expId", "-1");
        map.put("title", "所有活动");
        list.add(0, map);
        return list;
    }

    @Override
    public List<Map<String,String>> findExpAppById(ExpTaskOrder order) {
        List<Map<String,String>> list=new ArrayList<Map<String,String>>	();
        list.add(this.expTaskOrderMapper.findExpAppById(order));
        return list;
    }

    @Override
    public List<Map<String,String>> findExpFormById(ExpTaskOrder order) {
        // TODO Auto-generated method stub
        return this.expTaskOrderMapper.findExpFormById(order);	
    }

    @Override
    public List<Map<String, Object>> findNouserCoinTeller(ExpTaskOrder entity) {

        return expTaskOrderMapper.findNouserCoinTeller(entity);
    }


}
