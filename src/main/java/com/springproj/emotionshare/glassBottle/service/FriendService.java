package com.springproj.emotionshare.glassBottle.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproj.emotionshare.domain.UserEntity;
import com.springproj.emotionshare.glassBottle.DTO.FriendRequestDto;
import com.springproj.emotionshare.glassBottle.DTO.UserDto;
import com.springproj.emotionshare.glassBottle.model.FriendList;
import com.springproj.emotionshare.glassBottle.model.FriendRequest;
import com.springproj.emotionshare.glassBottle.model.FriendRequestStatus;
import com.springproj.emotionshare.glassBottle.repository.FriendListRepository;
import com.springproj.emotionshare.glassBottle.repository.FriendRequestRepository;
import com.springproj.emotionshare.repository.UserRepository;

@Service
public class FriendService {
	 @Autowired
	 private FriendRequestRepository friendRequestRepository;
	 @Autowired
	 private UserRepository userRepository;
	 @Autowired
	 private FriendListRepository friendListRepository;
	
	 //친구 검색
	 public List<UserDto> searchFriendsByName(String name) {
		    return userRepository.findByNameContaining(name).stream()
		        .map(user -> UserDto.builder()
		            .id(user.getId())
		            .username(user.getUsername())
		            .nick(user.getNick()) // 예시 필드
		            .name(user.getName()) // 예시 필드
		            .tel(user.getTel()) // 예시 필드
		            .birth(user.getBirth()) // 예시 필드
		            .gender(user.getGender()) // 예시 필드
		            .useremail(user.getUseremail()) // 예시 필드
		            .role(user.getRole()) // 예시 필드
		            .build())
		        .collect(Collectors.toList());
		}
	 
	 // 친구 요청 보내기
	 public void sendFriendRequest(FriendRequestDto requestDto) {
		 // DTO를 Entity로 변환
		 FriendRequest friendRequest = convertToEntity(requestDto);
	     friendRequestRepository.save(friendRequest);
	     }

	    // 친구 요청 수락
	    public void acceptFriendRequest(FriendRequestDto requestDto) {
	        // 요청 찾기 및 상태 업데이트
	        FriendRequest friendRequest = friendRequestRepository.findById(requestDto.getId())//특정 id가진 데이터베이스 찾기
	            .orElseThrow(() -> new RuntimeException("친구 요청을 할 수 없음"));
	        friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
	        friendRequestRepository.save(friendRequest);
	        
	     // 친구 관계 생성 및 저장
	        FriendList friendList = new FriendList();
	        UserEntity user1 = userRepository.findByName(String.valueOf(requestDto.getRequesterId())).orElse(null);
	        UserEntity user2 = userRepository.findByName(String.valueOf(requestDto.getRequestedId())).orElse(null);
	        friendList.setUser1(user1);
	        friendList.setUser2(user2);
	        friendListRepository.save(friendList);
	    }
	    

	 //친구 목록 리스트 
	 // 현재 사용자의 모든 친구 목록
	    public List<UserDto> getAllFriends(Long userId) {
	        return friendListRepository.findByUser1IdOrUser2Id(userId, userId).stream()
	            .flatMap(friendList -> Stream.of(friendList.getUser1(), friendList.getUser2()))
	            .filter(user -> !user.getId().equals(userId))
	            .map(user -> UserDto.builder()
	                .id(user.getId())
	                .username(user.getUsername())
	                .nick(user.getNick()) // 예시 필드
	                .name(user.getName()) // 예시 필드
	                .tel(user.getTel()) // 예시 필드
	                .birth(user.getBirth()) // 예시 필드
	                .gender(user.getGender()) // 예시 필드
	                .useremail(user.getUseremail()) // 예시 필드
	                .build())
	            .collect(Collectors.toList());
	    }


	 // 친구 목록에서 특정 이름으로 친구를 검색합니다.
	 public List<UserDto> searchFriendsInList(Long userId, String name) {
	   return getAllFriends(userId).stream()
	           .filter(userDto -> userDto.getUsername().contains(name))
	           .collect(Collectors.toList());
	    }
	
	
	public void deleteFriend(Long userId, Long friendId) {
	// userId와 friendId가 user1Id와 user2Id인 관계를 삭제하거나,
	// userId와 friendId가 user2Id와 user1Id인 관계를 삭제.
	    friendListRepository.deleteByUser1IdAndUser2Id(userId, friendId);
	    friendListRepository.deleteByUser1IdAndUser2Id(friendId, userId);
	}
    
	 // DTO를 Entity로 변환하는 메소드
	 private FriendRequest convertToEntity(FriendRequestDto requestDto) {
		 FriendRequest friendRequest = new FriendRequest();
	     friendRequest.setId(requestDto.getId());
	     friendRequest.setRequesterId(requestDto.getRequesterId());
	     friendRequest.setRequestedId(requestDto.getRequestedId());
	        
	     // DTO의 status를 그대로 Entity에 설정
	     friendRequest.setStatus(requestDto.getStatus() != null ? requestDto.getStatus() : FriendRequestStatus.PENDING);

	     return friendRequest;
	     }
	

}

