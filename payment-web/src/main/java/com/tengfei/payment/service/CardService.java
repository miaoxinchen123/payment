package com.tengfei.payment.service;

import java.util.List;

import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;

/**
 * 银行卡服务
 * @author miaoxin.chen
 * @date   2016年8月24日
 *
 */
public interface CardService {

	public Page pageQueryCard(Page page);
	
	public boolean delCardById(Card card);

	public Card getCardById(String id);
	
	public void saveCard(Card card);
	
	public List<Card> getAvailableCard(String userId);
	
	public List<Card> getUserCard(String userId);
	
}
