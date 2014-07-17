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
 * File			: XML.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.common;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class XML {

	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param Node
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION: ��� �� ���(Get node value)
	 * <p>IMPORTANT
	 */
	public static String getNodeValue(Node node) throws Exception {
		
		if (node != null) {
			Node nodeCdata = node.getNextSibling();
			if ((nodeCdata != null) && (nodeCdata.getNodeType() == Node.CDATA_SECTION_NODE)) {
				return nodeCdata.getNodeValue();
			}
			else {
				// node.getTextContent().trim();
				return node.getNodeValue();
			}
		}
		return "";
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @return Document
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static Document getXmlFromFile(String filePath) throws Exception {
		DocumentBuilderFactory factory 	= DocumentBuilderFactory.newInstance();
		DocumentBuilder builder 		= factory.newDocumentBuilder();

		filePath = Strings.getReplaced(filePath, "\\", "/");
		
		int isWindows = filePath.indexOf(":");
		
		if (isWindows != -1) {
			filePath = "/" + filePath;
		}
		
		Document document = builder.parse(filePath);
		
		return document;
	}
}