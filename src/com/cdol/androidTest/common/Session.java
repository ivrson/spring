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
 * @since 2014-04-08
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Session.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140408164100][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.common;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.cdol.util.common.Strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-04-08
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Session {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Session.class);
	
	@Autowired
	Properties commonProperties;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-08
	 * 
	 * @param HttpServletRequest
	 * @return boolean
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public boolean checkAccess(HttpServletRequest request) {

		
		String device		= messageSourceAccessor.getMessage("api.access.device", "[UNDEFINED]");
		
		String devices[]	= Strings.getArraybySplitter(device, "");
		
		String agent		= request.getHeader("User-Agent");
		
		boolean isDevice	= false;
		
		for (int nLoop = 0; nLoop < devices.length; nLoop++) {
			if (agent.indexOf(devices[nLoop]) > -1) isDevice = true;
		}		
		
		if (!isDevice) {
			logger.error("# REGION ***************************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************");
			logger.error("User-Agent : " + agent);
			logger.error("IP         : " + request.getRemoteAddr());
			logger.error("# END REGION ***********************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************\n\n");
			return false;
		}	
		if (!"POST".equals(Strings.getString(request.getMethod(), ""))) {
			logger.error("# REGION ***************************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************");
			logger.error("METHOD : " + request.getMethod());
			logger.error("IP     : " + request.getRemoteAddr());
			logger.error("# END REGION ***********************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************\n\n");
			return false;
		}
		if (!"application/json; charset=UTF-8".equals(Strings.getString(request.getHeader("content-type"), ""))) {
			logger.error("# REGION ***************************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************");
			logger.error("content-type : " + request.getHeader("content-type"));
			logger.error("IP           : " + request.getRemoteAddr());
			logger.error("# END REGION ***********************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************\n\n");
			return false;
		}
		if (!"application/json".equals(Strings.getString(request.getHeader("accept"), ""))) {
			logger.error("# REGION ***************************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************");
			logger.error("accept : " + request.getHeader("accept"));
			logger.error("IP     : " + request.getRemoteAddr());
			logger.error("# END REGION ***********************" + commonProperties.getProperty("api.logging.trespass", "[UNDEFINED]") + "***************************\n\n");
			return false;
		}
		
		return true;
	}
}
