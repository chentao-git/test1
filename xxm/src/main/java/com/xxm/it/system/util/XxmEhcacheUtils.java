package com.xxm.it.system.util;

import java.net.URL;

import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类
 * 
 * @author Administrator
 *
 */
public class XxmEhcacheUtils {

	private static Logger logger = Logger.getLogger(XxmFilter.class);
	private static CacheManager cacheManager = null;

	public static final String TEMP_DATA_CACHE = "tempDataCache";// 缓存对象名称，存储临时数据
	public static final String SHARE_CONFIG_CACHE = "shareConfig";// 永久缓存共享配置

	static {
		// 初始化cacheManager对象
		URL url = XxmEhcacheUtils.class.getResource("/ehcache.xml");
		cacheManager = CacheManager.create(url);
		logger.info("初始化cacheManager...");
	}

	/**
	 * 获取缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 写入缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * 获得一个Cache，没有则创建一个。
	 * 
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	public static void main(String[] args) {
		XxmEhcacheUtils.put(XxmEhcacheUtils.SHARE_CONFIG_CACHE, "11111", "xxxxxxxxxxx");
		System.out.println("........开始..........");
		Object obj = XxmEhcacheUtils.get(XxmEhcacheUtils.SHARE_CONFIG_CACHE, "11111");
		System.out.println(obj.toString());
		for (int i = 0; i <= 5; i++) {
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object obj2 = XxmEhcacheUtils.get(XxmEhcacheUtils.SHARE_CONFIG_CACHE, "11111");
			System.out.println(i + "   " + obj2.toString());
		}
		System.out.println("..................");
	}
}
