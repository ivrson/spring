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
 * @since 2014-06-28
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: BusRouteDto.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140628000000][wscha@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.moving.dto;

/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class BusRouteDto {
	/** �뼱 Sequence */
	private int seq			= 0;
	/** ������ id */
	private String id		= "";
	/** ������ �̸� */
	private String nme		= "";
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNme() {
		return nme;
	}
	public void setNme(String nme) {
		this.nme = nme;
	}
	
}
