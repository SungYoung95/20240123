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
	<h1>친구 요청 목록</h1>
    <div id="friendRequestList"></div>

    <script>
        function loadFriendRequests() {
            var currentUserId = '${loggedInUserId}';
            fetch('/api/friends/requests/' + currentUserId)
            .then(response => response.json())
            .then(requests => {
                var list = document.getElementById("friendRequestList");
                list.innerHTML = "";
                requests.forEach(function(request) {
                    var requestDiv = document.createElement('div');
                    requestDiv.className = 'request-item';
                    requestDiv.innerHTML = `<span>${request.requesterId} 님이 친구 요청을 보냈습니다.</span>`;

                    var acceptBtn = document.createElement('button');
                    acceptBtn.innerText = '수락';
                    acceptBtn.onclick = function() { 
                        acceptFriendRequest(request.id);
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

        function acceptFriendRequest(requestId, requesterId, requestedId) {
            fetch('/api/friends/accept', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // 필요한 경우 인증 토큰을 추가해야 할 수도 있습니다.
                },
                body: JSON.stringify({ 
                    id: requestId,
                    requesterId: requesterId,
                    requestedId: requestedId,
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
                alert("성공"); //성공
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