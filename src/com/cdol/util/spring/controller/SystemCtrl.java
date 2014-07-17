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
 * File			: SystemCtrl.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.spring.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdol.util.spring.dto.SystemErrorDto;
/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
@Controller("kr.co.ipopcorn.util.spring.controller.SystemCtrl")
public class SystemCtrl {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SystemCtrl.class);
	
	@Autowired
	Properties commonProperties;
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest, HttpServletResponse
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorProc(HttpServletRequest request, SystemErrorDto systemErrorDto) {
	
		try {			
			
			if (systemErrorDto.getCode().equals("400")) {
				systemErrorDto.setCode_desc("BAD REQUEST");
			}
			else if (systemErrorDto.getCode().equals("401")) {
				systemErrorDto.setCode_desc("UNAUTHORIZED");
			}
			else if (systemErrorDto.getCode().equals("403")) {
				systemErrorDto.setCode_desc("ACCESS DENITED");
			}
			else if (systemErrorDto.getCode().equals("404")) {
				systemErrorDto.setCode_desc("FILE NOT FOUND");
			}
			else if (systemErrorDto.getCode().equals("405")) {
				systemErrorDto.setCode_desc("METHOD NOT ALLOWED");
			}
			else if (systemErrorDto.getCode().equals("408")) {
				systemErrorDto.setCode_desc("REQUEST TIMEOUT");
			}
			else if (systemErrorDto.getCode().equals("415")) {
				systemErrorDto.setCode_desc("FORMAT NOT SUPPORTE");
			}
			else if (systemErrorDto.getCode().equals("500")) {
				systemErrorDto.setCode_desc("INTERNAL SERVER ERROR");
			}
			else if (systemErrorDto.getCode().equals("503")) {
				systemErrorDto.setCode_desc("SERVICE UNAVAILABLE");
			}
			else if (systemErrorDto.getCode().equalsIgnoreCase("exception")) {
				systemErrorDto.setCode_desc("EXCEPTION");
			}
			else if (systemErrorDto.getCode().equalsIgnoreCase("servletException")) {
				systemErrorDto.setCode_desc("SERVLET EXCEPTION");
			}
			else {
				systemErrorDto.setCode_desc("UNKNOWN");
			}
			
			logger.error("# REGION ***************************" + commonProperties.getProperty("util.logging.system.error", "[UNDEFINED]") + "***************************");
			logger.error("CODE: " + systemErrorDto.getCode());
			logger.error("DESC: " + systemErrorDto.getCode_desc());
			logger.error("  IP: " + request.getRemoteAddr());
			logger.error("# END REGION ***********************" + commonProperties.getProperty("util.logging.system.error", "[UNDEFINED]") + "***************************\n");
			
			return "system/system-error";
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".errorProc()]" + e.getMessage(), e);
			return "redirect:/error.iwf";
		}
		finally {}
		
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	public String pauseProc(HttpServletRequest request) {
		
		try {
			
			return "system/system-pause";
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".pauseProc()]" + e.getMessage(), e);
			return "redirect:/error.iwf";
		}
		finally {}
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String denyProc(HttpServletRequest request) {
		
		try {
			return "system/system-deny";
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".denyProc()]" + e.getMessage(), e);
			return "redirect:/error.iwf";
		}
		finally {}
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public String rejectProc(HttpServletRequest request) {
		
		try {			
			return "system/system-reject";
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".rejectProc()]" + e.getMessage(), e);
			return "redirect:/error.iwf";
		}
		finally {}
	}
}