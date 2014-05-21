<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <% System.out.println("login.jsp가 호출됨");  %>
   <c:if test="${id=='a'}" >
       <c:if test="${passwd=='a'}" >
            <jsp:forward page="success.xml"/>
       </c:if>
   </c:if>
   <jsp:forward page="failed.xml"/>
</body>
</html>