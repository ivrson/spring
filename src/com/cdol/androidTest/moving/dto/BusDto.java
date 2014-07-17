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
 * File			: BusDto.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140616000000][wscha@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.moving.dto;

/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-16
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class BusDto {
	/** 차량번호 */
	private String lics_plat_no		= "";
	/** 정류장 id */
	private int stat_id				= 0;
	/** 정류소 이름 */
	private String stat_nme			= "";
	/** 버스 노선 번호 */
	private String bus_cors_full_no	= "";
	/** 버스 정지 Flag */
	private int is_stop				= 0;
	/** 버스 노선 수 */
	private int stat_count_			= 0;
	/** 버스 노선 List */
	private BusRouteDto[] stat_list	= null;
	
	public String getLics_plat_no() {
		return lics_plat_no;
	}
	public void setLics_plat_no(String lics_plat_no) {
		this.lics_plat_no = lics_plat_no;
	}
	public int getStat_id() {
		return stat_id;
	}
	public void setStat_id(int stat_id) {
		this.stat_id = stat_id;
	}
	public String getStat_nme() {
		return stat_nme;
	}
	public void setStat_nme(String stat_nme) {
		this.stat_nme = stat_nme;
	}	
	public String getBus_cors_full_no() {
		return bus_cors_full_no;
	}
	public void setBus_cors_full_no(String bus_cors_full_no) {
		this.bus_cors_full_no = bus_cors_full_no;
	}
	public int getIs_stop() {
		return is_stop;
	}
	public void setIs_stop(int is_stop) {
		this.is_stop = is_stop;
	}
	public int getStat_count_() {
		return stat_count_;
	}
	public void setStat_count_(int stat_count_) {
		this.stat_count_ = stat_count_;
	}
	public BusRouteDto[] getStat_list() {
		return stat_list;
	}
	public void setStat_list(BusRouteDto[] stat_list) {
		this.stat_list = stat_list;
	}
	
}
