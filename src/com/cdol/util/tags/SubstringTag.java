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
 * File			: SubstringTag.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class SubstringTag extends TagSupport  {
	
	/** Serial version UID */
	private static final long serialVersionUID = 20140225000004L;	
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SubstringTag.class);
	
	
	private String text = "";
	private int length;
	
	public int doEndTag() throws JspException {
		
		String returnString = "";
		
		try {
			String value 	= getText();
			int size 		= getLength();
			
			int byteLen 	= 0;
			int len 		= value.length();
			int blen 		= value.getBytes().length;
			
			
			if (blen <= size) {
				returnString = value;
			}
			else {
				for (int i=0; i<len; i++) {
					String temp = value.charAt(i) + "";
					byteLen += temp.getBytes().length;
					
					if (byteLen <= size) {
						returnString = returnString + temp;
					}
					else {
						returnString += "..."; 
						break;
					}
				}
			}
			
			pageContext.getOut().print(returnString);
			
		}
		catch(Exception e){
			logger.error("[" + this.getClass().getName() + ".doEndTag()] Error occurred while process 'dotcom:page' tag: " + e.getMessage(), e);
		}
		
		return SKIP_BODY;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}