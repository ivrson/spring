package com.cdol.backoffice.common.dao;

import org.springframework.stereotype.Repository;

import com.cdol.backoffice.common.vo.LoginVO;

@Repository
public class LoginDao extends BaseDao{

	public LoginVO getAuthorization(String id){
		return sqlSessionVenus.selectOne("com.cdol.mybatis.common.Login.select", id);
	}
}