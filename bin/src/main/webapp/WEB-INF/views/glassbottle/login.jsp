<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<div align="center">
		<form action="login.do" method="post">
			ID: <input type="text" name="userID"><br>
			PW: <input type="password" name="userPW"><br><br>
			<input type="submit" value="로그인">
		</form>
		
		<br><br>
		<a href="signup.do">회원가입</a>
	</div>
</body>
</html>