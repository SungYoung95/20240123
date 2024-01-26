<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="blackliststyle.css">
<title>블랙리스트 목록</title> 
    <style>
        #blacklist { /* 대소문자 일치 */
            overflow-y: auto;
            max-height: 400px;
        }
        .black-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container" id="container">
<h1>${username}</h1>
</div>
    <h1>블랙리스트</h1>
    <div id="blacklist"></div>
	<input type="text" id="searchInput" placeholder="이름으로 검색">
	<button onclick="searchBlacklistUsers()"> 검색 </button>
	
	<div id = "searchResults"></div>
	
    <script>
    
    // 수정 버전
    //블랙리스트 전체 리스트 로드하기
    function loadBlacklist() {
        var currentUserId = '${username}'; // 서버 사이드에서 설정된 사용자 ID

        fetch('/api/friends/blacklist/' + currentUserId) // 블랙리스트 조회 API
        	.then(function(response) {
            	return response.json();
       	 	})
        	.then(function(blacklistUsers) {
        		console.log("블랙리스트 전체 리스트 로드하기 첫번째 디버깅" + blacklistUsers); // 응답 데이터 로깅
            	var list = document.getElementById("blacklist");
            	list.innerHTML = "";
            
            	blacklistUsers.forEach(function(user) {
                var userDiv = document.createElement('div');
                userDiv.className = 'blacklist-item';
                userDiv.id = 'blacklist-user-' + user.id; // ID 할당
                userDiv.innerHTML = '<span>' + user.id + '</span>';
                
                // 블랙리스트 삭제 메서드
                // 수정 부분
                var removeBtn = document.createElement('button');
                removeBtn.innerText = '제거';
                removeBtn.onclick = function() {
                    removeFromBlacklist(currentUserId, user.id);
                 };

                userDiv.appendChild(removeBtn);
                list.appendChild(userDiv);
            });
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }
    
  //수정 버전
    //블랙리스트 검색창 api 
	function searchBlacklistUsers() {
    var name = document.getElementById("searchInput").value;
    var currentUserId = '${username}'; // 현재 사용자 ID
    var blacklistContainer = document.getElementById("blacklist"); // 기존 블랙리스트 컨테이너
    var resultsContainer = document.getElementById("searchResults"); // 검색 결과 컨테이너

    fetch('/api/friends/blacklist/search/' + currentUserId + '?name=' + encodeURIComponent(name))
        .then(response => response.json())
        .then(users => {
            resultsContainer.innerHTML = ""; // 이전 검색 결과 초기화

            // 검색 결과가 있을 경우, 기존 블랙리스트 숨기기
            if (users.length > 0) {
                blacklistContainer.style.display = 'none';
            } else {
                blacklistContainer.style.display = 'block'; // 검색 결과가 없으면 기존 블랙리스트를 다시 보여줌
            }

            users.forEach(user => {
                var userDiv = document.createElement('div');
                userDiv.className = 'blacklist-search-item';
                userDiv.id = 'blacklist-user-' + user.id; // 고유한 ID 할당
                userDiv.innerHTML = '<span>' + user.name + '</span>'; 

                var removeBtn = document.createElement('button');
                removeBtn.innerText = '블랙리스트에서 제거';
                removeBtn.onclick = function() {
                    removeFromBlacklist(currentUserId, user.id);
                };

                userDiv.appendChild(removeBtn);
                resultsContainer.appendChild(userDiv);
            });

            if (users.length === 0) {
                resultsContainer.innerHTML = '<p>검색 결과가 없습니다.</p>';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            resultsContainer.innerHTML = '<p>검색 중 오류가 발생했습니다.</p>'; 
            blacklistContainer.style.display = 'block'; // 오류가 발생하면 기존 블랙리스트를 다시 보여줌
        });
	 }
	
    //수정 버전
 	// 블랙리스트에서 사용자 제거 함수 수정
    function removeFromBlacklist(currentUserId, blockedId) {
        fetch('/api/friends/removeFromBlacklist/' + currentUserId + '/' + blockedId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (response.ok) {
                // 페이지를 새로 고침하여 최신 상태의 블랙리스트를 보여줌
                location.reload();
            } else {
                throw new Error('Something went wrong on api server!');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
 

    }
    
        window.onload = loadBlacklist;
    </script>

</body>
</html>