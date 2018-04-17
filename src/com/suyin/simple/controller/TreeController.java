package com.suyin.simple.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.simple.model.EasyUITree;
import com.suyin.simple.service.TreeService;
import com.suyin.system.model.Page;

@Controller
@RequestMapping("/tree")
public class TreeController {
	@Autowired
	TreeService treeService;

	
    @RequestMapping(value="")
    public ModelAndView index() {
        return new ModelAndView("simple/tree");
    }
    
    @RequestMapping(value="synTreeListByPage")
    public @ResponseBody Map<String, Object> synTreeListByPage(HttpServletRequest request) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		EasyUITree easyUITree=new EasyUITree();
		easyUITree.setParentId(Integer.parseInt(request.getParameter("parentId")));
		easyUITree.setPage(page);
		map.put("rows",treeService.findEasyUITreeSynByTreeIdByPage(easyUITree)); 
        map.put("total",easyUITree.getPage().getTotalResult());
    	return map ;
    }
    
    @RequestMapping(value="synTreeList")
    public @ResponseBody List<EasyUITree> synTreeList(HttpServletRequest request) {
    	EasyUITree easyUITree=new EasyUITree();
    	easyUITree.setParentId(Integer.parseInt(request.getParameter("parentId")));
    	return treeService.findEasyUITreeSynByTreeId(easyUITree);
    }
}
