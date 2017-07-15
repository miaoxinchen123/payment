package com.tengfei.payment.service;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.WithDraw;

/**
 * 提现服务
 * @author miaoxin.chen
 * @date   2016年8月24日
 *
 */
public interface WithDrawService {

	public Page pageQueryWithDraw(Page page);
	
	public Page adminPageQueryWithDraw(Page page);
	
	public void saveWithDraw(WithDraw withDraw);
	
	public WithDraw getWithDraw(String id);

	public void updateByStatus(WithDraw withDraw);

}
