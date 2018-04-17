package com.suyin.weChat.util;

import java.io.Writer;

import com.suyin.weChat.model.response.Article;
import com.suyin.weChat.model.response.MusicResponseMessage;
import com.suyin.weChat.model.response.NewsResponseMessage;
import com.suyin.weChat.model.response.TextResponseMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class ResponseMessageUtil {
	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(
			TextResponseMessage textResponseMessage) {
		xstream.alias("xml", textResponseMessage.getClass());
		return xstream.toXML(textResponseMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage
	 *            音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(
			MusicResponseMessage musicResponseMessage) {
		xstream.alias("xml", musicResponseMessage.getClass());
		return xstream.toXML(musicResponseMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(
			NewsResponseMessage newsResponseMessage) {
		xstream.alias("xml", newsResponseMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsResponseMessage);
	}

}
