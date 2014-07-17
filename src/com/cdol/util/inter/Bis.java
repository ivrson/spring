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
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-17
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Bis.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140617000300][wscha@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.inter;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-17
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Bis {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Bis.class);
	
	/**
	 * @author wscha@i-popcorn.co.kr
	 * @since 2014-06-17
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public String restClient(String bis_url, String bis_serviceKey, String param) {
		
		String addr = bis_url +"?ServiceKey=";
		String serviceKey = bis_serviceKey;
		String parameter = param;
		String returnString = "";
		String inLine;
		
		try {
			//인증키(서비스키) url인코딩
			serviceKey = URLEncoder.encode(serviceKey, "UTF-8");
			
			addr = addr + serviceKey + parameter;
			
			URL url = new URL(addr);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8")); 
			
			while((inLine = in.readLine()) != null)
				returnString += inLine;
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.app.Bis.restClient()]" + e.getMessage(), e);
		}
		
		return returnString;
	}
	
}
