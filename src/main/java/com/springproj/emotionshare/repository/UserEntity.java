package com.springproj.emotionshare.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
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

	}
	
	

