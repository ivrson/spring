package com.cdol.backoffice.common.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

public class BaseDao {
	
	@Inject
	protected SqlSession sqlSessionVenus;
	
	public SqlSession getSqlSessionVenus() {
		return sqlSessionVenus;
	}
	public void setSqlSessionVenus(SqlSession sqlSessionVenus) {
		this.sqlSessionVenus = sqlSessionVenus;
	}
}