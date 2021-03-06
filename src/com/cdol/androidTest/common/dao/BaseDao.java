/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 i-popcorn.co.kr에 있으며,
 * i-popcorn.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * i-popcorn.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: BaseDao.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140407140100][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.common.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION : Mercury(API), Sms(SMS)
 * <p>IMPORTANT
 */
public class BaseDao {
	
	@Inject
	protected SqlSession sqlSessionMercury;
	
	@Inject
	protected SqlSession sqlSessionSms;
	
	public SqlSession getSqlSessionMercury() {
		return sqlSessionMercury;
	}
	public void setSqlSessionMercury(SqlSession sqlSessionMercury) {
		this.sqlSessionMercury = sqlSessionMercury;
	}
	public SqlSession getSqlSessionSms() {
		return sqlSessionSms;
	}
	public void setSqlSessionSms(SqlSession sqlSessionSms) {
		this.sqlSessionSms = sqlSessionSms;
	}
}