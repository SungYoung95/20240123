 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <!DOCTYPE html>
<html lang="en">
<script>
    function logout() {
        // Perform any additional logout actions if needed

        // Redirect to the "/logout" URL
        window.location.href = "/logout";
    }
</script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>nokoSocial - Responsive Social Media WebSite Using, css &
    JavaScript
    </title>
    <!-- ICONSCOUT CDN 아이콘 사이트 CDN -->
   
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
    <!-- STYLESHEET 스타일 시트 css 적용-->
    <link rel="stylesheet" href="./style.css">
</head>
    <!---  상단 ---->
    <nav>
        <div class="container">
            <h2>
                <!-- 상단 왼쪽 로고 -->
                <h2 class="log">
                    유리병
                </h2>
                <!-- 상단 검색창 -->
                <div class="search-bar">
                    <i class="uil uil-search" ></i>
                    <input type="search" placeholder="Search for creators, inspirations, and projects">
                </div>
                <div class="create">
                    <label class="btn btn-primary" for="create-post" onclick="logout()">로그아웃</label>
                 
                </div>
                
            </h2>

        </div>
    </nav>

    <!------------------ MAIN ------------------------>
    <main>
        <div class="container">
            <!-----===========  왼쪽  =================-------->
            <div class="left">
                <!---- 프로필 ----->
                <a class="profile" href="/mypage">>
                    <div class="profile-photo">
                        <img src="./images/profile-1.jpg">
                    </div>
                    <div class="handle" >
                        <h4>${username}님</h4>
                        <p class="text-muted">
                        </p>
                    </div>
                  
                </a>
                <!-------------- 사이드바 ---------------------->
                <div class="sidebar">
                    <a class="menu-item active">
                        <span><i class="uil uil-home"></i></span><h3>Home</h3>
                    </a>
                    <a class="menu-item">
                        <span><i class="uil uil-compass"></i></span><h3>todayDiary</h3>
                    </a>
                    <a class="menu-item" id="notifications">
                        <span><i class="uil uil-bell"><small class="notification-count">9+</small></i></span><h3>sharedDairy</h3>
                        <!------------------- NOTIFICATION POPUP ---------------------->
                        <div class="notifications-popup">
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-2.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>Keke Benjamin</b> accepted your friend request
                                    <small class="text-muted">2 DAYS AGO</small>
                                </div>
                            </div>
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-3.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>John Doe</b> commented on your post
                                    <small class="text-muted">1 HOUR AGO</small>
                                </div>
                            </div>
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-4.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>Mary Oppong</b> and <b>283 others</b> liked your post
                                    <small class="text-muted">4 MINUTES AGO</small>
                                </div>
                            </div>
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-5.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>Doris Y. Lartey</b> commented on a post you are tagged
                                    <small class="text-muted">2 DAYS AGO</small>
                                </div>
                            </div>
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-6.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>Donald Trump</b> commented on a post you are tagged
                                    <small class="text-muted">1 HOUR AGO</small>
                                </div>
                            </div>
                            <div>
                                <div class="profile-photo">
                                        <img src="./images/profile-7.jpg" >
                                </div>
                                <div class="notification-body">
                                    <b>jane Doe</b> commented on your post
                                    <small class="text-muted">1 HOUR AGO</small>
                                </div>
                            </div>
                        </div>
                        <!----------------------- END NOTIFICATION POPUP -------------------->
                    </a>
                    <a class="menu-item" id="messages-notifications">
                        <span><i class="uil uil-envelope-alt" ><small class="notification-count">6</small></i></span><h3>Message</h3>
                    </a>
                    <a class="menu-item" href="/friends">
                        <span><i class="uil uil-bookmark"></i></span><h3>friends</h3>
                    </a>
                    <a class="menu-item">
                        <span><i class="uil uil-chart-line"></i></span><h3>lastDiary</h3>
                    </a>
                    <a class="menu-item">
                        <span><i class="uil uil-palette"></i></span><h3>미정</h3>
                    </a>
                    <a class="menu-item">
                        <span><i class="uil uil-setting"></i></span><h3>미정</h3>
                    </a>
                </div>
                <!--------------------- 사이드바 끝 ----------------------->
               
            </div>



            <!-------================= 중앙 ===============-------->
            <div class="middle">
                <!-------------------- STORIES --------------------->
                <div class="stories">
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-8.jpg" >
                        </div>
                        <p class="name">Your Story</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-0.jpg" >
                        </div>
                        <p class="name">Lilla James</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-10.jpg" >
                        </div>
                        <p class="name">Winnie Hale</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-11.jpg" >
                        </div>
                        <p class="name">Daniel Bale</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-12.jpg" >
                        </div>
                        <p class="name">Jane Doe</p>
                    </div>
                    <div class="story">
                        <div class="profile-photo">
                            <img src="./images/profile-13.jpg" >
                        </div>
                        <p class="name">Tina White</p>
                    </div>
                </div>
			<!-- -------END OF STORIES --------- -->
				<form class="create-post">
					<div class="profile-photo">
						<img src="./images/profile-1.jpg">
					</div>
					<input type="text" placeholder="What's on your mind, Diana?">
					<input type="submit" value="Post" class="btn btn-primary">
				</form>

				<!--  -------------FEEDS-------------- -->
				<div class="feeds">
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
					<!--  ------------_FEED 1 ------------- -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="./images/profile-13.jpg">
								</div>
								<div class="ingo">
									<h3>Lana Rose</h3>
									<small>Dubai, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit"> <i class="uil uil-ellipsis-h"></i>
							</span>
						</div>

						<div class="photo">
							<img src="./images/feed-1.jpg">
						</div>

						<div class="action-button">
							<div class="interaction-buttons">
								<span><i class="uil uil-heart"></i></span> <span><i
									class="uil uil-comment-dots"></i></span> <span><i
									class="uil uil-share-alt"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark-full"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="./images/profile-10.jpg"></span> <span><img
								src="./images/profile-4.jpg"></span> <span><img
								src="./images/profile-15.jpg"></span>
							<p>
								Liked by<b>Ernest Achiever</b> and <b>2,323 others</b>
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Lana ROse</b> Lorem ipsum dolor sit quisquam eius. <span
									class="harsh-tag">#lifestyle</span>
							</p>
						</div>
						<div class="comments text-muted">View all 277 comments</div>
					</div>
					<!--  --------------END OF FEED --------------- -->
				</div>
				<!--  --------------END OF FEEDS --------------- -->



			</div>
			<!--  ================= END OF MIDDLE ================= -->

			<!--  ============RIGHT ============ -->
			<div class="right">
				<div class="messages">
					<div class="heading">
						<h4>Messages</h4>
						<i class="uil uil-edit"></i>
					</div>
					<!-- ---------SEARCH BAR----------- -->
					<div class="search-bar">
						<i class="uil uil-search"></i> <input type="search"
							placeholder="Search Messages" id="message-search">
					</div>
					<!-- ---------MESSAGES CATEGORY----------- -->
					<div class="category">
						<h6 class="active">Primary</h6>
						<h6>General</h6>
						<h6 class="message-requests">Requests(7)</h6>
					</div>
					<!-- -------MESSAGE--------- -->
					<div class="message">
						<div class="profile-photo">
							<img src="./images/profile-17.jpg">
						</div>
						<div class="message-body">
							<h5>Edem Quist</h5>
							<p class="text-muted">Just woke up bruh</p>
						</div>
					</div>
					<!-- -------MESSAGE--------- -->
					<div class="message">
						<div class="profile-photo">
							<img src="./images/profile-17.jpg">
							<div class="active"></div>
						</div>
						<div class="message-body">
							<h5>Edem Quist</h5>
							<p class="text-bold">2 new messages</p>
						</div>
					</div>
					<!-- -------MESSAGE--------- -->
					<div class="message">
						<div class="profile-photo">
							<img src="./images/profile-17.jpg">
						</div>
						<div class="message-body">
							<h5>Edem Quist</h5>
							<p class="text-muted">Just woke up bruh</p>
						</div>
					</div>
				</div>
				<!--  -----------END OF MESSAGES------------ -->



				<!--  ----------- FRIEND REQUESTS ------------ -->
				<div class="friend-requests">
					<h4>Requests</h4>
					<div class="request">
						<div class="info">
							<div class="profile-photo">
								<img src="./images/profile-13.jpg">
							</div>
							<div>
								<h5>Hajia Bintu</h5>
								<p class="text-muted">8 mutual friends</p>
							</div>
						</div>
						<div class="action">
							<button class="btn btn-primary">Accept</button>
							<button class="btn">Decline</button>
						</div>
					</div>
					<div class="request">
						<div class="info">
							<div class="profile-photo">
								<img src="./images/profile-13.jpg">
							</div>
							<div>
								<h5>Hajia Bintu</h5>
								<p class="text-muted">8 mutual friends</p>
							</div>
						</div>
						<div class="action">
							<button class="btn btn-primary">Accept</button>
							<button class="btn">Decline</button>
						</div>
					</div>
					<div class="request">
						<div class="info">
							<div class="profile-photo">
								<img src="./images/profile-13.jpg">
							</div>
							<div>
								<h5>Hajia Bintu</h5>
								<p class="text-muted">8 mutual friends</p>
							</div>
						</div>
						<div class="action">
							<button class="btn btn-primary">Accept</button>
							<button class="btn">Decline</button>
						</div>
					</div>
				</div>


			</div>
		</div>
	</main>
</html>