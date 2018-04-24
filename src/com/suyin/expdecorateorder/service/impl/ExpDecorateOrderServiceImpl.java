package com.suyin.expdecorateorder.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.suyin.expdecorateorder.mapper.ExpDecorateOrderMapper;
import com.suyin.expdecorateorder.model.DecorateOrderDTO;
import com.suyin.expdecorateorder.model.ExpDecorateOrder;
import com.suyin.expdecorateorder.service.ExpDecorateOrderService;
import com.suyin.system.model.LoginUser;



@Transactional
@Service("ExpDecorateOrderService")
public class ExpDecorateOrderServiceImpl implements ExpDecorateOrderService{

    private final static Logger log=Logger.getLogger(ExpDecorateOrderServiceImpl.class);
    
    @Autowired
    private ExpDecorateOrderMapper ExpDecorateOrderMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDecorateOrder(ExpDecorateOrder entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDecorateOrderMapper.addExpDecorateOrder(entity);
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
    public Integer updateExpDecorateOrder(ExpDecorateOrder entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateOrderMapper.updateExpDecorateOrder(entity);
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateOrder信息修改异常"+e.getMessage());
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
    public Integer deleteExpDecorateOrder(String id){
        
        
        return ExpDecorateOrderMapper.deleteExpDecorateOrder(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateOrder> findExpDecorateOrder(ExpDecorateOrder entity){
        
        
        return ExpDecorateOrderMapper.findExpDecorateOrder(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<DecorateOrderDTO> findExpDecorateOrderByPage(ExpDecorateOrder entity){
        
        
        return ExpDecorateOrderMapper.findExpDecorateOrderByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateOrder findExpDecorateOrderById(ExpDecorateOrder entity){
        
        List<ExpDecorateOrder> list=ExpDecorateOrderMapper.findExpDecorateOrder(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }

	@Override
	public Integer reviewExpDecorateOrderById(ExpDecorateOrder entity) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		entity.setReviewTime(dateFormater.format(date));
		ExpDecorateOrder expDecorateOrder = this.ExpDecorateOrderMapper.findExpDecorateOrderByOrderId(entity.getOrderId().toString());
		//审核如果不通过将扣掉的钱返回用户钱包
		if(2 == entity.getState()){
			this.ExpDecorateOrderMapper.updateUserBalancePriceByOpenId(expDecorateOrder);
		}	
		//保存审核信息
		Integer result = this.ExpDecorateOrderMapper.reviewExpDecorateOrderById(entity);
		return result;
	}
}
