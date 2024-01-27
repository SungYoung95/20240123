<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="friendListstyle.css">
    <title>친구 목록</title>
    <style>
        #friendList {
            overflow-y: auto; /* 스크롤 가능하게 */
            max-height: 400px; /* 적당한 최대 높이 설정 */
        }
        .friend-item {
            display: flex;
            justify-content: space-between; /* 이름 왼쪽, 버튼 오른쪽 정렬 */
            margin-bottom: 10px; /* 각 아이템 사이의 마진 */
        }
    </style>
</head>
<body>
    <h1>친구 목록</h1>
    <h1>${username}</h1>
    <input type="text" id="searchInput" placeholder="친구 이름 검색">
    <button onclick="searchFriends()">검색</button>
	<!-- 기존의 코드에 이 부분을 추가 -->
	<div id="searchResultsContainer"></div>
	<div></div>
    <div id="friendList"></div>

    <script>
    
    //유저 본인의 친구 목록 불러오기
    function loadFriends() {
        var currentUserId = '${username}'; // 로그인한 사용자의 ID
        console.log(currentUserId);
     	// 로그인한 사용자의 ID 값이 제대로 설정되었는지 확인
        if (!currentUserId) {
            console.error('The logged-in user ID is not set.');
            return;
        }

        fetch('/api/friends/list/' + currentUserId) // currentUserId는 현재 로그인한 사용자의 ID
        .then(function(response) {
            console.log("loadFriends의 첫번째 디버깅" + response);
        	return response.json();
        })
        .then(function(friends) {
            var list = document.getElementById("friendList");
            list.innerHTML = ""; // 리스트 초기화

            friends.forEach(function(friend) {
                var friendDiv = document.createElement('div');
                friendDiv.className = 'friend-item';
                friendDiv.id = 'friend-' + friend.id; // 친구의 고유한 사용자 ID를 설정
                friendDiv.innerHTML =  '<span>' + friend.name + '</span>'; // 친구 이름

                var deleteBtn = document.createElement('button');
                deleteBtn.innerText = '친구 삭제';
                deleteBtn.onclick = function() {
                    deleteFriend(currentUserId, friend.id); // 현재 사용자 ID와 친구 ID를 함께 전달
                };

                friendDiv.appendChild(deleteBtn);
                list.appendChild(friendDiv);            
                })
        	})
        	.catch(function(error) {
            	console.error('Error:', error);
        	});
    }
    
    // 수정 버전
    //우저 본인의 친구 목록에서 유저의 친구 찾기 api
    function searchFriends() {
    var input = document.getElementById("searchInput");
    var name = input.value;
    var currentUserId = '${username}';

    // 검색 결과 컨테이너와 기존 친구 목록 컨테이너를 찾기
    var searchResultsContainer = document.getElementById("searchResultsContainer");
    var friendListContainer = document.getElementById("friendList");
	
 	// 검색 결과 컨테이너 초기화 및 기존 친구 목록 숨기기
    searchResultsContainer.innerHTML = '';
    friendListContainer.style.display = 'none'; // 기존 친구 목록을 숨기기

    fetch('/api/friends/searchInList/' + currentUserId + '?name=' + encodeURIComponent(name))
        .then(function(response) {
            if (!response.ok) {
                throw new Error('네트워크 에러 : ' + response.statusText);
            }
            return response.json();
        })
        .then(function(searchResults) {
            if (searchResults.length === 0) {
            	searchResultsContainer.innerHTML = '<p>검색 결과 없음</p>';
                friendListContainer.style.display = 'block'; // 검색 결과가 없을 때 기존 친구 목록을 다시 보여주기
            } else {
            	searchResults.forEach(function(friend) {
                    var friendDiv = document.createElement('div');
                    friendDiv.innerHTML = '<p>이름:   ' + friend.name + '</p>';
                    searchResultsContainer.appendChild(friendDiv);
                });
            }
        })
        .catch(function(error) {
        	 searchResultsContainer.innerHTML = '<p>에러</p>';
             friendListContainer.style.display = 'block'; // 에러 발생 시 기존 친구 목록을 다시 보여주기
             console.error('Error:', error);
        });
	}

    	
		// 수정 버전 
		//친구 삭제 api
       // 유저 본인의 친구 목록에서 친구 삭제 매서드
    	function deleteFriend(currentUserId, friendId) {
        	fetch('/api/friends/delete/' + currentUserId + '/' + friendId, {
            	method: 'DELETE',
            	headers: {
                	'Content-Type': 'application/json'
            		}
        		})
        		.then(response => {
        	        if (response.ok) {
        	            // 서버에서 요청이 성공적으로 처리됐을 때
        	            var friendDiv = document.getElementById('friend-' + friendId);
        	            if (friendDiv) {
        	                friendDiv.remove(); // 친구 항목 삭제
        	            }
        	            alert("친구가 목록에서 삭제되었습니다.");
        	        } else {
        	            throw new Error('Something went wrong on api server!');
        	        }
        	    })
        	    .catch(error => {
        	        console.error(error);
        	    });
        	}

        // 페이지 로드 시 친구 목록을 불러오도록 설정
        window.onload = loadFriends;
    
    </script>

</body>
</html>
