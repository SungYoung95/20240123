package com.springproj.emotionshare.glassBottle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproj.emotionshare.glassBottle.model.FriendList;

import jakarta.transaction.Transactional;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
	  List<FriendList> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);
	  
	  @Transactional
	  void deleteByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}
