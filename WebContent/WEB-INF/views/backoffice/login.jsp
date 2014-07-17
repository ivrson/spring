<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<form method="post" action="/backoffice/login.do">
		<table width="300" border="1">
			<tr>
				<td width="100">아이디</td>
				<td width="100"><input name="id" type="text" /></td>
			</tr>
			<tr>
				<td width="100">비번</td>
				<td width="100"><input name="passwd" type="passwd" /></td>
			</tr>
			<tr>
				<td width="100"></td>
				<td width="100"><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
	<form method="post" action="/backoffice/config/manageWriteForm.do">
		<table>
			<tr>
				<td width="100"><input type="submit" value="등록" /></td>
			</tr>
		</table>l
	</form>
</body>
</html>