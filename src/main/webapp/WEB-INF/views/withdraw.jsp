<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="loginstyle.css">
<title>Insert title here</title>
</head>
<body>
		<div class="container" id="container">
			<form   action="/signupProc" method="post" id="signupForm">
				<a>회원 탈퇴시 서비스를 더 이상 이용할수 없습니다 탈퇴를 진행하겠습니까 ?</a>



				<!-- <form action="/withdrawProc" method="post"> -->
					<div>
						<input type="password" name="password" placeholder="현재 비밀번호 입력"
							required />
					</div>
					
						<button type="submit">탈퇴하기</button>
				</form>
		</div>












</body>
</html>