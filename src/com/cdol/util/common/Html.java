/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 cdol에 있으며,
 * cdol이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * cdol의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 cdol All Rights Reserved.
 *
 *
 * @author ivrson
 * @since 2014-02-25
 * @version 1.0.0
 *
 *
 * Program		: com.cdol
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SystemInterceptor.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140225000000][ivrson][CREATE: Initial Release]
 */
package com.cdol.util.common;

import com.cdol.util.common.Strings;

/**
 * @author ivrson
 * @since 2014-04-10
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Html {
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION: 이스케이프 얻기(Get Escape)
	 * <p>IMPORTANT
	 */
	public static String getEscape(boolean isCrlf, String value) {
		if (value == null || value.length() == 0) return value;
		
		value = value.trim();
		
		if (isCrlf) {
			value = Strings.getReplaced(value, "\r", "<br>\n");
			value = Strings.getReplaced(value, "\n", "");
		}
		value = Strings.getReplaced(value, " ", "&nbsp;");
		value = Strings.getReplaced(value, "'", "&apos;");
		value = Strings.getReplaced(value, "\"", "&quot;");
		value = Strings.getReplaced(value, "<", "&lt;");
		value = Strings.getReplaced(value, ">", "&gt;");
		value = Strings.getReplaced(value, "&", "&amp;");
		
		value = value.trim();
		
		return value;
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getRestrictedTag(String value) {
		
		if (value == null) return "";
		
		value = value.replaceAll("(?i)<script"	, "<script");
		value = value.replaceAll("(?i)<iframe"	, "<iframe");
		value = value.replaceAll("(?i)<object"	, "<object");
		value = value.replaceAll("(?i)<embed"	, "<embed");
		value = value.replaceAll("(?i)<div"		, "<div");

		value = setComment(value, "<script");
		value = setComment(value, "<iframe");
		value = setComment(value, "<object");
		value = setComment(value, "<embed");
		value = setComment(value, "<div");
		
		return value;
	}

	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String, String
	 * @return String
	 * <p>DESCRIPTION: Comment 설정(Get comment)
	 * <p>IMPORTANT
	 */
	public static String setComment(String value, String tagName) {
		
		if (value == null)
			return "";

		if (tagName == null)
			return value;

		StringBuffer sb = new StringBuffer(value);

		int pos1 = 0;
		while ((pos1 = sb.indexOf(tagName, pos1)) > -1) {

			int pos2 = 0;

			if ((pos2 = sb.indexOf(">", pos1)) > -1) {
				sb.insert(pos1 + 1, "!-- ");
				sb.insert(pos2 + 4, " --");
				pos1 += 7;
			}
		}
		
		return sb.toString();
	}
}
