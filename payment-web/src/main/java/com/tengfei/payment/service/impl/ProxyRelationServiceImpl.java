package com.tengfei.payment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dto.ProxyRelationDto;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;

@Service
public class ProxyRelationServiceImpl implements ProxyRelationService {
	
	@Autowired
	private ProxyRelationDao proxyRelationDao;

	@Override
	public boolean addProxyRelation(ProxyRelation proxyRelation) {
		if(Utility.isEmpty(proxyRelation.getId())) {
			proxyRelation.setId(Tools.generatorId());
			proxyRelation.setCreateDate(new Date());
		}
		proxyRelationDao.addProxyRelation(proxyRelation);
		
		return true;
	}

	@Override
	public boolean editProxyRelation(ProxyRelation proxyRelation) {
		proxyRelation.setUpdateDate(new Date());
		proxyRelationDao.updateProxyRelation(proxyRelation);
		
		return true;
	}

	@Override
	public boolean delProxyRelation(ProxyRelation proxyRelation) {
		proxyRelationDao.delProxyRelation(proxyRelation);
		
		return true;
	}

	@Override
	public List<ProxyRelationDto> queryByProxyId(String proxyId) {
		return proxyRelationDao.queryByProxyId(proxyId);
	}

	@Override
	public Page pageAvailProxyRelation(Page page) {
		return proxyRelationDao.pageAvailProxyRelation(page);
	}

	@Override
	public List<ProxyRelation> getProxyMerchantId(String merchantId) {
		return proxyRelationDao.getProxyMerchantId(merchantId);
	}

}
