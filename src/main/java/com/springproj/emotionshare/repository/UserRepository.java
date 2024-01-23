package com.springproj.emotionshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity ,Integer>{

	
	boolean existsByUsername(String username); //존재하면 true ,존재하지않으면 false 커스텀jpa


	UserEntity findByUsername(String username);

}
