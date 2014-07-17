/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * �섍린 �꾨줈洹몃옩����븳 ��옉沅뚯쓣 �ы븿��吏�쟻�ъ궛沅뚯� i-popcorn.co.kr���덉쑝硫�
 * i-popcorn.co.kr��紐낆떆�곸쑝濡��덉슜�섏� �딅뒗 �ъ슜, 蹂듭궗, 蹂�꼍 諛���3�먯뿉 �섑븳 怨듦컻, 諛고룷���꾧꺽��湲덉��섎ŉ
 * i-popcorn.co.kr��吏�쟻�ъ궛沅�移⑦빐���대떦�쒕떎.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-06-02
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Gcm.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140602170300][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.app;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-06-02
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Gcm {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Gcm.class);
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-06-02
	 * 
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String sendPush(String apiKey, String regId, String title, String contents, String msg_type, String target) {
		
		String returnCode = "0000";
		
		try {	
			if (title == null || title.equals("")) {
				returnCode = "7001";
			}
			if (contents == null || contents.equals("")) {
				returnCode = "7001";
			}	
			if (regId == null || regId.equals("")){
				return "7002";
			}
			Sender sender = new Sender(apiKey);
			
			Message message = new Message.Builder()
				// Random Key for GCM
				.collapseKey(String.valueOf(Math.random() % 100 + 1))
				// Data for GCM
				.addData("title", title)
				.addData("contents", contents)
				.addData("msg_type", msg_type)
				.addData("target", target)
				
				// Always Display Message
				.delayWhileIdle(false)
				// Time(Second) to live when device is idle
				.timeToLive(3)
				.build();
			
			// 3: Retry for GCM
			Result result = sender.send(message, regId, 3);
			
			if (result.getMessageId() == null) {
				logger.error("[kr.co.ipopcorn.util.app.Gcm.sendPush().getErrorCodeName()]" + result.getErrorCodeName());
				if (result.getErrorCodeName().equals("MissingRegistration")) {
					returnCode = "7002";
				}
				else {
					returnCode = "7000";
				}
			}
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.app.Gcm.sendPush()]" + e.getMessage(), e);
			returnCode = "9100";
		}
		
		return returnCode;
	}
	
	/*
	public static void main(String[] args) {

		try {
			Gcm.sendPush("AIzaSyA9QdwxEmtM7CXLTUaqDR55J6YIi_WlRvE"					
					, "APA91bEh1ynXJ5tqjFVUhcZvA4cabPXcJpvGVxssvIXaQY76j-Nm7EmkhFSYt3JL856SJhN-zPOVv2WudPIcYk_TuFq0kREI7yoJOdHNAXUv5dnaXTKaHdndy2KGdP3WdgmA9nj14OEnhkQdrpGkgeTdS91NArXRTg"
					, "�쒕ぉ"
					, "�댁슜");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
