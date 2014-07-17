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
 * File			: PagingInfo.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.paging;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class PagingInfo {

	/** Container open */
	private String containerOpen 				= "";
	/** Container close */
	private String containerClose 				= "";
	
	/** Button first */
	private String buttonFirst 					= "";
	/** Button first link */
	private String buttonFirstLink 				= "";
	/** Button first link replace link */
	private String buttonFirstLinkReplaceLink 	= "";
	
	/** Button previous */
	private String buttonPrev 					= "";
	/** Button previous link */
	private String buttonPrevLink 				= "";
	/** Button previous link replace link */
	private String buttonPrevLinkReplaceLink 	= "";
	
	/** Button next*/
	private String buttonNext 					= "";
	/** Button next link */
	private String buttonNextLink 				= "";
	/** Button next link replace link */
	private String buttonNextLinkReplaceLink 	= "";
	
	/** Button last */
	private String buttonLast 					= "";
	/** Button last link */
	private String buttonLastLink 				= "";
	/** Button last link replace link */
	private String buttonLastLinkReplaceLink 	= "";

	/** Page separator */
	private String pageSeparator 				= "";

	/** Page current */
	private String pageCurrent 					= "";
	/** Page current replace page */
	private String pageCurrentReplacePage 		= "";
	
	/** Page link */
	private String pageLink 					= "";
	/** Page link replace page */
	private String pageLinkReplacePage 			= "";
	/** Page link replace link */
	private String pageLinkReplaceLink 			= "";	
	
	public String getButtonFirst() {
		return buttonFirst;
	}
	
	public void setButtonFirst(String buttonFirst) {
		this.buttonFirst = buttonFirst;
	}
	
	public String getButtonFirstLink() {
		return buttonFirstLink;
	}
	
	public void setButtonFirstLink(String buttonFirstLink) {
		this.buttonFirstLink = buttonFirstLink;
	}
	
	public String getButtonFirstLinkReplaceLink() {
		return buttonFirstLinkReplaceLink;
	}
	
	public void setButtonFirstLinkReplaceLink(String buttonFirstLinkReplaceLink) {
		this.buttonFirstLinkReplaceLink = buttonFirstLinkReplaceLink;
	}
	
	public String getButtonLast() {
		return buttonLast;
	}
	
	public void setButtonLast(String buttonLast) {
		this.buttonLast = buttonLast;
	}
	
	public String getButtonLastLink() {
		return buttonLastLink;
	}
	
	public void setButtonLastLink(String buttonLastLink) {
		this.buttonLastLink = buttonLastLink;
	}
	
	public String getButtonLastLinkReplaceLink() {
		return buttonLastLinkReplaceLink;
	}
	
	public void setButtonLastLinkReplaceLink(String buttonLastLinkReplaceLink) {
		this.buttonLastLinkReplaceLink = buttonLastLinkReplaceLink;
	}
	
	public String getButtonNext() {
		return buttonNext;
	}
	
	public void setButtonNext(String buttonNext) {
		this.buttonNext = buttonNext;
	}
	
	public String getButtonNextLink() {
		return buttonNextLink;
	}
	
	public void setButtonNextLink(String buttonNextLink) {
		this.buttonNextLink = buttonNextLink;
	}
	
	public String getButtonNextLinkReplaceLink() {
		return buttonNextLinkReplaceLink;
	}
	
	public void setButtonNextLinkReplaceLink(String buttonNextLinkReplaceLink) {
		this.buttonNextLinkReplaceLink = buttonNextLinkReplaceLink;
	}
	
	public String getButtonPrev() {
		return buttonPrev;
	}
	
	public void setButtonPrev(String buttonPrev) {
		this.buttonPrev = buttonPrev;
	}
	
	public String getButtonPrevLink() {
		return buttonPrevLink;
	}
	
	public void setButtonPrevLink(String buttonPrevLink) {
		this.buttonPrevLink = buttonPrevLink;
	}
	
	public String getButtonPrevLinkReplaceLink() {
		return buttonPrevLinkReplaceLink;
	}
	
	public void setButtonPrevLinkReplaceLink(String buttonPrevLinkReplaceLink) {
		this.buttonPrevLinkReplaceLink = buttonPrevLinkReplaceLink;
	}
	
	public String getPageCurrent() {
		return pageCurrent;
	}
	
	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	
	public String getPageCurrentReplacePage() {
		return pageCurrentReplacePage;
	}
	
	public void setPageCurrentReplacePage(String pageCurrentReplacePage) {
		this.pageCurrentReplacePage = pageCurrentReplacePage;
	}
	
	public String getPageLink() {
		return pageLink;
	}
	
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	
	public String getPageLinkReplaceLink() {
		return pageLinkReplaceLink;
	}
	
	public void setPageLinkReplaceLink(String pageLinkReplaceLink) 	{
		this.pageLinkReplaceLink = pageLinkReplaceLink;
	}
	
	public String getPageLinkReplacePage() {
		return pageLinkReplacePage;
	}
	
	public void setPageLinkReplacePage(String pageLinkReplacePage) {
		this.pageLinkReplacePage = pageLinkReplacePage;
	}
	
	public String getPageSeparator() {
		return pageSeparator;
	}
	
	public void setPageSeparator(String pageSeparator) {
		this.pageSeparator = pageSeparator;
	}
	
	public String getContainerClose() {
		return containerClose;
	}
	
	public void setContainerClose(String containerClose) {
		this.containerClose = containerClose;
	}
	
	public String getContainerOpen() {
		return containerOpen;
	}
	
	public void setContainerOpen(String containerOpen) {
		this.containerOpen = containerOpen;
	}
}