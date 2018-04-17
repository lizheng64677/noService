
package com.suyin.expzhuan.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.expzhuan.model.ExpTaskOrder;
import com.suyin.expzhuan.service.ExpTaskOrderService;
import com.suyin.system.model.Page;


/**
 * 全民赚活动的订单处理
 * @see ExpTaskOrderController
 * @since
 */
@Controller
@RequestMapping("/exptaskorder")
public class ExpTaskOrderController{


    @Autowired
    private ExpTaskOrderService expTaskOrderService;

    private Logger log=Logger.getLogger(ExpTaskOrderController.class);

    private static SimpleDateFormat format =new SimpleDateFormat("yyyy年MM月dd日 ");

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("exptask/order/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> synExpTaskOrderList(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }

        ExpTaskOrder  entityInfo=new ExpTaskOrder ();
        entityInfo.setPage(page);

        if(StringUtils.isNotBlank(request.getParameter("expId")))
            entityInfo.setExpId(Integer.parseInt(request.getParameter("expId")));
        if(StringUtils.isNotBlank(request.getParameter("status")))
            entityInfo.setStatus(Integer.parseInt(request.getParameter("status")));
        if(StringUtils.isNotBlank(request.getParameter("showType")))
            entityInfo.setShowType(Integer.parseInt(request.getParameter("showType")));
        List<ExpTaskOrder > list=expTaskOrderService.findExpTaskOrderByPage(entityInfo);
        map.put("rows",list); 
        map.put("total",entityInfo.getPage().getTotalResult()); 
        return map;
    }


    @RequestMapping(value = "/getById")
    public @ResponseBody ModelMap getById(ExpTaskOrder order, HttpServletRequest request) {
        ModelMap map=new ModelMap();
        if("0".equals(request.getParameter("showType"))) 
            map.put("rows", this.expTaskOrderService.findExpAppById(order));
        else if("1".equals(request.getParameter("showType")))
            map.put("rows", this.expTaskOrderService.findExpFormById(order));
        return map;
    }





    /**
     * 
     * @param ids 需要修改的顶点的id，多个id之间用逗号分隔
     * @param status 2表示同意，3表示拒绝
     * @param request
     * @return
     */
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> updateExpTaskOrder(@RequestParam("ids") String ids,@RequestParam("status") String status, HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try {
            this.expTaskOrderService.updateExpTaskOrder(ids,status);
            map.put("error", "0");
        } catch (Exception e) {
            map.put("error", "1");
        }
        return map;
    }

    @RequestMapping(value = "/findAllExpA")
    public @ResponseBody List<Map<String,String>> findAllExpA(){
        return this.expTaskOrderService.findAllExpTaskA();
    }

    /**
     * 全民赚订单数据导出
     * @param entity
     * @param request
     * @param response
     * @throws Exception 
     * @see
     * @author gyx
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/dExcel")
    public void downExcelThrough(ExpTaskOrder entity,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        try
        {
            String status = request.getParameter("statu");
            entity.setStatus(Integer.parseInt(status));
            List<Map<String,Object>> list=null;
            if("y".equalsIgnoreCase(request.getParameter("isAll"))) list=this.expTaskOrderService.findNouserCoinTeller(entity);
            else  list=this.expTaskOrderService.findNouserCoinTeller(entity);
            if(null == list || list.isEmpty())
            {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                String html = "<script language='javascript'> alert('没有要导出的数据!');history.back();</script>";
                response.getWriter().write(html);
                return;
            }

            if("0".equals(status)) status =  "新参与";
            else if("1".equals(status)) status = "已提交";
            else if("2".equals(status)) status = "已同意";
            else if("3".equals(status)) status = "已拒绝";
            else status = "未知状态";

            Integer show_type = entity.getShowType();
            String showType = "未知类型";
            if(show_type == 0) showType =  "APP下载";
            else if(show_type == 1) showType =  "表单问答";

            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet();

            String[] title={"手机号码","活动标题","类型","状态","参与时间","提交时间"};
            HSSFRow row=sheet.createRow(0);
            HSSFCell cell=null;
            for(int i=0;i<title.length;i++)
            {
                cell=row.createCell((short)i);
                cell.setCellValue(title[i]);
            }
            for(int i=0;i<list.size();i++)
            {
                row=sheet.createRow(i+1);
                cell=row.createCell((short)0);
                if(list.get(i).containsKey("userPhone")) cell.setCellValue(new HSSFRichTextString(list.get(i).get("userPhone").toString()));
                else cell.setCellValue(new HSSFRichTextString(""));
                cell=row.createCell((short)1);
                if(list.get(i).containsKey("title")) cell.setCellValue(new HSSFRichTextString(list.get(i).get("title").toString()));
                else cell.setCellValue(new HSSFRichTextString(list.get(i).get("title").toString()));
                cell=row.createCell((short)2);
                if("0".equals(list.get(i).get("show_type").toString()))
                	cell.setCellValue(new HSSFRichTextString("APP下载"));
                else if("1".equals(list.get(i).get("show_type").toString()))
                	cell.setCellValue(new HSSFRichTextString("表单问答"));
                cell=row.createCell((short)3);
                if("0".equals(list.get(i).get("status").toString()))
                	cell.setCellValue(new HSSFRichTextString("新参与"));
                else if("1".equals(list.get(i).get("status").toString()))
                	cell.setCellValue(new HSSFRichTextString("已提交"));
                else if("2".equals(list.get(i).get("status").toString()))
                	cell.setCellValue(new HSSFRichTextString("已同意"));
                else if("3".equals(list.get(i).get("status").toString()))
                	cell.setCellValue(new HSSFRichTextString("已拒绝"));
                else
                	cell.setCellValue(new HSSFRichTextString("未知状态"));
                cell=row.createCell((short)4);
                if(list.get(i).containsKey("create_time")) cell.setCellValue(new HSSFRichTextString(list.get(i).get("create_time").toString()));
                else cell.setCellValue(new HSSFRichTextString(""));
                cell=row.createCell((short)5);
                if(list.get(i).containsKey("update_time")) cell.setCellValue(new HSSFRichTextString(list.get(i).get("update_time").toString()));
                else cell.setCellValue(new HSSFRichTextString(""));
            }
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            String name = "全民赚订单_" + format.format(new Date());
            byte[] b_gbk = name.getBytes("gb2312");
            String fileName = new String(b_gbk, "ISO8859-1");
            response.setHeader("Content-disposition", "attachment; filename="+ fileName + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            wb.write(os);
            wb.close();
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            String html = "<script language='javascript'> alert('没有要导出的数据!');history.back();</script>";
            response.getWriter().write(html);
            log.error("Controller Error -> doExcelRecord" + e.getMessage());
        }
    }
}

