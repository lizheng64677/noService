package com.suyin.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SystemPropertiesHolder {
	public static Resource resource;

	public static Properties props;

	static {
		resource = new FileSystemResourceLoader()
				.getResource("classpath:config/system.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		String s=null;
		try {
			s=new String(props.getProperty(key).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			s=props.getProperty(key);
		}
		return  s;
	}

	public static void main(String[] args) {
		System.out.println(SystemPropertiesHolder.get("REMOTE_URL"));
	}
}
