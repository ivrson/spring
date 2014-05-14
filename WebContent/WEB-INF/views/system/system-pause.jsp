<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 IPOPCORN ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 i-popcorn.co.kr에 있으며,
 * i-popcorn.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * i-popcorn.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-02-25
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.smartwallet
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /WEB-INF/views/system/system-pause.jsp
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140225000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page info="/WEB-INF/views/system/system-pause.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<div id="wrap">
	<header id="header"></header>
	<section id="container">
		<div class="errorDesc">
			<p><img src="/system/image/${LANG}/icon_error.gif" /></p>
			<p>SYSTEM PAUSE</p>
			<p><a href="/">[HOME]</a>&nbsp;<a href="javascript: history.back();">[BACK]</a></p>
		</div>
	</section>
	<footer id="footer"></footer>
</div>
</body>
</html>