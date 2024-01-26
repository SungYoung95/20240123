<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="friendsstyle.css">
    <title>친구 추가 및 블랙리스트 추가</title>
</head>
<body>
	<h1>${username}</h1>
    <h1>사용자 검색</h1>
    <input type="text" id="searchInput" placeholder="사용자 이름 입력">
    <button onclick="searchFriends()">검색</button>
	
    <h2>검색 결과</h2>
    <div id="searchResults"></div>
	<b></b>
	<b></b>
	<b></b>
	<button onclick="location.href='/friendsList'">친구 목록 보기</button>
	<b></b>
	<b></b>
	<b></b>
	<button onclick="location.href='/blacklist'">블랙리스트 목록 보기</button>
	<button onclick="location.href='/friendRequest'">친구 요청 목록 보기</button>
    
    <script>
    
    //전체 유저 검색
    function searchFriends() {
        var name = document.getElementById("searchInput").value;
        fetch('/api/friends/search?name=' + encodeURIComponent(name))
            .then(function(response) {
            	console.log(response);
                return response.json();
            })
            .then(function(users) {
            	console.log(users);
                var results = document.getElementById("searchResults");
                results.innerHTML = ""; // 이전 검색 결과 초기화
                
                var currentUserId = '${username}'; // 로그인한 사용자의 ID
                /* 이걸 security로 했으면 그에 맞게 고쳐야함. 로그인한 사용자의 ID */;

                users.forEach(function(user) {
                    var userDiv = document.createElement('div');
                    userDiv.id = 'user-' + user.id; // 사용자마다 고유한 ID 할당
                    userDiv.innerHTML = user.name + " - " + user.id;
					
                    //친구 요청 버튼
                    var friendRequestBtn = document.createElement('button');
                    friendRequestBtn.innerText = '친구 요청 보내기';
                    friendRequestBtn.onclick = function() {
                        sendFriendRequest(currentUserId, user.id);
                    };
					
                    //블랙리스트 추가 버튼
                    var blacklistBtn = document.createElement('button');
                    blacklistBtn.innerText = '블랙리스트 추가';
                    blacklistBtn.onclick = function() {
                        addToBlacklist(currentUserId, user.id);
                    };

                    userDiv.appendChild(friendRequestBtn);
                    userDiv.appendChild(blacklistBtn);
                    results.appendChild(userDiv);
                });
            })
            .catch(function(error) {
                console.error('Error:', error);
            });
    }
    
	//친구 요청
    function sendFriendRequest(requesterId, requestedId) {
        fetch('/api/friends/request', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                requesterId: requesterId, 
                requestedId: requestedId,
                status: 'PENDING' // 상태 pending
            })
        })
        .then(response => response.text())
        .catch(error => console.error('Error:', error));
    }
	
	//수정 버전
	//블랙리스트 추가
    function addToBlacklist(currentUserId, userId) {
        fetch('/api/friends/blacklistUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ 
                ownerId: currentUserId, 
                userId: userId 
            })
        })
         .then(response => {
        	if (response.ok) {
            	removeUserFromSearchResults(userId);
            	alert('블랙리스트에 추가되었습니다.');
        	} else {
            	alert('블랙리스트 추가에 실패했습니다.');
        	}
    	})
        .then(text => alert(text))
        .catch(error => console.error('Error:', error));
        
        function removeUserFromSearchResults(userId) {
            var userDiv = document.getElementById('user-' + userId);
            if (userDiv) {
                userDiv.remove();
            }
        }
    }

    </script>

</body>
</html>

