package com.tengfei.payment.sms.impl;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import com.tengfei.payment.sms.CodeCache;
import com.tengfei.payment.sms.util.CodeMakeUtil;

public class DefaultCodeCacheImpl implements CodeCache {
	@Resource(name="ehcacheManager")
	private EhCacheCacheManager ehcacheManager;
	
	private Cache codCache = null;
	
	public void init() {
		codCache = ehcacheManager.getCache("codeCache");
	}
	
	@Override
	public String getCode(String key) {
		Object getVal = codCache.get(key);
		if(null != getVal) return ((SimpleValueWrapper)getVal).get().toString();
		
		return null;
	}

	@Override
	public void putCode(String key, String code) {
		codCache.put(key, code);
	}

	@Override
	public String makeAndPutCode(String key) {
		String code = CodeMakeUtil.makeCode();
		putCode(key, code);
		
		return code;
	}

	@Override
	public void removeCode(String key) {
		codCache.evict(key);
	}

}
