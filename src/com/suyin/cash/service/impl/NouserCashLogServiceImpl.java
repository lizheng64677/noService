package com.suyin.cash.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.cash.mapper.NouserCashLogMapper;
import com.suyin.cash.model.CoinCashLog;
import com.suyin.cash.model.NouserCashTeller;
import com.suyin.cash.service.NouserCashLogService;
import com.suyin.system.mapper.AttachmentMapper;



@Transactional
@Service("NouserCashLogService")
public class NouserCashLogServiceImpl implements NouserCashLogService{

    private final static Logger log=Logger.getLogger(NouserCashLogServiceImpl.class);

    @Autowired
    private NouserCashLogMapper NouserCashLogMapper; 


    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addNouserCashLog(NouserCashTeller entity){
        Integer result=0;
        try {
            if(entity==null){
                return result;
            }else{
                result = NouserCashLogMapper.addNouserCashLog(entity);
            }
        } catch (Exception e) {
            log.error("NouserCashLog信息修改异常"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public Integer updateNouserCashLog(Map<String,String> entity){
        //先该t_nouser_cash_log的状态     
        String status=entity.get("status");
        if("1".equals(status)){

            entity.put("direction", "2");
            entity.put("content", "提现");//记录减少
            entity.put("log_status", "0");
        }else{

            entity.put("direction", "1");
            entity.put("content", "被驳回");//记录添加
            entity.put("log_status", "2");
        }
        //更改log记录信息
        Integer n=  this.NouserCashLogMapper.updataNouserCashLogoInfo(entity);
        if(n>0){
            //更改用户金钱记录状态
            this.NouserCashLogMapper.updateNouserCashLogStatus(entity);
            //再该钱
            this.NouserCashLogMapper.updateNouserCash(entity);    
            
        }

        return 1;
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Map<String,String>> findNouserCashLog(NouserCashTeller entity){


        return NouserCashLogMapper.findNouserCashLogList(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Map<String,String>> findNouserCashLogByPage(NouserCashTeller entity){


        return NouserCashLogMapper.findNouserCashLogByPage(entity);
    }

    /* (non-Javadoc)
     * @see com.suyin.cash.service.NouserCashLogService#cashTellerRecordList(com.suyin.cash.model.NouserCashTeller)
     */
    @Override
    public List<Map<String, Object>> cashTellerRecordList(NouserCashTeller entity)
    {
        // TODO Auto-generated method stub
        return NouserCashLogMapper.cashTellerRecordList(entity);
    }

	@Override
	public List<CoinCashLog> queryCoinCashLogByPage(CoinCashLog coinCashLog) {
		return NouserCashLogMapper.queryCoinCashLogByPage(coinCashLog);
	}

	@Override
	public Integer updateNum(Map<String, Object> map) {
		NouserCashLogMapper.updateNumLog(map);
		return NouserCashLogMapper.updateNum(map);
	}

}
