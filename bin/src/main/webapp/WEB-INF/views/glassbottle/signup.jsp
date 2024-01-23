<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<div align="center">
		<form action="signup.do" method="post">
			아이디: <input type="text" name="userID"/><button id="duplicateID">중복</button><br>
		    비밀번호: <input type="password" name="userPW"><br>
		    
		    
		    <input type="submit" value="회원가입">
		</form>
	</div>
</body>
</html>