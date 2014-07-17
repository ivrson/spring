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
			//����Ű(����Ű) url���ڵ�
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
