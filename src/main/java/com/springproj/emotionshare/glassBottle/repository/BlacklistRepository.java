package com.springproj.emotionshare.glassBottle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproj.emotionshare.glassBottle.model.Blacklist;


public interface BlacklistRepository extends JpaRepository<Blacklist, Long>{

	List<Blacklist> findByBlockedUserNameContaining(String name);
	Optional<Blacklist> findByBlockedUserId(Long userId);
	
	// ownerId를 사용하여 블랙리스트 항목을 조회하는 메서드
    List<Blacklist> findByOwnerId(Long ownerId);

}
