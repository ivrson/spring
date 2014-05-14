/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * �ϱ� ���α׷��� ���� ���۱��� ������ ���������� cdol�� ������,
 * cdol�� ��������� ������� �ʴ� ���, ����, ���� �� �� 3�ڿ� ���� ����, ������ ������ �����Ǹ�
 * cdol�� �������� ħ�ؿ� �ش�ȴ�.
 * Copyright (C) 2014 cdol All Rights Reserved.
 *
 *
 * @author ivrson
 * @since 2014-02-25
 * @version 1.0.0
 *
 *
 * Program		: com.cdol
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SystemInterceptor.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140225000000][ivrson][CREATE: Initial Release]
 */
package com.cdol.util.spring.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cdol.util.common.Datetime;
import com.cdol.util.common.Request;
import com.cdol.util.common.Strings;

/**
 * @author ivrson
 * @since 2014-02-25
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
@Service
public class SystemInterceptor extends HandlerInterceptorAdapter { // HandlerInterceptorAdapter�� ��ӹ��� Ŭ������ ����
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);
	
	@Autowired
	Properties commonProperties;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
		
	/** Front pause force*/
	protected boolean frontPauseForce		= true;
	/** Front pause time */
	protected String frontPauseTime 		= null;	
	/** Front pause except IPs */
	protected String frontPauseExceptIPs	= null;	
	
	/** Backoffice directory */
	protected String adminDirectory 		= null;
	/** Backup office IPs */
	protected String adminIPs 				= null;	
	/** Backoffice pause force */
	protected boolean adminPauseForce		= true;	
	/** Backup office pause except IPs */
	protected String adminPauseExceptIPs 	= null;
	
	/** System pause web page */
	protected String pausePage 			= null;	
	/** System error web page */
	protected String errorPage 			= null;
	/** System deny web page */
	protected String denyPage 				= null;
	
	/**
	 * @author ivrson@
	 * @since 2014-02-25
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param Object
	 * @return boolean
	 * @throws IOException, ServletException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException { // ��Ʈ�ѷ� �������� ����ä�� ���� preHandle ���
		String ip				= ((HttpServletRequest)request).getRemoteAddr();
		String requestURL 		= ((HttpServletRequest)request).getRequestURI();
		String queryString 		= ((HttpServletRequest)request).getQueryString();
		
		String frontPauseForceValue		= messageSourceAccessor.getMessage("front.frontPauseForce"			, "[UNDEFINED]");
		this.frontPauseTime				= messageSourceAccessor.getMessage("front.frontPauseTime"			, "[UNDEFINED]");
		this.frontPauseExceptIPs 		= messageSourceAccessor.getMessage("front.frontPauseExceptIPs"		, "[UNDEFINED]");
		
		this.adminDirectory 			= messageSourceAccessor.getMessage("backoffice.adminDirectory"		, "[UNDEFINED]");
		this.adminIPs 					= messageSourceAccessor.getMessage("backoffice.adminIPs"			, "[UNDEFINED]");
		String adminPauseForceValue		= messageSourceAccessor.getMessage("backoffice.adminPauseForce"		, "[UNDEFINED]");
		this.adminPauseExceptIPs 		= messageSourceAccessor.getMessage("backoffice.adminPauseExceptIPs"	, "[UNDEFINED]");
		
		this.pausePage 					= messageSourceAccessor.getMessage("backoffice.pausePage"			, "[UNDEFINED]");
		this.errorPage 					= messageSourceAccessor.getMessage("backoffice.errorPage"			, "[UNDEFINED]");
		this.denyPage 					= messageSourceAccessor.getMessage("backoffice.denyPage"			, "[UNDEFINED]");
		
		if (frontPauseForceValue == null) this.frontPauseForce = true;
		else if (frontPauseForceValue.equalsIgnoreCase("true")) 		this.frontPauseForce = true;
		else if (frontPauseForceValue.equalsIgnoreCase("yes")) 			this.frontPauseForce = true;
		else this.frontPauseForce = false;
		
		if (adminPauseForceValue == null) this.adminPauseForce = true;
		else if (adminPauseForceValue.equalsIgnoreCase("true")) 		this.adminPauseForce = true;
		else if (adminPauseForceValue.equalsIgnoreCase("yes")) 			this.adminPauseForce = true;
		else this.adminPauseForce = false;
		
		// Full URL
		String fullURL = "";
		if (queryString == null || queryString == "") {
			fullURL = requestURL;
		} 
		else {
			fullURL = requestURL + "?" + queryString;
		}
		
		String exceptPage 			= pausePage + "|" + errorPage + "|" + denyPage;
		
		int nConsole 				= fullURL.indexOf(adminDirectory);
		int nfrontPauseExceptIPs	= frontPauseExceptIPs.indexOf(ip);
		
		int nAdminIPs				= adminIPs.indexOf(ip);
		int nAdminPauseExceptIPs	= adminPauseExceptIPs.indexOf(ip);
		String prefixLogin			= commonProperties.getProperty("backoffice.logging.interceptor.login", "[UNDEFINED]");
		
		// **************************************************************************
		// Language Configuration by Client Cookies
		// **************************************************************************
		try {
			((HttpServletRequest)request).setAttribute("LANG", Request.getCookie(request, "LANG", "EN"));
			((HttpServletRequest)request).setAttribute("FLG_IMG", Request.getCookie(request, "FLG_IMG", "N"));
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".preHandle()]" + e.getMessage(), e);
		}
		
		
		// **************************************************************************
		// Front pause force: frontPauseForce�� true �� ���, frontPauseExceptIPs�� �����ϰ� ���� ���� �� �ý��� ���� �������� ���� �̵�
		// **************************************************************************		
		if (selectFrontPause(request)) {
			try {
				if (fullURL.indexOf(exceptPage) >= 0 || fullURL.equals("/")) {
					if (nfrontPauseExceptIPs < 0 ) {
						((HttpServletResponse)response).sendRedirect(pausePage);
						return true;
					}
				}
			}
			catch (Exception e) {
				logger.error("[" + this.getClass().getName() + ".preHandle()]" + e.getMessage(), e);
			}
		}
		
		// **************************************************************************
		// Front pause time: frontPauseTime�� ���� �ð����� Ŭ ���, frontPauseExceptIPs�� �����ϰ� ���� ���� �� �ý��� ���� �������� ���� �̵�
		// **************************************************************************
		Date dateNow	 			= Datetime.getStringToDatetime(Datetime.getDatetimeToString("yyyy-MM-dd kk:mm:ss"), "yyyy-MM-dd kk:mm:ss");
		Date datefrontPauseTime 	= Datetime.getStringToDatetime(frontPauseTime, "yyyy-MM-dd kk:mm:ss");
		
		if (datefrontPauseTime.getTime() > dateNow.getTime()) {
			try {	
				if (fullURL.indexOf(exceptPage) >= 0 || fullURL.equals("/")) {	
					if (nfrontPauseExceptIPs < 0) {
						((HttpServletResponse)response).sendRedirect(pausePage);
						return true;
					}
				}
			}
			catch (Exception e) {
				logger.error("[" + this.getClass().getName() + ".preHandle()]" + e.getMessage(), e);
			}
		}
		
		// **************************************************************************
		// Backoffice: adminIPs�� ��ϵ� IP�� �ƴ� ���, ���� ���� �������� ���� �̵�
		// **************************************************************************
		if (nConsole >= 0 ) {
			if (nAdminIPs < 0) {
				String referURL = Strings.getString(request.getHeader("refer"));
				logger.error("# REGION ***************************" + prefixLogin + "***************************");
				logger.error("DENY   IP: " + ip);
				logger.error("DENY  URL: " + fullURL);
				logger.error("REFER URL: " + referURL);
				logger.error("DENY TIME: " + Datetime.getDatetimeToString("yyyy-MM-dd kk:mm:ss"));
				logger.error("# END REGION ***********************" + prefixLogin + "***************************\n\n");
				((HttpServletResponse)response).sendRedirect(denyPage);				
				return true;
			}
		}
		
		// **************************************************************************
		// Backoffice pause: adminPauseForce�� true �� ���, adminPauseExceptIPs�� �����ϰ� �ý��� ���� �������� ���� �̵�
		// **************************************************************************
		if (selectAdminPause(request)) {
			try {
				if (nConsole >= 0) {
					if (nAdminPauseExceptIPs < 0) {
						((HttpServletResponse)response).sendRedirect(pausePage);
						return true;
					}
				}
			}
			catch (Exception e) {
				logger.error("[" + this.getClass().getName() + ".preHandle()]" + e.getMessage(), e);
			}
		}	
		
		return true;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-02-25
	 * 
	 * @param ServletRequest
	 * @return String
	 * <p>DESCRIPTION: Select an appropriate frontPause mode to be used, based on the characteristics of the current request and/or filter initialization parameters.
	 * If no character encoding should be set, return <code>null</code>.
	 * The default implementation unconditionally returns the value configured by the <strong>frontPause mode</strong> initialization parameter for this filter.
	 * <p>IMPORTANT
	 */
	protected boolean selectFrontPause(ServletRequest request) {
		return (this.frontPauseForce);
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-02-25
	 * 
	 * @param ServletRequest
	 * @return String
	 * <p>DESCRIPTION: Select an appropriate adminPause mode to be used, based on the characteristics of the current request and/or filter initialization parameters.
	 * If no character encoding should be set, return <code>null</code>.
	 * The default implementation unconditionally returns the value configured by the <strong>adminPause mode</strong> initialization parameter for this filter.
	 * <p>IMPORTANT
	 */
	protected boolean selectAdminPause(ServletRequest request) {
		return (this.adminPauseForce);
	}
}