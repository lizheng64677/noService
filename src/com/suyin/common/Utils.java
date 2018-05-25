package com.suyin.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public class Utils {
	public static void main(String[] args) throws Exception {
        double max = 0.01;
        double min = 0.00;  
        System.out.println(Utils.addUserPrice(max,min));		
    }
	/**
	 * 获取随机字符
	 * @param length
	 * @return
	 */
    public static String getRandomString(int length){
        StringBuffer buffer=new StringBuffer("0123456789");
        StringBuffer sb=new StringBuffer();
        Random r=new Random();
        int range=buffer.length();
        for(int i=0;i<length;i++){
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }
	/**  
	 * 提供精确的加法运算。  
	 * @param v1 被加数  
	 * @param v2 加数  
	 * @return 两个参数的和  
	 */  
	public static double addUserPrice(double v1,double v2){   
		BigDecimal b1 = new BigDecimal(Double.toString(v1));   
		BigDecimal b2 = new BigDecimal(Double.toString(v2));   
		return b1.add(b2).doubleValue();   
	}   
	/**  
	 * 提供精确的减法运算。  
	 * @param v1 被减数  
	 * @param v2 减数  
	 * @return 两个参数的差  
	 */  
	public static double subUserPrice(double v1,double v2){   
		BigDecimal b1 = new BigDecimal(Double.toString(v1));   
		BigDecimal b2 = new BigDecimal(Double.toString(v2));   
		return b1.subtract(b2).doubleValue();   
	}   
	/**
	 * 浮点数 金额 区间随机
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
    public static double nextDouble(final double min, final double max) throws Exception {
        if (max < min) {
            throw new Exception("min < max");
        }
        if (min == max) {
            return min;
        }
        
        return min + ((max - min) * new Random().nextDouble());
    }
}
