
package com.suyin.expdecorateorder.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.expdecorateorder.model.DecorateOrderDTO;
import com.suyin.expdecorateorder.model.ExpDecorateOrder;
import com.suyin.expdecorateorder.service.ExpDecorateOrderService;
import com.suyin.system.model.LoginUser;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;


/**
 * 余额提现订单
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expdecorateorder")
public class ExpDecorateOrderController{

    private final static Logger log=Logger.getLogger(ExpDecorateOrderController.class);
    @Autowired
    private ExpDecorateOrderService expDecorateOrderService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("expdecorateorder/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDecorateOrderAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String type=request.getParameter("type");
        String text=request.getParameter("text");	
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            ExpDecorateOrder  entityInfo=new ExpDecorateOrder ();
            if(!"-1".equals(type)){
        		entityInfo.setSaerchType(1);
	        	if("0".equals(type)){
	            	if(!"".equals(text) && null!=text){
	            		entityInfo.setUserName(text);
	            	}
	        	}else if("1".equals(type)){
	        		if(!"".equals(text) && null!=text){
	        			entityInfo.setUserPhone(text);
	        		}
	        	}else if("2".equals(type)){
	        		if(!"".equals(text) && null!=text){
	        			entityInfo.setNickName(text);
	        		}
	        	}
	        }else{
	        	if(!"".equals(text) && null!=text){
	            	entityInfo.setText(text);
	        	}
	    		entityInfo.setSaerchType(-1);
	        }
            entityInfo.setPage(page);
            List<DecorateOrderDTO > list=expDecorateOrderService.findExpDecorateOrderByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> findExpDecorateOrderByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDecorateOrderAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("expdecorateorder/save",map);
    }
    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpReview")
    public ModelAndView jumpExpDecorateOrderReview(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ExpDecorateOrder entity=new ExpDecorateOrder();
                entity.setOrderId(Integer.parseInt(request.getParameter("id")));
                entity=expDecorateOrderService.findExpDecorateOrderById(entity);
                map.put("expdecorateorder",entity);

            }
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> jumpExpDecorateOrderReview  " + e.getMessage());
        }
        return new ModelAndView("expdecorateorder/review",map);
    }
 
    /**
     * 审批提交
     * @param entity
     * @return
     */
    @RequestMapping(value = "/review")
    public @ResponseBody Map<String, Object> reviewExpDecorateOrderById(HttpServletRequest request,ExpDecorateOrder entity) {
        ModelMap map=new ModelMap();
        try
        {     
    		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
    		entity.setReviewUser(loginUser.getLoginName());
            map.put("result",expDecorateOrderService.reviewExpDecorateOrderById(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> updateExpDecorateOrderById  " + e.getMessage());
        }
        return map;
    }
    
    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDecorateOrderEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ExpDecorateOrder entity=new ExpDecorateOrder();
                entity.setOrderId(Integer.parseInt(request.getParameter("id")));
                entity=expDecorateOrderService.findExpDecorateOrderById(entity);
                map.put("expdecorateorder",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDecorateOrderController-> jumpExpDecorateOrderEdit  " + e.getMessage());
        }
        return new ModelAndView("expdecorateorder/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpDecorateOrderInfo(ExpDecorateOrder entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",expDecorateOrderService.addExpDecorateOrder(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> saveExpDecorateOrderInfo " + e.getMessage());
        }
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
    public @ResponseBody Map<String, Object> updateExpDecorateOrderById(ExpDecorateOrder entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",expDecorateOrderService.updateExpDecorateOrder(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> updateExpDecorateOrderById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpDecorateOrderById(String id) {

        ModelMap map=new ModelMap();
        try
        {
        	String[] idArr = id.split(",");
       	 	for (String ids:idArr) {
       	 	map.put("result",expDecorateOrderService.deleteExpDecorateOrder(id));
           }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateOrderController-> deleteExpDecorateOrderById  " + e.getMessage());
        }
 
        return map;
    }


}

