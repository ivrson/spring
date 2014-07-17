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
 * @since 2014-05-28
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: ExecuteTimeAop.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.spring.aop;

import java.text.DecimalFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION: Aspect�� advice + pointcut
 * 				 : Pointcut�� pointcut�� ������ �޼ҵ�� ���� Ÿ���� void�� ���
 * 				 : Around�� pointcut�� ������ �޼ҽ��� ���� ���Ŀ� �����
 * <p>IMPORTANT
 */
@Aspect
public class ExecuteTimeAop {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(ExecuteTimeAop.class);
	
	// @Pointcut("execution(* kr.co.ipopcorn.backoffice.config.dao.ManagerDao.*(..))")
	// @Pointcut("execution(* kr.co.ipopcorn..*Dao.*(..))")
	@Pointcut("execution(* kr.co.ipopcorn..*Ctrl.*(..))")
	private void timeTarget(){}
	
	@Around("timeTarget()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		/** �޼ҵ� ���� �� ������ */
		long startTime 		= System.currentTimeMillis();				// ���� �ð�
		String methodName 	= joinPoint.getSignature().toString();		// ȣ��Ǵ� �޼ҵ��
		
		/**  �޼ҵ� ���� */
		Object returnValue 	= joinPoint.proceed(); 						// �޼ҵ� ȣ��
		
		/** �޼ҵ� ���� �� ���� ��� */
		long endTime 		= System.currentTimeMillis();				// ���� ����
		long executeTime 	= endTime - startTime;						// ���� �ð�(��): ���� ���� - ���� �ð�
		
		DecimalFormat df = new DecimalFormat("#,###0.000");				
		if (executeTime / 1000.0 >= 1) {
			logger.warn("[ExecuteTimeAop: " + methodName + "] " + df.format((executeTime / 1000.0)) + "s");
		}
		else {
			logger.debug("[ExecuteTimeAop: " + methodName + "] " + df.format((executeTime / 1000.0)) + "s");
		}
		
		return returnValue;
	}
}