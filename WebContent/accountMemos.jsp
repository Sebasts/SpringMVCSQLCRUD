<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/Master.css" />" rel="stylesheet" >
<title>Your memos</title>
</head>
<body>
<form action="newMemo.do" method="POST">
<div id="newMEMO">
<label>Memo title</label><input type="text" name="name"><br>
<input type="hidden" name="user" value="${user}"><br>
<input type="text" name="content" style="height:40px; width:280px;">
<button type="submit">Create new Memo</button>
</div>
</form>

	<h1>Here are your memos!</h1>
	<div id="mainContainer">
		<c:forEach var="memos" items="${memo}">
		  <div id="memoBlock">
			<label style="color:blue"> ${memos.name}
				<div style="color:black">${memos.content}</div>
			</label>
			</div>
		</c:forEach>
	</div>
	
<div>


</div>	
</body>
</html>