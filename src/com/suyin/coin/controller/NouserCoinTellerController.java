package com.suyin.coin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.coin.model.CoinLog;
import com.suyin.coin.model.NouserCoinTeller;
import com.suyin.coin.service.NouserCoinTellerService;
import com.suyin.system.model.Page;

/**
 * 金币相关流程操作
 * 
 * @version 2015-10-15
 * @see NouserCoinTellerController
 * @since
 */
@Controller
@RequestMapping("/nousercointeller")
public class NouserCoinTellerController {

	@Autowired
	private NouserCoinTellerService nouserCoinTellerService;
	// 重定向地址

	private Logger log = Logger.getLogger(NouserCoinTellerController.class);

	/**
	 * 首页
	 * 
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("coin/index");
	}

	/**
	 * 读取列表
	 * 
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody Map<String, Object> synNouserCoinTellerList(
			HttpServletRequest request) {
		ModelMap map = new ModelMap();

		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		NouserCoinTeller entityInfo = new NouserCoinTeller();
		entityInfo.setPage(page);
		if (StringUtils.isNotBlank(request.getParameter("status")))
			entityInfo.setStatus(Integer.parseInt(request
					.getParameter("status")));
		List<NouserCoinTeller> list = nouserCoinTellerService
				.findNouserCoinTellerByPage(entityInfo);
		map.put("rows", list);
		map.put("total", entityInfo.getPage().getTotalResult());
		return map;
	}

	/**
	 * 查询用户金币的变化数量
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryGoldenList")
	public @ResponseBody Map<String, Object> queryGoldenList(
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
		
		CoinLog log = new CoinLog();
		log.setUserId(userId);
		log.setPage(page);
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = null;
		
		for (CoinLog coinLog : nouserCoinTellerService
				.findCoinLogByUserByPage(log)) {
			resultMap = new HashMap<String, String>();
			String mark = coinLog.getDirection();
			if (StringUtils.isNotBlank(mark)) {
				// 增加了金币是1，减少金币是2
				if ("1".equals(mark)) {
					mark = "+";
				} else if ("2".equals(mark)) {
					mark = "-";
				}
			}
			resultMap.put("desc", coinLog.getContent());
			resultMap.put("num", mark + coinLog.getGoldCoin());
			mapList.add(resultMap);
		}
		
		map.put("rows", mapList);
		map.put("total", log.getPage().getTotalResult());
		
		return map;
	}

	/**
	 * 信息修改 Description: <br>
	 * 
	 * @param
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody Map<String, Object> updateNouserCoinTeller(
			HttpServletRequest request) {
		ModelMap map = new ModelMap();
		Map<String, String> entity = new HashMap<String, String>();
		entity.put("ids", request.getParameter("ids"));
		entity.put("status", request.getParameter("status"));
		map.put("result",
				nouserCoinTellerService.updateNouserCoinTeller(entity));
		return map;
	}

	/**
	 * 金币提现记录列表页面跳转
	 * 
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/toFindCoinView")
	public ModelAndView toFindCoinView(HttpServletRequest request) {
		ModelMap model = new ModelMap();

		return new ModelAndView("coin/coinIndex", model);
	}

	/**
	 * 查询金币提取成功累积记录
	 * 
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/coinTellerRecordList")
	public @ResponseBody Map<String, Object> coinTellerRecordList(
			HttpServletRequest request) {
		ModelMap model = new ModelMap();
		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		NouserCoinTeller entity = new NouserCoinTeller();
		entity.setPage(page);
		if (StringUtils.isNotBlank(request.getParameter("userPhone"))) {
			entity.setUserPhone(request.getParameter("userPhone"));
		}

		List<NouserCoinTeller> list = nouserCoinTellerService
				.coinTellerRecordList(entity);
		model.put("rows", list);
		model.put("total", entity.getPage().getTotalResult());
		return model;
	}

}
