<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="../Master.css" />" rel="stylesheet">
<title>Your memos</title>
</head>
<body>
	<form action="newMemo.do" method="POST">

		<div id="newMEMO">
			<label>Memo title</label><input type="text" name="name"><br>
			<input type="hidden" name="id" value="${user.id}">
			<input type="hidden" name="firstName" value="${user.firstName}">
			<input type="hidden" name="lastName" value="${user.lastName}">
			<input type="hidden" name="password" value="${user.password}">
			<input type="hidden" name="email" value="${user.email}">
			<textarea name="content" style="height: 40px; width: 280px;"> </textarea>
			<button type="submit">Create new Memo</button>
		</div>
		</label>
	</form>

	<h1>Here are your memos!</h1>
	<div id="mainContainer">
		<c:forEach var="memos" items="${memo}" varStatus="loop">
			<div id="memoBlock" style="color: blue">
				${memos.name}
				<form action="deleteMemo.do" method="POST">
				<input type="hidden" value="${memos.id}" name="id">
				<input type="hidden" value="${loop.index}" name="index">
				<p style="color: black" onClick="this.contentEditable='true';" id="memoContent${memos.id}">${memos.content}</p>
				<button type="submit" onclick="myFunction()">Remove</button>
				<script>
					function myFunction() {
						var x = document.getElementById('memoBlock');
						if (x.style.display === 'none') {
							x.style.display = 'block';
						} else {
							x.style.display = 'none';
						}
					}
				</script>
				</form>
			</div>
		</c:forEach>
	</div>

</body>
</html>