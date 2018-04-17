/*
 * 文件名：VoucherUtil.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-9-29
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.util;

import java.util.Date;
import java.util.Random;


/**
 * 券的生成
 * @author lz
 * @version 2015-9-29
 * @see VoucherUtil
 * @since
 */

public class VoucherUtil
{    /*
     * 获取随机字符
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

    public static void main(String[] args)
    {
            String tel="18705164891";
            String ee="524406665219";
            System.out.println(ee.length());
            Long str=System.nanoTime();
            Tools.date2Str(new Date(), "yyyyMMdd");
          for (int i = 0; i < 100000; i++ )
          {
              
              System.out.println(VoucherUtil.getRandomString(12));
          }
    }
}
