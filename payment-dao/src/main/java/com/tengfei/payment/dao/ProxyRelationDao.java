package com.tengfei.payment.dao;

import java.util.List;

import com.tengfei.payment.dto.ProxyRelationDto;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;

/**
 * 用户dao
 * @author jianfei.xu
 * @date   2016年04月07日
 *
 */
public interface ProxyRelationDao {
	
	public String prefix = "com.tengfei.payment.dao.ProxyRelationDao";
		
	
	/**
	 * 添加代理商户表
	 * @param proxyRelation
	 */
	public void addProxyRelation(ProxyRelation proxyRelation);

	
	/**
	 * 根据代理id查询下属所有商户的名字
	 * @param proxyRelation
	 */
	public List<String> getProxyUserName(String proxyId);
	
	/**
	 * 根据代理id查询下属所有商户的Id
	 * @param proxyRelation
	 */
	public List<ProxyRelation> getProxyUserId(String proxyId);
	
     /** ======================系统管理员部分逻辑 ========================/
	
	/**
	 * 根据单个代理管理的商户列表
	 * @param proxyRelation
	 */
	public Page pageQueryProxyRelation(Page page);
	
	/**
	 * 查询单条代理商户具体信息
	 * @param proxyRelation
	 */
	public ProxyRelation getProxyRelation(String id);
	
	
	/**
	 * 管理员修改代理商户的利率
	 * @param proxyRelation
	 */
	public void updateProxyRelation(ProxyRelation proxyRelation);

	public void delProxyRelation(ProxyRelation proxyRelation);

	public List<ProxyRelationDto> queryByProxyId(String proxyId);

	public Page pageAvailProxyRelation(Page page);

	public List<ProxyRelation> getProxyMerchantId(String merchantId);
}
