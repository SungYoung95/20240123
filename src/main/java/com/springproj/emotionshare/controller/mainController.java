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

import com.springproj.emotionshare.securityConfig.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class mainController {
	
	@GetMapping("/mainpage")
	public String mainP(Model model,HttpServletRequest request) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		String Id = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);
		request.getSession().setAttribute("username", username);
		model.addAttribute("role", role);

		return "mainpage";

	}

	@GetMapping("/mypage")
	public String myPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
	    String nick = userDetails.getNick();
	    String name = userDetails.getName();
	    String tel = userDetails.getTel();
	    String birth = userDetails.getBirth();
	    String gender = userDetails.getGender();
	    String useremail = userDetails.getUseremail();
	   
	  
	    model.addAttribute("nick", nick);
	    model.addAttribute("name", name);
	    model.addAttribute("tel", tel);
	    model.addAttribute("birth", birth);
	    model.addAttribute("gender", gender);
	    model.addAttribute("useremail", useremail);
	  
	    return "mypage";
	}
	
	

}