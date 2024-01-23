package com.springproj.emotionshare.Dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springproj.emotionshare.repository.UserEntity;

public class CustomUserDetails implements UserDetails {

	private UserEntity userEntity;
	
	public CustomUserDetails(UserEntity userEntity) {

		this.userEntity = userEntity;
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {

				return userEntity.getRole();
			}
		});

		return collection;
	}

	public String getNick() {
		return userEntity.getNick();
	}
	
	public String getUname() {
		return userEntity.getUname();
	}
	
	public String getTel() {
		return userEntity.getTel();
	}
	public String getBirth() {
		return userEntity.getBirth();
	}
	public String getGender() {
		return userEntity.getGender();
	}
	public String getUseremail() {
		return userEntity.getUseremail();
	}
	public String getEdomain() {
		return userEntity.getEdomain();
	}
	
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}




}
