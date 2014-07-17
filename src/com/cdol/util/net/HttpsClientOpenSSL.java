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
 * @since 2014-04-16
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: HttpsClientOpenSSL.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140416152000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-04-16
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT: ���� ������������ �ƴ� OpenSSL ����� ���� ������������
 */
public class HttpsClientOpenSSL {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(HttpsClientOpenSSL.class);
	
	/** URL */
	private URL url = null;
	
	public HttpsClientOpenSSL(String url) {
		try {
			this.url = new URL(url);
		}
		catch (MalformedURLException e) {
			logger.error("[" + this.getClass().getName() + "]" + e.getMessage(), e);
		}
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-16
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier (
			new javax.net.ssl.HostnameVerifier() {
		
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("pluto.v-ipopcorn.co.kr")) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-16
	 * 
	 * @param String
	 * @param int
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public String connect(String requestJSON, int timeout) {
	
		String responseJSON = "";
	
		try {
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "application/json; charset=UTF-8");
			conn.setRequestProperty("accept", "application/json");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			OutputStream os 		= conn.getOutputStream();
			OutputStreamWriter osw 	= new OutputStreamWriter(os);
			osw.write(requestJSON);
			
			osw.flush();
			osw.close();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTPS Error Code: " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
			
			
			String line			= "";
			while ((line = br.readLine()) != null) {
				responseJSON += line;
			}
			
			conn.disconnect();
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".connect()]" + e.getMessage(), e);
		}
		
		return responseJSON;
	}
}