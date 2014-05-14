/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 cdol에 있으며,
 * cdol이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * cdol의 지적재산권 침해에 해당된다.
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
package com.cdol.util.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ivrson
 * @since 2014-04-10
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Request {
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getString(HttpServletRequest request, String name, String defaultValue) throws Exception {
		String value = request.getParameter(name);
		if (value == null)
			return defaultValue;
		else
			return value.trim();
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param HttpServletRequest
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getRemoteAddr(HttpServletRequest request) throws Exception {
		String value =  request.getRemoteAddr();
		if (value == null)
			return "";
		else
			return value.trim();
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getCookie(HttpServletRequest request, String key, String defaultValue) throws Exception {
		
		String value	= defaultValue;
		
		HttpSession session	 = ((HttpServletRequest)request).getSession();
		
		if (session != null) {
			Cookie[] cookie = ((HttpServletRequest)request).getCookies();
			if (cookie != null) {
				for (int nLoop = 0; nLoop < cookie.length; nLoop++) {
					if (cookie[nLoop].getName().equals(key))
						value = cookie[nLoop].getValue();
				}
			}
		}
		
		return value;
	}
}