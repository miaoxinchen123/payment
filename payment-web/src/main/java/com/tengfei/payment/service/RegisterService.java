package com.tengfei.payment.service;

import com.tengfei.payment.dto.Register;
import com.tengfei.payment.system.RegisterException;

public interface RegisterService {

	public void register(Register register) throws RegisterException;
	
}
