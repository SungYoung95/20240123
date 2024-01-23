package com.springproj.emotionshare.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
	
	private String username;
	private String password;
	private String nick;
	private String uname;
	private String tel;
	private String birth;
	private String gender;
	private String useremail;
	private String edomain;
	
	@Builder
	public SignupDTO(String username,String password,String nick,String uname,String tel,
			String birth,String gender,String useremail,String edomain){
		this.username=username;
		this.password=password;
		this.nick=nick;
		this.uname=uname;
		this.tel=tel;
		this.birth=birth;
		this.gender=gender;
		this.useremail=useremail;
		this.edomain=edomain;
	}
	
}
