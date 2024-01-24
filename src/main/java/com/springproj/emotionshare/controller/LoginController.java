package com.springproj.emotionshare.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproj.emotionshare.Dto.SignupDTO;
import com.springproj.emotionshare.repository.UserEntity;
import com.springproj.emotionshare.repository.UserRepository;
import com.springproj.emotionshare.service.PasswordService;
import com.springproj.emotionshare.service.SignupService;

@Controller
public class LoginController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/login")
	public String loginP() {
		return "login";

	}

	@GetMapping("/signup")
	public String singupP() {
		return "signup";
	}

	@GetMapping("/checkDuplicateUsername")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkDuplicateUsername(@RequestParam String username) {
		boolean isDuplicate = userRepository.existsByUsername(username);
		Map<String, Object> response = new HashMap<>();

		if (isDuplicate) {
			response.put("error", "이미 사용 중인 아이디입니다. 아이디 변경 후 중복 확인을 진행해 주세요");
		} else {
			response.put("available", true);
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/signupProc")
	public String signupProcess(SignupDTO signupDTO, Model model) {
		if (signupDTO.getPassword().length() < 6) {
			// 비밀번호가 6자 미만인 경우
			model.addAttribute("error", "비밀번호를 6자 이상 입력해주세요 회원가입.");
			return "signup";
		}
		if (!signupDTO.getPassword().equals(signupDTO.getConfirmPassword())) {
			// 비밀번호가 일치하지 않는 경우
			model.addAttribute("error", "비밀번호를 확인해주세요. 회원가입 ");
			return "signup";
		}
		boolean isUser = userRepository.existsByUsername(signupDTO.getUsername());
		if (isUser) {
			model.addAttribute("error", "이미 사용 중인 아이디입니다. 아이디 변경 후 중복 확인을 진행해 주세요");
			model.addAttribute("signupDTO", signupDTO);
			return "signup";
		}

		signupService.signupProcess(signupDTO);

		return "redirect:/login";
	}

	@GetMapping("/update")
	public String updateProfileForm(Model model) {
		// 현재 사용자 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity currentUser = userRepository.findByUsername(username);

		model.addAttribute("user", currentUser);
		return "update";
	}

	@PostMapping("/updateProfileProc")
	public String updateProfile(UserEntity updatedUser) {
		// 현재 사용자 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		// 사용자 정보 업데이트
		UserEntity existingUser = userRepository.findByUsername(username);

		if (existingUser != null) {
			// 사용자 정보 업데이트
			existingUser.updateUserInfo(updatedUser);

			// 업데이트된 사용자 엔터티 저장
			userRepository.save(existingUser);
		}

		return "redirect:/login";
	}

	// 회원 탈퇴
	@GetMapping("/withdraw")
	public String Withdraw(Model model) {

		return "withdraw";
	}

	// 탈퇴처리
	@PostMapping("/withdrawProc")
	public String withdrawProc(String password, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity userEntity = userRepository.findByUsername(username);
		if (PasswordService.equals(password, userEntity.getPassword())) {

			userRepository.delete(userEntity);

			SecurityContextHolder.getContext().setAuthentication(null);

			return "withdraw-success";
		} else {
			// Passwords do not match
			model.addAttribute("error", "비밀번호X 다시입력.");
			return "withdraw";
		}
	}
}
