<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="loginstyle.css">
<title>Login Form</title>
</head>
<body>

	<div class="container" id="container">
		<div class="form-container sign-up">
			<form>
				
			
				<input type="text"
				placeholder="아이디">
				<input type="text"
				placeholder="Name">
				<input type="text"
				placeholder="Name">
				<input type="text"
				placeholder="Name">
				<input type="text"
				placeholder="Name">
				<input type="email"
				placeholder="Email">
				<input type="password"
				placeholder="Password">
				<button>Sign Up</button>
			</form>
		</div>
		<div class="form-container sign-in">
					
			<!-- <form action="<c:url value='j_spring_security_check'/>" method="post"> -->
			<c:url value='j_spring_security_check' var="loginUrl"/>
		 	<form action="${loginUrl}" method="post"> 
				<h1>로그인</h1>
			
				<span>or use your email password</span>
				<c:if test="${param.error != null}">
				<p>
					login Error!<br>
					${error_message}
				</p>
				</c:if>
				<input type="text" name="username"
				placeholder="아이디">
				<input type="password" name="password"
				placeholder="비밀번호">
				<a href="#">Forget Your Password?</a>
				<button type="submit" value="LOGIN">Sign In</button>
			</form>
		</div>
		<div class="toggle-container">
			<div class="toggle">
				<div class="toggle-panel toggle-left">
					<h1>회원가입</h1>
					<p>좋은 아침입니다!<br>지금 로그인하여 하루를 시작하세요!</p>
					<button class="hidden" id="login">Sign In</button>
				</div>
				<div class="toggle-panel toggle-right">
					<h1>반가워요!</h1>
					<p>우리 학원의 소소한 이야기부터 정보 공유까지, <br>지금 시작해보세요!</p>
					<button class="hidden" id="register">Sign Up</button>
				</div>
			</div>
		</div>
	</div>

	<script src="script.js"></script>

</body>
</html>