<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
                
                console.log(requests)
       
                requests.forEach(function(request) {
                	
                	console.log("확인" + request);
                	console.log("567")
                    var requestDiv = document.createElement('div');
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
                    return response.text();
                }
                throw new Error('Network response was not ok.');
            })
            .then(text => {
                alert("성공"); // 성공 메시지를 표시합니다.
                // 성공적으로 요청을 수락했으므로 UI에서 요청을 제거하거나 갱신할 수 있습니다.
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

        
        function rejectFriendRequest(requestId) {
            // 거절 처리를 위한 API 호출
        }

        // 페이지 로드 시 친구 요청 목록을 불러오도록 설정
        window.onload = loadFriendRequests;
    </script>

</body>
</html>