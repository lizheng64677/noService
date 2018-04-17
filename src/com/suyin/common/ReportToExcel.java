package com.suyin.common;


import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * excel导出工具类
 * @author yiqifendou
 *
 */
public class ReportToExcel
{
    /**
     * 将list集合中的数据写入到excel中
     * 
     * @param response  httpservletresponse对象
     * @param sheetName 第一个sheet表的名字
     * @param title		标题名
     * @param th		th头包含哪些字段（字符串数组类型）
     * @param columnWidths int[]类型数组，指定每一列的宽度
     * @param dataList		list数据集合
     * @param classFullName	list集合中的泛型对象
     * @param Methods		依次调用泛型对象的哪些方法来和th表头对应，注意：实现为反射，调用getxxx方法
     * @param fileName		下载的文件名字
     * @param th2		        导出数据前自定义包含哪些数据头部（字符串数组类型）
     * @param array		        导出数据前自定义包含哪些数据（字符串数组类型）
     * @param decision		决定是否自己自定义一部分数据（1：是2：否）
     * @throws Exception
     */
    public static void createDataExcel(HttpServletResponse response, String sheetName,
                                       String title, String[] th, int[] columnWidths,
                                       List<Map<String,String>> dataList, String[] fields,
                                       String fileName, String[] th2, String[] array,
                                       Integer decision) {

    }

    public static void createDataExcel(OutputStream out, String sheetName,
                                       String[] th, int[] columnWidths,
                                       List<Map<String,String>> dataList, String[] fields,
                                       String[] th2, String[] array,
                                       Integer decision)throws Exception{

        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet(sheetName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置每一列的宽度
        if (columnWidths != null)
        {
            for (int columnIndex = 0; columnIndex < columnWidths.length; columnIndex++ )
            {
                sheet.setColumnWidth(columnIndex, columnWidths[columnIndex]);
            }
        }
        else
        {
            for (int columnIndex = 0; columnIndex < th.length; columnIndex++ )
            {
                sheet.setColumnWidth(columnIndex, 4000);
            }
        }
        HSSFFont titleFont = workBook.createFont();
        titleFont.setFontHeightInPoints((short)15);
        titleFont.setColor(HSSFColor.BLUE.index);
        titleFont.setFontName("宋体");
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 设置excel表头
        HSSFRow secondRow = sheet.createRow(2);
        //设置自定义部分数据
        HSSFCellStyle thStyle = workBook.createCellStyle();
        HSSFFont thFont = workBook.createFont();
        thFont.setFontHeightInPoints((short)12);
        thFont.setFontName("宋体");
        thFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        thStyle.setFont(thFont);
        // 设置excel正文普通列样式
        HSSFCellStyle bodyStyle = workBook.createCellStyle();
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        //设置excel正文强调列的样式
        HSSFCellStyle strongBodyStyle = workBook.createCellStyle();
        strongBodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        HSSFFont strongBodyFont = workBook.createFont();
        strongBodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        strongBodyFont.setColor(HSSFColor.RED.index);
        strongBodyStyle.setFont(strongBodyFont);

        //判断是否自定义插入数据
        if (decision == 1)
        {
            HSSFRow row = sheet.createRow(0);
            HSSFRow row2 = sheet.createRow(1);
            for (int r = 0; r < th2.length; r++ )
            {
                HSSFCell cell2 = row.createCell(r);
                cell2.setCellStyle(thStyle);
                cell2.setCellValue(th2[r]);
            }
            for (int k = 0; k < array.length; k++ )
            {
                HSSFCell cell = row2.createCell(k);
                cell.setCellValue(array[k]);
            }
        }

        for (int i = 0; i < th.length; i++ )
        {
            HSSFCell cell = secondRow.createCell(i);
            cell.setCellStyle(thStyle);
            cell.setCellValue(th[i]);
        }
        Map<String,String> mk=null;
        for (int j = 3; j < dataList.size() + 3; j++ )
        {
            HSSFRow bodyRow = sheet.createRow(j);
            bodyRow.setHeightInPoints(20);
            for (int m = 0; m < th.length; m++ )
            {
                mk=dataList.get(j-3);
                Object value =mk.get(fields[m]);
                HSSFCell bodyCell = bodyRow.createCell(m);
                bodyCell.setCellStyle(bodyStyle);
                if("reason".equals(fields[m]))
                    bodyCell.setCellValue("提现");
                else
                    bodyCell.setCellValue(String.valueOf(value));
            }
        }
        workBook.write(out);
        out.flush();
        out.close();
    }




    /**
     * 
     * 通用模块导出
     * 
     * @param out
     * @param sheetName
     * @param th
     * @param columnWidths
     * @param dataList
     * @param fields
     * @param th2
     * @param array
     * @param decision
     * @throws Exception 
     * @see
     */
    public static void createDataExcela(OutputStream out, String sheetName,
                                        String[] th, int[] columnWidths,
                                        List<Map<String,Object>> dataList, String[] fields)throws Exception{

        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet(sheetName);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置每一列的宽度
        if (columnWidths != null)
        {
            for (int columnIndex = 0; columnIndex < columnWidths.length; columnIndex++ )
            {
                sheet.setColumnWidth(columnIndex, columnWidths[columnIndex]);
            }
        }
        else
        {
            for (int columnIndex = 0; columnIndex < th.length; columnIndex++ )
            {
                sheet.setColumnWidth(columnIndex, 4000);
            }
        }
        HSSFFont titleFont = workBook.createFont();
        titleFont.setFontHeightInPoints((short)15);
        titleFont.setColor(HSSFColor.BLUE.index);
        titleFont.setFontName("宋体");
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 设置excel表头
        HSSFRow secondRow = sheet.createRow(1);
        //设置自定义部分数据
        HSSFCellStyle thStyle = workBook.createCellStyle();
        HSSFFont thFont = workBook.createFont();
        thFont.setFontHeightInPoints((short)12);
        thFont.setFontName("宋体");
        thFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        thStyle.setFont(thFont);
        // 设置excel正文普通列样式
        HSSFCellStyle bodyStyle = workBook.createCellStyle();
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        //设置excel正文强调列的样式
        HSSFCellStyle strongBodyStyle = workBook.createCellStyle();
        strongBodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        HSSFFont strongBodyFont = workBook.createFont();
        strongBodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        strongBodyFont.setColor(HSSFColor.RED.index);
        strongBodyStyle.setFont(strongBodyFont);
        

        for (int i = 0; i < th.length; i++ )
        {
            HSSFCell cell = secondRow.createCell(i);
            cell.setCellStyle(thStyle);
            cell.setCellValue(th[i]);
        }
        Map<String,Object> mk=null;
        for (int j = 2; j < dataList.size() + 2; j++ )
        {
            HSSFRow bodyRow = sheet.createRow(j);
            bodyRow.setHeightInPoints(20);
            for (int m = 0; m < th.length; m++ )
            {
                mk=dataList.get(j-2);
                Object value =mk.get(fields[m]);
                HSSFCell bodyCell = bodyRow.createCell(m);
                bodyCell.setCellStyle(bodyStyle);
                bodyCell.setCellValue(String.valueOf(value));
            }
        }
        workBook.write(out);
        out.flush();
        out.close();
    }
}
