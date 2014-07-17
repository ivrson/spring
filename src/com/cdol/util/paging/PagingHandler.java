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
 * File			: PagingHandler.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.paging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class PagingHandler {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PagingHandler.class);
	
	/** Links per page default */
	private final static int LINE_PER_PAGE = 10;
		
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param int
	 * @param int
	 * @param long
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getPageNavigator(int currentPage, int linePerPage, long totalLine, String scriptFunction) throws Exception {
		return getPageNavigator(null, currentPage, linePerPage, totalLine, null, scriptFunction, null);
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param int
	 * @param int
	 * @param long
	 * @param String
	 * @param String 
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getPageNavigator(int currentPage, int linePerPage, long totalLine, String pageURL, String pageString) throws Exception {
		return getPageNavigator(null, currentPage, linePerPage, totalLine, pageURL, pageString);
	}
		
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @param int
	 * @param int
	 * @param long
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getPageNavigator(String styleId, int currentPage, int linePerPage, long totalLine, String scriptFunction) throws Exception {
		return getPageNavigator(styleId, currentPage, linePerPage, totalLine, null, scriptFunction, null);
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @param int
	 * @param int
	 * @param long
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getPageNavigator(String styleId, int currentPage, int linePerPage, long totalLine, String pageURL, String pageString) throws Exception {
		
		int pos1, pos2;
		pos1 = pageURL.indexOf(pageString + "=");
		
		if (pos1 > -1) {
			pos2 = pageURL.indexOf("&", pos1);
			if (pos2 > -1)
				pageURL = pageURL.substring(0, pos1) + pageURL.substring(pos2 + 1);
			else
				pageURL = pageURL.substring(0, pos1);
		}
		
		if ((pageURL.length() > 1) && (pageURL.substring(pageURL.length() - 1, pageURL.length()).equals("&")))
			pageURL = pageURL.substring(0, pageURL.length() - 1);
		
		pos1 = pageURL.indexOf("?");
		if (pos1 < 0)
			pageURL = pageURL + "?";
		
		String pages = getPageNavigator(styleId, currentPage, linePerPage, totalLine, pageURL, null, pageString);
		
		return pages.replaceAll("\\?&", "?");
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @param int
	 * @param int
	 * @param long
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	private static String getPageNavigator(String styleId, int currentPage, int linePerPage, long totalLine, String pageURL, String scriptFunction, String pageString) throws Exception {
		
		if (styleId == null)
			styleId = "default";
		
		PagingLoader loader 	= PagingLoader.getInstance();
		// JRE 1.5.x
		PagingInfo pageNavInfo = loader.getPagingInfo(styleId);
		// JRE 1.4.x
		//PagingInfo pageNavInfo = loader.getPagingInfo(styleId);
		
		
		if (pageNavInfo == null) {
			logger.error("[kr.co.ipopcorn.util.paging.PagingHandler.getPageNavigator()] Can not found page style '" + styleId + "'.");
			return null;
		}
		
		StringBuffer pages = new StringBuffer();
		
		try {
			
			int totalPages = (int)Math.ceil((double)totalLine / (double)linePerPage);
		
			if (totalPages < 1)
				totalPages = 1;
			
			if ((currentPage > 0) && (currentPage <= totalPages) && (linePerPage > 0) && (linePerPage < 101)) {
				int pageCount2 = (int)Math.ceil((double)currentPage / (double)LINE_PER_PAGE) * LINE_PER_PAGE;
				int pageCount1 = pageCount2 - LINE_PER_PAGE + 1;
				
				if (pageCount2 > totalPages)
					pageCount2 = totalPages;
				
				pages.append(pageNavInfo.getContainerOpen());
		
				if (currentPage == 1)
					pages.append(pageNavInfo.getButtonFirst());
				else {
					if (pageURL != null)
						pages.append(pageNavInfo.getButtonFirstLink().replaceFirst(pageNavInfo.getButtonFirstLinkReplaceLink(),
										pageURL + "&" + pageString + "=1"));
					else
						pages.append(pageNavInfo.getButtonFirstLink().replaceFirst(pageNavInfo.getButtonFirstLinkReplaceLink(),
										"javascript:" + scriptFunction + "(1)"));
				}
				
				if (currentPage <= 10)
					pages.append(pageNavInfo.getButtonPrev());
				else {
					if (pageURL != null)
						pages.append(pageNavInfo.getButtonPrevLink().replaceFirst(pageNavInfo.getButtonPrevLinkReplaceLink(),
										pageURL + "&" + pageString + "=" + (int)(Math.ceil((double)currentPage / 10) * 10 - 19)));
					else
						pages.append(pageNavInfo.getButtonPrevLink().replaceFirst(pageNavInfo.getButtonPrevLinkReplaceLink(),
										"javascript:" + scriptFunction + "(" + (int)(Math.ceil((double)currentPage / 10) * 10 - 19) + ")"));
				}
				
				for (int count = pageCount1; count <= pageCount2; count++) {
					
					if (count == currentPage)
						pages.append(pageNavInfo.getPageCurrent().replaceFirst(pageNavInfo.getPageCurrentReplacePage(), String.valueOf(count)));
					else {
						String pageLink2 = pageNavInfo.getPageLink().replaceFirst(pageNavInfo.getPageLinkReplacePage(), String.valueOf(count));
						if (pageURL != null)
							pages.append(pageLink2.replaceFirst(pageNavInfo.getPageLinkReplaceLink(), pageURL + "&" + pageString + "=" + count));
						else
							pages.append(pageLink2.replaceFirst(pageNavInfo.getPageLinkReplaceLink(), "javascript:" + scriptFunction + "(" + count
											+ ")"));
					}
					
					if (count < pageCount2) {
						if (pageNavInfo.getPageSeparator() != null)
							pages.append(pageNavInfo.getPageSeparator());
					}
				}
				
				if (Math.ceil((double)currentPage / 10) == Math.ceil((double)totalPages / 10))
					pages.append(pageNavInfo.getButtonNext());
				else {
					if (pageURL != null)
						pages.append(pageNavInfo.getButtonNextLink().replaceFirst(pageNavInfo.getButtonNextLinkReplaceLink(),
										pageURL + "&" + pageString + "=" + (int)(Math.ceil((double)currentPage / 10) * 10 + 1)));
					else
						pages.append(pageNavInfo.getButtonNextLink().replaceFirst(pageNavInfo.getButtonNextLinkReplaceLink(),
										"javascript:" + scriptFunction + "(" + (int)(Math.ceil((double)currentPage / 10) * 10 + 1) + ")"));
				}
				
				if (currentPage == totalPages)
					pages.append(pageNavInfo.getButtonLast());
				else {
					if (pageURL != null)
						pages.append(pageNavInfo.getButtonLastLink().replaceFirst(pageNavInfo.getButtonLastLinkReplaceLink(),
										pageURL + "&" + pageString + "=" + totalPages));
					else
						pages.append(pageNavInfo.getButtonLastLink().replaceFirst(pageNavInfo.getButtonLastLinkReplaceLink(),
										"javascript:" + scriptFunction + "(" + totalPages + ")"));
				}
				pages.append(pageNavInfo.getContainerClose());
			}
			
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.paging.PagingHandler.getPageNavigator()] Error occured during generate page navigator: " + e.getMessage(), e);
			throw new Exception("[kr.co.ipopcorn.util.paging.PagingHandler.getPageNavigator()] Error occured during generate page navigator: " + e.getMessage());
		}
		
		return pages.toString();
	}

}