package com.springproj.emotionshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springproj.emotionshare.Dto.CustomUserDetails;
import com.springproj.emotionshare.repository.UserEntity;
import com.springproj.emotionshare.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userData = userRepository.findByUsername(username);

		if (userData != null) {
			return new CustomUserDetails(userData);
		} else {
			throw new UsernameNotFoundException("사용자 이름으로 찾을 수 없습니다: " + username);
		}
	}
}