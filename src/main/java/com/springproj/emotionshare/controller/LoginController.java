package com.springproj.emotionshare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.emotionshare.Dto.SignupDTO;
import com.springproj.emotionshare.service.SignupService;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginP() {
		return "login";

	}


	@Autowired
	private SignupService signupService;

	@GetMapping("/signup")
	public String singupP() {
		return "signup";
	}

	@PostMapping("/signupProc")
	public String signupProcess(SignupDTO signupDTO) {

		signupService.signupProcess(signupDTO);

		return "redirect:/login";
	}


}
