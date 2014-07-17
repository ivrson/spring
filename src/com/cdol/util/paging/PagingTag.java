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
 * File			: PagingTag.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class PagingTag  extends TagSupport  {
	
	/** Serial version UID */
	private static final long serialVersionUID = 20140225000011L;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PagingTag.class);
	
	/** Style id */
	private String styleID 			= "";
	/** Script function */
	private String scriptFunction 	= "";
	/** Page */
	private int currentPage		= 0;
	/** Lines per page */
	private int linePerPage 		= 0;
	/** Total rows */
	private long totalLine 		= 0;
	/** Page url */
	private String pageURL	 		= "";
	/** Page string */
	private String pageString 		= "";	
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @return int
	 * @throws JspException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public int doStartTag() throws JspException {
		
		try {
			
			String pageNavigator = "";
			
			if (this.scriptFunction.length() > 0) {
				if (this.styleID.length() > 0)
					pageNavigator = PagingHandler.getPageNavigator(this.styleID, this.currentPage, this.linePerPage, this.totalLine, this.scriptFunction);
				else
					pageNavigator = PagingHandler.getPageNavigator(this.currentPage, this.linePerPage, this.totalLine, this.scriptFunction);
				
			}
			else if (this.pageURL.length() > 0) {
				
				if (this.pageString.length() < 1)
					this.pageString = "page";
				
				if (this.styleID.length() > 0)
					pageNavigator = PagingHandler.getPageNavigator(this.styleID, this.currentPage, this.linePerPage, this.totalLine, this.pageURL, this.pageString);
				else
					pageNavigator = PagingHandler.getPageNavigator(this.currentPage, this.linePerPage, this.totalLine, this.pageURL, this.pageString);
				
			}
			pageContext.getOut().print(pageNavigator);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".doStartTag()] Error occurred while process 'dotcom:page' tag: " + e.getMessage(), e);
			throw new JspException("[" + this.getClass().getName() + ".doStartTag()] Error occurred while process 'dotcom:page' tag: " + e.getMessage());
		}
		
		return SKIP_BODY;
	}
	
	
	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}
	
	public void setScriptFunction(String scriptFunction) {
		this.scriptFunction = scriptFunction;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}
	
	public void setTotalLine(long totalLine) {
		this.totalLine = totalLine;
	}
	
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}
	
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
}