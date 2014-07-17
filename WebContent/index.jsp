<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page info="/index.jsp" %><%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="content-script-type" content="text/javascript" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="expires" content="now" />
	<title>::: Cdol<%="(" + this.getServletInfo() + ")"%> :::</title>
	<link rel="stylesheet" href="/css/common.css" type="text/css" title="common stylesheet" />
	<link rel="shortcut icon" href="/image/favicon.ico" />
	<link rel="apple-touch-icon" href="/image/apple-touch-icon.png" />
</head>
<body>
<% /****************************** wrap start ******************************/ %>
<div id="wrap">
	<% /********** header start *************/ %>
	<header id="header"></header>
	<% /********** header end ***************/ %>
	<% /********** container start **********/ %>
	<section id="container">
		<div class="box">
			<ul>
				<li>(프론트 PC 웹 사이트)<em><a href="/front/">/front/</a></em></li>
				<li>(프론트 Mobile 웹 사이트)<em><a href="/front/mobile">/front/mobile/</a></em></li>
				<li>(백오피스 PC 웹 사이트)<em><a href="/backoffice/">/backoffice/</a></em></li>
				<li>(버스 API 테스트)<em><a href="/bus/">/bus/</a></em></li>
			</ul>
		</div>
		<div class="boxDesc">
			<p>구축중</p>
		</div>
	</section>
	<% /********** container end ************/ %>
	<% /********** footer start *************/ %>
	<footer id="footer">COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED</footer>
	<% /********** footer end ***************/ %>
</div>
<% /****************************** wrap end ********************************/ %>
</body>
</html>