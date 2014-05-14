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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author ivrson
 * @since 2014-04-10
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class XML {

	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param Node
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION: 노드 값 얻기(Get node value)
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
	 * @author ivrson
	 * @since 2014-04-10
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