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
 * File			: PagingLoader.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.paging;

import java.util.HashMap;
import java.util.Map;

import com.cdol.util.common.XML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class PagingLoader {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PagingLoader.class);
	
	/** Paging loader */
	private static PagingLoader instance 		= null;
	/** Paging info */
	// JRE 1.5.x
	private Map<String, PagingInfo> pagingMap 	= null;
	// JRE 1.4.x
	// private Map pagingMap = null;
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	private PagingLoader() {
		// JRE 1.5.x
		this.pagingMap = new HashMap<String, PagingInfo>();
		// JRE 1.4.x
		// this.pagingMap = new HashMap();
		
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @return PagingLoader
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static synchronized PagingLoader getInstance() {
		if (instance == null)
			instance = new PagingLoader();
		return instance;
	}	
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public void setPageNavigators(String fileName) throws Exception {
		
		try {
			this.pagingMap.clear();
			Document document = XML.getXmlFromFile(fileName);
			NodeList nodeList = XPathAPI.selectNodeList(document, "/pageNavigator/style");
			
			for (int index = 0; index < nodeList.getLength(); index++) {
				
				Element elem = (Element)nodeList.item(index);
				String styleId = elem.getAttribute("id");
				
				if (styleId != null) {
					
					PagingInfo pageNavInfo = new PagingInfo();
					
					pageNavInfo.setContainerOpen(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/containers/container[@name='containerOpen']/text()")));
					pageNavInfo.setContainerClose(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/containers/container[@name='containerClose']/text()")));					
					
					pageNavInfo.setButtonFirst(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonFirst']/text()")));
					pageNavInfo.setButtonFirstLink(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonFirstLink']/text()")));
					pageNavInfo.setButtonFirstLinkReplaceLink(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonFirstLink']")).getAttribute("replaceLink"));
					
					pageNavInfo.setButtonPrev(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonPrev']/text()")));
					pageNavInfo.setButtonPrevLink(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonPrevLink']/text()")));
					pageNavInfo.setButtonPrevLinkReplaceLink(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonPrevLink']")).getAttribute("replaceLink"));
					
					pageNavInfo.setButtonNext(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonNext']/text()")));
					pageNavInfo.setButtonNextLink(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonNextLink']/text()")));
					pageNavInfo.setButtonNextLinkReplaceLink(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonNextLink']")).getAttribute("replaceLink"));
					
					pageNavInfo.setButtonLast(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonLast']/text()")));
					pageNavInfo.setButtonLastLink(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonLastLink']/text()")));
					pageNavInfo.setButtonLastLinkReplaceLink(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/buttons/button[@name='buttonLastLink']")).getAttribute("replaceLink"));
					
					pageNavInfo.setPageSeparator(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='separator']/text()")));
					
					pageNavInfo.setPageCurrent(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='pageCurrent']/text()")));
					pageNavInfo.setPageCurrentReplacePage(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='pageCurrent']")).getAttribute("replacePage"));
					
					pageNavInfo.setPageLink(XML.getNodeValue(XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='pageLink']/text()")));
					pageNavInfo.setPageLinkReplacePage(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='pageLink']")).getAttribute("replacePage"));
					pageNavInfo.setPageLinkReplaceLink(((Element)XPathAPI.selectSingleNode(document, "/pageNavigator/style[@id='" + styleId
									+ "']/pages/page[@name='pageLink']")).getAttribute("replaceLink"));
					
					this.pagingMap.put(styleId, pageNavInfo);
				}
			}
			logger.info("[" + this.getClass().getName() + ".setPageNavigators()] " + fileName + " is loaded.");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".setPageNavigators()] Error occured during load " + fileName + ".", e);
		}

	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @return PagingInfo
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	protected PagingInfo getPagingInfo(String styleId) {
		// JRE 1.5.x
		PagingInfo pageNavInfo = this.pagingMap.get(styleId);
		// JRE 1.4.x
		// PagingInfo pageNavInfo = (PagingInfo)this.pagingMap.get(styleId);
		return pageNavInfo;
	}

}