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
 * @since 2014-06-16
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: PushGcmDto.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140616000000][wscha@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.common.dto;

/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-16
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class PushGcmDto {
	/* 푸시 송신자 idx */
	private int send_usr_idx 	= 0;
	/* 푸시 수신자 idx */
	private int recv_usr_idx 	= 0;
	/* 푸시 수신자 id */
	private String push_id		= "";
	/* 푸시 수신자 이름 */
	private String recv_usr_nme	= "";
	/* 전송할 푸시 제목 */
	private String title		= "";
	/* 전송할 푸시 내용 */
	private String contents		= "";
	/* 수신자 전화번호 */
	private String recv_tel		= "";
	/* 메세지 타입 */
	private String meg_type		= "";
	
	public int getSend_usr_idx() {
		return send_usr_idx;
	}
	public void setSend_usr_idx(int send_usr_idx) {
		this.send_usr_idx = send_usr_idx;
	}
	public int getRecv_usr_idx() {
		return recv_usr_idx;
	}
	public void setRecv_usr_idx(int recv_usr_idx) {
		this.recv_usr_idx = recv_usr_idx;
	}
	public String getPush_id() {
		return push_id;
	}
	public void setPush_id(String push_id) {
		this.push_id = push_id;
	}
	public String getRecv_usr_nme() {
		return recv_usr_nme;
	}
	public void setRecv_usr_nme(String recv_usr_nme) {
		this.recv_usr_nme = recv_usr_nme;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRecv_tel() {
		return recv_tel;
	}
	public void setRecv_tel(String recv_tel) {
		this.recv_tel = recv_tel;
	}
	public String getMeg_type() {
		return meg_type;
	}
	public void setMeg_type(String meg_type) {
		this.meg_type = meg_type;
	}
	
}
