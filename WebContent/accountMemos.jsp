<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="./Master.css" />" rel="stylesheet">
<title>Your memos</title>
	<c:if test="${empty user}">
	<meta http-equiv="Refresh" content="0; url=./login.do">
	</c:if>
</head>
<body>


	<form action="logout.do" method="GET">
		<button type="submit">Logout</button>
	</form>
	<form action="newMemo.do" method="POST">

		<div id="newMEMO">
			<label>Memo title</label><input type="text" name="name"><br>
			<input type="hidden" name="id" value="${sessionScope.user.id}"> <input
				type="hidden" name="firstName" value="${sessionScope.user.firstName}"> <input
				type="hidden" name="lastName" value="${sessionScope.user.lastName}"> <input
				type="hidden" name="password" value="${sessionScope.user.password}"> <input
				type="hidden" name="email" value="${sessionScope.user.email}">
			<textarea name="content" style="height: 40px; width: 280px;"> </textarea>
			Date to email this to you: (date and time):
  			<input type="datetime-local" name="sendTime">
			<button type="submit">Create new Memo</button>
		</div>
		</label>
	</form>

	<h1>Here are your memos!</h1>
	<div id="mainContainer">
		<c:forEach var="memos" items="${memo}" varStatus="loop">
			<div id="memoBlock" style="color: blue">
				<form action="deleteMemo.do" method="POST">
				<textarea type="text" name="names" style=" width: calc(100% - 160px); border:none; background-color:grey; color:blue;">${memos.name}</textarea>
				
				<input type="hidden" name="id" value="${sessionScope.user.id}"> <input
				type="hidden" name="firstName" value="${sessionScope.user.firstName}"> <input
				type="hidden" name="lastName" value="${sessionScope.user.lastName}"> <input
				type="hidden" name="password" value="${sessionScope.user.password}"> <input
				type="hidden" name="email" value="${sessionScope.user.email}">
					<input type="hidden" value="${memos.id}" name="memId"> <input
						type="hidden" value="${loop.index}" name="index">
					<p style="color: black" onClick="this.contentEditable='true';"
						id="memoContent${memos.id}" name="content"><textarea type="text" name="content" style="border:none; background-color:grey">${memos.content}</textarea></p>
					<button type="submit" onclick="myFunction()">Remove</button>
					<input type="submit" formaction="updateMemo.do" value="Update"></input>
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