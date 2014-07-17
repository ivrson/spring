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
 * File			: Config.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdol.util.paging.PagingLoader;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Config extends HttpServlet {
	
	/** Serial version UID */
	private static final long serialVersionUID = 20140225000010L;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Config.class);
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException, IOException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException, IOException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(getServletConfig());
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param ServletConfig
	 * @throws ServletException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		try {
			// Page navigator
			String pageNavigatorFileName = this.getInitParameter("pageNavigator");
			if ((pageNavigatorFileName != null) && (pageNavigatorFileName.length() > 0))
				PagingLoader.getInstance().setPageNavigators(getServletContext().getRealPath(pageNavigatorFileName));
		} 
		catch(Exception e) {
			logger.error("[" + this.getClass().getName() + ".init()]" + e.getMessage(), e);
		}
	}
}