package com.springproj.emotionshare.repository;

import org.springframework.security.crypto.bcrypt.BCrypt;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String username;
	private String password;
	private String nick;
	private String uname;
	private String tel;
	private String birth;
	private String gender;
	private String useremail;
	private String edomain;

	private String role;

	public void updateUserInfo(UserEntity updatedUser) {
		this.nick = updatedUser.getNick();
		this.uname = updatedUser.getUname();
		this.tel = updatedUser.getTel();
		this.birth = updatedUser.getBirth();
		this.gender = updatedUser.getGender();
		this.useremail = updatedUser.getUseremail();
		this.edomain = updatedUser.getEdomain();

	
		
		//회원변경 필요한 비밀번호암호화
		if (StringUtils.isNotBlank(updatedUser.getPassword())) {
		
			this.password = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());
		}

	}
}
