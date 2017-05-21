<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome back!</title>
</head>
<body>
<div id="loginBox" >
<form:form action="loginAttempt.do" method="POST" modelAttribute="user">
  <form:input path="email" />
  <form:errors path="email" />
  <br />
  <form:input path="password" />
  <form:errors path="password" />
  <br />
  <input type="submit" value="Login" />
</form:form>


</div>

</body>
</html>