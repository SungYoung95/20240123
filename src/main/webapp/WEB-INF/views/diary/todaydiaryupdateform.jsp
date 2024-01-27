 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <!DOCTYPE html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js">
</script>
<script>
    function logout() {
        // Perform any additional logout actions if needed
        
        // Redirect to the "/logout" URL
        window.location.href = "/logout";
    }
$(document).ready(function(){
    // 오늘의 일기 삭제
	$("#deleteTD").click(function(){
		//alert("deleteTD clicked!");
		var dID = $("#deleteTD").attr('value');
		console.log("dID : " + dID);
		$.ajax({
			url:"/diary/today/delete",
			type:"POST",
			data:{
				dID:dID
			},
			success:function(){
				//console.log('deleteTodayDiary success');
				location.href='/diary/todayDiary';
			},
			error:function(){
				//alert('deleteTodayDiary Error');
			}
		});
	});
    // 오늘의 일기 수정
    $("#updateTD").click(function(){
    	alert("updateTD clicked!");
    	var diary = $("#updateTD").attr('value');
    	console.log("diary : " + diary);
    	$.ajax({
			url:"/diary/today/updateForm",
			type:"POST",
			data:{
				diary:diary
			},
			success:function(){
				console.log('deleteTodayDiary success');
				//location.href='/diary/todayDiary';
			},
			error:function(){
				alert('deleteTodayDiary Error');
			}
		});
    });
});
</script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>todayDiary</title>
    <!-- ICONSCOUT CDN 아이콘 사이트 CDN -->
   
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
    <!-- STYLESHEET 스타일 시트 css 적용-->
    <link rel="stylesheet" href="../style.css">
</head>
    <!---  상단 ---->
    <%@ include file="index_nav.jsp" %>

    <!------------------ MAIN ------------------------>
    <main>
        <div class="container">
            <!-----===========  왼쪽  =================-------->
            <%@ include file="index_left.jsp" %>



            <!-------================= 중앙 ===============-------->
            <div class="middle">
                <!-------------------- STORIES --------------------->
                <div class="stories">
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-8.jpg" >
                        </div>
                        <p class="name" id="dTITLE">Your Story</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-5.jpg" >
                        </div>
                        <p class="name" id="dFROMID">Lilla James</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-10.jpg" >
                        </div>
                        <p class="name">Winnie Hale</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-11.jpg" >
                        </div>
                        <p class="name">Daniel Bale</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-12.jpg" >
                        </div>
                        <p class="name">Jane Doe</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="../images/profile-13.jpg" >
                        </div>
                        <p class="name">Tina White</p>
                    </div>
                </div>
			<!-- -------END OF STORIES --------- -->
				<form class="create-post">
					<div class="profile-photo">
						<img src="../images/profile-1.jpg">
					</div>
					<input type="text" placeholder="What's on your mind, Diana?">
					<input type="submit" value="Post" class="btn btn-primary">
				</form>
				<c:set var="diary" value="${diary}"/>
				        <!-- var1 is empty or null. -->
				        <br>
				        <form action="/diary/update" method="POST" enctype="multipart/form-data">
				        	<input type="hidden" name='dID' id='dID' value='${diary.getDID()}'/>
					        제목: <input type='text' name='dTITLE' id='dTITLE' size='30' maxlength='100' value='${diary.getDTITLE()}'><br>
							  	<textarea name='dCONTENT' id='dCONTENT'>${diary.getDCONTENT()}</textarea><br>
							감정: <input type='radio' name='dEMOTION' value='HAPPY'/>즐거움
								  <input type='radio' name='dEMOTION' value='SAD'/>슬픔<br>
							날씨: <select name='dWEATHER' id='dWEATHER'>
									<option value='SUN'>맑음</option>
									<option value='CLOUD'>구름</option>
									<option value='RAIN'>비</option>
									<option value='SNOW'>눈</option>
								   </select>
									<br>
							<input id='uploadFile' type='file' name='file' class='fileInput' accept='image/*' />
							<button class='updateDiary' type='submit'>작성</button>&nbsp;&nbsp;
							<button class='goMain' type='reset'>취소</button>
						</form>
				    
				



			</div>
			<!--  ================= END OF MIDDLE ================= -->
			<%@ include file="index_right.jsp" %>
			

			</div>
		</div>
	</main>
</html>