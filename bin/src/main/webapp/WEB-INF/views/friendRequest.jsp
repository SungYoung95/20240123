<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="friendRequsetstyle.css">
<title>친구 요청 목록</title>
    <style>
        .request-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
	<h1>${username}</h1>
	<h1>친구 요청 목록</h1>
    <div id="friendRequestList"></div>

    <script>
        function loadFriendRequests() {
            var currentUserId = '${username}';
            fetch('/api/friends/requests/' + currentUserId)
            .then(response => response.json())
            .then(requests => {
                var list = document.getElementById("friendRequestList");
                console.log("friendRequestList = 임형수 " + list)
                list.innerHTML = "";
                console.log("friendRequstList.jsp파일 디버깅 완료");
                console.log(requests)
       			console.log("request를 받는가?"+ requests)
                requests.forEach(function(request) {
                	console.log("확인" + request);
                	console.log("567")
                    var requestDiv = document.createElement('div');
                	requestDiv.id = 'request-item-' + request.id;
                	requestDiv.className = 'request-item';
                	
                    requestDiv.innerHTML = request.requesterId + ' 님이 친구 요청을 보냈습니다.';
					console.log("123" + request)
                    
                    var acceptBtn = document.createElement('button');
                    acceptBtn.innerText = '수락';
                    acceptBtn.onclick = function() { 
                        acceptFriendRequest(request.id, request.requesterId, currentUserId);
                    };


                    var rejectBtn = document.createElement('button');
                    rejectBtn.innerText = '거절';
                    rejectBtn.onclick = function() { 
                        rejectFriendRequest(request.id);
                    };

                    requestDiv.appendChild(acceptBtn);
                    requestDiv.appendChild(rejectBtn);
                    list.appendChild(requestDiv);
                });
            })
            .catch(error => console.error('Error:', error));
        }
		
        //친구 요청 수락 api
        function acceptFriendRequest(requestId, requesterId, currentUserId) {
            fetch('/api/friends/accept', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // 인증 토큰이 필요한 경우 여기에 추가합니다.
                },
                body: JSON.stringify({ 
                    id: requestId,
                    requesterId: requesterId,
                    requestedId: currentUserId, // 현재 로그인한 사용자의 ID를 추가
                    status: 'ACCEPTED' 
                })
            })
            .then(response => {
                if (response.ok) {
                    var requestDiv = document.getElementById('request-item-' + requestId);
                    if (requestDiv) {
                        requestDiv.remove(); // 요청 항목 제거
                    }
                    alert("성공");
                } else {
                    throw new Error('Network response was not ok.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
		
        //수정 버전
        //친구 요청 거절 api
    function rejectFriendRequest(requestId) {
        var currentUserId = '${username}';
        console.log("rejectFriendRequest 첫번째 디버깅" + requestId)
        fetch('/api/friends/reject/' + currentUserId + '/' + requestId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                // 인증 토큰이 필요한 경우 여기에 추가합니다.
            }
        })
        .then(response => {
            if (response.ok) {
                // 요청 항목을 DOM에서 제거
                var requestDiv = document.getElementById('request-item-' + requestId);
                if (requestDiv) {
                    requestDiv.remove();
                }
                console.log("rejectFriendRequest 두번째 디버깅 요청이 거절 완료")

                alert("친구 요청이 거절되었습니다.");
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

        // 페이지 로드 시 친구 요청 목록을 불러오도록 설정
        window.onload = loadFriendRequests;
    </script>

</body>
</html>