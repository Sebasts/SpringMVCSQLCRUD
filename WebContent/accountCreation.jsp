<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Quick account creation</h2>
<form:form action="validate.do" method="POST" modelAttribute="user" id="registrationForm">
<label>First Name
<form:input path="firstName"/></label><br>
<label>Last Name
<form:input path="lastName"/></label><br>
<label>Email
<form:input path="email"/></label><br>
<label>Password
<form:input path="password" type="password"/> (Must be atleast 6 characters)</label><br>


<button type="submit">Create account!</button>
</form:form>
</body>
</html>