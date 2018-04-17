package com.suyin.coin.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.coin.mapper.NouserCoinTellerMapper;
import com.suyin.coin.model.CoinLog;
import com.suyin.coin.model.NouserCoinTeller;
import com.suyin.coin.service.NouserCoinTellerService;



@Transactional
@Service("NouserCoinTellerService")
public class NouserCoinTellerServiceImpl implements NouserCoinTellerService{

    private final static Logger log = Logger.getLogger(NouserCoinTellerServiceImpl.class);

    @Autowired
    private NouserCoinTellerMapper NouserCoinTellerMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addNouserCoinTeller(NouserCoinTeller entity){
        Integer result=0;
        return result;

    }


    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteNouserCoinTeller(String id){
        return NouserCoinTellerMapper.deleteNouserCoinTeller(id);
    }

    /**
     * 批量删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteNouserCoinTellerByBatch(String[] ids){
        return NouserCoinTellerMapper.deleteNouserCoinTellerByBatch(ids);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<NouserCoinTeller> findNouserCoinTeller(NouserCoinTeller entity){


        return NouserCoinTellerMapper.findNouserCoinTellerList(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<NouserCoinTeller> findNouserCoinTellerByPage(NouserCoinTeller entity){


        return NouserCoinTellerMapper.findNouserCoinTellerByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public NouserCoinTeller findNouserCoinTellerById(NouserCoinTeller entity){
        return NouserCoinTellerMapper.findNouserCoinTellerById(entity);
    }

    @Override
    public Integer updateNouserCoinTeller(Map<String, String> entity) {
        String status=entity.get("status");
        if("1".equals(status)){

            entity.put("direction", "2");
            entity.put("content", "提取金币");//记录减少
            entity.put("log_status", "0");
        }else{

            entity.put("direction", "1");
            entity.put("content", "被驳回");//记录添加
            entity.put("log_status", "2");
        }

        //修改t_nouser_coin_teller的状态
        this.NouserCoinTellerMapper.updateNouserCoinTellerStatus(entity);
        //修改金币 frozen_gold_coin
        this.NouserCoinTellerMapper.updateNouserCoinOrMoney(entity);
        //同意_成功的话添加t_nouser_cash_log记录 
        if("1".equals(entity.get("status"))) {
            this.NouserCoinTellerMapper.addCashLog(entity);

        }

        this.NouserCoinTellerMapper.updateCoinLog(entity);

        return 1;
    }


    /**
     * 查询金币累积提现记录
     */
    @Override
    public List<NouserCoinTeller> coinTellerRecordList(NouserCoinTeller entity)
    {
        // TODO Auto-generated method stub
        return this.NouserCoinTellerMapper.coinTellerRecordList(entity);
    }
    
    @Override
	public List<CoinLog> findCoinLogByUserByPage(
			CoinLog condition) {
		return this.NouserCoinTellerMapper.findCoinLogByUserByPage(condition);
	}
}
