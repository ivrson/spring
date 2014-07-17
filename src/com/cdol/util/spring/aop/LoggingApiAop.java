/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * �ϱ� ���α׷��� ���� ���۱��� ������ ���������� i-popcorn.co.kr�� ������,
 * i-popcorn.co.kr�� ��������� ������� �ʴ� ���, ����, ���� �� �� 3�ڿ� ���� ����, ������ ������ �����Ǹ�
 * i-popcorn.co.kr�� �������� ħ�ؿ� �ش�ȴ�.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-07-01
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: LoggingApiAop.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140701170700][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-07-01
 * 
 * <p>DESCRIPTION: Aspect�� advice + pointcut
 * 				 : Pointcut�� pointcut�� ������ �޼ҵ�� ���� Ÿ���� void�� ���
 * 				 : Around�� pointcut�� ������ �޼ҽ��� ���� ���Ŀ� �����
 * <p>IMPORTANT
 */
@Aspect
public class LoggingApiAop {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoggingApiAop.class);
	
	// @Pointcut("execution(* kr.co.ipopcorn.backoffice.config.dao.ManagerDao.*(..))")
	// @Pointcut("execution(* kr.co.ipopcorn..*Dao.*(..))")
	@Pointcut("execution(* kr.co.ipopcorn.api..*Ctrl.*(..))")
	private void timeTarget(){}
	
	@Around("timeTarget()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		/** �޼ҵ� ���� �� ������ */
		String methodName 	= joinPoint.getSignature().toString();		// ȣ��Ǵ� �޼ҵ��
		Object requestObject	= joinPoint.getArgs()[0];
		
		ObjectMapper m 			= new ObjectMapper();
		String requestJSON		= "";
		byte[] requestByte		= null;
		String responseJSON		= "";
		byte[] responseByte		= null;
		
		if (requestObject != null) {
			requestJSON	= m.writeValueAsString(requestObject);
			requestByte	= requestJSON.getBytes("UTF-8");
			logger.info("[LoggingApiAop.Req: " + methodName + "][" + requestByte.length + "] " + requestJSON);
		}
		
		/**  �޼ҵ� ���� */
		Object returnValue 	= joinPoint.proceed(); 						// �޼ҵ� ȣ��
		
		/** �޼ҵ� ���� �� ���� ��� */
		if (returnValue != null) {
			responseJSON	= m.writeValueAsString(returnValue);
			responseByte	= responseJSON.getBytes("UTF-8");
			logger.info("[LoggingApiAop.Res: " + methodName + "][" + responseByte.length + "] " + responseJSON + "\n");
		}
		
		return returnValue;
	}
}
