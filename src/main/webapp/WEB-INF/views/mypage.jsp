<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://lib.yongin.go.kr/include/js/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="mypagestyle.css">
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>

	<div class="container" id="container">
		<div class="form-container up-date">
			<form action="/updateProfileProc" method="post" id="updateForm">
					<div>
					<label for="name">변경할 이름 :</label>
					<input type="text" name="name" value="${user.name}"
							placeholder="${user.name}"	required />
					</div>
					<div>
					<label for="nick">변경할 닉네임 :</label>
					<input type="text" name="nick" value="${user.nick}"
							placeholder=${nick} required />
					</div>
					<div>
					<label for="nick">변경할 전화번호 :</label>
					<input type="text" name="tel" value="${user.tel}" 
							placeholder=${tel} required />
					</div>
					<div>
					<label for="birth">변경할 생년월일 :</label>
					<input type="text" name="birth" value="${user.birth}"
							placeholder=${birth} required />
					</div>
					<div>
						<div id="genderController">
						<label for="gender">변경할 성별 : </label>
							<label for="genderController">성별 :</label>
					        <button id="maleButton" type="button" onclick="setGender('남자')">남자</button>
    				 		<button id="femaleButton" type="button" onclick="setGender('여자')">여자</button>
        				</div>
        		 		<input type="hidden" name="gender" id="gender" value="남자">
					</div>
					<div>
					<label for="useremail">변경할 이메일 : </label>
					<input type="text" name="useremail" value="${user.useremail}" 
								placeholder=${useremail} required />
					</div>
					<div>
					<label for="password">변경할 비밀번호 :</label>
					<input type="password" name="password"
						placeholder="Enter new password" />
					</div>

				<button type="submit">정보 변경</button>
			</form>
		</div>
		<div class="form-container sign-in">
			<form>
				<div>
					<label for="name">이름 : ${name}</label>
				</div>
				<div>
					<label for="nick">닉네임 : ${nick}</label>
				</div>
				<div>
					<label for="tel">전화번호 : ${tel}</label>
				</div>
				<div>
					<label for="birth">생년월일 : ${birth}</label>
				</div>
				<div>
					<label for="gender">성별 : ${gender}</label>
				</div>
				<div>
					<label for="useremail">이메일 : ${useremail}</label>
				</div>

				<div>		
				<button type="button" id="withdrawButton" onclick="location.href='/withdraw'">회원탈퇴</button>
				<button type="button" id="withdrawButton" onclick="location.href='/mainpage'">메인페이지</button>
				</div>
			</form>
		</div>
		<div class="toggle-container">
			<div class="toggle">
				<div class="toggle-panel toggle-left">
					<h1>정보변경</h1>
					<p>정보변경하는페이지.</p>
					<button class="hidden" id="login">돌아가기</button>
				</div>
				<div class="toggle-panel toggle-right">
					<h1>반가워요!</h1>
					<p>정보변경을 원하시면 이쪽을 눌러주세요</p>
					<button class="hidden" id="register">정보변경</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="script.js"></script>

</html>