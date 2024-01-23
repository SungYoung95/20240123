<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<p>${username}</p>

<!-- 도메인 선택 스크립트 -->
<script src="https://lib.yongin.go.kr/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		const domainListEl = document.querySelector('#domain-list');
		const domainInputEl = document.querySelector('#domain-txt');
		// select 옵션 변경 시
		domainListEl.addEventListener('change',function(event) {
		// option에 있는 도메인 선택 시
			if (domainListEl.options[domainListEl.selectedIndex].value != "type") {
		// 선택한 도메인을 input에 입력하고 disabled
			domainInputEl.value = domainListEl.options[domainListEl.selectedIndex].value;
			domainInputEl.disabled = true;
			} else { // 직접 입력 시
		// input 내용 초기화 & 입력 가능하도록 변경
			domainInputEl.value = "";
			domainInputEl.disabled = false;
		}
	  });
	});
</script>
</head>
<body>

	<div align="center">
		<form action="/updateProc" method="post">
			<tr>
				<th width="80">아이디:</th>
				<td><input type="text" name="username" id="uid"placeholder="ID를 입력해주세요" />
					<button id="duplicateID"></td>
			</tr>
			중복
			</button>
			
			
			
			<br>
			<tr>
				<th width="80">비밀번호:</th>
				<td><input type="password" name="password"
					placeholder="PW를 입력해주세요" /></td>
			</tr>
			<br>
			<!-- <tr>
				<th width="80">비밀번호확인:</th>
				<td><input type="rpassword" name="rpassword"
					placeholder="PW를 다시해주세요" /></td>
			</tr>
			<br> -->
			<tr>
				<th width="80">닉네임:</th>
				<td><input type="text" name="nick" /></td>
			</tr>
			<br>
			<tr>
				<th width="80">이름:</th>
				<td><input type="text" name="uname"placeholder="${uname}" /></td>
			</tr>
			<br>
			<tr>
				<th width="80">전화번호:</th>
				<td><input type="text" name="tel" /></td>
			</tr>
			<br>
			<tr>
				<th width="80">생년월일:</th>
				<td>
				<input type="text" name="birth" />
				</td>
			</tr>
			<br>
			<tr>
				<th width="80">성별:</th>
				<td><input type="radio" name="gender" value="남자" checked>
					<font size=2>남</font>
					<input type="radio" name="gender" value="여자">
					<font size=2>여</font></td>
			</tr>
			<br>
			<th width="80">이메일:</th>
			<td><input type="text" name="useremail" />@ 
			<input id="domain-txt" type="text" name="edomain"/> 
				<select id="domain-list">
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="kakao.com">kakao.com</option>
					<option value="type" >직접 입력</option>
			</select></td>

			<br> <input type="submit" value="회원가입">
		</form>
	</div>
</body>



</body>
</html>