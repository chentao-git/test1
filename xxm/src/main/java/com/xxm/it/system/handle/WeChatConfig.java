package com.xxm.it.system.handle;

import java.util.Properties;

import com.xxm.it.system.util.XxmConstant;
import com.xxm.it.system.util.XxmUtils;

public class WeChatConfig {
	private static WeChatConfig instance = new WeChatConfig();

	public static WeChatConfig getInstance() {
		return instance;
	}

	private Properties properties;// 用来存放配置Properties对象

	public Properties getProperties() {
		return this.properties;
	}

	public String getParameter(String key) {
		return this.properties.getProperty(key);
	}

	private WeChatConfig() {
		readConfig();// 调用读取配置文件的方法
	}

	// 读取配置文件，将配置文件中的内容读取出来设置到属性上
	private void readConfig() {
		this.properties = XxmUtils.loadProperties(XxmConstant.WeChatConstant.PROPERTIES_FILE_NAME);
	}
}