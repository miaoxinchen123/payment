package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.dto.ProxyRelationDto;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;

@Repository
public class ProxyRelationDaoImpl implements ProxyRelationDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addProxyRelation(ProxyRelation proxyRelation) {
		sqlSessionTemplate.insert(ProxyRelationDao.prefix + ".addProxyRelation", proxyRelation);
	}

	@Override
	public void updateProxyRelation(ProxyRelation proxyRelation) {
		sqlSessionTemplate.update(ProxyRelationDao.prefix + ".updateProxyRelation", proxyRelation);
	}

	@Override
	public List<String> getProxyUserName(String proxyId) {
		return sqlSessionTemplate.selectList(ProxyRelationDao.prefix + ".selectProxyUserName", proxyId);
	}
	

	@Override
	public List<ProxyRelation> getProxyUserId(String proxyId) {
		return sqlSessionTemplate.selectList(ProxyRelationDao.prefix + ".selectProxyRelationById", proxyId);
	}
	

	@Override
	public List<ProxyRelation> getProxyMerchantId(String merchantId) {
		return sqlSessionTemplate.selectList(ProxyRelationDao.prefix + ".selectProxyRelationByMerchantId", merchantId);
	}

	@Override
	public Page pageQueryProxyRelation(Page page) {
		List<ProxyRelation> proxyRelationList = sqlSessionTemplate.selectList(UserDao.prefix + ".pageQueryProxyRelation", page);
		page.setRes(proxyRelationList);
		return page;
	}

	@Override
	public ProxyRelation getProxyRelation(String id) {
		return sqlSessionTemplate.selectOne(ProxyRelationDao.prefix + ".selectProxyRelationById", id);
	}

	@Override
	public void delProxyRelation(ProxyRelation proxyRelation) {
		sqlSessionTemplate.delete(ProxyRelationDao.prefix + ".delProxyRelation", proxyRelation);
	}

	@Override
	public List<ProxyRelationDto> queryByProxyId(String proxyId) {
		return sqlSessionTemplate.selectList(ProxyRelationDao.prefix + ".queryByProxyId", proxyId);
	}

	@Override
	public Page pageAvailProxyRelation(Page page) {
		List<ProxyRelation> proxyRelationList = sqlSessionTemplate.selectList(ProxyRelationDao.prefix + ".pageAvailProxyRelation", page);
		page.setRes(proxyRelationList);
		return page;
	}

}
