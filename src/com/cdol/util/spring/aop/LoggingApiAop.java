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
 * <p>DESCRIPTION: Aspect는 advice + pointcut
 * 				 : Pointcut은 pointcut를 지정한 메소드는 리턴 타입을 void만 허용
 * 				 : Around는 pointcut로 설정된 메소스의 실행 전후에 수행됨
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
		
		/** 메소드 실행 전 공통기능 */
		String methodName 	= joinPoint.getSignature().toString();		// 호출되는 메소드명
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
		
		/**  메소드 실행 */
		Object returnValue 	= joinPoint.proceed(); 						// 메소드 호출
		
		/** 메소드 실행 후 공통 기능 */
		if (returnValue != null) {
			responseJSON	= m.writeValueAsString(returnValue);
			responseByte	= responseJSON.getBytes("UTF-8");
			logger.info("[LoggingApiAop.Res: " + methodName + "][" + responseByte.length + "] " + responseJSON + "\n");
		}
		
		return returnValue;
	}
}
