package com.springproj.emotionshare.glassBottle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproj.emotionshare.domain.UserEntity;
import com.springproj.emotionshare.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; //
    }

    public UserEntity createUser(String username, String password, String nick, String name, 
            String tel, String birth, String gender, String useremail, 
            String edomain, String role) {
    		UserEntity newUser = new UserEntity();

    			newUser.setUsername(username);
    			newUser.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
    			newUser.setNick(nick);
    			newUser.setName(name);
    			newUser.setTel(tel);
    			newUser.setBirth(birth);
    			newUser.setGender(gender);
    			newUser.setUseremail(useremail);
    			newUser.setRole(role);

    			return userRepository.save(newUser); // userRepository를 통해 데이터베이스에 저장
    }
}

