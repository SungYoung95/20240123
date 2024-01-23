package com.springproj.emotionshare.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.springproj.emotionshare.Dto.CustomUserDetails;

@Controller
public class mainController {

	@GetMapping("/mainpage")
	public String mainP(Model model) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		String Id = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userID", username);
		model.addAttribute("role", role);

		return "mainpage";

	}

	@GetMapping("/mypage")
	public String myPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
	    String nick = userDetails.getNick();
	    String uname = userDetails.getUname();
	    String tel = userDetails.getTel();
	    String birth = userDetails.getBirth();
	    String gender = userDetails.getGender();
	    String useremail = userDetails.getUseremail();
	    String edomain = userDetails.getEdomain();
	  
	    model.addAttribute("nick", nick);
	    model.addAttribute("uname", uname);
	    model.addAttribute("tel", tel);
	    model.addAttribute("birth", birth);
	    model.addAttribute("gender", gender);
	    model.addAttribute("useremail", useremail);
	    model.addAttribute("edomain", edomain);
	    return "mypage";
	}
	
	

}