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
 * @since 2014-07-14
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Email.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140714133200][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.spring;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-07-14
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
@Component
public class Email {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(Email.class);
	
	@Autowired
	protected JavaMailSender mailSender;
	
	public void sendMail(String to, String subject, String text) throws Exception {
			
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(text);
			
			mailSender.send(mimeMessage);
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.spring.Email.sendMail()]" + e.getMessage(), e);
		}
	}
}
