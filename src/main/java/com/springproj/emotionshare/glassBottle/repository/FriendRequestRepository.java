package com.springproj.emotionshare.glassBottle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproj.emotionshare.glassBottle.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    
}

