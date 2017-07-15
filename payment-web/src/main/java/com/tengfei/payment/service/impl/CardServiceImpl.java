package com.tengfei.payment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.CardDao;
import com.tengfei.payment.service.CardService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;

/**
 * @author miaoxin.chen
 * @date   2016年8月25日
 *
 */
@Service
public class CardServiceImpl implements CardService {

	
	@Autowired
	private CardDao cardDao;
	
	@Override
	public Page pageQueryCard(Page page) {
		return cardDao.pageQueryCard(page);
	}

	@Override
	public boolean delCardById(Card card) {
		cardDao.delCardById(card);
		return true;
	}

	@Override
	public Card getCardById(String id) {
		return 	cardDao.geCardtById(id);
	}

	@Override
	public void saveCard(Card card) {
		
		if(Utility.isEmpty(card.getId())) {
			card.setId(Tools.generatorId());
			card.setCreateDate(new Date());
			cardDao.addCard(card);
		}
		
		card.setUpdateDate(new Date());
		cardDao.updateCard(card);
	}

	@Override
	public List<Card> getAvailableCard(String userId) {
		return cardDao.getAvailableCard(userId);
	}

	@Override
	public List<Card> getUserCard(String userId) {
		return cardDao.getUserCard(userId);
	}
	
}
