package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.CardDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;


@Repository
public class CardDaoImpl implements CardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addCard(Card card) {
		sqlSessionTemplate.update(CardDao.prefix + ".addCard", card);
	}

	@Override
	public boolean delCardById(Card card) {
		sqlSessionTemplate.delete(CardDao.prefix + ".delCardById", card);
		return true;
	}

	@Override
	public void updateCard(Card card) {
		sqlSessionTemplate.update(CardDao.prefix + ".updateCard", card);
		
	}
	
	@Override
	public Card geCardtById(String id) {
		List<Card> cardList = sqlSessionTemplate.selectList(CardDao.prefix + ".getCardById", id);
		if(Utility.isEmpty(cardList)) return null;
		return cardList.get(0);
	}

	@Override
	public Page pageQueryCard(Page page) {
		List<Card> rateList = sqlSessionTemplate.selectList(CardDao.prefix + ".pageQueryCard", page);
		page.setRes(rateList);
		return page;
	}

	@Override
	public List<Card> getAvailableCard(String userId) {
		return sqlSessionTemplate.selectList(CardDao.prefix + ".getAvailableCard", userId);
	}

	@Override
	public List<Card> getUserCard(String userId) {
		return sqlSessionTemplate.selectList(CardDao.prefix + ".getUserCard", userId);
	}

}
