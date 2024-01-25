package com.springproj.emotionshare.glassBottle.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.emotionshare.domain.UserEntity;
import com.springproj.emotionshare.glassBottle.DTO.BlacklistDto;
import com.springproj.emotionshare.glassBottle.DTO.FriendRequestDto;
import com.springproj.emotionshare.glassBottle.DTO.UserDto;
import com.springproj.emotionshare.glassBottle.repository.FriendListRepository;
import com.springproj.emotionshare.glassBottle.service.BlacklistService;
import com.springproj.emotionshare.glassBottle.service.FriendService;
import com.springproj.emotionshare.securityConfig.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/friends")
public class FriendController {
	
	 	@Autowired
	    private FriendService friendService;
	 	
	 	@Autowired
	 	private BlacklistService blacklistService;
	 	@Autowired
	 	private FriendListRepository friendListRepository;
	 	
	 	
	 	//친구 검색 
	 	 @GetMapping("/search")
	     public ResponseEntity<List<UserDto>> searchFriends(@RequestParam("name") String name) {
	 		 log.info("name = " + name);
	         // name을 기반으로 친구 검색, UserDto 구조에 맞게 반환
	         List<UserDto> users = friendService.searchFriendsByName(name);
	         log.info("users = " + users);
	         return ResponseEntity.ok(users);
	     }
			/*
			 * @GetMapping("/searchById") public ResponseEntity<List<UserDto>>
			 * searchFriends(@RequestParam String Id) { List<UserDto> users =
			 * friendService.searchFriendsById(Id); return ResponseEntity.ok(users); }
			 * 
			 * @GetMapping("/searchBy") public ResponseEntity<List<UserDto>>
			 * searchFriends(@RequestParam String PhoneNum) { List<UserDto> users =
			 * friendService.searchFriendsByPhoneNum(PhoneNum); return
			 * ResponseEntity.ok(users); }
			 */
	   
	 	 
	 	//친구 리스트 불러오기
	 	@GetMapping("/list/{userId}")
	    public ResponseEntity<List<UserDto>> getFriendList(@PathVariable Long userId) {
	        List<UserDto> friends = friendService.getAllFriends(userId);
	        if (friends.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(friends);
	    }
	 	
	 	@GetMapping("/requests/{userId}")
	 	public List<FriendRequestDto> getFriendRequests(@PathVariable Long userId) {
	 	    System.out.println("성공" + userId);

	 	    List<FriendRequestDto> requests = friendService.getFriendRequests(userId);

	 	    if (requests.isEmpty()) {
	 	        // 비어있는 경우 빈 리스트를 반환할 수 있습니다.
	 	        return Collections.emptyList();
	 	    }

	 	    System.out.println("성공 5" + requests);
	 	    return requests;
	 	}


	 	// 친구 요청 보내기
	    @PostMapping("/request")
	    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDto requestDto) {
	        friendService.sendFriendRequest(requestDto);
	        return ResponseEntity.ok("친구 요청 전송");
	    }

	    @PostMapping("/accept")
	    public ResponseEntity<String> acceptFriendRequest(@RequestBody FriendRequestDto requestDto, Authentication authentication) {
	        // 현재 로그인한 사용자 정보를 얻지만, FriendService에는 전달하지 않음
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        UserEntity currentUser = userDetails.getUserEntity();

	        // FriendService 호출 시 requestDto만 전달
	        friendService.acceptFriendRequest(requestDto);
	        System.out.println("성공 6" + requestDto);
	        System.out.println("성공 7" + currentUser);
	        return ResponseEntity.ok("Friend request accepted.");
	    }




	    //친구 삭제
	    // 인증 토큰 확인 필요!!!!!!!!
	    @DeleteMapping("/delete/{friendId}")
	    public ResponseEntity<?> deleteFriend(@PathVariable Long friendId, Authentication authentication) {
	        Long currentUserId = ((UserEntity) authentication.getPrincipal()).getId(); // 현재 로그인한 사용자의 ID를 인증 정보로부터 가져오기.
	        friendService.deleteFriend(currentUserId, friendId); // 서비스에서 친구 삭제 처리
	        return ResponseEntity.ok().build(); // 성공 응답 반환
	    }
	    
	    // 블랙 리스트 추가
	    @PostMapping("/blacklistUser")
	    public ResponseEntity<String> blacklistRequest(@RequestBody BlacklistDto blacklistDto) {
	        blacklistService.addUserToBlacklist(blacklistDto);
	        return ResponseEntity.ok("사용자가 블랙리스트에 추가되었습니다.");
	    }
	    
	    //블랙리스트 조회
	    @GetMapping("/blacklist")
	    public ResponseEntity<List<UserDto>> getBlacklist(Authentication authentication) {
	        Long currentUserId = ((UserEntity) authentication.getPrincipal()).getId();
	        List<UserDto> blacklist = blacklistService.getBlacklist(currentUserId);
	        if (blacklist.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(blacklist);
	    }

		
	    //블랙리스트 삭제
	    @DeleteMapping("/removeFromBlacklist/{userId}")
	    public ResponseEntity<?> removeFromBlacklist(@PathVariable Long userId) {
	        blacklistService.removeUserFromBlacklist(userId);
	        return ResponseEntity.ok().build();
	    }

	    
}
