/*
 * package com.springproj.emotionshare.service;
 * 
 * import org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.springproj.emotionshare.repository.UserEntity; import
 * com.springproj.emotionshare.repository.UserRepository;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * @RequiredArgsConstructor
 * 
 * @Transactional
 * 
 * @Service public class UserService {
 * 
 * private final UserRepository userRepository;
 * 
 * public UserEntity saveUser(UserEntity userEntity) {
 * 
 * return; }
 * 
 * 
 * private void validateDuplicateMember(UserEntity userEntity) { UserEntity
 * findUser = UserRepository.findByUsername(userEntity.getUsername())); if
 * (findUser != null) { throw new IllegalStateException("이미 가입된 회원입니다."); } } }
 */