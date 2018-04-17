package com.suyin.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 支持xls和xlsx的解析，
 * 提供三个读取的方法：readMultipartFile，readNormalFile，readInputStream
 * 两个生成方法:writeXLS，writeXLSX
 * @author Administrator
 *
 */
public class ExcelHelper {

    /**
     * 生成一个list每一项是一个map，该map的key是从参数mapper中读取的，value是对应的值。
     *  姓名         		年龄
     *  mike		10
     *  
     *  现在想把  姓名 这一列对应 name ,年龄 对应 age
     *  那么 mapper中就是  姓名=>name ; 年龄=> age
     * @param file
     * @param mapper 
     * @return	
     * @throws IOException 
     */
    public List<Map<String,String>> readMultipartFile(MultipartFile file,Map<String,String> mapper) throws IOException {
        if(file.getOriginalFilename().endsWith(".xls"))
            return readXLS(file.getInputStream(),mapper);
        else if(file.getOriginalFilename().endsWith(".xlsx"))
            return readXLSX(file.getInputStream(),mapper);
        return null;
    }
    public List<Map<String,String>> readNormalFile(File file,Map<String,String> mapper) throws FileNotFoundException{
        if(file.getName().endsWith(".xls"))
            return readXLS(new FileInputStream(file),mapper);
        else if(file.getName().endsWith(".xlsx"))
            return readXLSX(new FileInputStream(file),mapper);
        return null;
    }

    public List<Map<String,String>> readInputStream(InputStream file,String fileType,Map<String,String> mapper){
        if("xls".equalsIgnoreCase(fileType))
            return this.readXLS(file, mapper);
        else if("xlsx".equalsIgnoreCase(fileType))
            return this.readXLSX(file, mapper);
        return null;
    }


    /**
     * 输出 xls文件
     * @param out
     * @param result 是要写到excel中的结果集
     * @param mapper 就为了excel中第一行的标题，比如在map中key是name，对应的excel中的标题是“姓名”, 那么此mapper中存为 name=>姓名
     * @param titles 为了写入顺序，mapper是无序的，所有要这个来控制标题的写入顺序
     * @throws IOException
     */
    public void writeXLS(OutputStream out,List<Map<String,String>> result,Map<String,String> mapper,String[] titles) throws IOException {
        Workbook book=new HSSFWorkbook();
        book=this.write(book, result,mapper, titles);
        book.write(out);
        out.close();
        out.flush();
        book.close();
    }
    /**
     * 输出 xlsx文件
     * @param out
     * @param result 是要写到excel中的结果集
     * @param mapper 就为了excel中第一行的标题，比如在map中key是name，对应的excel中的标题是“姓名”, 那么此mapper中存为 name=>姓名
     * @param titles 为了写入顺序，mapper是无序的，所有要这个来控制标题的写入顺序
     * @throws IOException
     */
    public void writeXLSX(OutputStream out,List<Map<String,String>> result,Map<String,String> mapper,String[] titles) throws IOException {
        Workbook book=new XSSFWorkbook();
        book=this.write(book, result,mapper, titles);
        book.write(out);
        out.close();
        out.flush();
        book.close();
    }


    private Workbook write(Workbook book,List<Map<String,String>> result,Map<String,String> mapper,String[] titles){
        Sheet sheet = book.createSheet("sheet1");
        Row row=sheet.createRow(0);
        Cell cell=null;
        for(int i=0;i<titles.length;i++) {
            cell=row.createCell(i);
            cell.setCellValue(mapper.get(titles[i]));
        }
        for(int i=0;i<result.size();i++) {
            row=sheet.createRow(i+1);
            Map<String,String> map=result.get(i);
            for(int j=0;j<titles.length;j++) {
                cell=row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(map.get(titles[j]));
            }
        }
        return book;
    }




    private List<Map<String, String>> readXLS(InputStream file,Map<String,String> mapper) {
        try {

            return this.read(new HSSFWorkbook(file), mapper);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, String>> readXLSX(InputStream file,Map<String,String> mapper) {
        try {

            return this.read(new XSSFWorkbook(file), mapper);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, String>> read(Workbook book,Map<String,String> mapper) throws IOException{
        List<Map<String, String>> result=new ArrayList<Map<String,String>>();
        Sheet sheet=book.getSheetAt(0);
        //预处理第一行，获得每个cell的值,以便和mapper中的key对应起来
        Row row = sheet.getRow(0);
        Cell cell=null;
        String[] titles=new String[row.getLastCellNum()];
        for(int i=0;i<row.getLastCellNum();i++) {
            titles[i]=row.getCell(i).getStringCellValue().trim();
        }
        //处理数据
        for(int i=1;i<=sheet.getLastRowNum();i++) {
            row=sheet.getRow(i);
            Map<String,String> map=new HashMap<String, String>();
            for(int j=0;j<row.getLastCellNum();j++) {
                //只把mapper中有映射的添加到结果中
                if(mapper.get(titles[j])==null) continue;
                cell=row.getCell(j);
                map.put(mapper.get(titles[j]), this.getValue(cell));
            }
            if(map.size()>0 && !map.isEmpty()){
                result.add(map);
            }
        }
        book.close();
        return result;
    }

    /**
     * 类型转换
     * @param cell
     * @return 
     * @see
     */
    private String getValue(Cell cell) {
        
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }


    public static void main(String[] args) throws IOException {
        File f1=new File("d:\\2.xls");
        ExcelHelper h=new ExcelHelper();
        Map<String,String> mapper=new HashMap<String, String>();
        mapper.put("姓名", "name");
        mapper.put("年龄", "age");
        mapper.put("电话", "phone");
        List<Map<String, String>> result=h.readXLS(new FileInputStream(f1), mapper);
        System.out.println(result.toString());

        mapper=new HashMap<String, String>();
        mapper.put("name","姓名");
        mapper.put("age","年龄" );
        mapper.put("phone","电话");
        File f2=new File("d:\\3.xlsx");
        h.writeXLSX(new FileOutputStream(f2), result,mapper, new String[] {"name","age"});
    }

    //从excel表格中读取数据
    public List<Map<String, String>> getExcel(String file) throws FileNotFoundException{
        File f1=new File(file);
        ExcelHelper h=new ExcelHelper();
        Map<String,String> mapper=new HashMap<String, String>();
        mapper.put("手机号", "userPhone");
        List<Map<String, String>> result=h.readXLS(new FileInputStream(f1), mapper);
        return result;
    }
}
