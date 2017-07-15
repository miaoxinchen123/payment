package com.tengfei.payment.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tengfei.payment.dto.Register;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.service.RegisterService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.BalanceType;
import com.tengfei.payment.system.RegisterException;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.MsgUtil;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.CustomerService;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private com.tengfei.payment.service.CustomerService customerService;
	
	@Autowired
	private ProxyRelationService proxyRelationService;

	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	@Override
	public void register(Register register) throws RegisterException {
		User user = userService.getByUserCount(register.getCount());
		if(null != user) {
			throw new RegisterException("账号已存在");
		}
		
		Page page = new Page();
		page.setMapBean(new HashMap<String, Object>(1));
		page.getMapBean().put("phone", register.getPhone());
		page = userService.pageQueryUser(page);
		List<User> userList = (List<User>) page.getRes();
		if(!Utility.isEmpty(userList)) {
			throw new RegisterException("手机号码已存在");
		}
		
		if(UserType.MERCHANT.code().endsWith(register.getType())) {//商户
			page.getMapBean().put("phone", register.getProxyPhone());
			page = userService.pageQueryUser(page);
			userList = (List<User>) page.getRes();
			if(Utility.isEmpty(userList)) {
				throw new RegisterException("推荐代理商户手机号码, 系统不存在");
			}
		}
		
		user = fillUser(register);
		Page pages = new Page();
		pages = customerService.pageQueryCustomerService(pages);
		List<CustomerService> csList = (List<CustomerService>) pages.getRes();
		String notifyPhone = null;
		if(!Utility.isEmpty(csList)) {
			user.setServerQQ(csList.get(0).getQq());
			notifyPhone = csList.get(0).getPhone();
		}
		userService.saveUser(user);
		
		if(UserType.MERCHANT.code().endsWith(register.getType())) {
			ProxyRelation pr = new ProxyRelation();
			pr.setMerchantId(user.getId());
			pr.setUserId(userList.get(0).getId());
			pr.setRate(Tools.defaultRate());
			proxyRelationService.addProxyRelation(pr);
		}
		
		if(!Utility.isEmpty(notifyPhone)) {
			MsgUtil.notify(notifyPhone, new String[] {user.getCount(), user.getPhone(), UserType.toName(user.getType())});
		}
	}
	
	protected User fillUser(Register register) {
		User user = new User();
		user.setCount(register.getCount());
		user.setPassword(register.getPassword());
		user.setPhone(register.getPhone());
		user.setQq(register.getQq());
		user.setHosts(register.getHosts());
		user.setType(register.getType());
		user.setStatus(UserStatu.WAIT_FOR_AUDIT.code());
		user.setBalanceType(BalanceType.T1.code());
		
		return user;
	}

}
