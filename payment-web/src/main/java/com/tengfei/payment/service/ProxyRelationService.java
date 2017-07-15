package com.tengfei.payment.service;

import java.util.List;

import com.tengfei.payment.dto.ProxyRelationDto;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;

public interface ProxyRelationService {

	public boolean addProxyRelation(ProxyRelation proxyRelation);

	public boolean editProxyRelation(ProxyRelation proxyRelation);

	public boolean delProxyRelation(ProxyRelation proxyRelation);

	public List<ProxyRelationDto> queryByProxyId(String proxyId);
	
	public List<ProxyRelation> getProxyMerchantId(String merchantId);

	public Page pageAvailProxyRelation(Page page);

}
