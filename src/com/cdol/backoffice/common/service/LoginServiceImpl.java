package com.cdol.backoffice.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdol.backoffice.common.dao.LoginDao;
import com.cdol.backoffice.common.vo.LoginVO;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public LoginVO getLoginAuthorizationCheck(String id) {
		return loginDao.getAuthorization(id);
	}
}