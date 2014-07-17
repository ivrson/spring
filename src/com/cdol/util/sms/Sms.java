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
 * @since 2014-07-04
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Sms.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140704000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.sms;

import java.io.IOException;
import java.util.ArrayList;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-07-04
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Sms {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Sms.class);
	
	/**
	 * @author wscha@i-popcorn.co.kr
	 * @since 2014-07-04
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static JSONObject sendSms(String url_api, int timeout, String sms_auth_idx, String no_phone, String no_callback, String sms_msg) throws Exception {
		
		JSONObject resultObj = null;
		
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url_api);
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new NameValuePair("nm_service", "safeAPI"));
		parameters.add(new NameValuePair("key_service", sms_auth_idx));
		parameters.add(new NameValuePair("no_phone", no_phone));
		parameters.add(new NameValuePair("no_callback", no_callback));
		parameters.add(new NameValuePair("sms_msg", sms_msg));
		parameters.add(new NameValuePair("dtm_req", ""));
		
		postMethod = new PostMethod(url_api);
		postMethod.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		for(NameValuePair param : parameters) {
			postMethod.addParameter(param);
		}
		
		int statusCode = client.executeMethod(postMethod);
		
		if (statusCode == HttpStatus.SC_OK) {
			try {
				byte[] responseBody = postMethod.getResponseBody();
				
				String resultStr = new String(responseBody);
				// logger.info("API Return(JSON) : " + resultStr);

				resultObj = JSONObject.fromObject(JSONSerializer.toJSON(resultStr));
			}
			catch (Exception e) {
				logger.error("[kr.co.ipopcorn.util.sms.SMS.sendSms()]" + e.getMessage(), e);
				resultObj = null;
			}
		}
		else {
			logger.error("[kr.co.ipopcorn.util.sms.SMS.sendSms()]" + statusCode + ": " + statusCode);
		}
		return resultObj;
		
	}
	
	/**
	 * @author wscha@i-popcorn.co.kr
	 * @since 2014-07-10
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION 폰번호를 -로 구분하여 전달
	 * <p>IMPORTANT
	 */
	public static String phoneNumberDash(String phonenumber){
		if (phonenumber != null && phonenumber.length() == 10) {
			phonenumber = phonenumber.substring(0, 3) + "-"
					+ phonenumber.substring(3, 6) + "-"
					+ phonenumber.substring(6);
		} else if (phonenumber != null && phonenumber.length() == 9) {
			phonenumber = phonenumber.substring(0, 3) + "-"
					+ phonenumber.substring(3, 7) + "-"
					+ phonenumber.substring(7);
		}
		return phonenumber;
	}	

}
