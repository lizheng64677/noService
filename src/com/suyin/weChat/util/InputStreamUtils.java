/*
 * 文件名：InputStreamUtils.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：XLY
 * 修改时间：2014-10-28
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.weChat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class InputStreamUtils {
	final static int BUFFER_SIZE = 4096;

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) {
		String result = null;
		ByteArrayOutputStream outStream = null;
		if (in != null) {
			try {
				outStream = new ByteArrayOutputStream();
				byte[] data = new byte[BUFFER_SIZE];
				int count = -1;
				while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
					outStream.write(data, 0, count);
				data = null;
				result = new String(outStream.toByteArray(), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					outStream.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
	/**
	 * 解析微信发来的请求（XML） 转换成Map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 从request中取得输入流
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());

			// 释放资源
			inputStream.close();
			inputStream = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

}
