package com.tengfei.payment.dao;

import java.util.List;

import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserRate;

/**
 * 银行卡dao
 * @author miaoxin.chen
 * @date   2017年04月08日
 *
 */
public interface CardDao {
	
	public String prefix = "com.tengfei.payment.dao.CardDao";
		
	
	/**
	 * 添加银行卡
	 * @param Card
	 */
	public void addCard(Card card);
	
	
	/**
	 * 修改银行卡
	 * @param Card
	 */
	public boolean delCardById(Card card);

	
	
	/**
	 * 修改银行卡
	 * @param Card
	 */
	public void updateCard(Card card);
	
	/**
	 * 分页查询订单
	 * @param Card
	 */
	public Page pageQueryCard(Page page);


	public Card geCardtById(String id);


	public List<Card> getAvailableCard(String userId);


	public List<Card> getUserCard(String userId);
	
}
