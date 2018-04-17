
package com.suyin.cash.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.cash.model.CoinCashLog;
import com.suyin.cash.model.NouserCashTeller;
import com.suyin.cash.service.NouserCashLogService;
import com.suyin.coin.model.CoinLog;
import com.suyin.common.ExcelHelper;
import com.suyin.common.ReportToExcel;
import com.suyin.common.SystemPropertiesHolder;
import com.suyin.system.model.Page;


/**
 * 
 * 支付宝提现相关流程操作
 * @version 2015-10-28
 * @see NouserCashLogController
 * @since
 */
@Controller
@RequestMapping("/nousercashlog")
public class NouserCashLogController{


    @Autowired
    private NouserCashLogService nouserCashLogService;

    @SuppressWarnings("unused")
    private Logger log=Logger.getLogger(NouserCashLogController.class);
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("cash/index");
    }

    
    /**
     * 
     * 修改用户支付宝账号
     * @return 
     * @see
     */
    @RequestMapping(value="/updateNum")
    public @ResponseBody ModelMap updateNum(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String userId=request.getParameter("userId");
        String aliNum=request.getParameter("seq");
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("aliNum", aliNum);
        Integer n=nouserCashLogService.updateNum(map);
        if(n>0){
            model.put("message", "1");
        }else{
            model.put("message", "2");
        }
        return model;

    }
    
    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> synNouserCashLogList(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }

        NouserCashTeller  entityInfo=new NouserCashTeller ();
        entityInfo.setPage(page);
        if(StringUtils.isNotBlank(request.getParameter("status")))
            entityInfo.setStatus(request.getParameter("status"));
        List<Map<String,String> > list=nouserCashLogService.findNouserCashLogByPage(entityInfo);
        map.put("rows",list); 
        map.put("total",entityInfo.getPage().getTotalResult()); 
        return map;
    }


    /**
     * 信息修改
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> updateNouserCashLog(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Map<String,String> entity=new HashMap<String, String>();
        entity.put("ids", request.getParameter("ids"));
        entity.put("status", request.getParameter("status"));
        map.put("result",nouserCashLogService.updateNouserCashLog(entity));
        return map;
    }

    @RequestMapping("/excel")
    public void downExcelThroughPage(NouserCashTeller entity,HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");  
        List<Map<String,String>> list=null;
        if("y".equalsIgnoreCase(request.getParameter("isAll"))) {
            list=this.nouserCashLogService.findNouserCashLog(entity);
        }else
            list=this.nouserCashLogService.findNouserCashLogByPage(entity);

        Map<String,String> mapper=new HashMap<String, String>();
        mapper.put("ali", "支付宝");
        mapper.put("money", "金额");
        mapper.put("logId", "id");
        new ExcelHelper().writeXLS(response.getOutputStream(), list, mapper, new String[] {"ali","money","logId"});
    }
    @RequestMapping("/dExcel")
    public void downExcelThrough(NouserCashTeller entity,HttpServletRequest request,HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");  

        List<Map<String,String>> list=null;
        if("y".equalsIgnoreCase(request.getParameter("isAll"))) {
            list=this.nouserCashLogService.findNouserCashLog(entity);
        }else
            list=this.nouserCashLogService.findNouserCashLogByPage(entity);
        BigDecimal sum=new BigDecimal(0);
        for(Map<String,String> map : list) {
            sum=sum.add(new BigDecimal(map.get("money")));
        }
        ReportToExcel.createDataExcel(response.getOutputStream(), 
            "全名赚订单", 
            //"分账报表", 
            new String[] {"商户流水号", "收款人email", "收款人姓名", "付款金额（元）", "付款理由","状态"}, 
            null, 
            list, 
            new String[] {"logId","ali","name","money","reason","paystatus"}, 
            new String[] {"批次号", "付款日期", "付款人email", "账户名称",  "总金额（元）", "总笔数"}, 
            new String[] {new SimpleDateFormat("yyyyMMddHHmm").format(new Date()),
            new SimpleDateFormat("yyyyMMdd").format(new Date()),
            SystemPropertiesHolder.get("email"),
            SystemPropertiesHolder.get("company"),
            String.valueOf(sum),
            String.valueOf(list.size())}, 1);

    }

    /**
     * 记录页面跳转
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/cashToIndex")
    public ModelAndView cashToIndex(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("cash/cashIndex",model);
    }


    /**
     * 查询钱包提现成功累积记录
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/cashTellerRecordList")
    public @ResponseBody Map<String,Object> cashTellerRecordList(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }

        NouserCashTeller  entityInfo=new NouserCashTeller ();
        entityInfo.setPage(page);
        if(StringUtils.isNotBlank(request.getParameter("userPhone"))){
            entityInfo.setUserPhone(request.getParameter("userPhone")); 
        }

        List<Map<String,Object>> list=nouserCashLogService.cashTellerRecordList(entityInfo);
        model.put("rows",list); 
        model.put("total",entityInfo.getPage().getTotalResult()); 
        return model;
    }
    
    /**
	 * 查询用户金钱的变化数量
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMoneyList")
	public @ResponseBody Map<String, Object> queryMoneyList(
			HttpServletRequest request) {
		ModelMap map = new ModelMap();
		
		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		int userId = request.getParameter("userId") == null ? 0 : Integer
				.parseInt(request.getParameter("userId"));
		
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		
		CoinCashLog log = new CoinCashLog();
		log.setUserId(userId);
		log.setPage(page);
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = null;
		
		for (CoinCashLog coinCashLog : nouserCashLogService
				.queryCoinCashLogByPage(log)) {
			resultMap = new HashMap<String, String>();
			String mark = coinCashLog.getDirection();
			if (StringUtils.isNotBlank(mark)) {
				// 增加了钱币是1，减少钱币是2
				if ("1".equals(mark)) {
					mark = "+";
				} else if ("2".equals(mark)) {
					mark = "-";
				}
			}
			resultMap.put("desc", coinCashLog.getContent());
			resultMap.put("num", mark + coinCashLog.getMoney());
			mapList.add(resultMap);
		}
		
		map.put("rows", mapList);
		map.put("total", log.getPage().getTotalResult());
		
		return map;
	}

}

