package com.cdol.backoffice.common.service;

import com.cdol.backoffice.common.vo.LoginVO;

public interface LoginService {
	public LoginVO getLoginAuthorizationCheck(String id);
}
